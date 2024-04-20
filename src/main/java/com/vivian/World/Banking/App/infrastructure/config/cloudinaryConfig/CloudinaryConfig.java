package com.vivian.World.Banking.App.infrastructure.config.cloudinaryConfig;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    private final String CLOUD_NAME = "dkmn6ovfb";

    private final String API_KEY = "394522375623541";

    private final String API_SECRET = "S7UoQBMQ1mPJiaPce_GnuoGs1U0";

    @Bean
    public Cloudinary cloudinary(){
        Map<String, String> config = new HashMap<>();

        config.put("cloud_name", CLOUD_NAME);
        config.put("api_key", API_KEY);
        config.put("api_secret", API_SECRET);

        return new Cloudinary(config);
    }
}
