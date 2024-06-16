package project.Common;

import project.Managers.CollectionManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.Socket;

public class User implements Serializable {

    private static final long serialVersionUID = 120312021301203120L;
    private final String host;
    private final int port;
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;

    public User(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() throws IOException{
        connect();
    }

    private void connect() throws  IOException{
        socket = new Socket();
        socket.connect(new InetSocketAddress(host, port));
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
    }


    public Socket getSocket() {
        return socket;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }
}
