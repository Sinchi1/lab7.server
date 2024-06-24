package project.Commands;

import com.google.common.hash.Hashing;
import project.Collections.Movie;
import project.Common.Account;
import project.Common.Request;
import project.Managers.*;
import project.ProgrammEnums.OperationCode;
import project.ProgrammEnums.ProgrammState;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
/**
 * The class used to call the method and display its work
 */
public class LogIn extends AbstractCommand {
    DataBaseManager dataBaseManager = DataBaseManager.getInstance();

    Account account = Account.getInstance();

    OperationCodeManager operationCodeManager = OperationCodeManager.getInstance();

    public LogIn(String name, String description) {
        super(name, description);
    }

    /**
     * The method that printing all information about collection
     *
     * @return void
     */
    @Override
    public String execute(String args, Object object) throws SQLException {
        ArrayList<String> user = (ArrayList<String>) object;
        String name = user.get(0);
        String pass = Hashing.sha512().hashString(user.get(1), StandardCharsets.UTF_16).toString();
        String action = user.get(2);
        if (action.equals("reg")){
            if (dataBaseManager.registration(name,pass )){
                account.setUserName(name);
                account.setPassword(pass);
                operationCodeManager.setProgrammState(OperationCode.ok);
                return ("Успешная регистрация");
            }
            else {
                operationCodeManager.setProgrammState(OperationCode.error);
                return ("Аккаунт уже существует");
            }
        } else if (action.equals("log")) {
            if (dataBaseManager.log(name, pass)){
                account.setUserName(name);
                account.setPassword(pass);
                operationCodeManager.setProgrammState(OperationCode.ok);
                return ("Вы успешно зашли в аккаунт");
            }
            else {
                operationCodeManager.setProgrammState(OperationCode.error);
                return ("В пароле или имени пользователя допущена ошибка ");
            }
        }
        operationCodeManager.setProgrammState(OperationCode.error);
        return "Такого пользователя не существует!";
    }
}