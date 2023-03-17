package com.kafein.cms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@RequiredArgsConstructor
@Service
public class MessageService {

    private final MessageSource messageSource;
    public  String getMessage(String key){
        return this.messageSource.getMessage(key,null, Locale.ENGLISH);
    }
}
