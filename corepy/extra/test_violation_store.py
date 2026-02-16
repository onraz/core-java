import pytest

from violation_store import PolicyViolation, ViolationStore

_sample = [
    PolicyViolation(1, "spam", "2025-01-01"),
    PolicyViolation(2, "harassment", "2025-01-02"),
    PolicyViolation(1, "misinformation", "2025-01-03"),
    PolicyViolation(3, "spam", "2025-01-04"),
]

store = ViolationStore(_sample)


def test_add_keeps_sorted():
    extra = PolicyViolation(99, "test", "2025-01-02")
    store.add(extra)
    dates = [v.date for v in store]
    assert dates == sorted(dates)


@pytest.mark.parametrize(
    "start,end,count",
    [
        ("2025-01-01", "2025-01-02", 3),
        ("2025-01-03", "2025-01-04", 2),
        ("2024-12-31", "2025-01-01", 1),
        ("2025-02-01", "2025-02-02", 0),
    ],
)
def test_find_by_range(start, end, count):
    assert len(store.find_by_date_range(start, end)) == count

