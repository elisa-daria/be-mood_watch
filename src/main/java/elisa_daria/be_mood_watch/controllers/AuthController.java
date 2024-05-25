package elisa_daria.be_mood_watch.controllers;

import elisa_daria.be_mood_watch.entities.User;
import elisa_daria.be_mood_watch.exceptions.BadRequestEx;
import elisa_daria.be_mood_watch.payloads.user.LoginUserDTO;
import elisa_daria.be_mood_watch.payloads.user.LoginUserRespDTO;
import elisa_daria.be_mood_watch.payloads.user.NewUserDTO;
import elisa_daria.be_mood_watch.payloads.user.NewUserRespDTO;
import elisa_daria.be_mood_watch.services.AuthService;
import elisa_daria.be_mood_watch.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")

public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public LoginUserRespDTO login(@RequestBody LoginUserDTO payload){
        return new LoginUserRespDTO(authService.authenticatingUAndGenerateT(payload));
    }

   @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
   public NewUserRespDTO postUser(@RequestBody @Validated NewUserDTO payload, BindingResult validation) throws Exception {
       if (validation.hasErrors()) {
           throw new BadRequestEx(validation.getAllErrors());
       }
       User newUser =userService.saveUser(payload);
       return new NewUserRespDTO(newUser.getId());
   }



}
