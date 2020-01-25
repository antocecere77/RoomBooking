package com.virtualpairprogrammers.roombooking.rest;

import com.virtualpairprogrammers.roombooking.data.BookingRepository;
import com.virtualpairprogrammers.roombooking.model.entities.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class RestBookingController {

    @Autowired
    BookingRepository bookingRepository;

    @GetMapping("/{date}")
    public List<Booking> getBookingByDate(@PathVariable("date") String date) {
        Date sqlDate = Date.valueOf(date);
        return bookingRepository.findAllByDate(sqlDate);
    }
}
