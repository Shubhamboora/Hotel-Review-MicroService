package com.shb.user.service.services.impl;

import com.shb.user.service.common.CommonUtils;
import com.shb.user.service.entity.Hotel;
import com.shb.user.service.entity.Rating;
import com.shb.user.service.entity.User;
import com.shb.user.service.exceptions.ResourceNotFoundException;
import com.shb.user.service.external.HotelService;
import com.shb.user.service.external.RatingService;
import com.shb.user.service.repositories.UserRepository;
import com.shb.user.service.services.UserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Logger logger;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RatingService ratingService;


    @Override
    public User saveUser(User user) {
        String randomId = UUID.randomUUID().toString();
        user.setUserId(randomId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {

        List<User> users = userRepository.findAll();


        List<User> collect = users.stream().map(user -> {

            //Fetch ratings given by user.
            List<Rating> allRatingsByuserId = ratingService.getAllRatingsByuserId(user.getUserId());

            //Fetch hotel by using ratings.
            List<Rating> ratingsWithHotel = allRatingsByuserId.stream().map(rating -> {
                rating.setHotel(hotelService.getHotel(rating.getHotelId()));
                return rating;
            }).collect(Collectors.toList());


            user.setRating(ratingsWithHotel);

            return user;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public User getUser(String userId) {
        //Fetch User Details
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found for given id: " + userId));

        //Fetching all ratings of user using rest template.
        Rating[] ratingResponse = restTemplate.getForObject("http://" + CommonUtils.RATING_SERVICE + CommonUtils.RATINGS_BY_USERID + userId, Rating[].class);
        logger.info("Response of rating microservice call: {}", ratingResponse);

        //Converting response to Arraylist.
        List<Rating> ratingsList = Arrays.asList(ratingResponse);


        //Fetching hotel information from hotel microservice.
        List<Rating> collect = ratingsList.stream().map(rating -> {
            ResponseEntity<Hotel> hotelData = restTemplate.getForEntity("http://"+CommonUtils.HOTEL_SERVICE + CommonUtils.HOTEL_BY_HOTELID + rating.getHotelId(), Hotel.class);
            rating.setHotel(hotelData.getBody());
            return rating;
        }).collect(Collectors.toList());

        //Set processed collection in user.
        user.setRating(collect);
        return user;
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
