import sys
sys.setrecursionlimit(10 ** 6)  # 재귀 호출 한계를 늘려줌 * 파이썬 기본: 제귀 문제 풀때 필수

T = int(sys.stdin.readline())

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]


def dfs(y, x):
    global m, n
    if x < 0 or y < 0 or x >= m or y >= n:
        return False

    if graph[y][x] == 1:
        graph[y][x] = 0

        for i in range(4):
            dfs(y + dy[i], x + dx[i])

        return True

    return False


for _ in range(T):
    cnt = 0
    m, n, k = list(map(int, sys.stdin.readline().rstrip("\n").split()))

    graph = [[0] * m for _ in range(n)]

    for _ in range(k):
        x, y = list(map(int, sys.stdin.readline().rstrip("\n").split()))
        graph[y][x] = 1

    for i in range(n):
        for j in range(m):
            if dfs(i, j):
                cnt += 1

    print(cnt)
