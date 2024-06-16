package project.Commands;

import project.Managers.*;


import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
/**
 * The class used to call the method and display its work
 */
public class RemoveByIdCommand extends AbstractCommand {

    public RemoveByIdCommand(String name, String description) {
        super(name, description);
    }
    CollectionManager collectionManager;
    /**
     * The method that printing information about deleted element of collection
     * @return void
     */
    @Override
    public String execute(String args, Object object) {
        String argument = args;
        collectionManager = CollectionManager.getInstance();
        return (collectionManager.removeById(argument).toString().replaceAll("^\\[|\\]$", ""));
    }

}
