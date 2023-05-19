package com.tms.mapper;

import java.util.ArrayList;
import java.util.List;

import com.tms.model.domain.Car;
import com.tms.model.domain.Comments;
import com.tms.model.response.car.GetAllCarsResponseDto;
import com.tms.model.response.car.GetCarByIdResponseDto;
import org.springframework.stereotype.Component;


/**
 * Class for change object from car to car dto
 */
@Component
public class MappersFromCar {

    public GetCarByIdResponseDto carToGetCarByIdResponseDto(Car car, GetCarByIdResponseDto getCarByIdResponseDto, List<Comments> commentsForCar) {
        getCarByIdResponseDto.setName_brand(car.getNameBrand());
        getCarByIdResponseDto.setName_model(car.getNameModel());
        getCarByIdResponseDto.setTransmission(car.getTransmission());
        getCarByIdResponseDto.setDate_of_issue(car.getDateOfIssue());
        getCarByIdResponseDto.setColor(car.getColor());
        getCarByIdResponseDto.setRegistration_number(car.getRegistrationNumber());
        getCarByIdResponseDto.setPrice(car.getPrice());
        getCarByIdResponseDto.setEngine_type(car.getEngineType());
        getCarByIdResponseDto.setCity(car.getCity());
        getCarByIdResponseDto.setStatus(car.getStatus());
        getCarByIdResponseDto.setCreated(car.getCreated());
        getCarByIdResponseDto.setChanged(car.getChanged());
        getCarByIdResponseDto.setUser_id(car.getUserId());
        getCarByIdResponseDto.setComments(commentsForCar);
        getCarByIdResponseDto.setCount_view(car.getCountView());
        return getCarByIdResponseDto;
    }

    public ArrayList<GetAllCarsResponseDto> carToGetAllCarsResponseDto(ArrayList<Car> car) {
        ArrayList<GetAllCarsResponseDto> getAllCarsResponseDto = new ArrayList<>();
        for (int i = 0; i < car.size(); i++) {
            GetAllCarsResponseDto duringCycleCar = new GetAllCarsResponseDto();
            duringCycleCar.setName_brand(car.get(i).getNameBrand());
            duringCycleCar.setName_model(car.get(i).getNameModel());
            duringCycleCar.setTransmission(car.get(i).getTransmission());
            duringCycleCar.setDate_of_issue(car.get(i).getDateOfIssue());
            duringCycleCar.setColor(car.get(i).getColor());
            duringCycleCar.setPrice(car.get(i).getPrice());
            duringCycleCar.setEngine_type(car.get(i).getEngineType());
            duringCycleCar.setCity(car.get(i).getCity());
            duringCycleCar.setChanged(car.get(i).getChanged());
            duringCycleCar.setCreated(car.get(i).getCreated());
            getAllCarsResponseDto.add(duringCycleCar);
        }
        return getAllCarsResponseDto;
    }
}
