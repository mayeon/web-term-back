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
public class UserStats { // 예매 통계 자료
    private Gender gender;
    private Short age;
}
