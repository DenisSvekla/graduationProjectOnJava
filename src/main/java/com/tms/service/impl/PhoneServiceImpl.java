package com.tms.service.impl;

import java.util.List;

import com.tms.ExceprtionResolver.NotFoundException;
import com.tms.ExceprtionResolver.OtherException;
import com.tms.model.domain.Phone;
import com.tms.model.request.phone.PhoneCreateRequestDto;
import com.tms.model.response.phone.PhoneRequestDto;
import com.tms.repository.PhoneRepository;
import com.tms.service.PhoneService;
import com.tms.utils.validation.service.CheckUserByIdInUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.tms.utils.ExceptionMessage.*;

@Service
public class PhoneServiceImpl implements PhoneService {

    private final PhoneRepository phoneRepository;
    private final CheckUserByIdInUserService checkUserByIdInUserService;

    @Autowired
    public PhoneServiceImpl(PhoneRepository phoneRepository, CheckUserByIdInUserService checkUserByIdInUserService) {
        this.phoneRepository = phoneRepository;
        this.checkUserByIdInUserService = checkUserByIdInUserService;
    }

    public List<PhoneRequestDto> getAllPhonesByIdUser(int id) {
        if(checkUserByIdInUserService.checkUserByIdAndType(id)) {
            return  phoneRepository.getPhonesByUserId(id).orElseThrow(()-> new NotFoundException(PHONES_NOT_FOUND));
        }
        throw new OtherException(NO_ACCESS);
    }

    public Phone createPhoneForUser(PhoneCreateRequestDto phoneCreateRequestDto, int id) {
        Phone phone = new Phone();
        if(checkUserByIdInUserService.checkUserByIdAndType(id)) {
            phone.setUserId(id);
            phone.setNumber(phoneCreateRequestDto.getNumber());
            phone.setOperator(phoneCreateRequestDto.getOperator());
            return phoneRepository.save(phone);
        }
        throw new OtherException(NO_ACCESS);
    }

    @Transactional
    public void deletePhone(int userId, int phoneId) {
        if(checkUserByIdInUserService.checkUserByIdAndType(userId)) {
            int result = phoneRepository.deletePhoneByUserIdAndId(userId,phoneId);
            if (result == 0) {
                throw new OtherException(DELETE_PHONE_EXCEPTION);
            }
        }
        else {
            throw new OtherException(NO_ACCESS);
        }
    }
}
