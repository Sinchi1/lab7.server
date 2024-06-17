package project.Commands;

import project.Collections.Movie;
import project.Managers.DataBaseManager;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckId extends AbstractCommand{

    DataBaseManager dataBaseManager = DataBaseManager.getInstance();

    
    public CheckId(String name, String description) {
        super(name, description);
    }

    @Override
    public String execute(String args, Object object) throws IOException, SQLException {
        Movie replace = dataBaseManager.getMovieById(Integer.parseInt(args));
        if (replace != null){
            return "true";
        }
        else {
            return "false";
        }
    }
}
