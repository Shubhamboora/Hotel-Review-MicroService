package com.shb.rating.RatingService.services;

import com.shb.rating.RatingService.entity.Rating;

import java.util.List;


public interface RatingService {

    //create

    Rating create(Rating rating);


    //get all ratings
    List<Rating> getRatings();


    //get all ratings by user Id
    List<Rating> getRatingByUserId(String id);


    //get all by hotel
    List<Rating> getRatingByHotelId(String hotelId);
}
