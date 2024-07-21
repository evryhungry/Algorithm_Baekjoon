N, won = map(int, input().split()) # 동전 수와 원하는 금액 차출

won_list = sorted([int(input()) for _ in range(N)], reverse=True) # 각각의 금액을 뽑아 리버스 솔트

cnt = 0
for i in won_list:
    if won == 0:
        break
    cnt += won // i # 나누어 진다면 해당 갯수만큼 cnt 에 저장
    won %= i # 나머지 금액 저장


print(cnt)
