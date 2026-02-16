import pytest

from policy_violations import (
    policy_violations,
    find_violations_by_date_range_iter,
)


@pytest.mark.parametrize(
    "start_date,end_date,expected_count",
    [
        ("2025-01-10", "2025-01-15", 6),  # middle range
        ("2025-01-01", "2025-01-05", 5),  # beginning of list
        ("2025-01-25", "2025-01-30", 6),  # end of list
        ("2025-01-15", "2025-01-15", 1),  # single day
        ("2025-02-01", "2025-02-05", 0),  # no matches
    ],
)
def test_find_violations_counts(start_date, end_date, expected_count):
    results = find_violations_by_date_range_iter(policy_violations, start_date, end_date)
    assert len(results) == expected_count


def test_results_are_within_range():
    start_date, end_date = "2025-01-10", "2025-01-20"
    results = find_violations_by_date_range_iter(policy_violations, start_date, end_date)

    for _pin_id, _policy, date in results:
        assert start_date <= date <= end_date, f"Date {date} out of range"

    # ensure results are sorted (should already be, but verify)
    dates = [d for *_rest, d in results]
    assert dates == sorted(dates)
