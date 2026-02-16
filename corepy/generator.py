# Generator
from collections import deque


def generate(seed):
    i: int = 0
    while i < seed * 5:
        yield i + 1
        i += 1


# generator are compile-time state
g = generate(5)
print(next(g))
print(next(g))
for i in g:
    print(i)



def search(token, text, maxlen=3):
    history = deque(maxlen=maxlen)
    for line in text:
        if token in line:
            yield line, history
        history.append(line)

print("X-----------------------------------X")
with open('sequence.py') as foo:
    lines = foo.readlines()
    for match, prev in search('readlines', lines, 1):
        for pline in prev:
            print(f"{pline.rstrip()}")
        print(f"{match.rstrip()}")
        print("X-----------------------------------X")