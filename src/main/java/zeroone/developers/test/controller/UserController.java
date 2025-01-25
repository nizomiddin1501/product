package zeroone.developers.test.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zeroone.developers.test.payload.UserDto;
import zeroone.developers.test.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        UserDto result = userService.registration(userDto);
        return ResponseEntity.ok(result);
    }

}
