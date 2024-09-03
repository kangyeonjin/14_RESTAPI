package com.ohgiraffers.restapi.section03.valid;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/valid")
public class ValidTestController {

    private List<UserDTO> users;

    public ValidTestController(){
        users = new ArrayList<>();

        users.add(new UserDTO(1, "user01", "pass01", "너구리", LocalDate.now()));
        users.add(new UserDTO(2, "user02", "pass02", "코알라", LocalDate.now()));
        users.add(new UserDTO(3, "user03", "pass03", "곰", LocalDate.now()));
    }
    @PostMapping("/users")
    public ResponseEntity<?> registUser(@RequestBody UserDTO newUser){

        System.out.println("boby로 들어온 userDTO" + newUser);

        return ResponseEntity.created(URI.create("/valid/users/"+"userNo")).build();
    }

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
