package zeroone.developers.test.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import zeroone.developers.test.entity.User;
import zeroone.developers.test.entity.enums.Role;
import zeroone.developers.test.entity.enums.Status;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private String phoneNumber;
    private String password;
    private Role role;
    private Status status;

    public CustomUserDetails(User user) {
        this.phoneNumber = user.getPhoneNumber();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.status = user.getStatus();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(role.name()));
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return phoneNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status.equals(Status.ACTIVE);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
