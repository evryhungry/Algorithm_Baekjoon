'''
트리의 지름이란, 트리에서 임의의 두 점 사이의 거리 중 가장 긴 것을 말한다. 트리의 지름을 구하는 프로그램을 작성하시오.

트리가 입력으로 주어진다.
먼저 첫 번째 줄에서는 트리의 정점의 개수 V가 주어지고 (2 ≤ V ≤ 100,000)둘째 줄부터 V개의 줄에 걸쳐 간선의 정보가 다음과 같이 주어진다.
정점 번호는 1부터 V까지 매겨져 있다.

먼저 정점 번호가 주어지고, 이어서 연결된 간선의 정보를 의미하는 정수가 두 개씩 주어지는데, 하나는 정점번호, 다른 하나는 그 정점까지의 거리이다.
예를 들어 네 번째 줄의 경우 정점 3은 정점 1과 거리가 2인 간선으로 연결되어 있고, 정점 4와는 거리가 3인 간선으로 연결되어 있는 것을 보여준다.
각 줄의 마지막에는 -1이 입력으로 주어진다.
주어지는 거리는 모두 10,000 이하의 자연수이다.

첫째 줄에 트리의 지름을 출력한다.

간선길이 가장 긴거 출력..?

간선의 길이가 가장 긴 노드를 먼저 구한다.
    1) 임의의 node a에서 가장 먼 거리에 있는 node b를 구한다.
    2) node b에서 가장 먼 거리에 있는 node c를 구한다.
    3) 트리의 지름은 node b와 c 사이의 거리이다.

'''
import sys
from collections import deque

N = int(sys.stdin.readline())
tree = [[] for _ in range(N + 1)]

for _ in range(N):
    list_node = list(map(int, sys.stdin.readline().split()))

    infancy = list_node[0]

    cont_num = 1
    while True:
        if list_node[cont_num] == -1:
            break

        index_node = list_node[cont_num]
        index_dist = list_node[cont_num + 1]
        tree[infancy].append((index_node, index_dist))

        cont_num += 2


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