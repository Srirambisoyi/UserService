package com.Mcs.user.service.UserService.external.service;

import com.Mcs.user.service.UserService.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTELSERVICE")
public interface HotelService {
    @GetMapping("/api/{hotelId}")
    public Hotel getHotel(@PathVariable String hotelId);
}
