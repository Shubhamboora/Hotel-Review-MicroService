package com.shb.rating.RatingService.repository;

import com.shb.rating.RatingService.entity.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends MongoRepository<Rating, String> {

    //custom finder mathods
    List<Rating> findRatingsByUserId(String userId);

    List<Rating> findRatingsByHotelId(String hotelId);
}
