package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
	//class connection to database

    static Connection connection = null;  
    public Connection connect() throws SQLException {
		int i = 0;
		return DriverManager.getConnection("jdbc:sqlite:maindatabase.db");
    }
	
}
