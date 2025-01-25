package zeroone.developers.test.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zeroone.developers.test.entity.User;
import zeroone.developers.test.payload.UserDto;
import zeroone.developers.test.repository.UserRepository;
import zeroone.developers.test.service.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto registration(UserDto userDto) {
        if (userRepository.findByPhoneNumberAndVisibleTrue(
                userDto.getPhoneNumber()).isPresent()) return null;
        User user = new User();
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        userRepository.save(user);
        userDto.setId(user.getId());
        return userDto;
    }
}
