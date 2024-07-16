n = int(input())
members = [tuple(map(int, input().split())) for _ in range(n)]
for i in members:
    rank = 1
    for j in members:
        if i[0] < j[0] and i[1] < j[1]:
            rank += 1
    print(rank, end=" ")