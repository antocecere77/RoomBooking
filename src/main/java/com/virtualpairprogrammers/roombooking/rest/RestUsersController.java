package com.virtualpairprogrammers.roombooking.rest;

import com.virtualpairprogrammers.roombooking.data.UserRepository;
import com.virtualpairprogrammers.roombooking.model.AngularUser;
import com.virtualpairprogrammers.roombooking.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class RestUsersController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<AngularUser> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new AngularUser(user))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AngularUser getUser(@PathVariable("id") Long id) {
        return new AngularUser(userRepository.findById(id).get());
    }

    @PutMapping
    public AngularUser updateUser(@RequestBody AngularUser updatedUser) {
        User originalUser = userRepository.findById(updatedUser.getId()).get();
        originalUser.setName(updatedUser.getName());
        return new AngularUser(userRepository.save(originalUser));
    }

    @PostMapping
    public AngularUser newUser(@RequestBody User user) {
        return new AngularUser(userRepository.save(user));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userRepository.deleteById(id);
    }

    @GetMapping("/resetPassword/{id}")
    public void resetPassword(@PathVariable("id") Long id) {
        User user = userRepository.findById(id).get();
        user.setPassword("secret");
        userRepository.save(user);
    }
}
