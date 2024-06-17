package project.Commands;

import project.Collections.Movie;
import project.Managers.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
/**
 * The class used to call the method and display its work
 */
public class InfoCommand extends AbstractCommand{

    public InfoCommand(String name, String description) {
        super(name, description);
    }

    CollectionManager collectionManager;

    DataBaseManager dataBaseManager = DataBaseManager.getInstance();

    /**
     * The method that printing all information about collection
     * @return void
     */
    @Override
    public String execute(String args, Object object) throws SQLException {
        collectionManager = CollectionManager.getInstance();
        collectionManager.setMoviesCollection((LinkedList<Movie>) dataBaseManager.getCollection());
        return (collectionManager.getCollectionInfo());
    }

}
