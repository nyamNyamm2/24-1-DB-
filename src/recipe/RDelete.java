package recipe;

import abs.AbstractDAO;
import java.sql.*;

public class RDelete extends AbstractDAO
{
    public RDelete(RecipeVO recipeVO) throws SQLException
    {
        pstmt = conn.prepareStatement("DELETE cookrecipe WHERE title = ?");
        pstmt.setString(1, recipeVO.getRecipeTitle());
        pstmt.executeUpdate();
    }
}
