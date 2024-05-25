package elisa_daria.be_mood_watch.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
//import elisa_daria.be_mood_watch.config.MailgunConfig;
import elisa_daria.be_mood_watch.entities.User;
import elisa_daria.be_mood_watch.exceptions.BadRequestEx;
import elisa_daria.be_mood_watch.exceptions.NotFoundEx;
import elisa_daria.be_mood_watch.payloads.user.NewUserDTO;
import elisa_daria.be_mood_watch.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private Cloudinary cloudinary;

//    @Autowired
//    private MailgunConfig emailSender;

    public Page<User> getUsers(int page, int size, String sort_by) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort_by));
        return this.userDAO.findAll(pageable);
    }

    public User saveUser(NewUserDTO payload) {
        this.userDAO.findByEmail(payload.email()).ifPresent((
                user -> {
                    throw new BadRequestEx("Email address is already in use.");
                }
        ));
        User newUser = new User(payload.name(), payload.surname(), payload.email(), payload.password(), payload.username(), "https://ui-avatars.com/api/?name=" + payload.name() + "+" + payload.surname());
//        emailSender.sendRegistrationEmail(newUser);
        return userDAO.save(newUser);
    }

    public User findById(long userId) {
        return this.userDAO.findById(userId).orElseThrow(() -> new NotFoundEx(userId));
    }


    public User updateUser(long id, User updatedUser) {
        User found = userDAO.findById(id).orElseThrow(() -> new NotFoundEx(id));

        found.setName( updatedUser.getName() == null ? found.getName() : updatedUser.getName());
        found.setSurname(updatedUser.getSurname() == null? found.getSurname() : updatedUser.getSurname());
        found.setEmail(updatedUser.getEmail() == null ? found.getEmail():updatedUser.getEmail());
        found.setPassword(updatedUser.getPassword() == null ? found.getPassword() :updatedUser.getPassword());
        found.setUsername(updatedUser.getUsername() == null? found.getUsername() : updatedUser.getUsername());
        found.setAvatarURL(updatedUser.getAvatarURL() == null? found.getAvatarURL() : updatedUser.getAvatarURL());
        return userDAO.save(found);
    }

    public String uploadProfilePic(long id, MultipartFile img) throws IOException {
        User found = this.findById(id);
        String avatarURL = (String) cloudinary.uploader().upload(img.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setAvatarURL(avatarURL);
        userDAO.save(found);
        return avatarURL;
    }

    public void deleteUser(long id) {
        User found = this.findById(id);
        userDAO.delete(found);
    }
}
