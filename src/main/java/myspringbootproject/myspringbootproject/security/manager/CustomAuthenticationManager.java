package myspringbootproject.myspringbootproject.security.manager;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import myspringbootproject.myspringbootproject.entity.Consumer;
import myspringbootproject.myspringbootproject.service.ConsumerService;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

    private ConsumerService consumerService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Consumer consumer = consumerService.getConsumer(authentication.getName());
        if (!bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), consumer.getPassword())) {
            throw new BadCredentialsException("You provided an incorrect password.");
        }
        return new UsernamePasswordAuthenticationToken(authentication.getName(), consumer.getPassword());
    }
}
