from typing import List, Tuple

# Sample data: array of tuples (pin_id, policy, date)
policy_violations: List[Tuple[int, str, str]] = [
    (1, "spam", "2025-01-01"),
    (2, "harassment", "2025-01-02"),
    (1, "misinformation", "2025-01-03"),
    (3, "spam", "2025-01-04"),
    (4, "copyright", "2025-01-05"),
    (2, "spam", "2025-01-06"),
    (5, "adult_content", "2025-01-07"),
    (1, "spam", "2025-01-08"),
    (6, "violence", "2025-01-09"),
    (3, "harassment", "2025-01-10"),
    (7, "misinformation", "2025-01-11"),
    (4, "spam", "2025-01-12"),
    (8, "hate_speech", "2025-01-13"),
    (5, "spam", "2025-01-14"),
    (2, "copyright", "2025-01-15"),
    (9, "spam", "2025-01-16"),
    (1, "harassment", "2025-01-17"),
    (10, "misinformation", "2025-01-18"),
    (3, "spam", "2025-01-19"),
    (6, "adult_content", "2025-01-20"),
    (11, "spam", "2025-01-21"),
    (4, "harassment", "2025-01-22"),
    (7, "spam", "2025-01-23"),
    (12, "violence", "2025-01-24"),
    (2, "misinformation", "2025-01-25"),
    (5, "copyright", "2025-01-26"),
    (8, "spam", "2025-01-27"),
    (1, "adult_content", "2025-01-28"),
    (13, "hate_speech", "2025-01-29"),
    (3, "copyright", "2025-01-30"),
]

# ------------------------------------------------------------------
# Iterative binary-search helpers (work directly on the sorted list)
# ------------------------------------------------------------------


def _left_boundary_iter(data: List[Tuple[int, str, str]], target: str) -> int:
    """Return the index of the first element whose date is >= *target*.

    If all dates are < *target*, returns len(data) (past the end).
    """
    lo, hi = 0, len(data) - 1
    while lo <= hi:
        mid = (lo + hi) // 2
        if data[mid][2] < target:
            lo = mid + 1
        else:
            hi = mid - 1
    return lo


def _right_boundary_iter(data: List[Tuple[int, str, str]], target: str) -> int:
    """Return the index of the last element whose date is <= *target*.

    If all dates are > *target*, returns -1.
    """
    lo, hi = 0, len(data) - 1
    while lo <= hi:
        mid = (lo + hi) // 2
        if data[mid][2] > target:
            hi = mid - 1
        else:
            lo = mid + 1
    return hi


def find_violations_by_date_range_iter(
    violations: List[Tuple[int, str, str]],
    start_date: str,
    end_date: str,
) -> List[Tuple[int, str, str]]:
    """Return all violations whose date is within *start_date* and *end_date* (inclusive).

    The *violations* list **must** be sorted by the date string (ISO-8601), as it is in
    the sample data above. The function performs two iterative binary searches to find
    the slice boundaries, yielding O(log n) search time and O(k) output size.
    """

    if not violations or start_date > end_date:
        return []

    left = _left_boundary_iter(violations, start_date)
    right = _right_boundary_iter(violations, end_date)

    if left <= right:
        return violations[left : right + 1]
    return []


# Example: Print violations grouped by pin_id
if __name__ == "__main__":
    print("Sample Policy Violations:")
    print(f"Total violations: {len(policy_violations)}")
    print("\nFirst 10 violations:")
    for (pin_id, policy, date) in policy_violations[:2]:
        print(f"  Pin ID: {pin_id}, Policy: {policy}, Date: {date}")
    
   