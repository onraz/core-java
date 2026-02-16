package recursion

//import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/*
    - Solve the problem One step smaller:
        The problem can broken down into smaller problems of same type.
    - The complex problem can be solved by combining the smaller problem.
        Small problems can be merged or filtered to yield a solution to a bigger problem.
    - Problem has some base case(s).
        Base case is reached before the stack size limit exceeds.
 */
class Recursion {
    /*
        factorial(5)
            factorial(4)
                factorial(3)
                    factorial(2)
                        factorial(1)
                            return 1 // base case
                    2 * factorial(1) = 2
                3 * factorial(2) = 3 * 2
            4 * factorial(3) = 4 * 6
        5 * factorial(4) = 5 * 24
    */
    fun factorial(n: Int): Int = if (n <= 1) 1 else n * factorial(n - 1)


// Recursing left
    fun reverse(s: String): String =
            if (s.length == 1)
                s
            else
                reverse(s.substring(1)) + s[0]

    fun reverseIter(s: String): String {
        var reversed = ""
        for (i in s.length-1 downTo 0) {
            reversed += s[i]
        }
        return reversed
    }

    fun sum(arr: Array<Int>, i: Int): Int =
            if (i == 0)
                arr[0]
            else
                arr[i] + sum(arr, i - 1)

    // recurse inside - each recursive call iterates with smaller substring
    fun palindrome(str: String): Boolean =
            if (str.length <= 1)
                true
            else
                (str[0] == str[str.length-1]) // first & last characters should match
                        && palindrome(str.substring(1, str.length - 1)) // recurse inside for middle bits

    // Iterative solution using two pointers
    fun palindromeIter(str: String): Boolean {
        var low = 0
        var high = str.length
        while (low < high) {
            if (str[low] != str[high]) {
                return false
            }
            low++
            high--
        }
        return true
    }


    /**
     *      ""
     *     /  \
     *    a   b
     *    /\  /\
     *   a b a b
     * = aa ab ba bb
     */
    fun permute(str: String, current: String) {
        if (current.length == str.length)
            println(current)
        else
            for ((i, char) in str.withIndex()) {
                val newCurrent = current + char
                permute(str, newCurrent)
            }
    }

    // all possible lengths upto str.length
    fun permute2(str: String, current: String) {
        println(current)
        if (current.length == str.length)
            return
        else
            for ((i, char) in str.withIndex()) {
                val newCurrent = current + char
                permute2(str, newCurrent)
            }
    }
    /**
     *      ""
     *     /  \ \
     *    a   b  c
     *    /\  /\ /\
     *   b c a c a b
     * = aa ab ba bb
     */
    fun comb(str: String, current: String) {
        println(current)
        if (str.isEmpty() || current.length == str.length)
            return
        else
            for ((i, char) in str.withIndex()) {

                val newCurrent = current + char
                comb(str.replace(char + "", ""), newCurrent)
            }
    }

    @Test
    fun `factorial of 5 should be 120`() {
        assertEquals(120, factorial(5))
    }

    @Test
    fun `Reverse of RaziB should be BizaR`() {
        assertEquals("bizar", reverse("razib"))
    }

    @Test
    fun `Sum of array(1,2,3) is 6`() {
        assertEquals(6, sum(arrayOf(1, 2, 3), 2))
    }

    @Test
    fun `bizar is not palindrome`() {
        assertEquals(false, palindrome("bizar"), "bizar isn't a palindrome")
    }

    @Test
    fun `Check palindromes`() {
        assertEquals(true, palindrome("eye"), "eye should be palindrome")
        assertEquals(true, palindrome("looopoool"), "looopoool should be palindrome")
        assertEquals(true, palindrome("looopoool"), "looopoool should be palindrome")
    }

    @Test
    fun `Check permute`() {
        permute2("abc", "")
    }

    @Test
    fun `Check Comb`() {
        comb("abc", "")
    }

    @Test
    fun `Check iteration`() {
        println(reverseIter("razib"))
    }
}