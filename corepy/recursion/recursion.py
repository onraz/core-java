def factorial(num: int) -> int:
    if num == 1:
        return 1
    return num * factorial(num - 1)

print(factorial(3))
print(factorial(4))
print(factorial(5))

for i in range(1,5):
    print(f"Factorial of {i} is {factorial(i)}")


def fibonacci(num: int)->int:
    if num == 0 or num == 1:
        return 1
    else:
        return fibonacci(num-1) + fibonacci(num-2)

for i in range(1, 6):
    print(f"Fib of {i} is {fibonacci(i)}")












