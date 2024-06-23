package user;

import abs.AbstractDAO;
import java.sql.*;
import java.util.*;

public class UView extends AbstractDAO
{
    public UView() {}

    public List<UserVO> view() throws SQLException
    {
        List<UserVO> users = new ArrayList<UserVO>();

        pstmt = conn.prepareStatement("SELECT * FROM cookuser ORDER BY id");
        ResultSet rs = pstmt.executeQuery();

        while (rs.next())
        {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            UserVO uservo = new UserVO(id, name);
            users.add(uservo);
        }

        rs.close();
        return users;
    }
}
