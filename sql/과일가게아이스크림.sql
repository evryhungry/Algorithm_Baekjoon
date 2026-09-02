-- 코드를 입력하세요
SELECT f.FLAVOR 
FROM FIRST_HALF as f JOIN ICECREAM_INFO as i ON f.FLAVOR = i.FLAVOR
where i.INGREDIENT_TYPE = 'fruit_based' and f.TOTAL_ORDER > 3000
order by f.TOTAL_ORDER desc
