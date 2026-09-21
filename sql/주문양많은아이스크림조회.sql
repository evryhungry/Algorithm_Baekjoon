-- https://school.programmers.co.kr/learn/courses/30/lessons/133027

SELECT h.FLAVOR
from FIRST_HALF as h 
join (
    select FLAVOR, sum(TOTAL_ORDER) as JULY_ORDER
    from JULY
    group by FLAVOR
) as j
on h.FLAVOR = j.FLAVOR
order by (h.TOTAL_ORDER + j.JULY_ORDER) desc
limit 3;

-- as 랑 on 햇갈리지 말자 음음!
-- join 안에 서브쿼리 가능함을 다시 한번 더 생각.