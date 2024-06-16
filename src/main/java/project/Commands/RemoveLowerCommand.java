package project.Commands;

import project.Managers.*;

import java.io.IOException;
import java.util.LinkedList;
/**
 * The class used to call the method and display its work
 */
public class RemoveLowerCommand extends AbstractCommand {

    public RemoveLowerCommand(String name, String description) {
        super(name, description);
    }

    CollectionManager collectionManager;

    ConsolePrinter consolePrinter = new ConsolePrinter();
    /**
     * The method that printing all removed elements above under integer
     * @return void
     */
    @Override
    public String execute(String args, Object object) {
    String argument = args;
    collectionManager = CollectionManager.getInstance();
    return (collectionManager.removeLower(argument).toString().replaceAll("^\\[|\\]$", ""));
    }



}
