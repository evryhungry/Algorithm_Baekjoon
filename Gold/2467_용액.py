import sys

N = int(sys.stdin.readline())
scales = list(map(int, sys.stdin.readline().rstrip("\n").split()))

low = 0
high = N - 1

ans = abs(scales[low] + scales[high])
ans_low = low
# answer index 찾아 내기
# 왜 answer index 가 있어야 하는거?
# while 문을 빠져 나와고 바로 low index 를 사용 하는 경우
# 1. low 와 high 가 같아 진다
# 2. 같아 지고 나오면 print 값 또한 같게 된다 * 가장 큰 문제가 되는 것이고
# 3. ans 에 대한 refresh 가 되지 않는다
ans_high = high

while low < high:
    mid = scales[low] + scales[high]

    if abs(mid) < ans:
        ans_low = low
        ans_high = high
        ans = abs(mid)

        if mid == 0:
            break

    if mid < 0:
        low += 1
    else:
        high -= 1

print(scales[ans_low], scales[ans_high])