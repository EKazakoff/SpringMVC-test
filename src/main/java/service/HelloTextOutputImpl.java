package service;

import org.springframework.stereotype.Service;

@Service
public class HelloTextOutputImpl implements HelloTextOutput {
    @Override
    public String sayHello() {
        return "Hello";
    }

    @Override
    public String getWeather() {
        return null;
    }
}
