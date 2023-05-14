package com.tms.controller;

import javax.validation.Valid;
import java.util.ArrayList;

import com.tms.model.domain.Car;
import com.tms.model.domain.Comments;
import com.tms.model.domain.FavoriteCar;
import com.tms.model.request.car.AddCarRequestDto;
import com.tms.model.request.car.DeleteCarRequestDto;
import com.tms.model.request.car.UpdateCarRequestDto;
import com.tms.model.response.Car.GetAllCarsResponseDto;
import com.tms.model.response.Car.GetCarByIdResponseDto;
import com.tms.service.CarService;
import com.tms.service.impl.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/car")
public class CarController {

    private final CarService carService;


    @Autowired
    public CarController(CarServiceImpl carService) {
        this.carService = carService;

    }

    @GetMapping
    public ResponseEntity< ArrayList<GetAllCarsResponseDto>> getAllCars() {
        ArrayList<GetAllCarsResponseDto> list = carService.getAllCars();
        return new ResponseEntity<>(list, (!list.isEmpty()) ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> createCar(@RequestBody @Valid AddCarRequestDto addCarRequestDto) {
        carService.createCar(addCarRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/{id}")
    public ResponseEntity<HttpStatus> updateCar(@RequestBody @Valid UpdateCarRequestDto updateCarRequestDto, @PathVariable int id) {
        Car car = carService.updateCar(updateCarRequestDto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/addComment")
    public ResponseEntity<HttpStatus> addComment(@RequestBody @Valid Comments comment, @PathVariable int id) {
        carService.addComment(id, comment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCarByIdResponseDto> getCarById(@PathVariable int id) {
        GetCarByIdResponseDto car = carService.getCarById(id);
        return new ResponseEntity<>(car, (car.getUser_id() == 0) ? HttpStatus.OK : HttpStatus.CONFLICT);

    }

    @PutMapping("/{id}/addCar")
    public ResponseEntity<FavoriteCar> addCarAsFavorite(@PathVariable int id) {
        FavoriteCar favoriteCar = carService.addCarAsFavorite(id);
        return new ResponseEntity<>(favoriteCar, (favoriteCar.getId() == 0 ? HttpStatus.OK : HttpStatus.CONFLICT));
    }

    @DeleteMapping("/{id}/deleteCar")
    public ResponseEntity<HttpStatus> deleteCarAsFavorite(@PathVariable int id) {
        carService.deleteCarAsFavorite(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCar(@RequestBody @Valid DeleteCarRequestDto deleteCarRequestDto, @PathVariable int id) {
        carService.deleteCar(deleteCarRequestDto, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

