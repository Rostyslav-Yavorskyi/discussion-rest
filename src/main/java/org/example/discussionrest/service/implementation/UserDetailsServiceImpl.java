package org.example.discussionrest.service.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.discussionrest.dao.UserDao;
import org.example.discussionrest.entity.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDao userDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {
        return userDao.findByEmail(email).map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
