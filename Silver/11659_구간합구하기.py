import sys

N, M = map(int, sys.stdin.readline().split())
list_n = list(map(int, sys.stdin.readline().split()))

# 누적합 계산 Prefix Sum !!
prefix_sum = [0] * (N + 1)
for i in range(1, N + 1):
    prefix_sum[i] = prefix_sum[i-1] + list_n[i-1]

for _ in range(M):
    a, b = map(int, sys.stdin.readline().split())
    print(prefix_sum[b] - prefix_sum[a - 1])