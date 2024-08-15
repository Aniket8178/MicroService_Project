package com.lcwd.HotelService.Controller;


import com.lcwd.HotelService.Service.HotelService;
import com.lcwd.HotelService.entities.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping("/create")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getAll(){
        List<Hotel> hotels = hotelService.getAll();
        return ResponseEntity.ok(hotels);
    }

    @GetMapping("/getsingle/{id}")
    public ResponseEntity<Hotel> getsingle(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getsingle(id));
    }
}
