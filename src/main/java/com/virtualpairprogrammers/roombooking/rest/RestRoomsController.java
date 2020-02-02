package com.virtualpairprogrammers.roombooking.rest;

import com.virtualpairprogrammers.roombooking.data.RoomRepository;
import com.virtualpairprogrammers.roombooking.model.entities.Room;
import netscape.javascript.JSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RestRoomsController {

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping
    public List<Room> getAllRooms(@CookieValue(value="token", defaultValue = "empty") String token) {
        System.out.println("token received by cookie is " + token);
        return roomRepository.findAll();
    }

    @GetMapping("/{id}")
    public Room getRoom(@PathVariable("id") long id) {
        return roomRepository.findById(id).get();
    }

    @PostMapping()
    public Room newRoom(@RequestBody Room room) {
        return roomRepository.save(room);
    }

    @PutMapping()
    public Room updateRoom(@RequestBody Room updatedRoom) {
        Room originalRoom = roomRepository.findById(updatedRoom.getId()).get();
        originalRoom.setName(updatedRoom.getName());
        originalRoom.setLocation(updatedRoom.getLocation());
        originalRoom.setCapacities(updatedRoom.getCapacities());
        return roomRepository.save(originalRoom);
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable("id") Long id) {
        roomRepository.deleteById(id);
    }
}
