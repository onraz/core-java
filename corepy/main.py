# Unpack an N-element tuple or sequence into collection of N variables
p = ('4', 5, 6)
x, _, z = p
print(p)
print(x, z)

data = []
print(type(data))
print(type(p))
print(type(x))

# tuples are immutables, where as lists can be mutable
# unpacking can work with any iterables, whetehr its string, generator or any other sequence


# Python star expressions can be used to address the problem
def drop_first_last(grades):
    first, *middle, last = grades
    print(middle)


drop_first_last([1, 2, 3, 4, 5])


p = (4, 5)
x, y = p

records = [
    (1, 2),
    (1, 2, 3, 4),
    (1, 2, 3)
]


def foo(x, y):
    print(x, y)

def bar(p):
    print(p)

for a, *rec in records:
    foo(a, rec)