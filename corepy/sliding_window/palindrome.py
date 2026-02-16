def isPalindrome(input: str) -> bool:
    L, R = 0, len(input) - 1
    while L < R:
        if input[L] != input[R]:
            return False
        L += 1
        R -= 1
    return True


for i in ['racecar', 'eye', 'miko', 'mashi']:
    print(f"{i}: {isPalindrome(i)}")


#
# Reverse using slicing
def is_palindrome(text):
    text = ''.join(filter(str.isalnum, text)).lower()
    return text == text[::-1]


def validPalindrome(s: str) -> bool:
    def palindrome(str):
        L = 0
        R = len(str) - 1
        while L < R:
            if str[L] != str[R]:
                return False
            L += 1
            R -= 1
        return True

    L = 0
    R = len(s) - 1
    mismatch = 0

    while L < R:
        if s[L] != s[R]:
            mismatch += 1
            if mismatch > 1:
                return False
            elif mismatch == 1:
                print(f"check {s[L:R]} or {s[L+1:R+1]} ")
                return palindrome(s[L:R]) or palindrome(s[L+1:R+1])
        L += 1
        R -= 1
    return True

print(validPalindrome("abc"))