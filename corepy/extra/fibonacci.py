
CACHE = {}
h = 0
m = 0

def fibonacci(n: int) -> int:
    global h, m
    if n <= 2:
        print("hit")
        h += 1
        return 1
    else:
        if n in CACHE:
            print("hit")
            h += 1
            return CACHE[n]
        else:
            print("miss")
            m += 1
            CACHE[n] = fibonacci(n-1) + fibonacci(n-2)
        return CACHE[n]


# test cases
for i in range(1, 100):
    print(f"fib({i}): {fibonacci(i)}")

print(f"hit {h}, miss {m}, out of {h+m} hrate {h/(h+m) * 100}")