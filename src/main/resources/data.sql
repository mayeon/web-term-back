-- 사용자 1
INSERT INTO users (user_id, password, name, age, gender, is_admin)
VALUES
    ('park', 'qwe123', '박', '18', 'MALE', 'false'),
    ('lee', 'qwer', '이', '22', 'FEMALE', 'false'),
    ('kim', 'asdf', '김', '35', 'FEMALE', 'false'),
    ('choi', '1234', '최', '43', 'MALE', 'false'),
    ('admin', 'admin', '관리자', '42', 'MALE', 'true');

-- 상영관 2
INSERT INTO theaters (theater_name, floor, max_row, max_col)
VALUES
    ('1상영관', '1', 'G', '10'),
    ('2상영관', '2', 'E', '8');

-- 영화 3
INSERT INTO movies (title, director, story, open_date, genre, running_time, poster_link, reservation_rate, grade)
VALUES
    ('닥터 스트레인지2', '샘 레이미', '스토리1' , '2022-05-04', 'ACTION', '126', 'https://drive.google.com/file/d/12jcTy8lW2kKCY-0ViTfPNdlsav-Px7WC/view?usp=sharing', '0.0', '0.0'),
    ('이미테이션 게임', '모튼 틸덤', '스토리2', '2015-02-17', 'DRAMA', '114', 'https://drive.google.com/file/d/10g2eVQjMjl9iTD9NRKFSuW8msddw8-YY/view?usp=sharing', '0.0', '0.0'),
    ('범죄도시1', '강윤성', '스토리3', '2017-10-03', 'CRIME', '121', 'https://drive.google.com/file/d/1qP_a3U9aHroyXwM2-I1OF6_6RX1PYtpm/view?usp=sharing', '0.0', '0.0'),
    ('범죄도시2', '이상용', '스토리4', '2022-05-18', 'CRIME', '106', 'https://drive.google.com/file/d/1qDzQ4d6OyQyi653Y0ELlc-PnGizBJ85A/view?usp=sharing', '0.0', '0.0'),
    ('악인전', '이원태', '스토리5', '2019-05-15', 'CRIME', '110', 'https://drive.google.com/file/d/1BfsNahwN7pVXQyaUgyxk6NhGos7Zr8hR/view?usp=sharing', '0.0', '0.0'),
    ('나쁜 녀석들: 더 무비', '손용호', '스토리6', '2019-09-11', 'CRIME', '114', 'https://drive.google.com/file/d/1zhCC0g5WV1s2Mv5PYKTnEW1ETiq_2l2p/view?usp=sharing', '0.0', '0.0'),
    ('시동', '최정열', '스토리7', '2019-12-18', 'DRAMA', '102', 'https://drive.google.com/file/d/1zp1_3JnrSzhMlu3rgrkqxkK_HiwpfKgU/view?usp=sharing', '0.0', '0.0'),
    ('이웃사람', '김휘', '스토리8', '2012-08-22', 'THRILLER', '115', 'https://drive.google.com/file/d/1qd8g0Sc4GtISYDg1AfdhBpyhw9tX95Y2/view?usp=sharing', '0.0', '0.0'),
    ('분노의 질주: 홉스&쇼', '데이빗 레이치', '스토리9', '2019-08-14', 'ACTION', '136', 'https://drive.google.com/file/d/1TYcDb7M2z1uKjq8j56xDQt3oHyzQFaok/view?usp=sharing', '0.0', '0.0'),
    ('레드 노티스', '로슨 마샬 터버', '스토리10', '2021-11-12', 'ACTION', '115', 'https://drive.google.com/file/d/1SZqKVuGi8eirPmFABy68XzA33x3HPOIj/view?usp=sharing', '0.0', '0.0'),
    ('블러드샷', '데이브 윌슨', '스토리11', '2020-05-21', 'ACTION', '109', 'https://drive.google.com/file/d/1MEaystdAfZEkZvdki2tk3URrNoDDoWe-/view?usp=sharing', '0.0', '0.0');

-- 배우 4
INSERT INTO actors (name)
VALUES
    ('베네딕트 컴버배치'), -- 닥스, 이미테이션 게임
    ('엘리자베스 올슨'), -- 닥스
    ('키이라 나이틀리'), -- 이미테이션
    ('마동석'), -- 범죄도시1, 범죄도시2, 악인전, 나쁜 녀석들, 시동, 이웃사람
    ('윤계상'), -- 범죄도시1
    ('손석구'), -- 범죄도시2
    ('김무열'), -- 악인전
    ('김아중'), -- 나쁜 녀석들
    ('박정민'), -- 시동
    ('임하룡'), -- 이웃사람
    ('드웨인 존슨'), -- 분노의 질주, 레드 노티스
    ('빈 디젤'), -- 분노의 질주, 블러드샷
    ('라이언 레이놀즈'), -- 레드노티스
    ('에이사 곤살레스'); -- 블러드샷

