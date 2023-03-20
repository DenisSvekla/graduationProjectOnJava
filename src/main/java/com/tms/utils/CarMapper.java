package com.tms.utils;

import com.tms.model.Car;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarMapper implements RowMapper<Car> {
    @Override
    public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
        Car car = new Car();
        car.setId(rs.getInt("id"));
        car.setName_brand(rs.getString("name_brand"));
        car.setName_model(rs.getString("name_model"));
        car.setTransmission(rs.getString("transmission"));
        car.setDate_of_issue(rs.getDate("date_of_issue"));
        car.setColor(rs.getString("color"));
        car.setRegistration_number(rs.getString("registration_number"));
        car.setPrice(rs.getInt("price"));
        car.setEngine_type(rs.getString("engine_type"));
        car.setCity(rs.getString("city"));
        car.setCount_view(rs.getInt("count_view"));
        car.setStatus(rs.getString("status"));
        return car;
    }
}
