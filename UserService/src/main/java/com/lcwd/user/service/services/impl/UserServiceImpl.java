package com.lcwd.user.service.services.impl;

import com.lcwd.user.service.Exceptions.ResourceNotFoundException;
import com.lcwd.user.service.External.Service.HotelService;
import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.services.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;



    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        //http://localhost:8083/ratings/getByUserid/2def49aa-c5ec-4855-89b4-6c99cafc2fb1
        User user =  userRepository.findById(userId).orElseThrow( ()-> new ResourceNotFoundException("User with the given id is not found on the server -- " + userId));

        Rating[] raatingofuser =   restTemplate.getForObject("http://RATINGSERVICE/ratings/getByUserid/"+ user.getUserId() , Rating[].class);
        logger.info("{}" , raatingofuser);
        List<Rating> ratings = Arrays.stream(raatingofuser).toList();

        //http://localhost:8082/hotels/getsingle/8dad425d-ad49-427f-a70c-563bef3e512c
        List<Rating> ratingList = ratings.stream().map(rating -> {
           // ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/getsingle/"+rating.getHotelId() , Hotel.class);
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
          //  logger.info("response status code: {} ",forEntity.getStatusCode());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingList);
        return user;
    }

    @Override
    public void  deleteUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user with this userId is not found on the server:--  " + userId));
        userRepository.deleteById(userId);
        //return user;

    }

    @Override
    public User updateUser(String userId , User userDetails) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user with this userId is not found on the server:--  " + userId));

        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setMobileNo((userDetails.getMobileNo()));
        user.setAbout(userDetails.getAbout());
        return userRepository.save(user);
    }
}
