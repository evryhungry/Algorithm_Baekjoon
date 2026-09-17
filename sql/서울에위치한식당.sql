SELECT i.REST_ID, i.REST_NAME, i.FOOD_TYPE, i.FAVORITES, i.ADDRESS, ROUND(AVG(r.REVIEW_SCORE) * 100) / 100 AS SCORE
FROM REST_INFO as i inner join REST_REVIEW as r on i.REST_ID = r.REST_ID
WHERE i.ADDRESS LIKE '서울%'
GROUP BY i.REST_ID
ORDER BY SCORE desc, i.FAVORITES desc

-- round : 반올림 / CEIL : 올림 / FLOOR : 내림
-- WHERE : 문제 잘보기.....!!!!!!
-- on : join 조건
-- case when : 조건문