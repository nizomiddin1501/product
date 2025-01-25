package zeroone.developers.test.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import zeroone.developers.test.entity.User;
import zeroone.developers.test.repository.UserRepository;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optional = userRepository.findByPhoneNumber(username);
        if (optional.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        User user = optional.get();
        CustomUserDetails userDetails = new CustomUserDetails(user);

        return userDetails;
    }
}
