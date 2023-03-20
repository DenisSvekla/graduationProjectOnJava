package com.tms.repository;

import com.tms.model.Security;
import com.tms.model.User;
import com.tms.utils.SecurityMapper;
import com.tms.utils.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;

@Repository
public class UserRepository {

    public JdbcTemplate template;

    @Autowired
    public UserRepository(JdbcTemplate template) {
        this.template = template;
    }

    public ArrayList<User> getAllUsers() {
        return (ArrayList<User>) template.query("SELECT * FROM user_table WHERE is_deleted = false", new UserMapper());
    }

    public User getUserById(int id) {
        return template.queryForObject("SELECT * FROM user_table WHERE id=?", new UserMapper(), id);
    }

    public Boolean createUser(User user, Security security) {
        int resultForCreateUser;
        int resultForSecurity= template.update("INSERT INTO security_table (id, login_user, password_user)" + "VALUES(DEFAULT, ? ,?) ",
                new Object[]{security.getLogin_user(), security.getPassword_user()});
        Security security1 = template.queryForObject("SELECT * FROM security_table WHERE login_user=?", new SecurityMapper(), security.getLogin_user());

        resultForCreateUser = template.update("INSERT INTO user_table (id, name, surname,email,birthday_date,security_id,user_type,is_deleted,created,changed)" +
                "VALUES (DEFAULT,?,?,?,?,?,?, DEFAULT, ?, ?)",
                new Object[]{user.getName(),user.getSurname(), user.getEmail(), user.getBirthday_date(), security1.getId(),user.getUser_type(),new Date((new java.util.Date()).getTime()),new Date((new java.util.Date()).getTime())});


        return resultForCreateUser==1;

    }

}
