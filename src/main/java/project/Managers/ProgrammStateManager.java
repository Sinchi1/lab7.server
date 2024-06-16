package project.Managers;

import project.ProgrammEnums.OperationCode;
import project.ProgrammEnums.ProgrammState;
/**
 * The singleton class that's contains information about reading mode
 */
public class ProgrammStateManager {

    private ProgrammState programmState;
    private static ProgrammStateManager instance;
    private ProgrammStateManager(){}
    public static ProgrammStateManager getInstance(){
        if(instance == null){
            instance = new ProgrammStateManager();
        }
        return instance;
    }

    public void setProgrammState(ProgrammState programmState) {
        this.programmState = programmState;
    }

    public static void setInstance(ProgrammStateManager instance) {
        ProgrammStateManager.instance = instance;
    }

    public ProgrammState getProgrammState() {
        return programmState;
    }
}
