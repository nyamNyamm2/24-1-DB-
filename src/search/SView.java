package search;

import abs.AbstractDAO;
import java.sql.*;
import java.util.*;

public class SView extends AbstractDAO
{
    public SView() {}

    public List<SearchVO> view() throws SQLException
    {
        List<SearchVO> searches = new ArrayList<SearchVO>();

        pstmt = conn.prepareStatement("SELECT * FROM search ORDER BY stime");
        ResultSet rs = pstmt.executeQuery();

        while(rs.next())
        {
            String title = rs.getString("title");
            int id = rs.getInt("id");
            String date = rs.getString("stime");
            SearchVO searchVO = new SearchVO(title, id, date);
            searches.add(searchVO);
        }

        rs.close();
        return searches;
    }
}
