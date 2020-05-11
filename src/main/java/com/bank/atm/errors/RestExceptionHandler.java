package com.bank.atm.errors;

import com.bank.atm.helpers.CustomHttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(InvalidCardException.class)
    protected ResponseEntity handleInvalidCard(
            InvalidCardException ex) {
        logger.error("Invalid card exception thrown", ex);
        return ResponseEntity.status(CustomHttpStatus.INVALID_DATA.getCode()).body(InvalidCardException.MESSAGE);
    }

    @ExceptionHandler(NotFoundBankAccountException.class)
    protected ResponseEntity handleNotFoundBankAccount(
            NotFoundBankAccountException ex) {
        logger.error("Not found bank account exception thrown", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NotFoundBankAccountException.MESSAGE);
    }

    @ExceptionHandler(NotEnoughAssetsException.class)
    protected ResponseEntity handleNotEnoughAssets(
            NotEnoughAssetsException ex) {
        logger.error("Not enough assets exception thrown", ex);
        return ResponseEntity.status(CustomHttpStatus.UNACHIEVABLE.getCode()).body(NotEnoughAssetsException.MESSAGE);
    }

    @ExceptionHandler(NotFoundATMException.class)
    protected ResponseEntity handleNotFoundATM(
            NotFoundATMException ex
    ) {
        logger.error("Not found atm exception thrown", ex);
        return ResponseEntity.status(CustomHttpStatus.INVALID_DATA.getCode()).body(NotFoundATMException.MESSAGE);
    }
}
