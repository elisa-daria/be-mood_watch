package elisa_daria.be_mood_watch.controllers;

import elisa_daria.be_mood_watch.entities.User;
import elisa_daria.be_mood_watch.exceptions.BadRequestEx;
import elisa_daria.be_mood_watch.payloads.error.ErrorRespDTO;
import elisa_daria.be_mood_watch.payloads.user.NewUserDTO;
import elisa_daria.be_mood_watch.payloads.user.NewUserRespDTO;
import elisa_daria.be_mood_watch.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    //http://localhost:3002/users
    @GetMapping("")
    public Page<User> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size,
            @RequestParam(defaultValue = "id") String sort_by
    ) {
        return this.userService.getUsers(page, size, sort_by);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserRespDTO postUser(@RequestBody @Validated NewUserDTO payload, BindingResult validation) throws Exception {
        if (validation.hasErrors()) {
            throw new BadRequestEx(validation.getAllErrors());
        }
        User newUser = this.userService.saveUser(payload);
        return new NewUserRespDTO(newUser.getId());
    }


    @GetMapping("{id}")
    public User findUserById(@PathVariable long id) {
        return this.userService.findById(id);
    }


    @PutMapping("{id}")
    public User findUpdate(@PathVariable long id, @RequestBody User payload) {
        return this.userService.updateUser(id, payload);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser (@PathVariable long id) {this.userService.deleteUser(id);}

    @PatchMapping("{id}/avatar")
    public String uploadAvatar(@RequestParam ("img")MultipartFile img,@PathVariable long id){
        try{
            return this.userService.uploadProfilePic(id,img);
        }catch (IOException e){
            throw new RuntimeException();

        }
    }
}
