# with open("main.py") as f:
#     for line in f:
#         print(line.rstrip())
print('File Contents')

with open("main.py") as f:
    lineNum = 1
    while True:
        line = next(f, None)
        if line is None:
            break
        print(f'{lineNum}:{line.rstrip()}')
        lineNum += 1
