package ru.omel.newpo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.omel.newpo.entity.Role;
import ru.omel.newpo.entity.UserEntity;
import ru.omel.newpo.repository.UserRepository;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MailSendService mailSendService;
    @Value("${hostname}")
    private String hostname;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(s);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return userEntity;
    }

    public boolean addUser(UserEntity user) {
        String username = user.getUsername();
        UserEntity userFromDb = userRepository.findByUsername(username);
        if (userFromDb != null) {
            return false;
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        sendMessage(user);
        return true;
    }

    public void updateProfile(UserEntity user, String password, String email) {
        String userEmail = user.getEmail();
        boolean isEmailChanged = (email != null && !email.equals(userEmail)) ||
                (userEmail != null && !userEmail.equals(email));
        if (isEmailChanged) {
            user.setEmail(email);
            if (!StringUtils.isEmpty(email)) {
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }
        if (!StringUtils.isEmpty(password)) {
            user.setPassword(password);
        }
        userRepository.save(user);
        /*
        if (isEmailChanged) {
            sendMessage(user);
        }

         */
    }

    private void sendMessage(UserEntity user) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Здравствуйте, %s! \n" +
                            "Добро пожаловать в Личный кабинет АО Омскэлектро.\n" +
                            "Пожалуйста перейдите по ссылке: http://%s/activate/%s\n" +
                            "для активации вашей регистрации.",
                    user.getFirstname() + user.getLastname(),
                    hostname,
                    user.getActivationCode()
            );

            mailSendService.send(user.getEmail(), "Activation code", message);
        }
    }


    public boolean activateUser(String code) {
        UserEntity user = userRepository.findByActivationCode(code);

        if (user == null) {
            return false;
        }

        user.setActivationCode(null);
        user.setActive(true);

        userRepository.save(user);

        return true;
    }
}
