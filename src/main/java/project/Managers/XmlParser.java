package project.Managers;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import project.Collections.Movie;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 * The class that parsing from java to xml
 */
public class XmlParser {

    public LinkedList<Movie> movies;

    private static String path = "src/main/java/project/data/test.xml";

    CollectionManager collectionManager;

    public String serializeCollection(LinkedList<Movie> movies) throws JsonProcessingException {
        ObjectMapper mapper = new XmlMapper();
        collectionManager = CollectionManager.getInstance();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String jsonStr;
        mapper.findAndRegisterModules();
        jsonStr = mapper.writeValueAsString(movies);
        try {
            FileWriter writer = new FileWriter(
                    path);
            writer.write(jsonStr);
            writer.close();

        } catch (IOException e) {
            ConsolePrinter.errorMessage("Ошибка сериализации");
            System.exit(1);
        } catch (NullPointerException ignored){
            return ("Файл не может быть сохранен");
        }
        return "Файл успешно сохранен!";
    }

}
