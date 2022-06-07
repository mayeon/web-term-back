package com.term.moviesite.dto;

import com.term.moviesite.domain.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserStats {
    private Long movieId;
    private Long screenId;
    private Long ticketId;
    private Gender gender;
    private Short age;

    public UserStats(Gender gender, Short age) {
        this.gender = gender;
        this.age = age;
    }
}
