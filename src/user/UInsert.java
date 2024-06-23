package user;

import abs.AbstractDAO;
import java.sql.*;

public class UInsert extends AbstractDAO
{
    public UInsert(String username) throws SQLException
    {
        pstmt = conn.prepareStatement("INSERT INTO cookuser (name) VALUES (?)");
        pstmt.setString(1, username);
        pstmt.executeUpdate();
        System.out.println("유저 추가 성공");
    }
}
