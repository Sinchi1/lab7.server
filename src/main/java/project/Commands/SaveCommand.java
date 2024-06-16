package project.Commands;

import project.Managers.*;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

/**
 * The class used to call the method and display its work
 */
public class SaveCommand extends AbstractCommand {

    public SaveCommand(String name, String description) {
        super(name, description);
    }
    CollectionManager collectionManager;
    /**
     * The method that printing that collection was saved
     * @return void
     */
    @Override
    public String execute(String args, Object object) throws JsonProcessingException {
        collectionManager = CollectionManager.getInstance();
        return  (collectionManager.saveCollection());
    }

}
