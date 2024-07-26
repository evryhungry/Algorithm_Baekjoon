import sys

n = int(sys.stdin.readline().strip())
chessboard = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]
bishops = [0, 0]

# Using two arrays to mark the diagonals.
# diagonal1 covers the diagonals from top-left to bottom-right
# diagonal2 covers the diagonals from top-right to bottom-left
diagonal1 = [[False] * (2 * n - 1) for _ in range(2)]
diagonal2 = [[False] * (2 * n - 1) for _ in range(2)]

def place_bishops(index, count, parity):
    if index >= n * n:
        bishops[parity] = max(bishops[parity], count)
        return

    x = index // n
    y = index % n

    if (x + y) % 2 == parity:
        if chessboard[x][y] == 1 and not diagonal1[parity][x + y] and not diagonal2[parity][x - y + n - 1]:
            # Place bishop
            diagonal1[parity][x + y] = True
            diagonal2[parity][x - y + n - 1] = True
            place_bishops(index + 1, count + 1, parity)
            # Remove bishop
            diagonal1[parity][x + y] = False
            diagonal2[parity][x - y + n - 1] = False

    # Skip this cell
    place_bishops(index + 1, count, parity)

# Process black and white squares separately
place_bishops(0, 0, 0) #흑색
place_bishops(0, 0, 1) #백색

print(sum(bishops))
