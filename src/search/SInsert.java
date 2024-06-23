package search;

import abs.AbstractDAO;
import java.sql.*;

public class SInsert extends AbstractDAO
{
    public SInsert(int id, String title) throws SQLException
    {
        pstmt = conn.prepareStatement("INSERT INTO search (title, id, stime) VALUES (?, ?, CONVERT(char(6),GETDATE(),12))");
        pstmt.setString(1, title);
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
    }
}
