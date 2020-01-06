package com.virtualpairprogrammers.roombooking.data;

import com.virtualpairprogrammers.roombooking.model.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
