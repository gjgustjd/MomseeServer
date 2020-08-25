package com.momsee.momsee.Models.Service.Interfaces;

import com.momsee.momsee.Models.VO.MissionVO;

import java.util.List;

public interface Mission {


    void insertMission(MissionVO vo);
    void updateMission(MissionVO vo);
    void deleteMission(MissionVO vo);
    MissionVO getMission(MissionVO vo);
    List<MissionVO> getMissionList(MissionVO vo);
}
