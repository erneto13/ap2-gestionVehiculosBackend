package com.erneto13.sgfa_backend.repository;

import com.erneto13.sgfa_backend.model.DriverModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DriverRepository implements IDriverRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public DriverModel findByUserId(Integer userId) {
        String SQL = "SELECT * FROM drivers WHERE user_id = ?";
        return jdbcTemplate.queryForObject(SQL, new Object[]{userId},
                new BeanPropertyRowMapper<>(DriverModel.class));
    }
}
