package project.Commands;

import project.Managers.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/**
 * The class used to call the method and display its work
 */
public class HelpCommand extends AbstractCommand {

    public HelpCommand(String name, String description) {
        super(name, description);
    }

    CommandManager commandManager = new CommandManager();
    /**
     *
     * The method that printing all commands of the programm
     * @return void
     */
    @Override
    public String execute(String args, Object object) {
        commandManager.cmdAdd();
        HashMap<String, AbstractCommand> commandHashMap = commandManager.getCommandHashMap();
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, AbstractCommand> entry : commandHashMap.entrySet()){
            result.append("Описание: ").append(entry.getValue() +"\n");
        }
        return result.toString();
    }

}
