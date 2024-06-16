package project;

//import java.util.logging.Logger;
//import org.slf4j.LoggerFactory;
//import org.slf4j.helpers.NOPLogger;
import project.Managers.CollectionManager;
import project.Managers.ConsolePrinter;
import project.Managers.XmlParser;
import project.Server.*;

import java.io.IOException;

public class Main {
//    public static Logger logger = (Logger) LoggerFactory.getLogger("ServerLogger");
    public static void main(String[] args) {
        Server server = new Server();
        Thread thread = new Thread(() -> {
            CollectionManager collectionManager = CollectionManager.getInstance();
            ConsolePrinter.messageToConsole(collectionManager.saveCollection());
            System.out.println("\nВыключение сервера");
        });
        Runtime.getRuntime().addShutdownHook(thread);
        CollectionManager.getInstance();
        XmlParser xmlParser = new XmlParser();
        xmlParser.deserializeCollection();

        server.openServerConnection();
        server.runServer();
    }




}