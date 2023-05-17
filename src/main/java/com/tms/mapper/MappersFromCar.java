package com.tms.mapper;

import java.util.ArrayList;
import java.util.List;

import com.tms.model.domain.Car;
import com.tms.model.domain.Comments;
import com.tms.model.response.Car.GetAllCarsResponseDto;
import com.tms.model.response.Car.GetCarByIdResponseDto;
import org.springframework.stereotype.Component;

/**
 * Class for change object from car to car dto
 */
@Component
public class MappersFromCar {

    public GetCarByIdResponseDto carToGetCarByIdResponseDto(Car car, GetCarByIdResponseDto getCarByIdResponseDto, List<Comments> commentsForCar) {
        getCarByIdResponseDto.setName_brand(car.getName_brand());
        getCarByIdResponseDto.setName_model(car.getName_model());
        getCarByIdResponseDto.setTransmission(car.getTransmission());
        getCarByIdResponseDto.setDate_of_issue(car.getDate_of_issue());
        getCarByIdResponseDto.setColor(car.getColor());
        getCarByIdResponseDto.setRegistration_number(car.getRegistration_number());
        getCarByIdResponseDto.setPrice(car.getPrice());
        getCarByIdResponseDto.setEngine_type(car.getEngine_type());
        getCarByIdResponseDto.setCity(car.getCity());
        getCarByIdResponseDto.setStatus(car.getStatus());
        getCarByIdResponseDto.setCreated(car.getCreated());
        getCarByIdResponseDto.setChanged(car.getChanged());
        getCarByIdResponseDto.setUser_id(car.getUserId());
        getCarByIdResponseDto.setComments(commentsForCar);
        getCarByIdResponseDto.setCount_view(car.getCount_view());
        return getCarByIdResponseDto;
    }

    public ArrayList<GetAllCarsResponseDto> carToGetAllCarsResponseDto(ArrayList<Car> car) {
        ArrayList<GetAllCarsResponseDto> getAllCarsResponseDto = new ArrayList<>();
        for (int i = 0; i < car.size(); i++) {
            GetAllCarsResponseDto duringCycleCar = new GetAllCarsResponseDto();
            duringCycleCar.setName_brand(car.get(i).getName_brand());
            duringCycleCar.setName_model(car.get(i).getName_model());
            duringCycleCar.setTransmission(car.get(i).getTransmission());
            duringCycleCar.setDate_of_issue(car.get(i).getDate_of_issue());
            duringCycleCar.setColor(car.get(i).getColor());
            duringCycleCar.setPrice(car.get(i).getPrice());
            duringCycleCar.setEngine_type(car.get(i).getEngine_type());
            duringCycleCar.setCity(car.get(i).getCity());
            duringCycleCar.setChanged(car.get(i).getChanged());
            duringCycleCar.setCreated(car.get(i).getCreated());
            getAllCarsResponseDto.add(duringCycleCar);
        }
        return getAllCarsResponseDto;
    }
}
