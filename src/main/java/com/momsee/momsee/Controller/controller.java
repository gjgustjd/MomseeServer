package com.momsee.momsee.Controller;

import com.google.gson.Gson;
import com.momsee.momsee.Models.Service.MissionService;
import com.momsee.momsee.Models.Service.UserChildService;
import com.momsee.momsee.Models.Service.UserService;
import com.momsee.momsee.Models.VO.MissionVO;
import com.momsee.momsee.Models.VO.UserChildVO;
import com.momsee.momsee.Models.VO.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class controller {
    @Autowired
    UserService userService;
    @Autowired
    MissionService missionService;
    @Autowired
    UserChildService userChildService;

    @RequestMapping("/")
    public String Hello() {
        return "Hello";
    }

    @RequestMapping("/test")
    public Map<String,String> Entry2()
    {
        Map<String,String> map = new HashMap<>();
        map.put("Hello","Hello");
        return map;
    }


    @RequestMapping(value = "/login" )  //로그인
    public ResponseEntity<String> UserLogin(@RequestBody Map<String,String > request)
    {
        UserVO vo = new UserVO();
        vo.setEmail(request.get("email"));
        vo.setEncrypted_password(request.get("password"));

        String result = new Gson().toJson(userService.Login(vo));
        HttpHeaders resHeader = new HttpHeaders();
        resHeader.add("Content-Type", "application/json;charset=UTF-8");

        return new ResponseEntity<>(result, resHeader, HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(value = "/childlogin" )  //자녀 로그인
    public ResponseEntity<String> childLogin(@RequestBody Map<String,String > request)
    {
        UserChildVO vo = new UserChildVO();
        vo.setEmail(request.get("email"));
        vo.setChild_name(request.get("child_name"));

        String result = new Gson().toJson(userChildService.childLogin(vo));
        HttpHeaders resHeader = new HttpHeaders();
        resHeader.add("Content-Type", "application/json;charset=UTF-8");


        return new ResponseEntity<>(result, resHeader, HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(value = "/register") //회원 가입
    public ResponseEntity<String> Register(@RequestBody Map<String,String> request)
    {

        UserVO userVO = new UserVO();
        userVO.setEmail(request.get("email"));
        userVO.setName(request.get("name"));
        userVO.setEncrypted_password(request.get("password"));
        userService.insertUser(userVO);

        HttpHeaders resHeader = new HttpHeaders();
        resHeader.add("Content-Type", "application/json;charset=UTF-8");


        return new ResponseEntity<>("삽입 성공", resHeader, HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(value = "/insertMission")      //미션 추가
    public ResponseEntity<String> InsertMission(@RequestBody Map<String,String > request)
    {
        MissionVO missionVO = new MissionVO();
        missionVO.setEmail(request.get("email"));
        missionVO.setChild_name(request.get("child_name"));
        missionVO.setMission_cont(request.get("mission_cont"));
        missionVO.setMission_time(Integer.parseInt(request.get("mission_time")));
        missionService.insertMission(missionVO);

        HttpHeaders resHeader = new HttpHeaders();
        resHeader.add("Content-Type", "application/json;charset=UTF-8");


        return new ResponseEntity<>("삽입 성공", resHeader, HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(value = "/getMission")      //미션 불러오기
    public ResponseEntity<String> GetMission(@RequestBody Map<String,String > request)
    {
        MissionVO missionVO = new MissionVO();
        missionVO.setEmail(request.get("email"));
        missionVO.setChild_name(request.get("child_name"));
        missionService.getMission(missionVO);

        HttpHeaders resHeader = new HttpHeaders();
        resHeader.add("Content-Type", "application/json;charset=UTF-8");


        return new ResponseEntity<>("삽입 성공", resHeader, HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(value = "/imageUpload")      //미션 이미지 업로드
    public ResponseEntity<String> ImageUpload(@RequestBody Map<String,String > request)
    {
        MissionVO missionVO = new MissionVO();
        missionVO.setEmail(request.get("email"));
        missionVO.setChild_name(request.get("child_name"));
        missionVO.setMission_image(request.get("mission_image"));
        missionService.uploadImage(missionVO);

        HttpHeaders resHeader = new HttpHeaders();
        resHeader.add("Content-Type", "application/json;charset=UTF-8");


        return new ResponseEntity<>("삽입 성공", resHeader, HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(value = "/imageDownload")      //미션 이미지 다운로드
    public ResponseEntity<String> ImageDownload(@RequestBody Map<String,String > request)
    {
        MissionVO missionVO = new MissionVO();
        missionVO.setEmail(request.get("email"));
        missionVO.setChild_name(request.get("child_name"));
        missionService.getImage(missionVO);

        String result = new Gson().toJson(missionService.getImage(missionVO));

        HttpHeaders resHeader = new HttpHeaders();
        resHeader.add("Content-Type", "application/json;charset=UTF-8");


        return new ResponseEntity<>(result, resHeader, HttpStatus.CREATED);
    }



    @ResponseBody
    @RequestMapping(value = "/getChildren" )  //자식 불러오기
    public ResponseEntity<String> getChildren(@RequestBody Map<String,String > request)
    {
        UserChildVO vo = new UserChildVO();
        vo.setEmail(request.get("email"));

        String result = new Gson().toJson(userChildService.getChildList(vo));
        HttpHeaders resHeader = new HttpHeaders();
        resHeader.add("Content-Type", "application/json;charset=UTF-8");


        return new ResponseEntity<>(result, resHeader, HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(value = "/insertChild") //자식 추가
    public ResponseEntity<String> InsertChild(@RequestBody Map<String,String > request)
    {
        UserChildVO userChildVO = new UserChildVO();
        userChildVO.setEmail(request.get("email"));
        userChildVO.setChild_name(request.get("child_name"));
        userChildVO.setChild_age(request.get("child_age"));
        userChildService.insertChild(userChildVO);

        HttpHeaders resHeader = new HttpHeaders();
        resHeader.add("Content-Type", "application/json;charset=UTF-8");


        return new ResponseEntity<>("삽입 성공", resHeader, HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(value = "/UpdateLocation") //자식 위치 갱신
    public ResponseEntity<String> UpdateLocation(@RequestBody Map<String,String > request)
    {
        UserChildVO userChildVO = new UserChildVO();
        userChildVO.setEmail(request.get("email"));
        userChildVO.setChild_name(request.get("child_name"));
        userChildVO.setLatitude(request.get("latitude"));
        userChildVO.setLongitude(request.get("longitude"));
        userChildService.UpdateLocation(userChildVO);

        HttpHeaders resHeader = new HttpHeaders();
        resHeader.add("Content-Type", "application/json;charset=UTF-8");


        return new ResponseEntity<>("삽입 성공", resHeader, HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(value = "/getLocation" )  //자식 위치 불러오기
    public ResponseEntity<String> getLocation(@RequestBody Map<String,String > request)
    {
        UserChildVO vo = new UserChildVO();
        vo.setEmail(request.get("email"));
        vo.setChild_name(request.get("child_name"));

        String result = new Gson().toJson(userChildService.getLocation(vo));
        HttpHeaders resHeader = new HttpHeaders();
        resHeader.add("Content-Type", "application/json;charset=UTF-8");


        return new ResponseEntity<>(result, resHeader, HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(value = "/UpdateDeviceKey") //자식 디바이스 키 갱신
    public ResponseEntity<String> UpdateDeviceKey(@RequestBody Map<String,String > request)
    {
        UserChildVO userChildVO = new UserChildVO();
        userChildVO.setEmail(request.get("email"));
        userChildVO.setChild_name(request.get("child_name"));
        userChildVO.setDevice_key(request.get("device_key"));
        userChildService.UpdateDeviceKey(userChildVO);

        HttpHeaders resHeader = new HttpHeaders();
        resHeader.add("Content-Type", "application/json;charset=UTF-8");


        return new ResponseEntity<>("삽입 성공", resHeader, HttpStatus.CREATED);
    }
}
