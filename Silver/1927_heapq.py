import sys
import heapq

N = int(sys.stdin.readline())
heap = []

for _ in range(N):
    n = int(sys.stdin.readline().rstrip("\n"))
    if n == 0:
        if len(heap) == 0:
            print(0)
        else:
            print(heapq.heappop(heap))
    else:
        heapq.heappush(heap, n)