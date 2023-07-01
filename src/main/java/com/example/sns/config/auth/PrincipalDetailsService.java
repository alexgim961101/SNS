package com.example.sns.config.auth;


import com.example.sns.domain.user.User;
import com.example.sns.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    // return 성공 시 자동으로 세션을 만들어줌
    // 패스워드는 시큐리티가 알아서 체킹해줌
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User userEntity = userRepository.findByUsername(username);

        if(userEntity == null) return null;
        else return new PrincipalDetails(userEntity);
    }
}
