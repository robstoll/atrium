package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class CharSequenceExpectationSamples {

    @Test
    fun toContainBuilder() {
        expect("ABC") toContain o exactly 1 value "A"

        expect("ABBC") toContain o atLeast 2 value "B"

        fails {
            expect("AAAAAA") toContain o atMost 3 value "A"
        }
    }

    @Test
    fun notToContainBuilder() {
        expect("ABC") notToContain o value "X"

        fails {
            expect("ABC") notToContain o value "B"
        }
    }

    @Test
    fun toContain() {
        expect("ABC") toContain "B"

        expect("ABC123") toContain values("AB", 'C', 12)

        // holds because `toContain` does not search for unique matches
        // use `toContain o exactly 2 value "A"` to check if the subject contains two "A"s
        expect("ABC") toContain values("A", "A")

        fails {
            expect("ABC") toContain "X"
        }

        fails { // because subject does not contain all values
            expect("ABC") toContain values("A", 99)
        }
    }

    @Test
    fun notToContain() {
        expect("ABC") notToContain "X"

        expect("ABC") notToContain values("X", 'Y', 1)

        fails {
            expect("ABC") notToContain "B"
        }

        fails {
            expect("ABC") notToContain values("B", "X")
        }
    }

    @Test
    fun toContainRegexStringSingle() {
        expect("ABC") toContainRegex "A(B)?"

        fails {
            expect("ABC") toContainRegex "X"
        }
    }

    @Test
    fun toContainRegexStringMultiple() {
        // all regex patterns match
        expect("ABC") toContain regexPatterns("A(B)?", "(B)?C")

        // holds because `toContain regexPatterns(...)` does not search for unique matches
        // use `toContain exactly 2 regex "A(B)?"` to check if the subject contains the regex two times
        expect("ABC") toContain regexPatterns("A(B)?", "A(B)?")

        fails { // because second regex doesn't match
            expect("ABC") toContain regexPatterns("A", "X")
        }
    }

    @Test
    fun toContainRegexSingle() {
        expect("ABC") toContain "(B)?C".toRegex()

        fails {
            expect("ABC") toContain "X".toRegex()
        }

    }

    @Test
    fun toContainRegexMultiple() {
        // all regex patterns match
        expect("ABC") toContain all("A".toRegex(), "B".toRegex())

        // holds because `toContain all(...)` does not search for unique matches
        // use `toContain exactly 2 regex regex` to check if the subject contains the regex two times
        val regex = "A(B)?".toRegex()
        expect("ABC") toContain all(regex, regex)

        fails { // because second regex doesn't match
            expect("ABC") toContain all("A".toRegex(), "X".toRegex())
        }
    }

    @Test
    fun toStartWith() {
        expect("ABC") toStartWith "AB"

        fails {
            expect("ABC") toStartWith "X"
        }
    }

    @Test
    fun notToStartWith() {
        expect("ABC") notToStartWith "X"

        fails {
            expect("ABC") notToStartWith "AB"
        }
    }

    @Test
    fun toEndWith() {
        expect("ABC") toEndWith "BC"

        fails {
            expect("ABC") toEndWith "X"
        }
    }

    @Test
    fun notToEndWith() {
        expect("ABC") notToEndWith "X"

        fails {
            expect("ABC") notToEndWith "BC"
        }
    }

    @Test
    fun toBeEmpty() {
        expect("") toBe empty

        fails {
            expect("XYZ") toBe empty
        }
    }

    @Test
    fun notToBeEmpty() {
        expect("XYZ") notToBe empty

        fails {
            expect("") notToBe empty
        }

        // use `notToBe blank` to check for whitespaces
        expect(" ") notToBe empty
    }

    @Test
    fun notToBeBlank() {
        expect("XZY") notToBe blank

        fails {
            expect(" ") notToBe blank
        }

        fails { // because an empty string is also considered to be a blank string
            expect("") notToBe blank
        }
    }

    @Test
    fun toMatch() {
        expect("ABC") toMatch "A(B)?C".toRegex() // subject is fully matched

        fails { // because subject isn't fully matched, use containsRegex for partial matching
            expect("ABC") toMatch "A".toRegex()
        }
    }

    @Test
    fun notToMatch() {
        expect("ABC") notToMatch "A".toRegex() // subject isn't fully matched

        fails { // because subject is fully matched, use notToContain.regex for partial matching
            expect("ABC")  notToMatch "A(B)?C".toRegex()
        }
    }
}
