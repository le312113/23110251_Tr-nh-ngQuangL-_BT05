package com.BT12.Service;

import com.BT12.Entity.UserInfo;
import com.BT12.Repository.UserInfoRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;  // Đảm bảo import Collectors

@Service
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserInfoRepository userInfoRepository;

    public CustomUserDetailsService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("🔍 Attempting to load user: " + email);

        UserInfo userInfo = userInfoRepository.findByEmail(email)
                .orElseThrow(() -> {
                    System.out.println("❌ User not found: " + email);
                    return new UsernameNotFoundException("User not found with email: " + email);
                });

        System.out.println("✅ User found: " + userInfo.getEmail());
        System.out.println("📋 Roles: " + userInfo.getRoles());
        System.out.println("🔑 Password hash: " + userInfo.getPassword().substring(0, 20) + "...");

        UserDetails user = new User(
                userInfo.getEmail(),
                userInfo.getPassword(),
                java.util.Arrays.stream(userInfo.getRoles().split(","))
                        .map(role -> {
                            System.out.println("🎭 Adding role: ROLE_" + role);
                            return new org.springframework.security.core.authority.SimpleGrantedAuthority(role);
                        })
                        .collect(Collectors.toList())
        );

        System.out.println("✅ UserDetails created successfully");
        return user;
    }
}
