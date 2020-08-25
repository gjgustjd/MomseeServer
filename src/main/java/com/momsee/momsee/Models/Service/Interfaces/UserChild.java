package com.momsee.momsee.Models.Service.Interfaces;

import com.momsee.momsee.Models.VO.UserChildVO;

import java.util.List;

public interface UserChild {

    void insertChild(UserChildVO vo);
    void updateChild(UserChildVO vo);
    void deleteChild(UserChildVO vo);
    UserChildVO getChild(UserChildVO vo);
    List<UserChildVO> getChildList(UserChildVO vo);
}
