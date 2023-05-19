package com.tms.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.tms.exceprtionResolver.NotFoundException;
import com.tms.exceprtionResolver.OtherException;
import com.tms.mapper.MappersFromCar;
import com.tms.mapper.MappersToCar;
import com.tms.model.domain.Car;
import com.tms.model.domain.Comments;
import com.tms.model.domain.FavoriteCar;
import com.tms.model.request.car.AddCarRequestDto;
import com.tms.model.request.car.DeleteCarRequestDto;
import com.tms.model.request.car.UpdateCarRequestDto;
import com.tms.model.response.car.GetAllCarsResponseDto;
import com.tms.model.response.car.GetCarByIdResponseDto;
import com.tms.repository.CarRepository;
import com.tms.repository.CommentsRepository;
import com.tms.repository.FavoriteCarRepository;
import com.tms.service.CarService;
import com.tms.utils.GetIdUserFromContext;
import com.tms.utils.validation.service.CheckUserOnAvailabilityForCarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.tms.utils.ExceptionMessage.CARS_NOT_FOUND;
import static com.tms.utils.ExceptionMessage.DELETE_CAR_AS_FAVORITE_EXCEPTION;
import static com.tms.utils.ExceptionMessage.DELETE_CAR_EXCEPTION;
import static com.tms.utils.ExceptionMessage.NO_ACCESS;

@Service
@Slf4j
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    private final FavoriteCarRepository favoriteCarRepository;

    private final MappersToCar mappersToCar;

    private final MappersFromCar mappersFromCar;

    private final CommentsRepository commentsRepository;

    private final GetIdUserFromContext getIdUserFromContext;

    private final CheckUserOnAvailabilityForCarService checkUserOnAvailabilityForCarService;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, FavoriteCarRepository favoriteCarRepository, MappersToCar mappersToCar,
                          MappersFromCar mappersFromCar, CommentsRepository commentsRepository, GetIdUserFromContext getIdUserFromContext,
                          CheckUserOnAvailabilityForCarService checkUserOnAvailabilityForCarService) {
        this.carRepository = carRepository;
        this.favoriteCarRepository = favoriteCarRepository;
        this.mappersToCar = mappersToCar;
        this.mappersFromCar = mappersFromCar;
        this.commentsRepository = commentsRepository;
        this.getIdUserFromContext = getIdUserFromContext;
        this.checkUserOnAvailabilityForCarService = checkUserOnAvailabilityForCarService;
    }

    public ArrayList<GetAllCarsResponseDto> getAllCars() {
        ArrayList<Car> cars = carRepository.getAllCars().orElseThrow(() -> new NotFoundException(CARS_NOT_FOUND));
        return mappersFromCar.carToGetAllCarsResponseDto(cars);
    }

    public Car createCar(AddCarRequestDto addCarRequestDto) {
        Car car = new Car();
        car = mappersToCar.carRequestDtoToCar(addCarRequestDto, car);
        car.setUserId(getIdUserFromContext.getIdUserFromContext());
        return carRepository.saveAndFlush(car);
    }


    public Car updateCar(UpdateCarRequestDto updateCarRequestDto, int id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new NotFoundException(CARS_NOT_FOUND));
        if (checkUserOnAvailabilityForCarService.checkUserOnAvailabilityForCarServiceByCarId(id)) {
            car = mappersToCar.UpdateCarRequestDtoToCar(updateCarRequestDto, car);
            car.setChanged(new Date(System.currentTimeMillis()));
            return carRepository.saveAndFlush(car);
        }
        throw new OtherException(NO_ACCESS);
    }

    public void addComment(int carId, Comments comment) {
        int idUsers = getIdUserFromContext.getIdUserFromContext();
        comment.setCarId(carId);
        comment.setUserId(idUsers);
        comment.setChanged(new Date(System.currentTimeMillis()));
        comment.setCreated(new Date(System.currentTimeMillis()));
        commentsRepository.saveAndFlush(comment);
    }

    @Transactional
    public GetCarByIdResponseDto getCarById(int id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new NotFoundException(CARS_NOT_FOUND));
        List<Comments> commentsForCar = commentsRepository.getCommentsByCar_id(id);
        car.setCountView(car.getCountView() + 1);
        GetCarByIdResponseDto carByIdWithResponse = new GetCarByIdResponseDto();
        carByIdWithResponse = mappersFromCar.carToGetCarByIdResponseDto(car, carByIdWithResponse, commentsForCar);
        return carByIdWithResponse;

    }

    public FavoriteCar addCarAsFavorite(int id) {
        int idUsers = getIdUserFromContext.getIdUserFromContext();
        FavoriteCar favoriteCar = new FavoriteCar();
        favoriteCar.setUserId(idUsers);
        favoriteCar.setCarId(id);
        return favoriteCarRepository.save(favoriteCar);
    }


    @Transactional
    public void deleteCarAsFavorite(int id) {
        int idUsers = getIdUserFromContext.getIdUserFromContext();
        FavoriteCar car = favoriteCarRepository.findFavoriteCarByCarIdAndUserId(id, idUsers).orElseThrow(() -> new NotFoundException(CARS_NOT_FOUND));
        int result = favoriteCarRepository.deleteFavoriteCarByCarId(car.getId());
        if (result == 0) {
            throw new OtherException(DELETE_CAR_AS_FAVORITE_EXCEPTION);
        }
    }

    @Transactional
    public void deleteCar(DeleteCarRequestDto deleteCarRequestDto, int id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new NotFoundException(CARS_NOT_FOUND));
        if (checkUserOnAvailabilityForCarService.checkUserOnAvailabilityForCarServiceByCarId(id)) {
            car.setStatus(deleteCarRequestDto.getStatus());
            int result = carRepository.deleteCarViaStatus(id, deleteCarRequestDto.getStatus());
            if (result == 0) {
                throw new OtherException(DELETE_CAR_EXCEPTION);
            }
        } else {
            throw new OtherException(NO_ACCESS);
        }
    }
}


