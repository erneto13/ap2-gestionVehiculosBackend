package com.erneto13.sgfa_backend.repository;

import com.erneto13.sgfa_backend.model.UserModel;

public interface IUserRepository {
    public UserModel findByEmail(String email);
}
