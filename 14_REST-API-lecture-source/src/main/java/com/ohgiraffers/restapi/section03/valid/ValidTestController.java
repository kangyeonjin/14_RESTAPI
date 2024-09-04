package com.ohgiraffers.restapi.section03.valid;

import com.ohgiraffers.restapi.section02.responsentity.ResponseMessage;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<?> findUserByNo(@PathVariable int userNo) throws UserNotFoundException{

//        //항상 userNotFoundException을 던지게함
//        boolean check = true;
//        if(check){
//            throw new UserNotFoundException("회원정보를 찾을 수없습니다");
//        }
//        return ResponseEntity.ok().build();

        //header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        //BODY
        List<UserDTO> foundUserList = users.stream().filter(user -> user.getNo() == userNo).toList();

        UserDTO foundUser = null;
        if(foundUserList.size() > 0){
            //userNo이 일치하는 회원이 있으면
            foundUser = foundUserList.get(0);
        }else {
            throw new UserNotFoundException("회원 정보를 찾을수 없습니다.");
        }

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("user", foundUser);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(
                        200, "조회성공",responseMap
                ));
    }


}
