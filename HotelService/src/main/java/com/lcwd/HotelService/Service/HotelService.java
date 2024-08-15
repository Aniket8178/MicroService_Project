package com.lcwd.HotelService.Service;

import com.lcwd.HotelService.entities.Hotel;

import java.util.List;

public interface HotelService {

    Hotel create(Hotel hotel);
    List<Hotel> getAll();
    Hotel getsingle(String id);
}
