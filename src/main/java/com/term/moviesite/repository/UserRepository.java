package com.term.moviesite.repository;

import com.term.moviesite.domain.Users;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserRepository {
    @PersistenceContext
    EntityManager em;

    public void addUser(Users user) {
        em.persist(user);
    }

    public boolean findUserIdAndPw(String userId, String userPw) { // 로그인
        Users user = em.find(Users.class, userId);
        if (user.getPassword().equals(userPw)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean findUser(String userId) { // ID 중복 체크
        Users user = em.find(Users.class, userId);
        if (user == null)
            return false;
        else
            return true;
    }

    public Users findUserInfo(String userId) {
        return em.find(Users.class, userId);
    }
}
