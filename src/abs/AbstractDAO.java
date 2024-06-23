package abs;

import java.sql.*;

public abstract class AbstractDAO {
    protected static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    protected static final String url = "jdbc:sqlserver://localhost:62104;databaseName=sky201910852;user=sky;password=helloworld;encrypt=false;";

    static protected Connection conn = null;
    protected PreparedStatement pstmt = null;

    public AbstractDAO()
    {
        if (conn == null)
        {
            connDB();
        }
    }

    protected void connDB() {
        try
        {
            Class.forName(driver);
            conn = DriverManager.getConnection(url);
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

}