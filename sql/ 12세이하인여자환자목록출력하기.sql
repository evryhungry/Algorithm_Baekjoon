SELECT PT_NAME, PT_NO, GEND_CD, AGE, 
    CASE 
        WHEN TLNO IS NULL THEN 'NONE'
        ELSE TLNO
    END AS TLNO
FROM PATIENT 
WHERE AGE <= 12 and GEND_CD = 'W'
order by AGE desc, PT_NAME asc;

-- 오늘의 교훈: 문제를 잘 읽자