package project.Commands;

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
    CollectionManager collectionManager;

    ConsolePrinter consolePrinter = new ConsolePrinter();
    /**
     * The method used to print first element of collection
     * @return void
     */
    @Override
    public String execute(String args, Object object) {
        collectionManager = CollectionManager.getInstance();
        return (collectionManager.findHeadOfCollection());
    }

}
