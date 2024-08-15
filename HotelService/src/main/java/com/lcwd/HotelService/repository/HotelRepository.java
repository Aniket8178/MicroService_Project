package com.lcwd.HotelService.repository;

import com.lcwd.HotelService.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel , String> {
}
