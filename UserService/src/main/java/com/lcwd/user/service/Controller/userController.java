package com.lcwd.user.service.Controller;

import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class userController {

    @Autowired
    private UserServices userServices;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1 = userServices.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);

    }

    @GetMapping("/get_user/{userId}")
    public  ResponseEntity<User> getSingleUser(@PathVariable String userId){
        User user = userServices.getUser(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public  ResponseEntity<List<User>> getAlluser(){
        List<User> alluser = userServices.getAllUser();
        return ResponseEntity.ok(alluser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId){
        userServices.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable String userId , @RequestBody User userDetails){
        User updateUser = userServices.updateUser(userId , userDetails);
        return  new ResponseEntity<>(updateUser , HttpStatus.OK);
    }


}

