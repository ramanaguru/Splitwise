package com.example.Splitwise.Services;

import com.example.Splitwise.exceptions.UserAlreadyExistsException;
import com.example.Splitwise.Repository.UserRepository;
import com.example.Splitwise.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(String name, String password, String email) throws UserAlreadyExistsException{
       Optional<User> userOptional = userRepository.findByEmail(email); // check if the user already exists

        if(userOptional.isPresent())throw new UserAlreadyExistsException(); // throw exception if user already exists

        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setEmail(email);

        return userRepository.save(user); // save the user

    }
}
