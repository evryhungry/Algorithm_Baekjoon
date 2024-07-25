# 보석 도둑
# 처음 한줄 N, K (보석, 가방 갯수)
# N 만큼 보석의 갯수
# K 만큼 가방의 갯수
# 얼마나 들어가나

# 1-1 쥬얼을 값 기준으로 역솔트를 하고
# 1-2 가방의 무게를 기준으로 쥬얼 값이 큰 것을 들어가게 만듬
# 시간 복잡도 O(N log N + K log K + N * K) -> 1초안에 되지 않음....
# 2. heap queue 를 사용하도록 함
# 2-1 쥬얼값과 가방의 무게를 기준으로 모두 sort
# 2-2 max heap 에 주얼의 가장 큰 가치가 있는 것부터 revers sort해서 넣고 -->
# 2-3 가장 큰 값을 max heap 에서 빼낸다. --> 무메가 가장 작은 것부터 시작 한 것으로 다음 가방이 들어 왔아도 해당 max heap을 사용할 수 있다.
# 2-3 시간 복잡도 O(N log N + K log K + N log K)
import sys, heapq

N, K = map(int, sys.stdin.readline().split())
jewels = [tuple(map(int, sys.stdin.readline().split())) for _ in range(N)]
bags = list(int(sys.stdin.readline().rstrip("\n")) for _ in range(K))

jewels.sort()
bags.sort()

much = 0
max_heap = []
jewel_index = 0

for bag in bags:
    while jewel_index < N and jewels[jewel_index][0] <= bag:
        heapq.heappush(max_heap, -jewels[jewel_index][1])
        jewel_index += 1
    if max_heap:
        much += -heapq.heappop(max_heap)

print(much)