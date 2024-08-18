'''
이 문제는 아주 평범한 배낭에 관한 문제이다.

한 달 후면 국가의 부름을 받게 되는 준서는 여행을 가려고 한다.
세상과의 단절을 슬퍼하며 최대한 즐기기 위한 여행이기 때문에, 가지고 다닐 배낭 또한 최대한 가치 있게 싸려고 한다.

준서가 여행에 필요하다고 생각하는 N개의 물건이 있다.
각 물건은 무게 W와 가치 V를 가지는데, 해당 물건을 배낭에 넣어서 가면 준서가 V만큼 즐길 수 있다.
아직 행군을 해본 적이 없는 준서는 최대 K만큼의 무게만을 넣을 수 있는 배낭만 들고 다닐 수 있다.
준서가 최대한 즐거운 여행을 하기 위해 배낭에 넣을 수 있는 물건들의 가치의 최댓값을 알려주자.

node
    value
    weight
    benefit = might / weight

'''
import sys

class Knapsack:
    def __init__(self, weight, value):
        self.weight = weight
        self.value = value
        self.benefit = value / weight

    def get_benefit(self):
        return self.benefit

    def get_value_and_weight(self):
        return self.weight, self.value

N, W = map(int, sys.stdin.readline().split())
backPack_list = list(Knapsack(*map(int, sys.stdin.readline().split())) for _ in range(N))
backPack_list.sort(key=lambda bp: bp.get_benefit())

dp = list([0] * (W + 1) for _ in range(N + 1))

for i in range(1, N + 1):
    weights, values = backPack_list[i - 1].get_value_and_weight()

    for j in range(1, W + 1):
        if weights > j:
            dp[i][j] = dp[i - 1][j]
        else:
            dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - weights] + values)


print(dp[-1][-1])
