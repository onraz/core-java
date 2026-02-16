# Implement two functions:
# flipCoin() → returns "Heads" or "Tails" with equal probability.
# rand1to7() → returns a random integer between 1 and 7 (inclusive), with uniform probability.
#
# You must not use any pre-built random number generator that directly produces values in that range
# (for example, no randint(1,7) in rand1to7()).
#
#  You may assume you have access to a function flipCoin()
#  which returns either Heads or Tails with equal probability.
#
#
import random
def flipCoin() -> str:
    """
    Returns "Heads" or "Tails" with equal probability.
    """
    return "Heads" if random.random() < 0.5 else "Tails"


def rand1to7() -> int:
    """
        Returns an integer between 1 and 7 (inclusive) using only flipCoin().
    """
    while True:
        bit1 = 1 if flipCoin() == "Heads" else 0
        bit2 = 1 if flipCoin() == "Heads" else 0
        bit3 = 1 if flipCoin() == "Heads" else 0

        randnum = (bit1 << 2) | (bit2 << 1) | bit3

        if randnum != 0:
            return randnum

if __name__ == '__main__':

    print(f"Random num: {rand1to7()}")