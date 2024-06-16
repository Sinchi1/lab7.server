package project.Managers;

import project.Common.Request;
import project.Common.Response;

import java.io.*;
import java.nio.ByteBuffer;

public class DeSerializer {

    public static Request deSerializeRequest(ByteBuffer buffer) {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(buffer.array());
             ObjectInputStream ois = new ObjectInputStream(bais)) {
            return (Request) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            ConsolePrinter.errorMessage("Десереализация запроса невозможна");
            System.exit(1);
            return null;
        }
    }
}