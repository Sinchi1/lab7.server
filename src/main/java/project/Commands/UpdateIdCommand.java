package project.Commands;

import project.Managers.*;
import project.ProgrammEnums.ProgrammState;
import project.Readers.*;
import project.Collections.Movie;

import java.io.IOException;
/**
 * The class used to call the method and display its work
 */
public class UpdateIdCommand extends AbstractCommand {

    public UpdateIdCommand(String name, String description) {
        super(name, description);
    }

    CollectionManager collectionManager;
    DataBaseManager dataBaseManager = DataBaseManager.getInstance();
    /**
     * The method that printing updated element of collection
     * @return void
     */
    @Override
    public String execute(String string, Object args) throws IOException {
        collectionManager  =  CollectionManager.getInstance();
        Movie mov1 = (Movie) args;
        dataBaseManager.updateMovie(mov1.getId(), mov1);
        return "Произошла успешная замена элемента коллекции по id: "+ mov1.getId();

    }

}


