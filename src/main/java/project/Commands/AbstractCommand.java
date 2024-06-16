package project.Commands;

/**
 * Abstract class for all Commands
 */
public abstract class AbstractCommand implements InterCommand{

    private final String name;

    private final String description;

    public AbstractCommand(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name + "(" + description + ")";
    }



}