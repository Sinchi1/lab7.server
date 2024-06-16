package project.Commands;

import project.Managers.*;

import java.io.IOException;
import java.util.LinkedList;
/**
 * The class used to call the method and display its work
 */
public class ClearCommand extends AbstractCommand {

    public ClearCommand(String name, String description) {
        super(name, description);
    }
    CollectionManager moviesCollection;
    /**
     * The method that admitting that collection was cleared
     * @return void
     */
    @Override
    public String execute(String args, Object object) {
        moviesCollection = CollectionManager.getInstance();
        moviesCollection.clearCollection();
        return ("Коллекция "+ "Успешно очищена!");
    }

}


