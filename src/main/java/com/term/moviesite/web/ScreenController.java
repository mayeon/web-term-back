package com.term.moviesite.web;

import com.term.moviesite.domain.Seats;
import com.term.moviesite.domain.enums.DiscountPolicy;
import com.term.moviesite.dto.MovieScreenDto;
import com.term.moviesite.dto.ScreenDto;
import com.term.moviesite.dto.SeatMatrix;
import com.term.moviesite.service.ScreenService;
import com.term.moviesite.service.SeatService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/screen")
public class ScreenController {
    private ScreenService screenService;
    private SeatService seatService;

    @GetMapping("/list")
    public List<MovieScreenDto> screenList() {
        Map<Long, MovieScreenDto> msdMap = screenService.findAllScreens();
        return msdMap.values().stream()
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @GetMapping("/{id}")
    public List<ScreenDto> movieScreenInfo(@PathVariable("id") Long movieId) {
        return screenService.findScreenByMovieId(movieId);
    }

    @GetMapping("/{id}/price")
    public short screenPriceInfo(@PathVariable("id") Long screenId) {
        return screenService.findScreenPrice(screenId);
    }

    @PostMapping("/modify/price/{id}")
    public void modifyScreenPrice(@PathVariable("id") Long screenId, @RequestBody DiscountInfo discountInfo) {
        screenService.updateScreenDiscount(screenId, discountInfo.getDiscountPolicy(), discountInfo.getDiscountRate());
    }

    @GetMapping("/{id}/seat")
    public List<SeatMatrix> screenSeatsInfo(@PathVariable("id") Long screenId) {
        List<Seats> findSeats = seatService.findSeatByScreenId(screenId);
        List<SeatMatrix> seats = findSeats.stream()
                .map(s -> new SeatMatrix(s.getRow(), s.getCol()))
                .collect(Collectors.toList());
        return seats;
    }

    @AllArgsConstructor
    @Getter
    static class DiscountInfo {
        private DiscountPolicy discountPolicy;
        private Short discountRate;
    }
}
