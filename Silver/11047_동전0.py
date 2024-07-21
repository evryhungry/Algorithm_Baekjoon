N, won = map(int, input().split())

won_list = sorted([int(input()) for _ in range(N)], reverse=True)

cnt = 0
for i in won_list:
    if won == 0:
        break
    cnt += won // i
    won %= i


print(cnt)
