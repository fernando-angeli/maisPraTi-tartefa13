package com.crud_user.api_user.service;

import com.crud_user.api_user.dto.UserDto;
import com.crud_user.api_user.dto.UserInsertDto;
import com.crud_user.api_user.model.User;
import com.crud_user.api_user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public List<UserDto> getAllUsers(Pageable pageable){
        return userRepository
                .findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(Long id){
        Optional<User> user = userRepository.findById(id);
        return user.map(this::convertToDto).orElse(null);
    }

    public UserDto insert(UserInsertDto userDto){
        User user = new User();
        user = convertToEntity(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
        return convertToDto(user);
    }

    public UserDto updateUser(Long id, UserDto userDto){
        Optional<User> userModelOptional = userRepository.findById(id);
        if(userModelOptional.isPresent()){
            User userUpdated = userModelOptional.get();
            userUpdated.setUsername(userDto.getUsername());
            userUpdated.setEmail(userDto.getEmail());
            updateRoles(userDto.getRoles(), userUpdated);
            userRepository.save(userUpdated);
            return convertToDto(userUpdated);
        }
        return null;
    }

    private void updateRoles(List<String> roles, User userUpdateRoles) {
       userUpdateRoles.getRoles().clear();
        roles.forEach(role -> userUpdateRoles.getRoles().add(role));
    }

    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

    private UserDto convertToDto(User userModel){
        UserDto userDto = new UserDto();
        userDto.setId(userModel.getId());
        userDto.setUsername(userModel.getUsername());
        userDto.setEmail(userModel.getEmail());
        userDto.setRoles(userModel.getRoles());
        return userDto;
    }

    private User convertToEntity(UserDto userDto){
        User userModel = new User();
        userModel.setUsername(userDto.getUsername());
        userModel.setEmail(userDto.getEmail());
        userModel.setRoles(userDto.getRoles());
        return userModel;
    }
}
