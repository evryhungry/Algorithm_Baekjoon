-- 코드를 입력하세요
SELECT USER_ID, PRODUCT_ID
from ONLINE_SALE
group by USER_ID, PRODUCT_ID
having COUNT(*) >= 2
order by USER_ID asc, PRODUCT_ID desc;

-- from / where / group by / having / order by 순
-- HAVING은 그룹으로 묶은 결과에 거는 조건