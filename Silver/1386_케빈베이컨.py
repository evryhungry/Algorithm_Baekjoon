import sys
from collections import deque

def bfs(start):
    queue = deque([start])
    distances = [-1] * (n + 1)
    distances[start] = 0

    while queue:
        v = queue.popleft()
        for i in graph[v]:
            if distances[i] == -1:
                distances[i] = distances[v] + 1
                queue.append(i)

    return distances

n, m = map(int, sys.stdin.readline().rstrip("\n").split())
graph = [[] for _ in range(n + 1)]
for _ in range(m):
    a, b = map(int, sys.stdin.readline().rstrip("\n").split())
    graph[a].append(b)
    graph[b].append(a)

bfs(1)
min_bacon = float('inf')
min_index = -1
for i in range(1, n+1):
    distances = bfs(i)
    bacon_sum = sum(d for d in distances if d != -1)
    if bacon_sum < min_bacon:
        min_bacon = bacon_sum
        min_index = i

print(min_index)