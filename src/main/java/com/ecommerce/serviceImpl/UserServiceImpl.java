package com.ecommerce.serviceImpl;

import com.ecommerce.entity.User;
import com.ecommerce.exceptions.ResourceNotFoundException;
import com.ecommerce.repositary.UserRepositary;
import com.ecommerce.service.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@NoArgsConstructor
public class UserServiceImpl implements UserService {


    private UserRepositary userRepositary;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImpl(UserRepositary userRepositary,PasswordEncoder passwordEncoder){
        this.userRepositary = userRepositary;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedt(new Date());
        return this.userRepositary.save(user);
    }

    @Override
    public User getOneUser(Long userId)
    {
        return this.userRepositary.findById(userId).orElseThrow(()->new ResourceNotFoundException("user is not found with id "+userId));
    }

    @Override
    public List<User> getAllUser() {
        return this.userRepositary.findAll();
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepositary.findByUsername(username);
    }

    @Override
    public User updateUser(User user, Long id) {
        User oldUser = this.getOneUser(id);
        user.setUsedId(oldUser.getUsedId());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userRepositary.save(user);
    }

    @Override
    public String deleteUser(Long id) {
        try{
         this.userRepositary.deleteById(id);
         return "user is delete successfully.....";
        }catch(Exception e) {
            e.printStackTrace();
            return "User is not deleted due to some error " + e.getMessage();
        }
    }

}
