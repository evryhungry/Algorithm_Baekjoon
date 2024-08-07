from collections import deque


def dfs(start):  #dfs 정의
    visits[start] = True
    print(start, end=" ")

    for neighbor in graph[start]:
        if not visits[neighbor]:
            dfs(neighbor)


def bfs(start):
    queue = deque([start])
    visits[start] = True

    while queue:
        v = queue.popleft()
        print(v, end=" ")

        for i in graph[v]:
            if not visits[i]:
                queue.append(i)
                visits[i] = True


n, m, s = map(int, input().split())

graph = [[] for _ in range(n + 1)]
for _ in range(m):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

graph = [sorted(neighbors) for neighbors in graph]

visits = [False for _ in range(n + 1)]
dfs(s)
print()
visits = [False for _ in range(n + 1)]
bfs(s)
