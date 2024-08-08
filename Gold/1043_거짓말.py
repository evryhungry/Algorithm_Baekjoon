import sys

N, M = map(int, sys.stdin.readline().split())
input_line = list(map(int, sys.stdin.readline().split()))
K = input_line[0]
if K > 0:
    true_people = set(input_line[1:])
else:
    true_people = set()

people_in_party = [list(map(int, sys.stdin.readline().split()))[1:] for _ in range(M)]


changed = True
while changed:
    changed = False
    for people in people_in_party:
        people_set = set(people)
        if people_set & true_people:
            new_true_people = true_people.union(people_set) # 이 부분이 달랐지
            if new_true_people != true_people:
                true_people = new_true_people
                changed = True


cnt = 0
for people in people_in_party:
    people_set = set(people)
    if not (people_set & true_people):
        cnt += 1

print(cnt)