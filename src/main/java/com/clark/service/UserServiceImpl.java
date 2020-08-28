package com.clark.service;

import com.clark.mapper.UserMapper;
import com.clark.pojo.User;
import com.clark.util.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    //service调dao层
    @Autowired
    private UserMapper userMapper;

    @Value("${filepath}")
    private String filepath;

    @Override
    public List<User> getUsers() {
        return userMapper.getUsers();
    }

    @Override
    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }

    @Override
    public String addUser(User user, String password2) {
        User userByUserame = userMapper.getUserByUserame(user.getUsername());
        if (userByUserame != null) {
            return "This username already exist.";
        }
        if (!(user.getPassword().equals(password2))) {
            return "The passwords don't match.";
        }
        List<User> users = getUsers();
        for (User u : users) {
            if (u.getEmployeeid() == user.getEmployeeid()) {
                return "Your employee ID already exist.";
            }
            if (u.getEmail().equals(user.getEmail())) {
                return "This email already exist.";
            }
        }
        user.setRoleid(4);
        //1.生成随机盐
        String salt = SaltUtils.getSalt(8);
        //2.保存随机盐到数据库
        user.setSalt(salt);
        //3.明文密码进行MD5 + salt + hash散列
        Md5Hash md5Hash = new Md5Hash(user.getPassword(),salt,1024);
        user.setPassword(md5Hash.toHex());
        //System.out.println(user);
        userMapper.addUser(user);
        return "User added successfully.";
    }

    @Override
    public String updateUser(User user) {
        List<User> users = userMapper.getUsersExceptByID(user.getId());
        for (User u : users) {
            if (u.getEmail().equals(user.getEmail())) {
                return "This Email is used, please enter again.";
            }
        }
        userMapper.updateUser(user);
        return "User updated successfully.";
    }

    @Override
    public int deleteUser(int id) {
        return userMapper.deleteUser(id);
    }

    @Override
    public User getUserByUserame(String username) {
        return userMapper.getUserByUserame(username);
    }

    @Override
    public void uploadingAvatar(User user, MultipartFile file) {
        File targetFile = new File(filepath, "avatar-s-" + user.getId() + ".jpg");
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
