package com.erneto13.sgfa_backend.repository;

import com.erneto13.sgfa_backend.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository implements IUserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserModel findByEmail(String email) {
        String SQL = "select * from users where email = ?";
        return jdbcTemplate.queryForObject(SQL, new Object[]{email},
                new BeanPropertyRowMapper<>(UserModel.class));
    }
}