package com.github.mcoder.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleService {

    public List<People> getAllPeople() {
        return List.of(new People("lalu", 66),
                new People("rabri", 60),
                new People("lalten", 20),
                new People("nitish", 60),
                new People("pappu", 55));
    }


    @Data
    @AllArgsConstructor
    @Builder
    static class People {
        private String name;
        private int age;
    }
}
