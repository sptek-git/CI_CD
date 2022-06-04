package org.keumann.wisestudy.handler;

import lombok.extern.slf4j.Slf4j;
import org.keumann.wisestudy.exception.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalController {

    /**
     * 비즈니스 로직 Exception
     */
    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<?> handleBusinessException(final BusinessException e) {
        log.error("handle BusinessException", e);
        return ResponseEntity.badRequest().build();
    }
}