-- 영화 - 배우 5
INSERT INTO movies_actors (movie_id, actor_id)
VALUES
    (1, 1), (1, 2),
    (2, 1), (2, 3),
    (3, 4), (3, 5),
    (4, 4), (4, 6),
    (5, 4), (5, 7),
    (6, 4), (6, 8),
    (7, 4), (7, 9),
    (8, 4), (8, 10),
    (9, 11), (9, 12),
    (10, 11), (10, 13),
    (11, 12), (11, 14);

-- 상영 6
INSERT INTO screens (movie_id, theater_id, start_time, end_time, price, discount_policy, discount_rate)
VALUES
    (1, 1, '2022-06-08 08:00:00', '2022-06-08 10:20:00', '8000', 'FIXED_AMOUNT', '0'), -- 1-2
    (1, 2, '2022-06-08 06:00:00', '2022-06-08 08:20:00', '8000', 'FIXED_AMOUNT', '0'), -- 2-1

    (2, 1, '2022-06-08 10:30:00', '2022-06-08 12:50:00', '8000', 'FIXED_AMOUNT', '0'), -- 1-3

    (3, 2, '2022-06-08 08:30:00', '2022-06-08 10:50:00', '8000', 'FIXED_AMOUNT', '0'), -- 2-2

    (4, 1, '2022-06-08 13:00:00', '2022-06-08 15:20:00', '8000', 'FIXED_AMOUNT', '0'), -- 1-4
    (4, 1, '2022-06-08 11:00:00', '2022-06-08 13:20:00', '8000', 'FIXED_AMOUNT', '0'), -- 2-3
    (4, 2, '2022-06-08 22:30:00', '2022-06-09 00:50:00', '8000', 'FIXED_AMOUNT', '0'), -- 2-8 *

    (5, 2, '2022-06-08 13:30:00', '2022-06-08 15:50:00', '8000', 'FIXED_AMOUNT', '0'), -- 2-4

    (7, 1, '2022-06-08 05:30:00', '2022-06-08 07:50:00', '8000', 'FIXED_AMOUNT', '0'), -- 1-1
    (7, 1, '2022-06-08 15:30:00', '2022-06-08 17:50:00', '8000', 'FIXED_AMOUNT', '0'), -- 1-5

    (8, 2, '2022-06-08 18:00:00', '2022-06-08 20:20:00', '8000', 'FIXED_AMOUNT', '0'), -- 1-6

    (9, 1, '2022-06-08 20:30:00', '2022-06-08 22:50:00', '8000', 'FIXED_AMOUNT', '0'), -- 1-7
    (9, 2, '2022-06-08 16:00:00', '2022-06-08 18:20:00', '8000', 'FIXED_AMOUNT', '0'), -- 2-5

    (10, 2, '2022-06-08 18:30:00', '2022-06-08 20:50:00', '8000', 'FIXED_AMOUNT', '0'), -- 2-6

    (11, 1, '2022-06-08 23:00:00', '2022-06-09 01:20:00', '8000', 'FIXED_AMOUNT', '0'), -- 1-8 *
    (11, 2, '2022-06-08 21:00:00', '2022-06-08 23:20:00', '8000', 'FIXED_AMOUNT', '0'); -- 2-7

---- 리뷰 7
INSERT INTO reviews(movie_id, user_id, comment, rate)
VALUES
    (1, 'park', '닥스 7점', '7'),
    (1, 'lee', '닥스 8점', '8'),
    (4, 'lee', '범죄도시 10점', '10'),
    (4, 'kim', '범죄도시 9점', '9'),
    (4, 'choi', '범죄도시 7점', '7'),
    (5, 'park', '악인전 6점', '6'),
    (9, 'kim', '분노의 질주 7점', '7');

-- 좋아요 목록 8
INSERT INTO LIKES(review_id, user_id)
VALUES
    (1, 'lee'),

    (2, 'park'),
    (2, 'kim'),

    (3, 'park'),
    (3, 'kim'),
    (3, 'choi'),

    (4, 'park'),
    (4, 'choi'),

    (5, 'kim'),

    (7, 'park');

-- 티켓 9
INSERT INTO TICKETS(user_id, screen_id)
VALUES
    ('park', 1),
    ('lee', 1),
    ('park', 2),
    ('kim', 2),
    ('choi', 5),
    ('lee', 5);

-- 좌석 10
INSERT INTO SEATS(ticket_id, screen_id, matrix_row, matrix_col, IS_RESERVED)
VALUES
    (1, 1, 'A', '1', 'true'), -- 1
    (1, 1, 'A', '2', 'true'), -- 1
    (1, 1, 'A', '3', 'true'), -- 1
    (2, 1, 'A', '4', 'true'), -- 2

    (3, 2, 'B', '1', 'true'), -- 3
    (3, 2, 'B', '2', 'true'), -- 3
    (4, 2, 'B', '3', 'true'), -- 4

    (5, 5, 'C', '1', 'true'), -- 5
    (6, 5, 'C', '2', 'true'); -- 6
