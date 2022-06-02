//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.infix.en_GB.samples.deprecated

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.infix.en_GB.samples.fails
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class CharSequenceAssertionSamples {

    @Test
    fun containsBuilder() {
        expect("ABC") contains o exactly 1 value "A"

        expect("ABBC") contains o atLeast 2 value "B"

        fails {
            expect("AAAAAA") contains o atMost 3 value "A"
        }
    }

    @Test
    fun containsNotBuilder() {
        expect("ABC") containsNot o value "X"

        fails {
            expect("ABC") containsNot o value "B"
        }
    }

    @Test
    fun contains() {
        expect("ABC") toContain "B"

        expect("ABC123") toContain values("AB", 'C', 12)

        // holds because `contains` does not search for unique matches
        // use `contains o exactly 2 value "A"` to check if subject contains two "A"s
        expect("ABC") toContain values("A", "A")

        fails {
            expect("ABC") toContain "X"
        }

        fails { // because subject does not contain all values
            expect("ABC") toContain values("A", 99)
        }
    }

    @Test
    fun containsNot() {
        expect("ABC") notToContain "X"

        expect("ABC") containsNot values("X", 'Y', 1)

        fails {
            expect("ABC") notToContain "B"
        }

        fails {
            expect("ABC") containsNot values("B", "X")
        }
    }

    @Test
    fun containsRegexStringSingle() {
        expect("ABC") toContainRegex "A(B)?"

        fails {
            expect("ABC") toContainRegex "X"
        }
    }

    @Test
    fun containsRegexStringMultiple() {
        // all regex patterns match
        expect("ABC") toContain regexPatterns("A(B)?", "(B)?C")

        // holds because `contains regexPatterns(...)` does not search for unique matches
        // use `contains exactly 2 regex "A(B)?"` to check if subject contains the regex two times
        expect("ABC") toContain regexPatterns("A(B)?", "A(B)?")

        fails { // because second regex doesn't match
            expect("ABC") toContain regexPatterns("A", "X")
        }
    }

    @Test
    fun containsRegexSingle() {
        expect("ABC") toContain "(B)?C".toRegex()

        fails {
            expect("ABC") toContain "X".toRegex()
        }

    }

    @Test
    fun containsRegexMultiple() {
        // all regex patterns match
        expect("ABC") toContain all("A".toRegex(), "B".toRegex())

        // holds because `contains all(...)` does not search for unique matches
        // use `contains exactly 2 regex regex` to check if subject contains the regex two times
        val regex = "A(B)?".toRegex()
        expect("ABC") toContain all(regex, regex)

        fails { // because second regex doesn't match
            expect("ABC") toContain all("A".toRegex(), "X".toRegex())
        }
    }

    @Test
    fun startsWith() {
        expect("ABC") startsWith "AB"

        fails {
            expect("ABC") startsWith "X"
        }
    }

    @Test
    fun startsWithChar() {
        expect("ABC") toStartWith 'A'.toString()

        fails {
            expect("ABC") toStartWith 'X'.toString()
        }
    }

    @Test
    fun startsNotWith() {
        expect("ABC") notToStartWith "X"

        fails {
            expect("ABC") notToStartWith "AB"
        }
    }

    @Test
    fun startsNotWithChar() {
        expect("ABC") notToStartWith 'X'.toString()

        fails {
            expect("ABC") notToStartWith 'A'.toString()
        }
    }


    @Test
    fun endsWith() {
        expect("ABC") toEndWith "BC"

        fails {
            expect("ABC") toEndWith "X"
        }
    }

    @Test
    fun endsWithChar() {
        expect("ABC") toEndWith 'C'.toString()

        fails {
            expect("ABC") toEndWith 'X'.toString()
        }
    }

    @Test
    fun endsNotWith() {
        expect("ABC") notToEndWith "X"

        fails {
            expect("ABC") notToEndWith "BC"
        }
    }

    @Test
    fun endsNotWithChar() {
        expect("ABC") notToEndWith 'X'.toString()

        fails {
            expect("ABC") notToEndWith 'C'.toString()
        }
    }

    @Test
    fun isEmpty() {
        expect("") toBe empty

        fails {
            expect("XYZ") toBe empty
        }
    }

    @Test
    fun isNotEmpty() {
        expect("XYZ") notToBe empty

        fails {
            expect("") notToBe empty
        }

        // use `notToBe blank` to check for whitespaces
        expect(" ") notToBe empty
    }

    @Test
    fun isNotBlank() {
        expect("XZY") notToBe blank

        fails {
            expect(" ") notToBe blank
        }

        fails { // because subject is empty but contains no whitespaces
            expect("") notToBe blank
        }
    }

    @Test
    fun matches() {
        expect("ABC") toMatch "A(B)?C".toRegex() // subject is fully matched

        fails { // because subject isn't fully matched, use containsRegex for partial matching
            expect("ABC") toMatch "A".toRegex()
        }
    }

    @Test
    fun mismatches() {
        expect("ABC") notToMatch "A".toRegex() // subject isn't fully matched

        fails { // because subject is fully matched, use containsNot.regex for partial matching
            expect("ABC") notToMatch "A(B)?C".toRegex()
        }
    }
}
