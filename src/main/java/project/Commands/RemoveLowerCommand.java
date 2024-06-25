package project.Commands;

import project.Managers.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
/**
 * The class used to call the method and display its work
 */
public class RemoveLowerCommand extends AbstractCommand {

    public RemoveLowerCommand(String name, String description) {
        super(name, description);
    }

    CollectionManager collectionManager;

    DataBaseManager dataBaseManager;

    /**
     * The method that printing all removed elements above under integer
     * @return void
     */
    @Override
    public String execute(String args, Object object) throws SQLException {
    String argument = args;
    collectionManager = CollectionManager.getInstance();
    dataBaseManager = DataBaseManager.getInstance();
    return (dataBaseManager.removeFirst().toString().replaceAll("^\\[|\\]$", ""));
    }



}
