package com.bank.atm.errors;

import com.bank.atm.helpers.CustomHttpStatus;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(InvalidCardException.class)
    protected ResponseEntity handleInvalidCard(
            InvalidCardException ex) {
        return ResponseEntity.status(CustomHttpStatus.INVALID_DATA.getCode()).body(InvalidCardException.message);
    }

    @ExceptionHandler(NotFoundBankAccountException.class)
    protected ResponseEntity handleNotFoundBankAccount(
            NotFoundBankAccountException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NotFoundBankAccountException.message);
    }

    @ExceptionHandler(NotEnoughAssetsException.class)
    protected ResponseEntity handleNotEnoughAssets(
            NotEnoughAssetsException ex) {
        return ResponseEntity.status(CustomHttpStatus.UNACHIEVABLE.getCode()).body(NotEnoughAssetsException.message);
    }
}
