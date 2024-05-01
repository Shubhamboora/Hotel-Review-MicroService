package com.shb.hotel.controllers;

import com.shb.hotel.entity.Hotel;
import com.shb.hotel.services.impl.HotelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelServiceImpl hotelService;

    @PostMapping("/create")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        Hotel createdHotel = hotelService.create(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHotel);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String id){
        Hotel hotel = hotelService.getHotel(id);
        return ResponseEntity.status(HttpStatus.OK).body(hotel);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Hotel>> getAllHotel(){
        List<Hotel> hotelList = hotelService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(hotelList);
    }
}
