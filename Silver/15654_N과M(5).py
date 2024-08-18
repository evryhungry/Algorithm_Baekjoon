'''
N개의 자연수와 자연수 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
N개의 자연수는 모두 다른 수이다.

N개의 자연수 중에서 M개를 고른 수열

permutation 즉 순열을 이용해서 풀엇다
'''
import sys
from itertools import permutations, combinations

N, M = map(int, sys.stdin.readline().split())
P = sorted(permutations([int(x) for x in  sys.stdin.readline().split()], M))

for p in P:
    for i in p:
        print(i, end=' ')
    print('')
