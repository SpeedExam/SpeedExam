package com.ensat.speedexam.Services;
import com.ensat.speedexam.Repositories.PersonRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
}
