package upc.edu.LoggyAPI.login.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.LoggyAPI.login.model.LoginRequest;
import upc.edu.LoggyAPI.login.service.LoginService;
import upc.edu.LoggyAPI.user.repository.UserRepository;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public Boolean login(LoginRequest loginRequest) {
        return userRepository.existsByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
    }
}
