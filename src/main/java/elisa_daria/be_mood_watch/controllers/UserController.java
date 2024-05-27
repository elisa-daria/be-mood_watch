package elisa_daria.be_mood_watch.controllers;
import elisa_daria.be_mood_watch.entities.User;
import elisa_daria.be_mood_watch.payloads.user.NewUserRespDTO;
import elisa_daria.be_mood_watch.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<User> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size,
            @RequestParam(defaultValue = "id") String sort_by
    ) {
        return this.userService.getUsers(page, size, sort_by);
    }

    @GetMapping("/me")
    public User getMeProfile(@AuthenticationPrincipal User currentUser)
    {return currentUser;}

    @PatchMapping("me/avatar")
    public NewUserRespDTO uploadAvatar(@AuthenticationPrincipal User currentU, @RequestParam ("img")MultipartFile img){
        try{
            String updatedAvatar=this.userService.uploadProfilePic(currentU.getId(), img);
            return new NewUserRespDTO(currentU.getId());
        }catch (IOException e){
            throw new RuntimeException();

        }
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
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser (@PathVariable long id) {this.userService.deleteUser(id);}


}
