package com.tms.service.impl;

import java.util.List;

import com.tms.exceprtionResolver.NotFoundException;
import com.tms.exceprtionResolver.OtherException;
import com.tms.model.domain.Phone;
import com.tms.model.request.phone.PhoneCreateRequestDto;
import com.tms.model.response.phone.PhoneRequestDto;
import com.tms.repository.PhoneRepository;
import com.tms.service.PhoneService;
import com.tms.utils.validation.service.CheckUserByIdInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.tms.utils.ExceptionMessage.DELETE_PHONE_EXCEPTION;
import static com.tms.utils.ExceptionMessage.NO_ACCESS;
import static com.tms.utils.ExceptionMessage.PHONES_NOT_FOUND;

@Service
public class PhoneServiceImpl implements PhoneService {

    private final PhoneRepository phoneRepository;
    private final CheckUserByIdInService checkUserByIdInService;

    @Autowired
    public PhoneServiceImpl(PhoneRepository phoneRepository, CheckUserByIdInService checkUserByIdInService) {
        this.phoneRepository = phoneRepository;
        this.checkUserByIdInService = checkUserByIdInService;
    }

    public List<PhoneRequestDto> getAllPhonesByIdUser(int id) {
        if (checkUserByIdInService.checkUserByIdAndType(id)) {
            return phoneRepository.getPhonesByUserId(id).orElseThrow(() -> new NotFoundException(PHONES_NOT_FOUND));
        }
        throw new OtherException(NO_ACCESS);
    }

    public Phone createPhoneForUser(PhoneCreateRequestDto phoneCreateRequestDto, int id) {
        Phone phone = new Phone();
        if (checkUserByIdInService.checkUserByIdAndType(id)) {
            phone.setUserId(id);
            phone.setNumber(phoneCreateRequestDto.getNumber());
            phone.setOperator(phoneCreateRequestDto.getOperator());
            return phoneRepository.save(phone);
        }
        throw new OtherException(NO_ACCESS);
    }

    @Transactional
    public void deletePhone(int userId, int phoneId) {
        if (checkUserByIdInService.checkUserByIdAndType(userId)) {
            int result = phoneRepository.deletePhoneByUserIdAndId(userId, phoneId);
            if (result == 0) {
                throw new OtherException(DELETE_PHONE_EXCEPTION);
            }
        } else {
            throw new OtherException(NO_ACCESS);
        }
    }
}
