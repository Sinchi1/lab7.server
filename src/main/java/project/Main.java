package project;

import project.Managers.CollectionManager;
import project.Managers.ConsolePrinter;
import project.Server.*;


public class Main {
    public static void main(String[] args) {
        CollectionManager collectionManager = CollectionManager.getInstance();
        Server server = new Server();
        Thread thread = new Thread(() -> {
            collectionManager.saveCollection();
            System.out.println("\nВыключение сервера");
        });
        Runtime.getRuntime().addShutdownHook(thread);
        server.openServerConnection();
        server.runServer();
    }
}
