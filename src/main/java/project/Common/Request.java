package project.Common;

import java.io.Serial;
import java.io.Serializable;

public class Request implements Serializable {
    @Serial
    private static final long serialVersionUID = 1404L;
    private final String commandName;
    private final String commandStringArgument;
    private final Serializable commandObjectArgument;

    public Request(String commandName, String commandStringArgument, Serializable commandObjectArgument) {
        this.commandName = commandName;
        this.commandStringArgument = commandStringArgument;
        this.commandObjectArgument = commandObjectArgument;
    }

    public String getCommandName() {
        return commandName;
    }

    public String getCommandStringArgument() {
        return commandStringArgument;
    }


    public Serializable getCommandObjectArgument() {
        return commandObjectArgument;
    }

}
