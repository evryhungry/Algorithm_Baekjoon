import sys

N = int(sys.stdin.readline())
greedy = [list(map(int, sys.stdin.readline().rstrip("\n").split())) for _ in range(N)]

greedy.sort(key=lambda x: (x[1], x[0]))

count = 0
last_finish_time = 0

for activity in greedy:
    if activity[0] >= last_finish_time:
        count += 1
        last_finish_time = activity[1]

print(count)