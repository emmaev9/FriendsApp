package com.springdemo.helloworld.Converters;

import com.springdemo.helloworld.Entity.Interest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class InterestConverter implements Converter<String, Interest> {
    @Override
    public Interest convert(String source) {
        Interest interest = new Interest();
        interest.setName(source);
        return interest;
    }
}
