package upc.edu.LoggyAPI.login.service;

import upc.edu.LoggyAPI.login.model.LoginRequest;

public interface LoginService {
    Boolean login(LoginRequest loginRequest);
}
