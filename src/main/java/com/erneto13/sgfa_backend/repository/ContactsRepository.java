package com.erneto13.sgfa_backend.repository;

import com.erneto13.sgfa_backend.model.ContactModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactsRepository implements IContactsResository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ContactModel> findAll() {
        String SQL = "SELECT * FROM contacts";
        return jdbcTemplate.query(SQL, BeanPropertyRowMapper.newInstance(ContactModel.class));
    }

}
