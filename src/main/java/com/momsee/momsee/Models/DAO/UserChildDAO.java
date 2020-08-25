package com.momsee.momsee.Models.DAO;

import com.momsee.momsee.Common.JDBCUtil;
import com.momsee.momsee.Models.VO.UserChildVO;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository("childDAO")
public class UserChildDAO {


    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;


    private final String CHILD_INSERT = "insert into userchild(email,child_name,child_age) values(?,?,?)";
    private final String LOGIN = "select * from userchild where email=? and child_name=?";
    private final String CHILD_DELETE = "delete child where email=? and child_name=?";
    private final String CHILD_LIST = "select * from userchild where email=?";
    private final String UPDATE_LOCATION = "update userchild set latitude=? longitude=? where email=? and child_name=?";
    private final String GET_LOCATION = "select * from userchild where email=? and child_name=?";

    public void insertChild(UserChildVO vo) {

        System.out.println("=====>JDBC로 insertboard() 기능 처리");
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(CHILD_INSERT);
            stmt.setString(1, vo.getEmail());
            stmt.setString(2, vo.getChild_name());
            stmt.setString(3, vo.getChild_age());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }

    }

    public void updateChild(UserChildVO vo) {

    }

    public void updateChildLocation(UserChildVO vo) {
        System.out.println("=====>JDBC로 insertboard() 기능 처리");
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(UPDATE_LOCATION);
            stmt.setString(1, vo.getLatitude());
            stmt.setString(2, vo.getLongitude());
            stmt.setString(3, vo.getEmail());
            stmt.setString(4, vo.getChild_name());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    public boolean childLogin(UserChildVO vo) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(LOGIN);
            stmt.setString(1, vo.getEmail());
            stmt.setString(2, vo.getChild_name());
            rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
        return false;
    }

    public UserChildVO getChildLocation(UserChildVO vo) {
        UserChildVO childVO = new UserChildVO();
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(GET_LOCATION);
            stmt.setString(1, vo.getEmail());
            stmt.setString(2, vo.getChild_name());
            rs = stmt.executeQuery();

            childVO.setLatitude(rs.getString("LATITUDE"));
            childVO.setLongitude(rs.getString("LONGITUDE"));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
        return childVO;
    }

    public UserChildVO getDeviceKey(UserChildVO vo)
    {        UserChildVO childVO = new UserChildVO();
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(GET_LOCATION);
            stmt.setString(1, vo.getEmail());
            stmt.setString(2, vo.getChild_name());
            rs = stmt.executeQuery();

            childVO.setDevice_key(rs.getString("DEVICE_KEY"));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
        return childVO;
    }

    public void updateDeviceKey(UserChildVO vo) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(UPDATE_LOCATION);
            stmt.setString(1, vo.getLatitude());
            stmt.setString(2, vo.getLongitude());
            stmt.setString(3, vo.getEmail());
            stmt.setString(4, vo.getChild_name());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }


    public void deleteChild(UserChildVO vo) {

    }

    public UserChildVO getChild(UserChildVO vo) {
        return null;
    }

    public List<UserChildVO> getChildList(UserChildVO vo) {
        System.out.println("=====>JDBC로 insertboard() 기능 처리");
        List<UserChildVO> childList = new ArrayList<UserChildVO>();
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(CHILD_LIST);
            stmt.setString(1, vo.getEmail());
            rs = stmt.executeQuery();

            while (rs.next()) {
                UserChildVO childVO = new UserChildVO();
                childVO.setChild_name(rs.getString("CHILD_NAME"));
                childList.add(childVO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
        return childList;
    }


}
