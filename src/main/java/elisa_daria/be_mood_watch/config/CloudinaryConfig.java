package elisa_daria.be_mood_watch.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary(
            @Value("${cloudinary.name}") String name,
            @Value("${cloudinary.secret}") String secret,
            @Value("${cloudinary.key}") String key) {

        Map<String, String> configuration = new HashMap<>();
        configuration.put("cloud_name", name);
        configuration.put("api_secret", secret);
        configuration.put("api_key", key);
        return new Cloudinary(configuration);
    }
}
