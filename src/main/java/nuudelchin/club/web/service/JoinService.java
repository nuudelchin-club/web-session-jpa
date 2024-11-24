package nuudelchin.club.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import nuudelchin.club.web.dto.JoinDTO;
import nuudelchin.club.web.entity.UserEntity;
import nuudelchin.club.web.repository.UserRepository;

@Service
public class JoinService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public void joinProc(JoinDTO dto) {


        boolean isExist = userRepository.existsByUsername(dto.getUsername());
        if (isExist) {
            return;
        }

        UserEntity data = new UserEntity();

        data.setUsername(dto.getUsername());
        data.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        data.setRole("ROLE_ADMIN");

        userRepository.save(data);
    }
}