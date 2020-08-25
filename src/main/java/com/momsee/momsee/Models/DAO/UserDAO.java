package com.momsee.momsee.Models.DAO;

import com.momsee.momsee.Common.JDBCUtil;
import com.momsee.momsee.Models.VO.UserVO;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@ComponentScan
@Repository("userDAO")
public class UserDAO {


    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;


    String email;
    String child_name;
    String mission_cont;
    int mission_time;
    String mission_image;

    private final String USER_INSERT = "insert into user(user_name,email,password) values(?,?,?)";
    private final String USER_GET = "select * from user where email=? and password=?";

    public void RegisterUser(UserVO vo) {
        System.out.println("RegisterUser 실행");
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(USER_INSERT);
            stmt.setString(1, vo.getName());
            stmt.setString(2, vo.getEmail());
            stmt.setString(3, vo.getEncrypted_password());
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }

    }

    public boolean Login(UserVO vo)
    {
        System.out.println("=====>JDBC로 insertboard() 기능 처리");
        List<UserVO> missionList = new ArrayList<UserVO>();
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(USER_GET);
            stmt.setString(1, vo.getEmail());
            stmt.setString(2, vo.getEncrypted_password());
            rs = stmt.executeQuery();

          if(rs.next())
              return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
        return false;
    }

    public void updateUser(UserVO vo) {

    }

    public void deleteUser(UserVO vo) {

    }

    public UserVO getUser(UserVO vo) {
        return null;
    }

    public List<UserVO> getUserList(UserVO vo) {
//        System.out.println("=====>JDBC로 insertboard() 기능 처리");
//        List<UserVO> missionList = new ArrayList<UserVO>();
//        try {
//            conn = JDBCUtil.getConnection();
//            stmt = conn.prepareStatement(USER_GET);
//            stmt.setString(1, vo.getEmail());
//            stmt.setString(2, vo.getChild_name());
//            stmt.executeUpdate();
//
//            while(rs.next())
//            {
//                UserVO missionVO = new UserVO();
//                missionVO.setUser_cont(rs.getString("USER_CONT"));
//                missionVO.setUser_time(rs.getInt("USER_TIME"));
//                missionList.add(missionVO);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            JDBCUtil.close(stmt, conn);
//        }
        return null;
    }
}
