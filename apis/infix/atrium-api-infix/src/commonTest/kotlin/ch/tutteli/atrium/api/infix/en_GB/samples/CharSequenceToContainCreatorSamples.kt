package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class CharSequenceToContainCreatorSamples {

    @Test
    fun value() {
        expect("ABC") toContain o exactly 1 value "A"
        expect("ABBBC") toContain o atLeast 2 value "B"

        fails {
            expect("AAAAAA") toContain o atMost 3 value "A"
        }
    }

    @Test
    fun valueIgnoringCase() {
        expect("ABC") toContain o ignoring case value "a"
        expect("AbbbC") toContain o ignoring case value "B"

        fails {
            expect("AAAAAA") toContain o ignoring case value "B"
        }
    }

    @Test
    fun valueIgnoringCaseWithChecker() {
        expect("ABC") toContain o ignoring case exactly 1 value "A"
        expect("AAABBC") toContain o ignoring case atMost 3 value "b"
        expect("aBBBCD") toContain o ignoring case atLeast 1 value "A"

        fails {
            expect("AAAAAABBBB") toContain o ignoring case atMost 3 value "A"
        }
        fails {
            expect("AAABBBb") toContain o ignoring case exactly 3 value "b"
        }
        fails {
            expect("AAAAAABBBB") toContain o ignoring case atLeast 3 value "D"
        }
    }

    @Test
    fun values() {
        expect("ABC") toContain o exactly 1  the values("A","B","C")
        expect("AAABC") toContain o atMost 3 the values("A", "B", "C")
        expect("ABBBCD") toContain o atLeast 1 the values("A", "B", "C", "D")

        fails {
            expect("AAAAAABBBB") toContain o atMost 3 the values("A", "B")
        }
        fails {
            expect("AAABBBB") toContain o exactly 3 the values("A", "B")
        }
        fails {
            expect("AAAAAABBBB") toContain o atLeast 3 the values("A", "B", "C")
        }
    }

    @Test
    fun valuesIgnoringCase() {
        expect("AbC") toContain o ignoring case the values("A", "B", "c")

        fails {
            expect("aabaabbb") toContain o ignoring case the values("A", "B", "C")
        }
    }

    @Test
    fun valuesIgnoringCaseWithChecker() {
        expect("ABc") toContain o ignoring case exactly 1 the values("A", "b", "C")
        expect("AaaBC") toContain o ignoring case atMost 3 the values("A", "B", "c")
        expect("ABBBcD") toContain o ignoring case atLeast 1 the values("a", "b", "C", "d")

        fails {
            expect("AAAAAABBBB") toContain o ignoring case atMost 3 the values("a", "b")
        }
        fails {
            expect("AAABBBB") toContain o ignoring case exactly 3 the values("A", "b")
        }
        fails {
            expect("AAAAAABBBB") toContain o ignoring case atLeast 3 the values("a", "b", "C")
        }
    }


    @Test
    fun regex() {
        expect("ABC") toContain o exactly 1 regex "A"
        expect("ABC") toContain o exactly 1 regex "B"
        expect("ABC") toContain o exactly 1 regex "C"
        expect("AAABC") toContain o atMost 3 regex "A"
        expect("AAABC") toContain o atMost 3 regex "B"
        expect("AAABC") toContain o atMost 3 regex "C"
        expect("ABBBCD") toContain o atLeast 1 regex "A"
        expect("ABBBCD") toContain o atLeast 1 regex "B"
        expect("ABBBCD") toContain o atLeast 1 regex "C"
        expect("ABBBCD") toContain o atLeast 1 regex "D"

        fails {
            expect("AAAAAABBBB") toContain o atMost 3 regex "A"
        }
        fails {
            expect("AAAAAABBBB") toContain o atMost 3 regex "B"
        }
        fails {
            expect("AAABBBB") toContain o exactly 3 regex "A"
        }
        fails {
            expect("AAABBBB") toContain o exactly 3 regex "B"
        }
        fails {
            expect("AAAAAABBBB") toContain o atLeast 3 regex "A"
        }
        fails {
            expect("AAAAAABBBB") toContain o atLeast 3 regex "B"
        }
        fails {
            expect("AAAAAABBBB") toContain o atLeast 3 regex "C"
        }
    }

    @Test
    fun regexIgnoringCase() {
        expect("AbC") toContain o ignoring case regex "A"
        expect("AbC") toContain o ignoring case regex "B"
        expect("AbC") toContain o ignoring case regex "c"

        fails {
            expect("aabaabbb") toContain o ignoring case regex "A"
        }
        fails {
            expect("aabaabbb") toContain o ignoring case regex "B"
        }
        fails {
            expect("aabaabbb") toContain o ignoring case regex "C"
        }
    }

    @Test
    fun regexIgnoringCaseWithChecker() {
        expect("ABc") toContain o ignoring case exactly 1 regex "A"
        expect("ABc") toContain o ignoring case exactly 1 regex "b"
        expect("ABc") toContain o ignoring case exactly 1 regex "C"
        expect("AaaBC") toContain o ignoring case atMost 3 regex "A"
        expect("AaaBC") toContain o ignoring case atMost 3 regex "B"
        expect("AaaBC") toContain o ignoring case atMost 3 regex "c"
        expect("ABBBcD") toContain o ignoring case atLeast 1 regex "a"
        expect("ABBBcD") toContain o ignoring case atLeast 1 regex "b"
        expect("ABBBcD") toContain o ignoring case atLeast 1 regex "C"
        expect("ABBBcD") toContain o ignoring case atLeast 1 regex "d"

        fails {
            expect("AAAAAABBBB") toContain o ignoring case atMost 3 regex "a"
        }
        fails {
            expect("AAAAAABBBB") toContain o ignoring case atMost 3 regex "b"
        }
        fails {
            expect("AAABBBB") toContain o ignoring case exactly 3 regex "A"
        }
        fails {
            expect("AAABBBB") toContain o ignoring case exactly 3 regex "b"
        }
        fails {
            expect("AAAAAABBBB") toContain o ignoring case atLeast 3 regex "a"
        }
        fails {
            expect("AAAAAABBBB") toContain o ignoring case atLeast 3 regex "b"
        }
        fails {
            expect("AAAAAABBBB") toContain o ignoring case atLeast 3 regex "C"
        }
    }

    @Test
    fun matchFor() {
        expect("ABC") toContain o exactly 1 matchFor Regex("A")
        expect("ABC") toContain o exactly 1 matchFor Regex("B")
        expect("ABC") toContain o exactly 1 matchFor Regex("C")
        expect("AAABC") toContain o atMost 3 matchFor Regex("A")
        expect("AAABC") toContain o atMost 3 matchFor Regex("B")
        expect("AAABC") toContain o atMost 3 matchFor Regex("C")
        expect("ABBBCD") toContain o atLeast 1 matchFor Regex("A")
        expect("ABBBCD") toContain o atLeast 1 matchFor Regex("B")
        expect("ABBBCD") toContain o atLeast 1 matchFor Regex("C")
        expect("ABBBCD") toContain o atLeast 1 matchFor Regex("D")

        fails {
            expect("AAAAAABBBB") toContain o atMost 3 matchFor Regex("A")
        }
        fails {
            expect("AAAAAABBBB") toContain o atMost 3 matchFor Regex("B")
        }
        fails {
            expect("AAABBBB") toContain o exactly 3 matchFor Regex("A")
        }
        fails {
            expect("AAABBBB") toContain o exactly 3 matchFor Regex("B")
        }
        fails {
            expect("AAAAAABBBB") toContain o atLeast 3 matchFor Regex("A")
        }
        fails {
            expect("AAAAAABBBB") toContain o atLeast 3 matchFor Regex("B")
        }
        fails {
            expect("AAAAAABBBB") toContain o atLeast 3 matchFor Regex("C")
        }
    }

    @Test
    fun elementsOf() {
        expect("ABC") toContain o exactly 1 elementsOf listOf("A", "B", "C")
        expect("AAABC") toContain o atMost 3 elementsOf listOf("A", "B", "C")
        expect("ABBBCD") toContain o atLeast 1 elementsOf listOf("A", "B", "C", "D")

        fails {
            expect("AAAAAABBBB") toContain o atMost 3 elementsOf listOf("A", "B")
        }
        fails {
            expect("AAABBBB") toContain o exactly 3 elementsOf listOf("A", "B")
        }
        fails {
            expect("AAAAAABBBB") toContain o atLeast 3 elementsOf listOf("A", "B", "C")
        }
    }

    @Test
    fun elementsOfIgnoreCase() {
        expect("AbC") toContain o ignoring case elementsOf listOf("A", "B", "c")

        fails {
            expect("aabaabbb") toContain o ignoring case elementsOf listOf("A", "B", "C")
        }
    }

    @Test
    fun elementsOfIgnoringCaseWithChecker() {
        expect("ABc") toContain o ignoring case exactly 1 elementsOf listOf("A", "b", "C")
        expect("AaaBC") toContain o ignoring case atMost 3 elementsOf listOf("A", "B", "c")
        expect("ABBBcD") toContain o ignoring case atLeast 1 elementsOf listOf("a", "b", "C", "d")

        fails {
            expect("AAAAAABBBB") toContain o ignoring case atMost 3 elementsOf listOf("a", "b")
        }
        fails {
            expect("AAABBBB") toContain o ignoring case exactly 3 elementsOf listOf("A", "b")
        }
        fails {
            expect("AAAAAABBBB") toContain o ignoring case atLeast 3 elementsOf listOf("a", "b", "C")
        }
    }
}
