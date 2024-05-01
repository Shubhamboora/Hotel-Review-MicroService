package com.shb.hotel.services;

import com.shb.hotel.entity.Hotel;

import java.util.List;

public interface HotelService {

    //create

    Hotel create(Hotel hotel);

    //getAll

    List<Hotel> getAll();

    //get Single

    Hotel getHotel(String id);
}
