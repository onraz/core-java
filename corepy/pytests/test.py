from typing import Tuple

import pytest


def test_uppercase():
    assert "raz".upper() == "RAZ"

def test_reversed():
    assert list(reversed([1, 2, 3])) == [3, 2, 1]

def test_some_primes():
    assert 37 in {
        num
        for num in range(2, 50)
        if not any(num % div == 0 for div in range(2, num))
    }

@pytest.fixture
def example():
    return "abc"

def test_example(example):
    assert "abc" is example, "should be euals"


def test_div():
    with pytest.raises(ZeroDivisionError):
        12 // 0

import pytest

class Tester:
    @staticmethod
    @pytest.fixture(params=[(2, 3), (5, 7), (10, 0)])
    def numbers(request):
        return request.param

    @staticmethod
    def add(num1, num2):
        return num1 + num2

    @staticmethod
    def test_add(numbers: Tuple[int, int]):
        n1, n2 = numbers
        assert n1 + n2 == Tester.add(n1, n2)


def is_palindrome(s: str):
    i = 0
    j = len(s) - 1

    while i < j:
        if s[i] != s[j]:
            return False
        i += 1
        j -= 1

    return True

@pytest.mark.parametrize("input, result", [
    ("", True),
    ("a", True),
    ("racecar", True),
    ("bye", False)
])
def test_palindrome(input, result):
    assert is_palindrome(input) == result


from dataclasses import dataclass, field
import pytest

@dataclass
class FileSizeTestCase:
    size_bytes: int
    expected_result: str
    id: str = field(init=False)
    expected_error: type[Exception] = None
    error_message: str | None = None

    def __post_init__(self):
        self.id = f"test_format_file_size_{self.size_bytes}_bytes"


test_cases = [
    FileSizeTestCase(0, "0B"),
    FileSizeTestCase(1, "1.00 B"),
    FileSizeTestCase(1024, "1.00 KB"),
    FileSizeTestCase(1024**2, "1.00 MB"),
    FileSizeTestCase(1024**3, "1.00 GB"),
    FileSizeTestCase(1024**4, "1.00 TB"),
    FileSizeTestCase(
        -1, "", ValueError, "Size cannot be negative"
    ),  # Test case expecting an error
]

from typing import Tuple

import pytest


def test_uppercase():
    assert "raz".upper() == "RAZ"

def test_reversed():
    assert list(reversed([1, 2, 3])) == [3, 2, 1]

def test_some_primes():
    assert 37 in {
        num
        for num in range(2, 50)
        if not any(num % div == 0 for div in range(2, num))
    }

@pytest.fixture
def example():
    return "abc"

def test_example(example):
    assert "abc" is example, "should be euals"


def test_div():
    with pytest.raises(ZeroDivisionError):
        12 // 0

import pytest

class Tester:
    @staticmethod
    @pytest.fixture(params=[(2, 3), (5, 7), (10, 0)])
    def numbers(request):
        return request.param

    @staticmethod
    def add(num1, num2):
        return num1 + num2

    @staticmethod
    def test_add(numbers: Tuple[int, int]):
        n1, n2 = numbers
        assert n1 + n2 == Tester.add(n1, n2)


def is_palindrome(s: str):
    i = 0
    j = len(s) - 1

    while i < j:
        if s[i] != s[j]:
            return False
        i += 1
        j -= 1

    return True

@pytest.mark.parametrize("input, result", [
    ("", True),
    ("a", True),
    ("racecar", True),
    ("bye", False)
])
def test_palindrome(input, result):
    assert is_palindrome(input) == result


from dataclasses import dataclass, field
import pytest

@dataclass
class FileSizeTestCase:
    size_bytes: int
    expected_result: str
    id: str = field(init=False)
    expected_error: type[Exception] = None
    error_message: str | None = None

    def __post_init__(self):
        self.id = f"test_format_file_size_{self.size_bytes}_bytes"


test_cases = [
    FileSizeTestCase(0, "0B"),
    FileSizeTestCase(1, "1.00 B"),
    FileSizeTestCase(1024, "1.00 KB"),
    FileSizeTestCase(1024**2, "1.00 MB"),
    FileSizeTestCase(1024**3, "1.00 GB"),
    FileSizeTestCase(1024**4, "1.00 TB"),
    FileSizeTestCase(
        -1, "", ValueError, "Size cannot be negative"
    ),  # Test case expecting an error
]


@pytest.mark.parametrize("test_case", test_cases, ids=lambda tc: tc.id)
def test_format_file_size(test_case):
    if test_case.expected_error:
        with pytest.raises(test_case.expected_error, match=test_case.error_message):
            format_file_size(test_case.size_bytes)

    else:
        assert format_file_size(test_case.size_bytes) == test_case.expected_result





@pytest.mark.parametrize("test_case", test_cases, ids=lambda tc: tc.id)
def test_format_file_size(test_case):
    if test_case.expected_error:
        with pytest.raises(test_case.expected_error, match=test_case.error_message):
            format_file_size(test_case.size_bytes)

    else:
        assert format_file_size(test_case.size_bytes) == test_case.expected_result


