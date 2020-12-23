package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class CharSequenceAssertionsSpec {

    @Test
    fun containsBuilder() {
        expect("ABC")
            .contains.exactly(1).value("A")

        expect("ABBC")
            .contains.atLeast(2).value("B")

        fails {
            expect("AAAAAA")
                .contains.atMost(3).value("A")
        }
    }

    @Test
    fun containsNotBuilder() {
        expect("ABC").containsNot.value("X")

        fails {
            expect("ABC").containsNot.value("B")
        }
    }

    @Test
    fun contains() {
        expect("ABC").contains("B")

        expect("ABC123").contains("AB", 'C', 12)

        // holds because `contains` does not search for unique matches
        // use `contains.exactly(2).value("A")` to check if subject contains two "A"s
        expect("ABC").contains("A", "A")

        fails {
            expect("ABC").contains("X")
        }

        fails { // because subject does not contain all values
            expect("ABC").contains("A", 99)
        }
    }

    @Test
    fun containsNot() {
        expect("ABC").containsNot("X")

        expect("ABC").containsNot("X", 'Y', 1)

        fails {
            expect("ABC").containsNot("B")
        }

        fails {
            expect("ABC").containsNot("B", "X")
        }
    }

    @Test
    fun containsRegexString() {
        expect("ABC").containsRegex("A(B)?")

        fails {
            expect("ABC").containsRegex("X")
        }

        expect("ABC")
            .containsRegex("A(B)?", "(B)?C") // all regex patterns match

        // holds because `containsRegex` does not search for unique matches
        // use `contains.exactly(2).regex("A(B)?")` to check if subject contains the regex two times
        expect("ABC")
            .containsRegex("A(B)?", "A(B)?")

        fails { // because second regex doesn't match
            expect("ABC").containsRegex("A", "X")
        }
    }

    @Test
    fun containsRegex() {
        expect("ABC").containsRegex("(B)?C".toRegex())

        fails {
            expect("ABC").containsRegex("X".toRegex())
        }

        expect("ABC")
            .containsRegex("A".toRegex(), "B".toRegex()) // all regex patterns match

        // holds because `containsRegex` does not search for unique matches
        // use `contains.exactly(2).regex(regex)` to check if subject contains the regex two times
        val regex = "A(B)?".toRegex()
        expect("ABC").containsRegex(regex, regex)

        fails { // because second regex doesn't match
            expect("ABC").containsRegex("A".toRegex(), "X".toRegex())
        }
    }

    @Test
    fun startsWith() {
        expect("ABC").startsWith("AB")

        fails {
            expect("ABC").startsWith("X")
        }
    }

    @Test
    fun startsWithChar() {
        expect("ABC").startsWith('A')

        fails {
            expect("ABC").startsWith('X')
        }
    }

    @Test
    fun startsNotWith() {
        expect("ABC").startsNotWith("X")

        fails {
            expect("ABC").startsNotWith("AB")
        }
    }

    @Test
    fun startsNotWithChar() {
        expect("ABC").startsNotWith('X')

        fails {
            expect("ABC").startsNotWith('A')
        }
    }


    @Test
    fun endsWith() {
        expect("ABC").endsWith("BC")

        fails {
            expect("ABC").endsWith("X")
        }
    }

    @Test
    fun endsWithChar() {
        expect("ABC").endsWith('C')

        fails {
            expect("ABC").endsWith('X')
        }
    }

    @Test
    fun endsNotWith() {
        expect("ABC").endsNotWith("X")

        fails {
            expect("ABC").endsNotWith("BC")
        }
    }

    @Test
    fun endsNotWithChar() {
        expect("ABC").endsNotWith('X')

        fails {
            expect("ABC").endsNotWith('C')
        }
    }

    @Test
    fun isEmpty() {
        expect("").isEmpty()

        fails {
            expect("XYZ").isEmpty()
        }
    }

    @Test
    fun isNotEmpty() {
        expect("XYZ").isNotEmpty()

        fails {
            expect("").isNotEmpty()
        }

        // use isNotBlank to check for whitespaces
        expect(" ").isNotEmpty()
    }

    @Test
    fun isNotBlank() {
        expect("XZY").isNotBlank()

        fails {
            expect(" ").isNotBlank()
        }

        fails { // because subject is empty but contains no whitespaces
            expect("").isNotBlank()
        }
    }

    @Test
    fun matches() {
        expect("ABC").matches("A(B)?C".toRegex()) // subject is fully matched

        fails { // because subject isn't fully matched, use containsRegex for partial matching
            expect("ABC").matches("A".toRegex())
        }
    }

    @Test
    fun mismatches() {
        expect("ABC").mismatches("A".toRegex()) // subject isn't fully matched

        fails { // because subject is fully matched, use containsNot.regex for partial matching
            expect("ABC").mismatches("A(B)?C".toRegex())
        }
    }
}
