package com.momsee.momsee.Common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCUtil {
    public static Connection getConnection()
    {
        Connection con = null;

        String server = "localhost"; // MySQL 서버 주소
        String database = "momsee"; // MySQL DATABASE 이름
        String user_name = "root"; //  MySQL 서버 아이디
        String password = "1234"; // MySQL 서버 비밀번호

        // 1.드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", user_name, password);
            System.out.println("데이터베이스 연결 성공");
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("데이터베이스 연결 실패");
        }
        return null;
    }

    public static void close(PreparedStatement stmt,Connection conn)
    {
        if(stmt !=null)
        {
            try{
                if(!stmt.isClosed())
                {
                    stmt.close();
                }
            }catch(Exception e)
            {
                e.printStackTrace();
            }finally
            {
                conn= null;
            }
        }
    }
    public static void close(ResultSet rs, PreparedStatement stmt,Connection conn)
    {
        if(rs!=null)
        {
            try
            {
                if(!rs.isClosed()) rs.close();
            }catch(Exception e)
            {
                e.printStackTrace();
            }finally
            {
                conn=null;
            }
        }
        if(stmt !=null)
        {
            try{
                if(!stmt.isClosed())
                {
                    stmt.close();
                }
            }catch(Exception e)
            {
                e.printStackTrace();
            }finally
            {
                conn= null;
            }
        }
    }
}
