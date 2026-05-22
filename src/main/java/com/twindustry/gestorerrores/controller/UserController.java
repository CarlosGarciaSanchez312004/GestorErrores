package com.twindustry.gestorerrores.controller;

import com.twindustry.gestorerrores.model.User;
import com.twindustry.gestorerrores.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public User createUser(@RequestBody User user){
    return userService.createUser(
            user.getNombre(),
            user.getApellido(),
            user.getEmail(),
            user.getPicture(),
            user.getPassword(),
            user.getRole()
    );
    }

    @PutMapping("/{id}/enable")
    public User enableUser(@PathVariable Long id) {
        return userService.enableUser(id);

    }
    @PutMapping("/{id}/disable")
    public User disable (@PathVariable Long id) {
        return userService.disableUser(id);
    }
    @GetMapping("/findAll")
    public List<User> findAllUser(){
        return userService.findAll();
    }
    @GetMapping("/find/{id}")
    public User getUser(@PathVariable Long id){
        return userService.findById(id);
    }
    @GetMapping("/search")
    public List<User> getUserByName(@RequestParam String nombre){
        return userService.findByName(nombre);
    }
}
