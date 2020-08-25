package com.momsee.momsee.Models.Service.Interfaces;

import com.momsee.momsee.Models.VO.UserVO;

import java.util.List;

public interface User {
    public boolean Login(UserVO vo);
    void insertUser(UserVO vo);
    void updateUser(UserVO vo);
    void deleteUser(UserVO vo);
    UserVO getUser(UserVO vo);
    List<UserVO> getUserList(UserVO vo);
}
