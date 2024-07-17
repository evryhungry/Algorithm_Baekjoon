n = int(input())
metrix = [tuple(map(int, input().split())) for _ in range(n)]
dp = [[float('inf')] * n for _ in range(n)]
for i in range(n):
    dp[i][i] = 0

for length in range(2, n + 1):
    for i in range(n - length + 1):
        j = i + length - 1
        for k in range(i, j):
            dp[i][j] = min(dp[i][j], dp[i][k] + dp[k + 1][j] + metrix[i][0] * metrix[k][1] * metrix[j][1])

print(dp[0][n - 1])

# 메모리 초과 나타남.