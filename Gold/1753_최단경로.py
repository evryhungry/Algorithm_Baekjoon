import sys
import heapq
INF = int(1e9)

V, E = map(int, sys.stdin.readline().split())
graph = [[] for _ in range(V+1)]
start_point = int(sys.stdin.readline())

for _ in range(E):
    u, v, w = map(int, sys.stdin.readline().split())
    graph[u].append((v, w))

def dijkstra(start):
    distance = [INF] * (V + 1)
    queue = []
    heapq.heappush(queue, (0, start))
    distance[start] = 0

    while queue:
        dist, now = heapq.heappop(queue)
        if distance[now] < dist:
            continue

        for b, c in graph[now]:
            cost = dist + c
            if cost < distance[b]:
                distance[b] = cost
                heapq.heappush(queue, (cost, b))

    return distance

answer = dijkstra(start_point)
print(*(answer[i] if answer[i] < INF else 'INF' for i in range(1, V + 1)), sep='\n')

