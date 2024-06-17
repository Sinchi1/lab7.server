package project;

import project.Managers.CollectionManager;
import project.Managers.ConsolePrinter;
import project.Server.*;


public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        Thread thread = new Thread(() -> {
            System.out.println("\nВыключение сервера");
        });
        Runtime.getRuntime().addShutdownHook(thread);
        CollectionManager.getInstance();

        server.openServerConnection();
        server.runServer();
    }




}