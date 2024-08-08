import sys
"""
    이분 탐색으로 풀었을 때 가장 쉬운 풀이가 되는 것 같음
    시작은 0로 하고 end 값을 나무 중에서 가장 큰 값을 가져와 탐색
    프린트 값은 start로 나타내 준다. 그럼 끝 
"""


def binary_search():
    start = 0
    end = trees[n - 1]

    while start + 1 < end:
        mid = (start + end) // 2

        if check(mid):
            start = mid
        else:
            end = mid

    print(start)


def check(mid):
    log = 0
    for tree in trees:
        if tree > mid:
            log += tree - mid

    if log >= m:
        return True
    else:
        return False


n, m = map(int, sys.stdin.readline().split())
trees = list(map(int, sys.stdin.readline().split()))
trees.sort()

binary_search()
