def fib_memo(n):
    a, b = 1, 0
    for i in range(n):
        a, b = b, a + b
    return a, b

T = int(input())
for _ in range(T):
    N = int(input())
    result = fib_memo(N)
    print(result[0], result[1])