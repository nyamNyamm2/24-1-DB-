package recipe;

import abs.AbstractDAO;

import java.sql.*;

public class RInsert extends AbstractDAO
{
    public RInsert(String recipetitle, int id, String content, String diff) throws SQLException
    {
        pstmt = conn.prepareStatement("INSERT INTO cookrecipe (title, id, content, diff) VALUES (?, ?, ?, ?)");
        pstmt.setString(1, recipetitle);
        pstmt.setInt(2, id);
        pstmt.setString(3, content);
        pstmt.setString(4, diff);
        pstmt.executeUpdate();
    }
}
