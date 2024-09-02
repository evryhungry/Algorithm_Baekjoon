import sys
import heapq
INF = int(1e9)

V = int(sys.stdin.readline().rstrip('\n'))
E = int(sys.stdin.readline().rstrip('\n'))

graph = [[] for _ in range(V+1)]
for _ in range(E):
    u, v, w = map(int, sys.stdin.readline().split())
    graph[u].append((v, w))

start_point, arrive_point = map(int, sys.stdin.readline().split())



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
print(answer[arrive_point])
# print(answer[start_point][arrive_point])

