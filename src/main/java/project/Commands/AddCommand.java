package project.Commands;

import project.Managers.*;
import project.ProgrammEnums.ProgrammState;
import project.Readers.*;
import project.Collections.Movie;

import java.io.IOException;
import java.util.List;

/**
 * The class used to call the method and display its work
 */
public class AddCommand extends AbstractCommand{

    public AddCommand(String name, String description) {
        super(name,description);
    }
    CollectionManager collectionManager;
    MovieReader movieReader = new MovieReader();
    ProgrammStateManager programmStateManager = ProgrammStateManager.getInstance();

    /**
     * The method that admitting to user new files of collection
     * @return void
     */
    @Override
    public String execute(String string, Object args) throws IOException {
        collectionManager = CollectionManager.getInstance();
            if (programmStateManager.getProgrammState() == ProgrammState.PROGRAMM_STATE_PASSIVE){
                Movie mov1 = movieReader.readMovieFromFile((List<String>) args);
                collectionManager.putMovieInCollection(mov1);
                return ("Вы успешно создали элемент коллекции!");
            }
            else {
                Movie mov1 = (Movie) args;
                int id = collectionManager.getElementId();
                id += 1;
                mov1.setId(id);
                collectionManager.setElementId(id);
                collectionManager.putMovieInCollection(mov1);
                return ("Вы успешно создали элемент коллекции!");
            }
    }

}