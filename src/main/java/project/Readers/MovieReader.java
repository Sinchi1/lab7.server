package project.Readers;

import project.Collections.*;
import project.Managers.*;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
/**
 * Class for simple reading element
 */
public class MovieReader {


    IntegerChecker integerChecker = (x, y ,z) -> {
        if (z){
            return x < y;
        }
        else{
            return x > y;
        }
    };

    LongChecker longCheck = (x, y ,z) -> {
        if (z){
            return x < y;
        }
        else{
            return x > y;
        }
    };

    DoubleCheck doubleCheck = (x, y ,z) -> {
        if (z){
            return x < y;
        }
        else{
            return x > y;
        }
    };

    Reader reader = new Reader();
    CollectionManager collectionManager;

    ProgramRunner programRunner = new ProgramRunner();
    /**
     * The method to read element of Collection from file
     */
    public Movie readMovieFromFile(List<String> args) throws IOException {
        collectionManager = CollectionManager.getInstance();
        Iterator<String> iter = args.iterator();
        int id = collectionManager.getElementId() + 1;
        collectionManager.setElementId(id);
        String movieName = iter.next();;
        ZonedDateTime creationDate1 = ZonedDateTime.now();
        int intX = Integer.parseInt(iter.next());
        int intY = Integer.parseInt(iter.next());
        Coordinates cor = new Coordinates(intX, intY);
        int oscarCount = Integer.parseInt(iter.next());
        long goldenPalmCount = Long.parseLong(iter.next());
        long length = Long.parseLong(iter.next());
        MovieGenre genre = MovieGenre.valueOf(iter.next().toUpperCase());
        String PersonName = iter.next();
        double height = Double.parseDouble(iter.next());
        Color col = Color.valueOf(iter.next().toUpperCase());
        Color hairCol = Color.valueOf(iter.next().toUpperCase());
        Country country = Country.valueOf(iter.next().toUpperCase());
        float x = Float.parseFloat(iter.next());
        double y = Double.parseDouble(iter.next());
        int z =  Integer.parseInt(iter.next());
        String name = iter.next();
        Location location = new Location(x, y, z, name);
        Person operator = new Person(PersonName, height, col, hairCol, country, location);
        String user = iter.next();
        return new Movie(id, movieName, cor, creationDate1, oscarCount, goldenPalmCount, length, genre, operator, user);
    }

}
