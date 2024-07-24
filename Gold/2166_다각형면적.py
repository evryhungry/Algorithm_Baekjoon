N = int(input())
polygon = [list(map(int, input().split())) for _ in range(N)]

plus = 0
minus = 0

for i in range(N):
    if i == (N-1):
        plus += polygon[i][0] * polygon[0][1]
        minus += polygon[i][1] * polygon[0][0]
    else:
        plus += polygon[i][0] * polygon[i+1][1]
        minus += polygon[i][1] * polygon[i+1][0]

print(round(abs((plus-minus)/2), 2))