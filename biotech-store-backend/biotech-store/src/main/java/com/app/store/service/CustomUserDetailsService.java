package com.app.store.service;

import com.app.store.entity.User;
import com.app.store.repository.IUserRepository;
import com.app.store.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service // or @Component also works!
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
    // dep : user repository : based upon spring data JPA
    @Autowired
    private IUserRepository userRepo;

    private static final Pattern PATTERN = Pattern.compile(Constants.EMAIL_REGEX);

    public boolean isValidEmail(String email) {
        return email != null && PATTERN.matcher(email).matches();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("in load by user nm " + email);

        User user = userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Invalid Email ID "));
        System.out.println(user.getUserName());
        System.out.println("lifted user dtls from db " + user);
        return new CustomUserDetails(user);
    }


    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        String userName;
        Object principal = authentication.getPrincipal();
        userName = (principal instanceof UserDetails) ? ((UserDetails) principal).getUsername() : principal.toString();
        return userRepo.findByEmail(userName).get().getUserName();
    }
}
