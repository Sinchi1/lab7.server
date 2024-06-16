package project.Managers;

import project.ProgrammEnums.OperationCode;
/**
 * The singleton class that's contains information about reading mode
 */
public class OperationCodeManager {

    private OperationCode operationCode;

    private static OperationCodeManager instance;
    private OperationCodeManager(){}
    public static OperationCodeManager getInstance(){
        if(instance == null){
            instance = new OperationCodeManager();
        }
        return instance;
    }

    public OperationCode getOperationCodeManager(){
        return operationCode;
    }

    public void setProgrammState(OperationCode operationCode){
        this.operationCode = operationCode;
    }

}