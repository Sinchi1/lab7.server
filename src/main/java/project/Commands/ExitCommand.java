package project.Commands;

import project.Common.Request;
import project.Common.User;
import project.Managers.*;
import project.Util.RequestHandler;

import java.io.IOException;

/**
 * The class used to call the method and display its work
 */
public class ExitCommand extends AbstractCommand{
    public ExitCommand(String name, String description) {
        super(name, description);
    }

    RequestHandler requestHandler = new RequestHandler();

    /**
     * The method used to end the program
     * @return void
     */
    @Override
    public String execute(String args, Object object) {
        System.exit(1);
        return "Сервер отключен!";
    }


}
