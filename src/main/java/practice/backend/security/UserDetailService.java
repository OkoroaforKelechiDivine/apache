package practice.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import practice.backend.model.roleType.UserType;
import practice.backend.model.user.BlogUser;
import practice.backend.repository.user.BlogUserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    BlogUserRepository blogUserRepository;

    @Override
    public UserDetails loadUserByUsername(String anyString) throws UsernameNotFoundException {
        BlogUser user = blogUserRepository.findByEmail(anyString);
        if (user == null) {
            throw new UsernameNotFoundException("User does not exist.");
        }
        return new User(user.getEmail(), user.getPassword(), getAuthorities(user.getUserType()));
    }

    private Collection<GrantedAuthority> getGrantedAuthorities(UserType role) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(String.valueOf(role)));
        return grantedAuthorities;
    }

    public Collection<? extends GrantedAuthority> getAuthorities(UserType role){
        return getGrantedAuthorities(role);
    }
}