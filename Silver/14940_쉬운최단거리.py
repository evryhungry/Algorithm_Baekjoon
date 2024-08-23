import sys
from collections import deque

N, M = map(int, sys.stdin.readline().split())
graph = []
start = None

for i in range(N):
    row = list(map(int, sys.stdin.readline().rstrip("\n").split()))
    graph.append(row)
    for j in range(M):
        if row[j] == 2:
            start = (i, j)

visited = [[-1] * M for _ in range(N)]
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]


def bfs(x, y):
    queue = deque()
    queue.append((x, y))
    visited[x][y] = 0

    while queue:
        x, y = queue.popleft()

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if 0 <= nx < N and 0 <= ny < M and visited[nx][ny] == -1:
                if graph[nx][ny] == 1:
                    visited[nx][ny] = visited[x][y] + 1
                    queue.append((nx, ny))
                elif graph[nx][ny] == 0:
                    visited[nx][ny] = 0

bfs(start[0], start[1])

for i in range(N):
    for j in range(M):
        if graph[i][j] == 0:
            print(0, end=' ')
        else:
            print(visited[i][j], end=' ')
    print()