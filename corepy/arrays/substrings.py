def substring(input):
    for i in range(len(input) - 1):
        for j in range(i + 1, len(input)):
            print(f"{input[i:j]}")


substring("abcd")
print("done")