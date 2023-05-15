package com.aninfo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class DepositNegativeSumException extends RuntimeException {

    public DepositNegativeSumException(String message) {
        super(message);
    }
}
