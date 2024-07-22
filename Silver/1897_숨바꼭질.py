from collections import deque

def bfs():
    q = deque()
    q.append(N)
    while q:
        v = q.popleft()
        if v == K:
            print(dist[v])
            break
        for i in (v-1, v+1, v*2):
            if 0 <= i <= MAX and not dist[i]:
                dist[i] = dist[v] + 1
                q.append(i)

MAX = 10**5
dist = [0] * (MAX+1)
N, K = map(int, input().split())

bfs()

