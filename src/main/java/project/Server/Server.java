package project.Server;

import com.google.common.hash.Hashing;
import project.Common.Account;
import project.Common.Request;
import project.Common.Response;
import project.Main;
import project.Managers.ConsolePrinter;
import project.Managers.DataBaseManager;
import project.Managers.DeSerializer;
import project.Managers.Serializer;
import project.ProgrammEnums.OperationCode;
import project.Util.RequestHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int port = 1000;
    private static final String host = "localhost";
    private int soTimeout;
    private ServerSocketChannel serverSocket;
    private static final RequestHandler requestHandler = new RequestHandler();
    private Account account = Account.getInstance();
    private static final ByteBuffer buffer = ByteBuffer.allocate(4096);
    DataBaseManager dataBaseManager = DataBaseManager.getInstance();
    private Selector selector;

    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public void runServer(){
        ConsolePrinter.messageToConsole("Сервер готов для операций...");
        try{
            while(true){
                selector.selectNow();
                Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();
                while (selectedKeys.hasNext()) {
                    SelectionKey key = takeKey(selectedKeys);
                    operateKey(key);
                }
            }
        } catch (IOException e) {
            ConsolePrinter.errorMessage("Неудачный набор ключей");
            System.exit(1);
        }
    }

    private Selector launchSelector() {
        try {
            Selector selector = Selector.open();
            serverSocket.register(selector, SelectionKey.OP_ACCEPT);
            return selector;
        } catch (IOException e) {
            ConsolePrinter.errorMessage("Неудачное создание селектора");
            System.exit(1);
            return null;
        }
    }

    private SelectionKey takeKey(Iterator<SelectionKey> selectionKeyIterator){
        SelectionKey key = selectionKeyIterator.next();
        selectionKeyIterator.remove();
        return key;
    }

    private void operateKey(SelectionKey key) {
        try {
            if (key.isValid()) {
                if (key.isAcceptable()) {
                    accept(key);
                } else if (key.isReadable()) {
                    key.interestOps(0);
                    executorService.execute(() -> {
                        read(key);
                    });
                } else if (key.isWritable()) {
                    key.interestOps(0);
                    write(key);
                }
            }
        } catch (IOException e) {
            ConsolePrinter.errorMessage("Запись невозможна");
            System.exit(1);
        }
    }

    public void openServerConnection(){
        try {
            serverSocket = ServerSocketChannel.open();
            serverSocket.configureBlocking(false);
            serverSocket.bind(new InetSocketAddress(host, port));
            selector = launchSelector();
            ConsolePrinter.messageToConsole("Сервер открыт для подключен...");
        } catch (IOException e) {
            ConsolePrinter.errorMessage("Не удалось открыть сервер для подключений");
            System.exit(1);
        }
    }

    private void read(SelectionKey key) {
        try {
            ConsolePrinter.messageToConsole("Производится Операция Чтения...");
            SocketChannel socketChannel = (SocketChannel) key.channel();
            buffer.clear();
            int byteRead;
            try {
                byteRead = socketChannel.read(buffer);
            } catch (IOException e) {
                key.cancel();
                socketChannel.close();
                return;
            }
            if (byteRead == -1){
                key.cancel();
                return;
            }
            buffer.flip();
            Request request = DeSerializer.deSerializeRequest(buffer);
            account.setPassword(Hashing.sha512().hashString(request.getPassword(), StandardCharsets.UTF_16).toString());
            executorService.execute(() -> executeRequest(request,key));

        } catch (IOException e) {
            ConsolePrinter.errorMessage("Не удалось изменить состояние канала");
            System.exit(1);
        }

    }

    public void executeRequest(Request request, SelectionKey key){
        Response response;
        if (request.getCommandName().equals("registration")){
            if (dataBaseManager.registration(request.getUserName(), request.getPassword())){
                response = new Response("Успешная регистрация", OperationCode.ok);
            }
            else {
                response = new Response("Аккаунт уже существует", OperationCode.error);
            }
        } else if (request.getCommandName().equals("log")) {
            if (dataBaseManager.log(request.getUserName(), request.getPassword())){
                response = new Response("Вы успешно зашли в аккаунт", OperationCode.ok);
            }
            else {
                response = new Response("В пароле или имени пользователя допущена ошибка ", OperationCode.error);
            }
        }else {
            int userId = dataBaseManager.getPersonId(request.getUserName(), request.getPassword());
            if (userId != -1) {
                response = requestHandler.handle(request, account);
            } else {
                response = new Response("Такого юзера не существует", OperationCode.error);
            }
        }

        key.attach(response);
        key.interestOps(SelectionKey.OP_WRITE);
        selector.wakeup();
    }

    private void write(SelectionKey key) throws IOException {
        ConsolePrinter.messageToConsole("Производится операция записи...");
        SocketChannel socketChannel = (SocketChannel) key.channel();
        Response response = (Response) key.attachment();
        ByteBuffer writeBuffer = Serializer.serialize(response);
        writeBuffer.flip();
        while (writeBuffer.hasRemaining()){
            socketChannel.write(writeBuffer);
        }
        socketChannel.register(selector, SelectionKey.OP_READ);
        ConsolePrinter.messageToConsole("Операция завершена успешно");
    }

    private void accept(SelectionKey key){
        try {
            SocketChannel socketChannel = serverSocket.accept();
            socketChannel.configureBlocking(false);
            ConsolePrinter.messageToConsole("Подключен пользователь " + socketChannel.getRemoteAddress().toString());
            socketChannel.register(selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            ConsolePrinter.errorMessage("Не удалось изменить состояние канала");
            System.exit(1);
        }
    }

}
