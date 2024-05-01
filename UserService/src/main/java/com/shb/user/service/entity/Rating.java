package com.shb.user.service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    private String RatingId;
    private String userId;
    private String hotelId;
    private int rating;
    private String feedback;
    private Hotel hotel = new Hotel();
}
