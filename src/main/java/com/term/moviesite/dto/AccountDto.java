package com.term.moviesite.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class AccountDto {
    private String userId;
    private String password;
    private Boolean isAdmin;

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}