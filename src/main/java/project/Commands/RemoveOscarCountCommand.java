package project.Commands;

import project.Managers.*;

import java.io.IOException;

/**
 * The class used to call the method and display its work
 */
public class RemoveOscarCountCommand extends AbstractCommand{

    public RemoveOscarCountCommand(String name, String description) {
        super(name, description);
    }

    DataBaseManager dataBaseManager = DataBaseManager.getInstance();

    /**
     * The method that printing information about deleted elements,which contained right amout of Oscars
     * @return void
     */
    @Override
    public String  execute(String args, Object object) {
        return (dataBaseManager.removeOscar(Integer.parseInt(args)).toString().replaceAll("^\\[|\\]$", ""));
    }

}
