package recipe;

import abs.AbstractDAO;
import java.sql.*;
import java.util.*;

public class RView extends AbstractDAO
{
    public RView() {}

    public List<RecipeVO> view() throws SQLException
    {
        List<RecipeVO> recipes = new ArrayList<RecipeVO>();

        pstmt = conn.prepareStatement("SELECT * FROM cookrecipe ORDER BY title");
        ResultSet rs = pstmt.executeQuery();

        while (rs.next())
        {
            String title = rs.getString("title");
            int id = rs.getInt("id");
            String content = rs.getString("content");
            String diff = rs.getString("diff");
            RecipeVO recipevo = new RecipeVO(title, id, content, diff);
            recipes.add(recipevo);
        }

        rs.close();
        return recipes;
    }
}
