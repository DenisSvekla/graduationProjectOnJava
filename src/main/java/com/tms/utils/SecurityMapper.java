//package com.tms.utils;
//
//import com.tms.model.Security;
//import org.springframework.jdbc.core.RowMapper;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class SecurityMapper implements RowMapper<Security> {
//    @Override
//    public Security mapRow(ResultSet rs, int rowNum) throws SQLException {
//        Security security = new Security();
//        security.setId(rs.getInt("id"));
//        security.setLogin_user(rs.getString("login_user"));
//        security.setPassword_user(rs.getString("password_user"));
//        return security;
//    }
//}
