package com.tms.repository;

import java.util.List;
import java.util.Optional;

import com.tms.model.domain.Phone;
import com.tms.model.response.phone.PhoneRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Integer> {

    Optional<List<PhoneRequestDto>> getPhonesByUserId(int id);

    int deletePhoneByUserIdAndId(int userId, int phoneId);
}
