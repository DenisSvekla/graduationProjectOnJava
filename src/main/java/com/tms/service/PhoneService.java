package com.tms.service;

import java.util.List;

import com.tms.model.domain.Phone;
import com.tms.model.request.phone.PhoneCreateRequestDto;
import com.tms.model.response.phone.PhoneRequestDto;

public interface PhoneService {

    List<PhoneRequestDto> getAllPhonesByIdUser(int id);

    Phone createPhoneForUser(PhoneCreateRequestDto phoneCreateRequestDto, int id);

    void deletePhone(int userId, int phoneId);
}
