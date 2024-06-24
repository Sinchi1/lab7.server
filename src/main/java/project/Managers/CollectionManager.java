package project.Managers;


import project.Collections.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import project.Commands.ExitCommand;
import project.Common.Account;

import javax.xml.crypto.Data;

/**
 * The class that works with collection
 */
public class CollectionManager {

    private final DataBaseManager dataBaseManager;
    String path;
    public ZonedDateTime creationDate = ZonedDateTime.now();

    public   LinkedList<Movie> moviesCollection;

    private static CollectionManager instance;
    private CollectionManager() {
        dataBaseManager = DataBaseManager.getInstance();
        try {
            moviesCollection = (LinkedList<Movie>) dataBaseManager.getCollection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static CollectionManager getInstance(){
        if(instance == null){
            instance = new CollectionManager();
        }
        return instance;
    }

    private int elementId;


    // Всё взаимодействие с коллекцией только внутри Манагера!!!
    private LinkedList<Movie> getMoviesCollection() {
        return moviesCollection;
    }

    public void setMoviesCollection(LinkedList<Movie> mov) {
        moviesCollection = mov;
    }
    public void setElementId(int id) {
        elementId = id;
    }
    public int getElementId(){
        return elementId;
    }
    public void putMovieInCollection(Movie mov){
        moviesCollection.add(mov);
    }

    public ZonedDateTime getCreationDate(){
        return creationDate;
    }

    public void clearCollection(){
        moviesCollection.clear();
    }

    public String filterByName(String args){
        LinkedList<Movie> moviesCollection = CollectionManager.getInstance().getMoviesCollection();
        int result = 0;
        for (Movie mov :  moviesCollection) {
            if (mov.getName().equals(args)) {
                result += 1;
            }
        }
        return "Количество фильмов с данным названием: "+ Integer.toString(result);
    }
    /**
     * The method that finds first element of collection
     * @return String
     */
    public String findHeadOfCollection(){
        if (moviesCollection.isEmpty()) {
            return ("Дорогой пользователь, коллекция пуста => первого элемента не существует");
        } else {
            Movie movie = moviesCollection.get(0);
            return("Первый элемент коллекции: " + "Название Фильма:  " + movie.getName() +"\nid Фильма:  " + movie.getId()
                    + " Координата X: " + movie.getCoordinates().getX() + " Координата Y: " + movie.getCoordinates().getY() + " Количество Оскаров: " + movie.getOscarsCount()
                    + " Количество Золотых пальм: " + movie.getGoldenPalmCount() + " Длина фильма: " + movie.getLength()
                    + " Жанр: " + movie.getGenre().toString() + " Страна: " + movie.getOperator().getLocation().getName()
                    + " Имя режиссёра: " + movie.getOperator().getName() +"\nЦвет глаз режиссёра: " + movie.getOperator().getEyeColor().toString()
                    + " Рост режиссёра: "+ movie.getOperator().getHeight());
        }
    }
    /**
     * The method that finds information about elements of collections
     * @return String
     */
    public String getCollectionInfo(){
        return("Тип коллекции: " + moviesCollection.getClass().getSimpleName() + "\n" +
                "Дата создания: " + CollectionManager.getInstance().getCreationDate() + "\n" +
                "Количество элементов в коллекции: " + moviesCollection.size());
    }

    public String saveCollection()  {
        try {
            XmlParser xmlParser = new XmlParser();
            return xmlParser.serializeCollection(moviesCollection);
        } catch (IOException e) {
            ConsolePrinter.messageToConsole("Не удалось сохранить файл, выход из программы");
            ExitCommand exitCommand = new ExitCommand("","");
            exitCommand.execute("", null);
        }
        return null;
    }



    /**
     * The method that removes some element from collection by its id
     * @return String
     */

    public ArrayList<String> removeLower(String args){
        int element = Integer.parseInt(args);
        ArrayList<String> removes = new ArrayList<String>();
        Iterator<Movie> iter = moviesCollection.iterator();
        if (moviesCollection.isEmpty()){
            removes.add("Коллекция пуста!");
        }
        else {
            while (iter.hasNext()) {
                Movie mov = iter.next();
                if (mov.getId() < element) {
                    removes.add("Элемент" + mov.getName() + " Удалён ");
                    iter.remove();
                }
            }
        }
        return removes;
    }

    /**
     * The method that shows  information about all elements in collection
     * @return String
     */
    public String showAllElements() {
        StringBuilder result = new StringBuilder();
        if (moviesCollection.isEmpty()) {
            result = new StringBuilder(("Дорогой пользователь, Коллекция пуста!"));
        } else {
            for (Movie movie : moviesCollection) {
                result.append("Название Фильма: ").append(movie.getName()).append("\nid Фильма:  ").append(movie.getId()).append(" Координата X: ");
                result.append(movie.getCoordinates().getX()).append(" Координата Y: ");
                result.append(movie.getCoordinates().getY()).append(" Количество Оскаров: ");
                result.append(movie.getOscarsCount()).append(" Количество Золотых пальм: ");
                result.append(movie.getGoldenPalmCount()).append(" Длина фильма: ");
                result.append(movie.getLength()).append(" Жанр: ");
                result.append(movie.getGenre().toString());
                result.append(" Страна: ").append(movie.getOperator().getNationality());
                result.append(" Имя режиссёра: ").append(movie.getOperator().getName());
                result.append("Цвет глаз режиссёра: ");
                result.append(movie.getOperator().getEyeColor().toString());
                result.append(" Рост режиссёра: ");
                result.append(movie.getOperator().getHeight()).append("\n");
                result.append(" Последняя модификация пользователя: ");
                result.append(movie.getUserModification());
            }
        }
        return result.toString();
    }

    public void setPath(String path){
        this.path = path;
    }

    public String getPath(){
        return path;
    }

    /**
     * The method that saving collection to file
     * @return String
     */

}

