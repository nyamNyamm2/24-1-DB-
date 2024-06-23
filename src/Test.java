/*
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
*/

import java.sql.*;

public class Test
{
    public static void main(String[] args) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        String connectionUrl = "jdbc:sqlserver://localhost:62104;databaseName=sky201910852;user=sky;password=helloworld;encrypt=false;";

        // 1.드라이버 로딩
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        }
        catch (ClassNotFoundException e)
        {
            System.err.println(" !! <JDBC 오류> Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 2.연결
        try
        {
            con = DriverManager.getConnection(connectionUrl);
            System.out.println("정상적으로 연결되었습니다.");
        }
        catch(SQLException e)
        {
            System.err.println("con 오류:" + e.getMessage());
            e.printStackTrace();
        }

        /*
        // 3.해제
        try
        {
            if(con != null)
                con.close();
        }
        catch (SQLException e) {}
        */

        pstmt = con.prepareStatement("INSERT INTO cookuser (name) VALUES (?)");
        pstmt.setString(1, "홍길동");
        pstmt.executeUpdate();
    }
}