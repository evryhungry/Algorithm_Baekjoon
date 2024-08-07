'''
    한 곳에 바이러스가 걸리면 해당 네트워크가 연결된 곳에 바이러스가 걸린다
    1. BFS, DFS 를 사용하여 푸는 것이 맨 처음 떠오름
    2. 제한 시간 1초라 BFS가 적합해 보이는데
    3. 해강 케이스가 최대 100 개 이므로 DFS 로 풀어도 어렵지 않을 것이라 보인다.
    4. 항상 1부터 걸림을 시작
'''
import sys


def dfs(start):
    global cnt
    visits[start] = True

    for i in graph[start]:
        if not visits[i]:
            dfs(i)
            cnt += 1


N = int(sys.stdin.readline())
M = int(sys.stdin.readline())
cnt = 0

graph = [[] for _ in range(N + 1)]

for _ in range(M):
    a, b = map(int, sys.stdin.readline().split())
    graph[a].append(b)
    graph[b].append(a)

visits = [False for _ in range(N + 1)]
dfs(1)
print(cnt)
