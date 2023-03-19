package com.tms.repository;

import com.tms.model.User;
import com.tms.utils.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Repository
public class UserRepository {

    public JdbcTemplate template;

    @Autowired
    public UserRepository(JdbcTemplate template) {
        this.template = template;
    }

    public ArrayList<User> getAllUsers() {
        return (ArrayList<User>) template.query("SELECT * FROM user_table", new UserMapper());
    }

    public User getUserById(int id) {
        return template.queryForObject("SELECT * FROM user_table WHERE id=?", new UserMapper(), id);
    }

}
