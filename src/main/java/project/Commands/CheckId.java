package project.Commands;

import project.Collections.Movie;
import project.Common.Account;
import project.Managers.DataBaseManager;
import java.io.IOException;
import java.sql.SQLException;

public class CheckId extends AbstractCommand{

    DataBaseManager dataBaseManager = DataBaseManager.getInstance();

    Account account = Account.getInstance();

    
    public CheckId(String name, String description) {
        super(name, description);
    }

    @Override
    public String execute(String args, Object object) throws IOException, SQLException {
        Movie replace = dataBaseManager.getMovieById(Integer.parseInt(args));
        String name = account.getUserName();
        if (replace != null && replace.getUserModification().equals(name)){
            return "true";
        }
        else {
            return "false";
        }
    }
}
