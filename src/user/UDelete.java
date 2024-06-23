package user;

import abs.AbstractDAO;
import java.sql.*;

public class UDelete extends AbstractDAO
{
    public UDelete(UserVO userVO) throws SQLException
    {
        pstmt = conn.prepareStatement("DELETE cookuser WHERE id = ?");
        pstmt.setInt(1, userVO.getUserId());
        pstmt.executeUpdate();
    }
}
