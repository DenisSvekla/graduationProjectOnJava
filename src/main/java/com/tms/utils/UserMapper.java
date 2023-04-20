package com.tms.utils;

import com.tms.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setSurname(resultSet.getString("surname"));
        user.setEmail(resultSet.getString("email"));
        user.setBirthday_date(resultSet.getDate("birthday_date"));
        user.setUser_type(resultSet.getString("user_type"));
        user.set_deleted(resultSet.getBoolean("is_deleted"));
        user.setCreated(resultSet.getDate("created"));
        user.setChanged(resultSet.getDate("changed"));
        return user;
    }
}
