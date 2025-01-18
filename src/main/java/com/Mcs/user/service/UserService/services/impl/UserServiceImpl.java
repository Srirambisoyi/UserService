package com.Mcs.user.service.UserService.services.impl;

import com.Mcs.user.service.UserService.ecxeption.ResourceNotFoundException;
import com.Mcs.user.service.UserService.entities.Hotel;
import com.Mcs.user.service.UserService.entities.Rating;
import com.Mcs.user.service.UserService.entities.User;
import com.Mcs.user.service.UserService.external.service.HotelService;
import com.Mcs.user.service.UserService.repository.UserRepository;
import com.Mcs.user.service.UserService.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HotelService hotelService;

    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

    //create user
    @Override
    public User saveUser(User user) {
        //generate  unique userid
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }
//get all user
    @Override
    public List<User> getAllUser() {
        //implement RATING SERVICE CALL: USING REST TEMPLATE
        return userRepository.findAll();
    }

    //get Single User By id

    @Override
    public User getUser(String userId) {
        // Fetch user from the repository
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId));

        // Fetch ratings from the Rating service
        ResponseEntity<Rating[]> responseEntity = restTemplate.getForEntity(
                "http://RATINGSERVICE/rating/user/" + user.getUserId(), Rating[].class);
        Rating[] ratingsArray = responseEntity.getBody();

       List<Rating> ratingList=Arrays.stream(ratingsArray).toList();
      List<Rating> ratings=ratingList.stream().map(rating -> {
           //api call
//          ResponseEntity<Hotel> hotelResponseEntity=restTemplate.getForEntity("http://HOTELSERVICE/api/"+rating.getHotelId(), Hotel.class);
         Hotel hotel=hotelService.getHotel(rating.getHotelId());
         rating.setHotel(hotel);
         return rating;
       }).collect(Collectors.toList());

      user.setRatings(ratings);

        return user;
    }


}
