package com.ohgiraffers.restapi.section03.valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.attribute.UserPrincipalNotFoundException;

@RestController
@RequestMapping("/valid")
public class ValidTestController {

    //에러 상황을 만들어서 에러를 처리할수있는 컨트롤러를 따로 만들것

    @GetMapping("/users/{userNo}")
    public ResponseEntity<?> findUserByNo() throws UserNotFoundException{

        //항상 userNotFoundException을 던지게함
        boolean check = true;
        if(check){
            throw new UserNotFoundException("회원정보를 찾을 수없습니다");
        }
        return ResponseEntity.ok().build();
    }
}
