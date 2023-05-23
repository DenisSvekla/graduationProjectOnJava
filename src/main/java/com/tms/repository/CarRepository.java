package com.tms.repository;

import java.util.ArrayList;
import java.util.Optional;

import com.tms.model.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    /**
     * @param id
     * @param status
     */
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE car SET stasus =:status WHERE id = :id",
            countQuery = "SELECT * from car WHERE id = :id")
    int deleteCarViaStatus(int id, String status);

    @Query(nativeQuery = true, value = "SELECT * FROM car left join user_table ut on ut.id = car.user_id left join subscription_table st on ut.id = st.user_id WHERE car.stasus = 'actual' ORDER BY st.expire_date")
    Optional<ArrayList<Car>> getAllCars();

    /**
     * @param id
     * @return
     */
    Optional<ArrayList<Car>> getCarsByUserId(int id);


}
