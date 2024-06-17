package project.Commands;

import project.Collections.Movie;
import project.Managers.*;

import java.io.IOException;
import java.util.LinkedList;
/**
 * The class used to call the method and display its work
 */
public class HeadCommand extends AbstractCommand{
    public HeadCommand(String name, String description) {
        super(name, description);
    }
    DataBaseManager dataBaseManager = DataBaseManager.getInstance();
    /**
     * The method used to print first element of collection
     * @return void
     */
    @Override
    public String execute(String args, Object object) {
        Movie result = dataBaseManager.getHead();
        if (result == null){
            return "В коллекции нет ни одного элемента";
        }
        else {
            return result.toString();
        }
    }

}
