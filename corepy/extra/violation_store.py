from __future__ import annotations

from dataclasses import dataclass
from typing import List, Protocol, Sequence


@dataclass(frozen=True, slots=True)
class PolicyViolation:
    """Value object representing a single policy violation."""

    pin_id: int
    policy: str
    date: str  # ISO-8601 (YYYY-MM-DD)


class ViolationSearchStrategy(Protocol):
    """Strategy interface for locating violations in a sorted collection."""

    def find(
        self,
        violations: Sequence[PolicyViolation],
        start_date: str,
        end_date: str,
    ) -> List[PolicyViolation]:
        """Return all violations whose *date* lies in the inclusive range."""
        ...  # pragma: no cover


class IterativeBinarySearchStrategy:
    """Iterative binary-search implementation (O(log n) + O(k))."""

    # Public method to conform to the *ViolationSearchStrategy* protocol
    def find(
        self,
        violations: Sequence[PolicyViolation],
        start_date: str,
        end_date: str,
    ) -> List[PolicyViolation]:
        if not violations or start_date > end_date:
            return []

        # Helper closures work with *date* attribute directly
        def left_boundary() -> int:
            lo, hi = 0, len(violations) - 1
            while lo <= hi:
                mid = (lo + hi) // 2
                if violations[mid].date < start_date:
                    lo = mid + 1
                else:
                    hi = mid - 1
            return lo

        def right_boundary() -> int:
            lo, hi = 0, len(violations) - 1
            while lo <= hi:
                mid = (lo + hi) // 2
                if violations[mid].date > end_date:
                    hi = mid - 1
                else:
                    lo = mid + 1
            return hi

        left = left_boundary()
        right = right_boundary()

        if left <= right:
            # Slicing a Sequence returns suitable type (List for list input)
            return list(violations[left : right + 1])
        return []


class ViolationStore:
    """Repository for policy violations.

    Internally keeps the violations sorted by date to allow efficient
    date-range queries. A custom *search_strategy* implementing
    *ViolationSearchStrategy* may be supplied to change search behaviour
    (e.g. recursive search, linear scan, database query, etc.).
    """

    def __init__(
        self,
        violations: Sequence[PolicyViolation] | None = None,
        *,
        search_strategy: ViolationSearchStrategy | None = None,
    ) -> None:
        # make a private mutable copy
        self._violations: List[PolicyViolation] = (
            sorted(list(violations), key=lambda v: v.date) if violations else []
        )
        self._strategy: ViolationSearchStrategy = (
            search_strategy or IterativeBinarySearchStrategy()
        )

    # ------------------------------------------------------------------
    # Mutation helpers
    # ------------------------------------------------------------------

    def add(self, violation: PolicyViolation) -> None:
        """Insert *violation* keeping the internal list ordered by date."""
        # Simple O(n) insertion; could be improved but adequate for demo.
        idx = self._find_insert_position(violation.date)
        self._violations.insert(idx, violation)

    def extend(self, violations: Sequence[PolicyViolation]) -> None:
        for v in violations:
            self.add(v)

    # ------------------------------------------------------------------
    # Query helpers
    # ------------------------------------------------------------------

    def find_by_date_range(self, start_date: str, end_date: str) -> List[PolicyViolation]:
        """Return violations whose date lies in the inclusive range."""
        return self._strategy.find(self._violations, start_date, end_date)

    # ------------------------------------------------------------------
    # Internal helpers
    # ------------------------------------------------------------------

    def _find_insert_position(self, target_date: str) -> int:
        """Binary search insertion index for *target_date* (private)."""
        lo, hi = 0, len(self._violations) - 1
        while lo <= hi:
            mid = (lo + hi) // 2
            if self._violations[mid].date < target_date:
                lo = mid + 1
            else:
                hi = mid - 1
        return lo

    # ------------------------------------------------------------------
    # Dunder methods to behave like a collection (optional convenience)
    # ------------------------------------------------------------------

    def __iter__(self):
        return iter(self._violations)

    def __len__(self):
        return len(self._violations)

    def __getitem__(self, item):
        return self._violations[item]

