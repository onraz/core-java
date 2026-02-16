arr = [7,1,5,3,6,4]

# Input: prices = [7,1,5,3,6,4]
# Output: 5
class Interval:
    def __init__(self, start, end):
        self.start = start
        self.end = end

def is_valid(input: str) -> bool:
    brackets = {
        ')' : '(',
        '}' : '{'
    }

    stack = []

    for ch in input:
        if ch in '({[':
            stack.append(ch)
        else:
            prior = stack.pop()
            if not stack or brackets[ch] != prior:
                return False

    return len(stack) == 0