package com.momsee.momsee.Models.Service;

import com.momsee.momsee.Models.DAO.MissionDAO;
import com.momsee.momsee.Models.Service.Interfaces.Mission;
import com.momsee.momsee.Models.VO.MissionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Service("missionService")
@Component
@Repository
public class MissionService implements Mission {
    @Autowired
    MissionDAO missionDAO;

    @Override
    public void insertMission(MissionVO vo) {
        missionDAO.insertMission(vo);
    }

    @Override
    public void updateMission(MissionVO vo) {

        missionDAO.updateMission(vo);
    }

    public void uploadImage(MissionVO vo)
    {
        missionDAO.uploadImage(vo);
    }

    public MissionVO getImage(MissionVO vo)
    {
        return missionDAO.getImage(vo);
    }

    @Override
    public void deleteMission(MissionVO vo) {

        missionDAO.deleteMission(vo);
    }

    @Override
    public MissionVO getMission(MissionVO vo) {

        missionDAO.getMission(vo);
        return null;
    }

    @Override
    public List<MissionVO> getMissionList(MissionVO vo) {
       return missionDAO.getMissionList(vo);
    }
}
