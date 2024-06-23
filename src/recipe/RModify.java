package recipe;

import java.sql.*;
import java.util.*;
import abs.AbstractDAO;

public class RModify extends AbstractDAO
{
    public RModify(String recipetitle, int id, String content,String diff) throws SQLException
    {
        pstmt = conn.prepareStatement("UPDATE cookrecipe SET id = ?, content = ?, diff = ? WHERE title = ?");
        pstmt.setInt(1, id);
        pstmt.setString(2, content);
        pstmt.setString(3, diff);
        pstmt.setString(4, recipetitle);
        pstmt.executeUpdate();
    }
}
