package com.momsee.momsee.Models.DAO;

import com.momsee.momsee.Common.JDBCUtil;
import com.momsee.momsee.Models.VO.MissionVO;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@ComponentScan
@Repository("missionDAO")
public class MissionDAO {

    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;


    private final String MISSION_INSERT = "insert into mission(email,child_name,mission_cont,mission_time,mission_image) values(?,?,?,?,?)";
    private final String MISSION_UPDATE = "update mission set mission_cont=?,mission_time=? where email=? and child_name=?";
    private final String IMAGE_UPLOAD = "update mission set  mission_upload=? where email=? and child_name=?";
    private final String MISSION_DELETE = "delete mission where email=? and child_name=?";
    private final String MISSION_GET = "select * from mission where email=? and child_name=?";

    public void insertMission(MissionVO vo) {

        System.out.println("missionInsert 실행");
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(MISSION_INSERT);
            stmt.setString(1, vo.getEmail());
            stmt.setString(2, vo.getChild_name());
            stmt.setString(3, vo.getMission_cont());
            stmt.setInt(4, vo.getMission_time());
            stmt.setString(5, null);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }

    }

    public void uploadImage(MissionVO vo)
    {
        System.out.println("uploadImage 실행");
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(IMAGE_UPLOAD);
            stmt.setString(1, vo.getEmail());
            stmt.setString(2, vo.getChild_name());
            stmt.setString(2, vo.getMission_image());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    public MissionVO getImage(MissionVO vo)
    {
        System.out.println("uploadImage 실행");
        MissionVO missionVO = new MissionVO();
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(IMAGE_UPLOAD);
            stmt.setString(1, vo.getEmail());
            stmt.setString(2, vo.getChild_name());
            rs = stmt.executeQuery();
            while(rs.next())
            {
                missionVO.setMission_cont(rs.getString("MISSION_IMAGE"));
            }
            return missionVO;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    public void updateMission(MissionVO vo) {

        System.out.println("missionUpdate 실행");
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(MISSION_UPDATE);
            stmt.setString(1, vo.getEmail());
            stmt.setString(2, vo.getChild_name());
            stmt.setString(3, vo.getMission_cont());
            stmt.setInt(4, vo.getMission_time());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    public void deleteMission(MissionVO vo) {

        System.out.println("missionDelete 실행");
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(MISSION_DELETE);
            stmt.setString(1, vo.getEmail());
            stmt.setString(2, vo.getChild_name());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    public MissionVO getMission(MissionVO vo) {
        MissionVO missionVO = new MissionVO();
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(MISSION_GET);
            stmt.setString(1, vo.getEmail());
            stmt.setString(2, vo.getChild_name());
            rs = stmt.executeQuery();

            while(rs.next())
            {
                missionVO.setMission_cont(rs.getString("MISSION_CONT"));
                missionVO.setMission_time(rs.getInt("MISSION_TIME"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
        return missionVO;
    }

    public List<MissionVO> getMissionList(MissionVO vo) {
        System.out.println("=====>JDBC로 insertboard() 기능 처리");
        List<MissionVO> missionList = new ArrayList<MissionVO>();
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(MISSION_GET);
            stmt.setString(1, vo.getEmail());
            stmt.setString(2, vo.getChild_name());
            rs = stmt.executeQuery();

            while(rs.next())
            {
                MissionVO missionVO = new MissionVO();
                missionVO.setMission_cont(rs.getString("MISSION_CONT"));
                missionVO.setMission_time(rs.getInt("MISSION_TIME"));
                missionList.add(missionVO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
        return missionList;
    }
}
