N, r, c = map(int, input().split())
# 재귀적 풀이
# r과 c가 2배 될때마다 retrun 값이 4배가 되는 상황
# 3(1, 1, 1) -> 12(2, 2, 2)
# 15(2, 3, 3) -> 60(3, 6, 6)
# 8(2, 2, 0) -> 32(3, 4, 0)
def Z(N, r, c):
    if N == 0:
        return 0
    return 2*(r%2)+c%2 + 4*Z(N-1, r//2, c//2)

print(Z(N, r, c))
