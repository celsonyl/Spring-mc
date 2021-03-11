package com.celso.springmc.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Integer status, String msg, Long timeStamp) {
        super(status, msg, timeStamp);

    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String fieldname, String message) {
        errors.add(new FieldMessage(fieldname, message));
    }
}
