-- https://school.programmers.co.kr/learn/courses/30/lessons/157339
-- CAR_RENTAL_COMPANY_CAR, CAR_RENTAL_COMPANY_RENTAL_HISTORY, CAR_RENTAL_COMPANY_DISCOUNT_PLAN
-- C : CAR_ID, CAR_TYPE, DAILY_FEE, OPTIONS
-- R : HISTORY_ID, CAR_ID, START_DATE, END_DATE
-- D : PLAN_ID, CAR_TYPE, DURATION_TYPE, DISCOUNT_RATE
-- 문제 : 1. CAR_TYPE - '세단' 또는 'SUV', 2. 2022년 11월 1일부터 2022년 11월 30일까지 대여 가능, 
--       3. 30일간의 대여 금액이 50만원 이상 200만원 미만인 자동차
-- order by 1. 대여금액 기준 desc, 2. CAR_TYPE asc, 3. CAR_ID desc

SELECT c.CAR_ID, c.CAR_TYPE, FLOOR(c.DAILY_FEE * 30 * (100 - d.DISCOUNT_RATE) / 100) as FEE
FROM CAR_RENTAL_COMPANY_CAR as c
JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN as d
  on c.CAR_TYPE = d.CAR_TYPE
 and d.DURATION_TYPE = '30일 이상'
where c.CAR_TYPE in ('세단', 'SUV') 
AND c.CAR_ID not in (
    SELECT CAR_ID 
    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
    WHERE START_DATE <= '2022-11-30'
      AND END_DATE   >= '2022-11-01')
HAVING FEE >= 500000 AND FEE < 2000000
ORDER by FEE desc, c.CAR_TYPE asc, c.CAR_ID desc

-- not in 의 사용 : 억지로 join으로 역지말자,
--     WHERE START_DATE <= '2022-11-30'
--      AND END_DATE   >= '2022-11-01') => where의 범위 사용 아 난 멍청하군 왜 이게 벗어난다고 생각했지. 해결: 1. 글로쓰자, 2. 글로쓰자,having 은 select에 있어야만 보이는 값.
