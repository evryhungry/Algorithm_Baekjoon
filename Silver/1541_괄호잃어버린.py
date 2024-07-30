import sys

parts = sys.stdin.readline().rstrip("\n").split("-")
result = 0

results = []
for i, part in enumerate(parts):
    sum_part = sum(int(num) for num in part.split("+"))
    if i == 0:
        result += sum_part
    else:
        result -= sum_part


print(result)