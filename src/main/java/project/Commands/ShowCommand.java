package project.Commands;

import project.Collections.Movie;
import project.Managers.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * The class used to call the method and display its work
 */
public class ShowCommand extends AbstractCommand {

    public ShowCommand(String name, String description) {
        super(name, description);
    }

    CollectionManager collectionManager;

    DataBaseManager dataBaseManager = DataBaseManager.getInstance();
    /**
     * The method that printing all elements of collection
     * @return void
     */
    @Override
    public String execute(String args, Object object) throws SQLException {
       collectionManager = CollectionManager.getInstance();
       collectionManager.setMoviesCollection((LinkedList<Movie>) dataBaseManager.getCollection());
       return collectionManager.showAllElements();
    }

}
