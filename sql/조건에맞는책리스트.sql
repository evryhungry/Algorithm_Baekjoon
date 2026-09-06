-- https://school.programmers.co.kr/learn/courses/30/lessons/144853
SELECT BOOK_ID, PUBLISHED_DATE
FROM BOOK
WHERE CATEGORY = '인문' and PUBLISHED_DATE like '2021%'
ORDER BY PUBLISHED_DATE