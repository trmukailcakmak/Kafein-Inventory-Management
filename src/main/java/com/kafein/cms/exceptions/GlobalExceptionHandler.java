package com.kafein.cms.exceptions;


import com.kafein.cms.constant.MessageKey;
import com.kafein.cms.model.base.FieldErr;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private final MessageSource messageSource;

    @ExceptionHandler(value = KafeinException.class)
    public ResponseEntity<Object> handleKafeinException(KafeinException exception) {
        String code = exception.getCode();
        Object[] args = exception.getPrmList();
        String msg = this.messageSource.getMessage(code,args.length==0 ? null:args, Locale.ENGLISH);
        return new ResponseEntity<>(this.messageSource.getMessage(MessageKey.GENERAL_ERR,new Object[]{code,msg},Locale.ENGLISH), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = AlreadyExistException.class)
    public ResponseEntity<Object> handleKafeinException(AlreadyExistException alreadyExistException) {
        logger.error("Zaten Var. Err Msg : "+alreadyExistException.getMessage(),alreadyExistException);
        return new ResponseEntity<>(alreadyExistException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Error methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processFieldErrors(fieldErrors);
    }

    private Error processFieldErrors(List<org.springframework.validation.FieldError> fieldErrors) {
        Error error = new Error(BAD_REQUEST.value(), "validation error");
        for (org.springframework.validation.FieldError fieldError: fieldErrors) {
            error.addFieldError(fieldError.getField(), this.messageSource.getMessage(fieldError.getDefaultMessage(),null,Locale.ENGLISH));
        }
        return error;
    }

    static class Error {
        private final int status;
        private final String message;
        private List<FieldErr> feildErrList = new ArrayList<>();

        Error(int status, String message) {
            this.status = status;
            this.message = message;
        }

        public int getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }

        public void addFieldError(String field, String message) {
            FieldErr error = new FieldErr(field, message);
            feildErrList.add(error);
        }

        public List<FieldErr> getFieldErrors() {
            return feildErrList;
        }
    }

}
