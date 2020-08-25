package com.momsee.momsee.Models.Service;

import com.momsee.momsee.Models.DAO.UserDAO;
import com.momsee.momsee.Models.Service.Interfaces.User;
import com.momsee.momsee.Models.VO.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserService implements User {

    @Autowired
    UserDAO userDAO;

    @Override
    public void insertUser(UserVO vo) {
        userDAO.RegisterUser(vo);
    }

    @Override
    public boolean Login(UserVO vo)
    {
        return userDAO.Login(vo);
    }

    @Override
    public void updateUser(UserVO vo) {
    }

    @Override
    public void deleteUser(UserVO vo) {

    }

    @Override
    public UserVO getUser(UserVO vo) {
        return null;
    }

    @Override
    public List<UserVO> getUserList(UserVO vo) {
        return null;
    }
}
