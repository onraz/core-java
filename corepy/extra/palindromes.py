
INPUT = ["eyes", "ink", "ee", "racecar", "mim"]


def is_palindrome(input: str) -> bool:
    if not input or len(input) < 2:
        return False
    else:
        left = 0
        right = len(input) - 1
        while left < right:
            if input[left] != input[right]:
                return False
            left += 1
            right -= 1
        return True


# test cases
for i in INPUT:
    print(f"is_palindrome({i}): {is_palindrome(i)}")