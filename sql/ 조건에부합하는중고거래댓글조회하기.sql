-- https://school.programmers.co.kr/learn/courses/30/lessons/164673
SELECT 
    b.TITLE, 
    b.BOARD_ID, 
    r.REPLY_ID, 
    r.WRITER_ID, 
    r.CONTENTS, 
    r.CREATED_DATE
from USED_GOODS_BOARD as b 
inner join USED_GOODS_REPLY as r 
    on b.BOARD_ID = r.BOARD_ID
where  b.CREATED_DATE like '2022-10-%'
order by r.CREATED_DATE asc, b.TITLE asc