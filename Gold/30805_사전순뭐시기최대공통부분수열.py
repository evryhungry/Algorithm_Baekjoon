import sys
input = sys.stdin.readline
from heapq import heappush, heappop


N = int(input())
A = list(map(int, input().split()))
M = int(input())
B = list(map(int, input().split()))
hq_a = []
hq_b = []
for i in range(N):
    heappush(hq_a, (-A[i], i))
for i in range(M):
    heappush(hq_b, (-B[i], i))

result = [[0, -1, -1]]
a, a_idx = heappop(hq_a)
b, b_idx = heappop(hq_b)
a, b = -a, -b
while True:
    if a == b:
        result.append((a, a_idx, b_idx))
        if not hq_a or not hq_b:
            break
        while hq_a:
            temp, temp_idx = heappop(hq_a)
            if temp_idx > result[-1][1]:
                a = -temp
                a_idx = temp_idx
                break
        if temp_idx != a_idx:
            break
        while hq_b:
            temp, temp_idx = heappop(hq_b)
            if temp_idx > result[-1][2]:
                b = -temp
                b_idx = temp_idx
                break
        if temp_idx != b_idx:
            break
    elif a > b:
        if hq_a:
            while hq_a:
                temp, temp_idx = heappop(hq_a)
                if temp_idx > result[-1][1]:
                    a = -temp
                    a_idx = temp_idx
                    break
            if temp_idx != a_idx:
                break
        else:
            break
    elif a < b:
        if hq_b:
            while hq_b:
                temp, temp_idx = heappop(hq_b)
                if temp_idx > result[-1][2]:
                    b = -temp
                    b_idx = temp_idx
                    break
            if temp_idx != b_idx:
                break
        else:
            break

print(len(result) - 1)
for i in range(1, len(result)):
    print(result[i][0], end=" ")
