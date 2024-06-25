package project.Commands;

import com.google.common.hash.Hashing;
import project.Common.Account;
import project.Managers.DataBaseManager;
import project.Managers.OperationCodeManager;
import project.ProgrammEnums.OperationCode;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;

public class Registration extends AbstractCommand {
    DataBaseManager dataBaseManager = DataBaseManager.getInstance();

    Account account = Account.getInstance();

    OperationCodeManager operationCodeManager = OperationCodeManager.getInstance();

    public Registration(String name, String description) {
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
        if (action.equals("registration")){
            if (dataBaseManager.registration(name,pass)){
                account.setUserName(name);
                account.setPassword(pass);
                operationCodeManager.setProgrammState(OperationCode.ok);
                return ("Успешная регистрация");
            }
            else {
                operationCodeManager.setProgrammState(OperationCode.error);
                return ("Аккаунт уже существует");
            }
        }
        operationCodeManager.setProgrammState(OperationCode.error);
        return "Такого пользователя не существует!";
    }
}
