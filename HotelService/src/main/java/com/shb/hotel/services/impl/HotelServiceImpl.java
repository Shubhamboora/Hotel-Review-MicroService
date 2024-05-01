package com.shb.hotel.services.impl;

import com.shb.hotel.entity.Hotel;
import com.shb.hotel.exception.ResourseNotFoundException;
import com.shb.hotel.repository.HotelRepository;
import com.shb.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;


    @Override
    public Hotel create(Hotel hotel) {
        String id = UUID.randomUUID().toString();
        hotel.setId(id);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotel(String id) {
        return hotelRepository.findById(id).orElseThrow(()-> new ResourseNotFoundException("Hotel not found by given Id: "+ id));
    }
}
