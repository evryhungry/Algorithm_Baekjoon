import sys
from collections import deque

N = int(sys.stdin.readline())
tree = [[] for _ in range(N + 1)]

for _ in range(1, N):
    list_node = list(map(int, sys.stdin.readline().split()))
    tree[list_node[0]].append((list_node[1], list_node[2]))
    tree[list_node[1]].append((list_node[0], list_node[2]))


def bfs(start):
    queue = deque()
    queue.append((start, 0))
    visits = [False for _ in range(N + 1)]
    visits[start] = True
    node, dist = 0, 0

    while queue:
        cnt_node, cnt_dist = queue.popleft()

        for fnd_node, fnd_dist in tree[cnt_node]:
            if not visits[fnd_node]:
                cal_dist = cnt_dist + fnd_dist
                queue.append((fnd_node, cal_dist))
                visits[fnd_node] = True

                if dist < cal_dist:
                    node = fnd_node
                    dist = cal_dist

    return node, dist


point, _ = bfs(1)
_, distance = bfs(point)
print(distance)