## 재귀, 분정복
import sys

n = int(sys.stdin.readline().rstrip("\n"))
sqr = [list(map(int, sys.stdin.readline().rstrip("\n").split())) for _ in range(n)]

white = 0
blue = 0

def square(x, y, N):
    global white, blue
    color = sqr[x][y]

    # 좌표 + N 을 하지 않을 시 해당 좌표부터 끝까지 가지 않는 경우가 발생한다.
    for i in range(x, x+N):
        for j in range(y, y+N):
            if color != sqr[i][j]:
                square(x, y, N // 2)
                square(x + N // 2, y, N // 2)
                square(x, y + N // 2, N // 2)
                square(x + N // 2, y + N // 2, N // 2)
                return # return 을 하지 않았을 시 모든 for 문을 돌아가게 만들기에 오류 발생 -> 되게 큰 수가 나오게 됨

    if color == 0:
        white += 1
    else:
        blue += 1


square(0, 0, n)
print(white)
print(blue)

