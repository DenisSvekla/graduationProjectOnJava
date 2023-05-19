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
        car.setNameBrand(addCarRequestDto.getNameBrand());
        car.setNameModel(addCarRequestDto.getNameModel());
        car.setTransmission(addCarRequestDto.getTransmission());
        car.setDateOfIssue(addCarRequestDto.getDateOfIssue());
        car.setColor(addCarRequestDto.getColor());
        car.setRegistrationNumber(addCarRequestDto.getRegistrationNumber());
        car.setPrice(addCarRequestDto.getPrice());
        car.setEngineType(addCarRequestDto.getEngineType());
        car.setCity(addCarRequestDto.getCity());
        car.setCountView(0);
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
        if (updateCarRequestDto.getNameBrand() != null) {
            car.setNameBrand(updateCarRequestDto.getNameBrand());
        }
        if (updateCarRequestDto.getNameModel() != null) {
            car.setNameModel(updateCarRequestDto.getNameModel());
        }
        if (updateCarRequestDto.getTransmission() != null) {
            car.setTransmission(updateCarRequestDto.getTransmission());
        }
        if (updateCarRequestDto.getDateOfIssue() != null) {
            car.setDateOfIssue(updateCarRequestDto.getDateOfIssue());
        }
        if (updateCarRequestDto.getRegistrationNumber() != null) {
            car.setRegistrationNumber(updateCarRequestDto.getRegistrationNumber());
        }
        if (updateCarRequestDto.getPrice() != 0) {
            car.setPrice(updateCarRequestDto.getPrice());
        }
        if (updateCarRequestDto.getEngineType() != null) {
            car.setEngineType(updateCarRequestDto.getEngineType());
        }
        return car;
    }

}
