"""
방향성이 없는 그래프가 주어진다.
세준이는 1번 정점에서 N번 정점으로 최단 거리로 이동하려고 한다.
또한 세준이는 두 가지 조건을 만족하면서 이동하는 특정한 최단 경로를 구하고 싶은데, 그것은 바로 임의로 주어진 두 정점은 반드시 통과해야 한다는 것이다.

세준이는 한번 이동했던 정점은 물론, 한번 이동했던 간선도 다시 이동할 수 있다.
하지만 반드시 최단 경로로 이동해야 한다는 사실에 주의하라.
1번 정점에서 N번 정점으로 이동할 때, 주어진 두 정점을 반드시 거치면서 최단 경로로 이동하는 프로그램을 작성하시오.

"""
import sys
import heapq
INF = int(1e9)

N, M = map(int, sys.stdin.readline().split())
graph = [[] for _ in range(N+1)]


for _ in range(M):
    a, b, cost = map(int, sys.stdin.readline().split())
    graph[a].append((b, cost))
    graph[b].append((a, cost))


def dijkstra(start):
    distance = [INF] * (N + 1)
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


v1, v2 = map(int,  sys.stdin.readline().split())
original_distance = dijkstra(1)
v1_distance = dijkstra(v1)
v2_distance = dijkstra(v2)

v1_first = original_distance[v1] + v1_distance[v2] + v2_distance[N]
v2_first = original_distance[v2] + v2_distance[v1] + v1_distance[N]

result = min(v1_first, v2_first)
print(result if result < INF else -1)