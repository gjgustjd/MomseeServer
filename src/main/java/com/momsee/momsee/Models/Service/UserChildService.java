package com.momsee.momsee.Models.Service;

import com.momsee.momsee.Models.DAO.UserChildDAO;
import com.momsee.momsee.Models.Service.Interfaces.UserChild;
import com.momsee.momsee.Models.VO.UserChildVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("childService")
@ComponentScan
public class UserChildService implements UserChild {

    @Autowired
    UserChildDAO userChildDAO;

    @Override
    public void insertChild(UserChildVO vo) {
        userChildDAO.insertChild(vo);
    }


    public boolean childLogin(UserChildVO vo) {
        return userChildDAO.childLogin(vo);
    }

    @Override
    public void updateChild(UserChildVO vo) {

    }

    @Override
    public void deleteChild(UserChildVO vo) {

    }

    public void UpdateLocation(UserChildVO vo) {
        userChildDAO.updateChildLocation(vo);
    }

    public void UpdateDeviceKey(UserChildVO vo) {
        userChildDAO.updateDeviceKey(vo);
    }


    public UserChildVO getLocation(UserChildVO vo) {
        return userChildDAO.getChildLocation(vo);
    }

    @Override
    public UserChildVO getChild(UserChildVO vo) {

        return null;
    }

    @Override
    public List<UserChildVO> getChildList(UserChildVO vo) {
        return userChildDAO.getChildList(vo);
    }

}
