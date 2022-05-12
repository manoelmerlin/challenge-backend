package com.backendchallenge.challenge.services;

import com.backendchallenge.challenge.dto.UserDto;
import com.backendchallenge.challenge.model.User;
import com.backendchallenge.challenge.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backendchallenge.challenge.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(UserDto userDto) {
        User user = convertUserDtoToUser(userDto);
        userRepository.save(user);

        return user;
    }

    public User getUserById(int id) {
        Optional<User> user = userRepository.findById(id);

        return user.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found: " + id + ", Type: " + User.class.getName()
        ));
    }

    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new ObjectNotFoundException("User does not exists");
        }

        return user;
    }

    public User convertUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());

        return user;
    }
}
