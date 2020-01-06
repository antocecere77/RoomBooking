package com.virtualpairprogrammers.roombooking.control;

import com.virtualpairprogrammers.roombooking.data.BookingRepository;
import com.virtualpairprogrammers.roombooking.data.RoomRepository;
import com.virtualpairprogrammers.roombooking.data.UserRepository;
import com.virtualpairprogrammers.roombooking.model.entities.Booking;
import com.virtualpairprogrammers.roombooking.model.BookingCommand;
import com.virtualpairprogrammers.roombooking.model.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    UserRepository userRepository;

    private Map<String,Object> getBookingFormModel(Booking booking) {
        Map<String,Object> model = new HashMap<>();
        model.put("booking",new BookingCommand(booking));
        model.put("rooms", roomRepository.findAll());
        model.put("layouts", Layout.values());
        model.put("users", userRepository.findAll());
        return model;
    }

    @RequestMapping("/edit")
    public ModelAndView editBooking(@RequestParam Long id) {
        return new ModelAndView("bookings/edit", getBookingFormModel(bookingRepository.findById(id).get()));
    }

    @RequestMapping("/new")
    public ModelAndView newBooking() {
        return new ModelAndView("bookings/edit", getBookingFormModel(new Booking()));
    }

    @PostMapping("/save")
    public String save(BookingCommand booking) {
        bookingRepository.save(booking.toBooking());
        return "redirect:/";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam Long id) {
        bookingRepository.deleteById(id);
        return "redirect:/";
    }
}
