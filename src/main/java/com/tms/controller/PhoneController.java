package com.tms.controller;

import javax.validation.Valid;
import java.util.List;

import com.tms.model.request.phone.PhoneCreateRequestDto;
import com.tms.model.response.phone.PhoneRequestDto;
import com.tms.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/{id}/phone")
public class PhoneController {

    private final PhoneService phoneService;

    @Autowired
    public PhoneController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @GetMapping
    public List<PhoneRequestDto> getAllPhones(@PathVariable int id) {
        return phoneService.getAllPhonesByIdUser(id);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createPhone(@PathVariable int id, @RequestBody @Valid PhoneCreateRequestDto phoneCreateRequestDto){
        return new ResponseEntity<>(phoneService.createPhoneForUser(phoneCreateRequestDto,id)!=null? HttpStatus.OK: HttpStatus.CONFLICT);
    }

    @DeleteMapping("{idPhone}")
    public ResponseEntity<HttpStatus> deletePhone(@PathVariable int id,@PathVariable int idPhone){
        phoneService.deletePhone(id, idPhone);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}
