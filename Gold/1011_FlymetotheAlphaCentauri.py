N = int(input())

for _ in range(N):
    x, y = map(int, input().split())
    dist, move  = y - x , 0
    cnt = 0
    moving = 0

    while move < dist:
        cnt += 1 # +1 2 3 4 5 6
        if cnt % 2 != 0: # T, F T F T F
            moving += 1 # 1 1 2 2 3 3
        move += moving # 1 2 4 6 9 12

    print(cnt)