package project.Commands;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Interface for all Commands
 */
public interface InterCommand {

    public String getDescription();

    public String getName();

    public String execute(String args, Object object) throws IOException, SQLException;
}
