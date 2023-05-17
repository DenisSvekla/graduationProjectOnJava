package com.tms.mapper;

import java.sql.Date;

import com.tms.model.domain.Car;
import com.tms.model.request.car.AddCarRequestDto;
import com.tms.model.request.car.UpdateCarRequestDto;
import org.springframework.stereotype.Component;

/**
 * Class for change object from car dto  to car
 */
@Component
public class MappersToCar {

    public Car carRequestDtoToCar(AddCarRequestDto addCarRequestDto, Car car) {
        car.setName_brand(addCarRequestDto.getName_brand());
        car.setName_model(addCarRequestDto.getName_model());
        car.setTransmission(addCarRequestDto.getTransmission());
        car.setDate_of_issue(addCarRequestDto.getDate_of_issue());
        car.setColor(addCarRequestDto.getColor());
        car.setRegistration_number(addCarRequestDto.getRegistration_number());
        car.setPrice(addCarRequestDto.getPrice());
        car.setEngine_type(addCarRequestDto.getEngine_type());
        car.setCity(addCarRequestDto.getCity());
        car.setCount_view(0);
        car.setStatus("actual");
        car.setCreated(new Date(System.currentTimeMillis()));
        car.setChanged(new Date(System.currentTimeMillis()));
        return car;
    }

    public Car UpdateCarRequestDtoToCar(UpdateCarRequestDto updateCarRequestDto, Car car) {
        if (updateCarRequestDto.getCity() != null) {
            car.setCity(updateCarRequestDto.getCity());
        }
        if (updateCarRequestDto.getColor() != null) {
            car.setColor(updateCarRequestDto.getColor());
        }
        if (updateCarRequestDto.getName_brand() != null) {
            car.setName_brand(updateCarRequestDto.getName_brand());
        }
        if (updateCarRequestDto.getName_model() != null) {
            car.setName_model(updateCarRequestDto.getName_model());
        }
        if (updateCarRequestDto.getTransmission() != null) {
            car.setTransmission(updateCarRequestDto.getTransmission());
        }
        if (updateCarRequestDto.getDate_of_issue() != null) {
            car.setDate_of_issue(updateCarRequestDto.getDate_of_issue());
        }
        if (updateCarRequestDto.getRegistration_number() != null) {
            car.setRegistration_number(updateCarRequestDto.getRegistration_number());
        }
        if (updateCarRequestDto.getPrice() != 0) {
            car.setPrice(updateCarRequestDto.getPrice());
        }
        if (updateCarRequestDto.getEngine_type() != null) {
            car.setEngine_type(updateCarRequestDto.getEngine_type());
        }
        return car;
    }

}
