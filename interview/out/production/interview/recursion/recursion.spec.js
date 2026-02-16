function factorial(n) {
    if (n <= 1) return 1;
    return n * factorial(n - 1);
}

describe("Recursion tests", function() {
    it("factorial", function() {
        console.log("Factorial of 5 should be 120")
        expect(factorial(5)).toBe(120);
    });
});