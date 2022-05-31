package com.term.moviesite.repository.insertData;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Controller
@RequiredArgsConstructor
public class InsertData {
    @PersistenceContext
    EntityManager em;


}
