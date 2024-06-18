package project.Util;

import project.Commands.AbstractCommand;
import project.Common.Account;
import project.Common.Request;
import project.Common.Response;
import project.Managers.CommandManager;
import project.Managers.ConsolePrinter;
import project.Managers.OperationCodeManager;
import project.Managers.ProgrammStateManager;
import project.ProgrammEnums.OperationCode;
import project.ProgrammEnums.ProgrammState;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.NoSuchElementException;


public class RequestHandler {

    CommandManager commandManager = new CommandManager();
    OperationCodeManager operationCodeManager = OperationCodeManager.getInstance();

    public Response handle(Request request, Account account) {
        commandManager.cmdAdd();
        String responseBody = executeCommand(request);
        OperationCode operationCode = operationCodeManager.getOperationCodeManager();
        return new Response(responseBody, operationCode);

    }

    private String executeCommand(Request request) {
        try {
            String commName = request.getCommandName();
            String commandArgument = request.getCommandStringArgument();
            Object commandObjectArgument = request.getCommandObjectArgument();
            if (commandManager.isCommandExists(commName)) {
                AbstractCommand com = commandManager.getCommandByName(commName);
                        operationCodeManager.setProgrammState(OperationCode.ok);
                        return com.execute(commandArgument, commandObjectArgument);

            }else {
                return "Указанной команды не существует";
            }
        } catch (IOException e) {
            ConsolePrinter.errorMessage("Непредвиденная операция");
            return "Невозможно выполнить команду";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

