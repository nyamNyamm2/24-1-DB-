package user;

import java.sql.*;
import java.util.*;
import abs.AbstractDAO;

public class UModify extends AbstractDAO
{
    public UModify(String username, int userid) throws SQLException
    {
        pstmt = conn.prepareStatement("UPDATE cookuser SET name = ? WHERE id = ?");
        pstmt.setString(1, username);
        pstmt.setInt(2, userid);
        pstmt.executeUpdate();
    }
}
