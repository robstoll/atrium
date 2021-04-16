package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class CharSequenceExpectationSamples {

    @Test
    fun toContainBuilder() {
        expect("ABC")
            .toContain.exactly(1).value("A")

        expect("ABBC")
            .toContain.atLeast(2).value("B")

        fails {
            expect("AAAAAA")
                .toContain.atMost(3).value("A")
        }
    }

    @Test
    fun notToContainBuilder() {
        expect("ABC").notToContain.value("X")

        fails {
            expect("ABC").notToContain.value("B")
        }
    }

    @Test
    fun toContain() {
        expect("ABC").toContain("B")

        expect("ABC123").toContain("AB", 'C', 12)

        // holds because `toContain` does not search for unique matches
        // use `toContain.exactly(2).value("A")` to check if the subject contains two "A"s
        expect("ABC").toContain("A", "A")

        fails {
            expect("ABC").toContain("X")
        }

        fails { // because subject does not contain all values
            expect("ABC").toContain("A", 99)
        }
    }

    @Test
    fun notToContain() {
        expect("ABC").notToContain("X")

        expect("ABC").notToContain("X", 'Y', 1)

        fails {
            expect("ABC").notToContain("B")
        }

        fails {
            expect("ABC").notToContain("B", "X")
        }
    }

    @Test
    fun toContainRegexString() {
        expect("ABC").toContainRegex("A(B)?")

        fails {
            expect("ABC").toContainRegex("X")
        }

        expect("ABC")
            .toContainRegex("A(B)?", "(B)?C") // all regex patterns match

        // holds because `toContainRegex` does not search for unique matches
        // use `toContain.exactly(2).regex("A(B)?")` to check if the subject contains the regex two times
        expect("ABC")
            .toContainRegex("A(B)?", "A(B)?")

        fails { // because second regex doesn't match
            expect("ABC").toContainRegex("A", "X")
        }
    }

    @Test
    fun toContainRegex() {
        expect("ABC").toContainRegex("(B)?C".toRegex())

        fails {
            expect("ABC").toContainRegex("X".toRegex())
        }

        expect("ABC")
            .toContainRegex("A".toRegex(), "B".toRegex()) // all regex patterns match

        // holds because `toContainRegex` does not search for unique matches
        // use `toContain.exactly(2).regex(regex)` to check if the subject contains the regex two times
        val regex = "A(B)?".toRegex()
        expect("ABC").toContainRegex(regex, regex)

        fails { // because second regex doesn't match
            expect("ABC").toContainRegex("A".toRegex(), "X".toRegex())
        }
    }

    @Test
    fun toStartWith() {
        expect("ABC").toStartWith("AB")

        fails {
            expect("ABC").toStartWith("X")
        }
    }

    @Test
    fun notToStartWith() {
        expect("ABC").notToStartWith("X")

        fails {
            expect("ABC").notToStartWith("AB")
        }
    }

    @Test
    fun toEndWith() {
        expect("ABC").toEndWith("BC")

        fails {
            expect("ABC").toEndWith("X")
        }
    }

    @Test
    fun notToEndWith() {
        expect("ABC").notToEndWith("X")

        fails {
            expect("ABC").notToEndWith("BC")
        }
    }

    @Test
    fun toBeEmpty() {
        expect("").toBeEmpty()

        fails {
            expect("XYZ").toBeEmpty()
        }
    }

    @Test
    fun notToBeEmpty() {
        expect("XYZ").notToBeEmpty()

        fails {
            expect("").notToBeEmpty()
        }

        // use notToBeBlank to check for whitespaces
        expect(" ").notToBeEmpty()
    }

    @Test
    fun notToBeBlank() {
        expect("XZY").notToBeBlank()

        fails {
            expect(" ").notToBeBlank()
        }

        fails { // because an empty string is also considered to be a blank string
            expect("").notToBeBlank()
        }
    }

    @Test
    fun toMatch() {
        expect("ABC").toMatch("A(B)?C".toRegex()) // subject is fully matched

        fails { // because subject isn't fully matched, use toContainRegex for partial matching
            expect("ABC").toMatch("A".toRegex())
        }
    }

    @Test
    fun notToMatch() {
        expect("ABC").notToMatch("A".toRegex()) // subject isn't fully matched

        fails { // because subject is fully matched, use notToContain.regex for partial matching
            expect("ABC").notToMatch("A(B)?C".toRegex())
        }
    }
}
