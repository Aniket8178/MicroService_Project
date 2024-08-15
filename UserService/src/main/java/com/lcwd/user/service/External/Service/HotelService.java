package com.lcwd.user.service.External.Service;

import com.lcwd.user.service.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTELSERVICE")
public interface HotelService {

    @GetMapping("/hotels/getsingle/{id}")
    Hotel getHotel(@PathVariable("id") String hotelId);
}
