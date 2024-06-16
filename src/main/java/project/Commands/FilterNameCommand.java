package project.Commands;

import project.Managers.*;

import java.io.IOException;

/**
 * The class used to call the method and display its work
 */
public class FilterNameCommand extends AbstractCommand{

    public FilterNameCommand(String name, String description) {
        super(name, description);
    }

    CollectionManager collectionManager;
    /**
     * The method used to print the number of matches in object names
     *
     * @return void
     */
    @Override
    public String execute(String args, Object object) {
        String argument = args;
        collectionManager = CollectionManager.getInstance();
        return (collectionManager.filterByName(argument));
    }


}
