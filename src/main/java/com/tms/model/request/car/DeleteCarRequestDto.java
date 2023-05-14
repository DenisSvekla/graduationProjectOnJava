package com.tms.model.request.car;

import com.tms.annotation.CheckStatusForDeletingCarAnnotation;
import lombok.Data;

@Data
public class DeleteCarRequestDto {

    @CheckStatusForDeletingCarAnnotation
    private String status;

}
