package com.clark.service;

import com.clark.pojo.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    User getUserById(int id);

    String addUser(User user, String password2);

    String updateUser(User user);

    int deleteUser(int id);

    User getUserByUserame(String username);

    public void uploadingAvatar(User user, MultipartFile file);

}
