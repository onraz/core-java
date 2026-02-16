"""Simple iterative solution for generating all subsets (power set) of a given iterable.

Usage:
    >>> generate_subsets([1, 2, 3])
    [[], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]]

The algorithm builds the power set by iteratively adding each element to the
existing partial subsets. Time complexity: O(n * 2^n) (inevitable for power
set generation).
"""

from typing import Iterable, List, Sequence, TypeVar

T = TypeVar("T")


def generate_subsets(items: Iterable[T]) -> List[List[T]]:
    """Return the power set of *items* as a list of lists (iterative)."""
    subsets: List[List[T]] = [[]]  # start with the empty subset
    for item in items:
        # For each existing subset, add a new subset that also contains *item*
        new_subsets = [current + [item] for current in subsets]
        subsets.extend(new_subsets)
    return subsets


if __name__ == "__main__":
    sample: Sequence[int] = [1, 2, 3]
    for subset in generate_subsets(sample):
        print(subset)

