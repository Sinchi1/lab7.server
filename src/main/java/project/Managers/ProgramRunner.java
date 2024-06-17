package project.Managers;

import project.Collections.Movie;
import project.ProgrammEnums.ProgrammState;
import project.Commands.AbstractCommand;
import project.Readers.MovieReader;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

public class ProgramRunner {
    private static String ScriptName;
    CommandManager commandManager = new CommandManager();
    HashSet<String> corrector = new HashSet<>();
    public String runOnce(ArrayList<String> args) throws IOException, SQLException {
        StringBuilder result = new StringBuilder();
        Iterator<String> iterator = args.iterator();
        ProgrammStateManager programmStateManager = ProgrammStateManager.getInstance();
        programmStateManager.setProgrammState(ProgrammState.PROGRAMM_STATE_PASSIVE);
        while (iterator.hasNext()) {
            String line = iterator.next();
            if (line == null) {
                break;
            }
            String[] parts = line.split("\\s+", 2);
            String commName = parts[0].toLowerCase();
            if (commandManager.isCommandExists(commName)) {
                AbstractCommand com = commandManager.getCommandByName(commName);
                if (commandManager.isHavingArgument(commName) && (parts.length == 1)) {
                    ConsolePrinter.messageToConsole("Вы забыли аргумент команды!");
                    break;
                }
                if ((commandManager.isHavingArgument(commName)) && (parts[1].split("\\s{1}", 2)).length > 1) {
                    ConsolePrinter.messageToConsole("Вы перестарались и ввели лишний аргумент для команды!");
                    break;
                }
                String commandArgument = "";
                if (commandManager.isHavingArgument(commName)) {
                    commandArgument = parts[1];
                }
                if (commName.equals("execute_script") && corrector.contains(commandArgument)){
                    ConsolePrinter.messageToConsole("Доступна только одна итерация скрипта!");
                    break;
                }

                if (commName.equals("execute_script")){
                    corrector.add(commandArgument);
                }

                if (commName.equals("add") || commName.equals("update_id")){
                    List<String> arguments = new ArrayList<>();
                    int i = 0;
                    while(i <= 15){
                        line = iterator.next();
                        arguments.add(line);
                        i++;

                    }
                    result.append(com.execute("",arguments));
                    result.append("\n");
                    continue;

                }
                result.append(com.execute(commandArgument, null));
                result.append("\n");
            }
        }
        corrector.clear();
        programmStateManager.setProgrammState(ProgrammState.PROGRAMM_STATE_ACTIVE);
        return result.toString();
    }

    public String getScriptName() {
        return ScriptName;
    }
}
