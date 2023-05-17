package com.tms.repository;


import java.util.Optional;

import com.tms.model.domain.FavoriteCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteCarRepository extends JpaRepository<FavoriteCar, Integer> {

    /**
     * @param car
     * @param user
     * @return
     */
    Optional<FavoriteCar> findFavoriteCarByCarIdAndUserId(Integer car, Integer user);

    /**
     * @param id
     * @return
     */
    int deleteFavoriteCarByCarId(int id);


}

