import sys

N = int(sys.stdin.readline())
scales = list(map(int, sys.stdin.readline().rstrip("\n").split()))

low = 0
high = N - 1

ans = abs(scales[low] + scales[high])
ans_low = low
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