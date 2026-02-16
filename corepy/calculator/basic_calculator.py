# LC
# Given a string s which represents an expression, evaluate this expression and return its value.
#
# The integer division should truncate toward zero.
#
# You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].
#
# Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
#
#
#
# Example 1:
#
# Input: s = "3+2*2"
# Output: 7
# Example 2:
#
# Input: s = " 3/2 "
# Output: 1
# Example 3:
#
# Input: s = " 3+5 / 2 "
# Output: 5

def calc(input: str) -> int:

    current_num: str = ''
    current_op: str = '+'
    stack: list = []

    for i, ch in enumerate(input):
        # parse numbers
        if ch.isdigit():
            current_num += ch

        # end conditions
        if (ch.isspace() or i == len(input) -1) or ch in "+-*/":
            if current_op == '+':
                stack.append(int(current_num) if current_num else 0)
            if current_op == '-':
                stack.append(-int(current_num))
            if current_op == '*':
                stack.append(stack.pop() * int(current_num))
            if current_op == '/':
                stack.append(stack.pop() // int(current_num))
            current_op = ch
            current_num = ''


    result: int = 0
    while stack:
        result += stack.pop()

    return result


print(calc("4-5+2*4-1"))


def calc(input: str) -> int:
    result: int = 0
    stack: list = []
    current_num: str = ''
    current_op: str = '+'
    for i, ch in enumerate(input):
        if ch.isdigit():
            current_num += ch

        if ch in ['+', '-', '*', '/'] or ( (ch.isspace() or i == len(input) - 1) and len(current_num) > 0):
            if current_op == '+':
                stack.append(int(current_num))
            elif current_op == '-':
                stack.append(-int(current_num))
            elif current_op == '*':
                opA = stack.pop()
                stack.append(opA * int(current_num))
            elif current_op == '/':
                opA = stack.pop()
                stack.append(opA // int(current_num))

            current_num = ''
            current_op = ch

    while stack:
        result += stack.pop()

    return result
