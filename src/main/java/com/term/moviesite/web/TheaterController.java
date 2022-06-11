package com.term.moviesite.web;

import com.term.moviesite.domain.Theaters;
import com.term.moviesite.dto.TheaterDto;
import com.term.moviesite.service.TheaterService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/theater")
public class TheaterController {
    private TheaterService theaterService;

    @GetMapping("/{id}")
    public TheaterDto theaterInfo(@PathVariable("id") Long theaterId) {
        Theaters theaters = theaterService.findTheater(theaterId);
        return new TheaterDto(theaters.getTheaterName(), theaters.getFloor(), theaters.getMaxRow(), theaters.getMaxCol());
    }
}
