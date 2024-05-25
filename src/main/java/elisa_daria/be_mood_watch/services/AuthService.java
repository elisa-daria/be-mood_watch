package elisa_daria.be_mood_watch.services;


import elisa_daria.be_mood_watch.entities.User;
import elisa_daria.be_mood_watch.exceptions.UnAuthorizedEx;
import elisa_daria.be_mood_watch.payloads.user.LoginUserDTO;
import elisa_daria.be_mood_watch.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService userService;
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String authenticatingUAndGenerateT(LoginUserDTO payload){
        User user = userService.findByEmail(payload.email());
        if(passwordEncoder.matches(payload.password(), user.getPassword())){
            return jwtTools.createToken(user);
        }else {
            throw new UnAuthorizedEx("Invalid credentials, try to log in again!");
        }
    }

}
