package com.BT11.Service;

import com.BT11.DTO.UserInfoUserDetails;
import com.BT11.Entity.UserInfo;
import com.BT11.Repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {
    @Autowired
    private UserInfoRepository userInfoRepository;
    public UserInfoService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("=== DEBUG: Trying to find user: " + username);
        UserInfo userInfo = userInfoRepository.findByName(username);

        if (userInfo == null) {
            System.out.println("=== DEBUG: User NOT FOUND!");
            throw new UsernameNotFoundException("User not found: " + username);
        }

        System.out.println("=== DEBUG: User found!");
        System.out.println("=== Name: " + userInfo.getName());
        System.out.println("=== Email: " + userInfo.getEmail());
        System.out.println("=== Password (encoded): " + userInfo.getPassword());
        System.out.println("=== Roles: " + userInfo.getRoles());

        // TEST PASSWORD MATCHING
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean matches = encoder.matches("123", userInfo.getPassword());
        System.out.println("=== Password '123' matches: " + matches);

        return new UserInfoUserDetails(userInfo);
    }
}
