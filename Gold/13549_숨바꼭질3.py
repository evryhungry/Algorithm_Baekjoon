import sys
from collections import deque

def bfs():
    q = deque()
    q.append(N)
    while q:
        v = q.popleft()
        if v == K:
            print(dist[v])
            return
        for i in (v-1, v+1, v*2):
            if 0 <= i <= MAX and not dist[i]:
                if i == v*2 and i != 0:
                    dist[i] = dist[v]
                    q.appendleft(v * 2)
                else:
                    dist[i] = dist[v] + 1
                    q.append(i)


MAX = 10**5
dist = [0] * (MAX+1)
N, K = map(int, sys.stdin.readline().split())

if N == K:
    print(0)
else:
    bfs()

