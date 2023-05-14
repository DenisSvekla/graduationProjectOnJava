package com.tms.service;

import java.util.ArrayList;

import com.tms.model.domain.Car;
import com.tms.model.domain.Comments;
import com.tms.model.domain.FavoriteCar;
import com.tms.model.request.car.AddCarRequestDto;
import com.tms.model.request.car.DeleteCarRequestDto;
import com.tms.model.request.car.UpdateCarRequestDto;
import com.tms.model.response.Car.GetAllCarsResponseDto;
import com.tms.model.response.Car.GetCarByIdResponseDto;

public interface CarService {

    ArrayList<GetAllCarsResponseDto> getAllCars();
    Car createCar(AddCarRequestDto addCarRequestDto);

    Car updateCar(UpdateCarRequestDto updateCarRequestDto, int id);

    void addComment(int carId, Comments comment);

    GetCarByIdResponseDto getCarById(int id);

    FavoriteCar addCarAsFavorite(int id);

    void deleteCarAsFavorite(int id);

    void deleteCar(DeleteCarRequestDto deleteCarRequestDto, int id);
}
