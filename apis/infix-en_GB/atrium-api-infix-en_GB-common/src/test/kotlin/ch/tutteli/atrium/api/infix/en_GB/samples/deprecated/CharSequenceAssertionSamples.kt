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
        expect("ABC") contains o exactly 1 value "B"

        expect("ABC123") contains values("AB", 'C', 12)

        // holds because `contains` does not search for unique matches
        // use `contains o exactly 2 value "A"` to check if subject contains two "A"s
        expect("ABC") contains o exactly 1 the values("A", "A")

        fails {
            expect("ABC") contains o exactly 1 value  "X"
        }

        fails { // because subject does not contain all values
            expect("ABC") contains o exactly 1 the values("A", 99)
        }
    }

    @Test
    fun containsNot() {
        expect("ABC") containsNot o value "X"

        expect("ABC") containsNot o the values("X", 'Y', 1)

        fails {
            expect("ABC") containsNot o value "B"
        }

        fails {
            expect("ABC") containsNot o the values("B", "X")
        }
    }

    @Test
    fun containsRegexString() {
        expect("ABC") containsRegex "A(B)?"

        fails {
            expect("ABC") containsRegex "X"
        }

        // all regex patterns match
        expect("ABC") containsRegex regexPatterns("A(B)?", "(B)?C")

        // holds because `containsRegex` does not search for unique matches
        // use `contains exactly 2 regex "A(B)?"` to check if subject contains the regex two times
        expect("ABC") containsRegex regexPatterns("A(B)?", "A(B)?")

        fails { // because second regex doesn't match
            expect("ABC") containsRegex regexPatterns("A", "X")
        }
    }

    @Test
    fun containsRegex() {
        expect("ABC") containsRegex "(B)?C".toRegex()

        fails {
            expect("ABC") containsRegex "X".toRegex()
        }

        // all regex patterns match
        expect("ABC") containsRegex all("A".toRegex(), "B".toRegex())

        // holds because `containsRegex` does not search for unique matches
        // use `contains exactly 2 regex regex` to check if subject contains the regex two times
        val regex = "A(B)?".toRegex()
        expect("ABC") containsRegex all(regex, regex)

        fails { // because second regex doesn't match
            expect("ABC") containsRegex all("A".toRegex(), "X".toRegex())
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
        expect("ABC") startsWith 'A'

        fails {
            expect("ABC") startsWith 'X'
        }
    }

    @Test
    fun startsNotWith() {
        expect("ABC") startsNotWith "X"

        fails {
            expect("ABC") startsNotWith "AB"
        }
    }

    @Test
    fun startsNotWithChar() {
        expect("ABC") startsNotWith 'X'

        fails {
            expect("ABC") startsNotWith 'A'
        }
    }


    @Test
    fun endsWith() {
        expect("ABC") endsWith "BC"

        fails {
            expect("ABC") endsWith "X"
        }
    }

    @Test
    fun endsWithChar() {
        expect("ABC") endsWith 'C'

        fails {
            expect("ABC") endsWith 'X'
        }
    }

    @Test
    fun endsNotWith() {
        expect("ABC") endsNotWith "X"

        fails {
            expect("ABC") endsNotWith "BC"
        }
    }

    @Test
    fun endsNotWithChar() {
        expect("ABC") endsNotWith 'X'

        fails {
            expect("ABC") endsNotWith 'C'
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

        // use notToBe blank to check for whitespaces
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
        expect("ABC") matches "A(B)?C".toRegex() // subject is fully matched

        fails { // because subject isn't fully matched, use containsRegex for partial matching
            expect("ABC") matches "A".toRegex()
        }
    }

    @Test
    fun mismatches() {
        expect("ABC") mismatches "A".toRegex() // subject isn't fully matched

        fails { // because subject is fully matched, use containsNot.regex for partial matching
            expect("ABC")  mismatches "A(B)?C".toRegex()
        }
    }
}
