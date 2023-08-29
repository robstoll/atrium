package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class CharSequenceToContainCreatorSamples {

    @Test
    fun value() {
        // `value` is a final step in the CharSequence.toContain building process and can be used with
        // various checkers (see CharSequenceToContainCheckerSamples) and search behaviours
        // (see CharSequenceToContainSearchBehaviourSamples)

        expect("ABC").toContain.exactly(1).value("A")
        expect("ABBBC").toContain.atLeast(2).value("B")

        fails {
            expect("AAAAAA").toContain.atMost(3).value("A")
        }
    }

    @Test
    fun valueIgnoringCase() {
        expect("ABC").toContain.ignoringCase.value("a")
        expect("AbbbC").toContain.ignoringCase.value("B")

        fails {
            expect("AAAAAA").toContain.ignoringCase.value("B")
        }
    }

    @Test
    fun valueIgnoringCaseWithChecker() {
        expect("ABC").toContain.ignoringCase.exactly(1).value("A")
        expect("AAABBC").toContain.ignoringCase.atMost(3).value("b")
        expect("aBBBCD").toContain.ignoringCase.atLeast(1).value("A")
        fails {
            expect("AAAAAABBBB").toContain.ignoringCase.atMost(3).value("A")
        }

        fails {
            expect("AAABBBb").toContain.ignoringCase.exactly(3).value("b")
        }

        fails {
            expect("AAAAAABBBB").toContain.ignoringCase.atLeast(3).value("D")
        }
    }

    @Test
    fun values() {
        expect("ABC").toContain.exactly(1).values("A", "B", "C")
        expect("AAABC").toContain.atMost(3).values("A", "B", "C")
        expect("ABBBCD").toContain.atLeast(1).values("A", "B", "C", "D")
        fails {
            expect("AAAAAABBBB").toContain.atMost(3).values("A", "B")
        }

        fails {
            expect("AAABBBB").toContain.exactly(3).values("A", "B")
        }

        fails {
            expect("AAAAAABBBB").toContain.atLeast(3).values("A", "B", "C")
        }
    }

    @Test
    fun valuesIgnoringCase() {
        expect("AbC").toContain.ignoringCase.values("A", "B", "c")
        fails {
            expect("aabaabbb").toContain.ignoringCase.values("A", "B", "C")
        }
    }

    @Test
    fun valuesIgnoringCaseWithChecker() {
        expect("ABc").toContain.ignoringCase.exactly(1).values("A", "b", "C")
        expect("AaaBC").toContain.ignoringCase.atMost(3).values("A", "B", "c")
        expect("ABBBcD").toContain.ignoringCase.atLeast(1).values("a", "b", "C", "d")
        fails {
            expect("AAAAAABBBB").toContain.ignoringCase.atMost(3).values("a", "b")
        }

        fails {
            expect("AAABBBB").toContain.ignoringCase.exactly(3).values("A", "b")
        }

        fails {
            expect("AAAAAABBBB").toContain.ignoringCase.atLeast(3).values("a", "b", "C")
        }
    }

    @Test
    fun regex() {
        expect("ABC").toContain.exactly(1).regex("A", "B", "C")
        expect("AAABC").toContain.atMost(3).regex("A", "B", "C")
        expect("ABBBCD").toContain.atLeast(1).regex("A", "B", "C", "D")
        fails {
            expect("AAAAAABBBB").toContain.atMost(3).regex("A", "B")
        }

        fails {
            expect("AAABBBB").toContain.exactly(3).regex("A", "B")
        }

        fails {
            expect("AAAAAABBBB").toContain.atLeast(3).regex("A", "B", "C")
        }
    }

    @Test
    fun regexIgnoringCase() {
        expect("AbC").toContain.ignoringCase.regex("A", "B", "c")
        fails {
            expect("aabaabbb").toContain.ignoringCase.regex("A", "B", "C")
        }
    }

    @Test
    fun regexIgnoringCaseWithChecker() {
        expect("ABc").toContain.ignoringCase.exactly(1).regex("A", "b", "C")
        expect("AaaBC").toContain.ignoringCase.atMost(3).regex("A", "B", "c")
        expect("ABBBcD").toContain.ignoringCase.atLeast(1).regex("a", "b", "C", "d")
        fails {
            expect("AAAAAABBBB").toContain.ignoringCase.atMost(3).regex("a", "b")
        }

        fails {
            expect("AAABBBB").toContain.ignoringCase.exactly(3).regex("A", "b")
        }

        fails {
            expect("AAAAAABBBB").toContain.ignoringCase.atLeast(3).regex("a", "b", "C")
        }
    }

    @Test
    fun matchFor() {
        expect("ABC").toContain.exactly(1).matchFor(Regex("A"), Regex("B"), Regex("C"))
        expect("AAABC").toContain.atMost(3).matchFor(Regex("A"), Regex("B"), Regex("C"))
        expect("ABBBCD").toContain.atLeast(1).matchFor(Regex("A"), Regex("B"), Regex("C"), Regex("D"))
        fails {
            expect("AAAAAABBBB").toContain.atMost(3).matchFor(Regex("A"), Regex("B"))
        }

        fails {
            expect("AAABBBB").toContain.exactly(3).matchFor(Regex("A"), Regex("B"))
        }

        fails {
            expect("AAAAAABBBB").toContain.atLeast(3).matchFor(Regex("A"), Regex("B"), Regex("C"))
        }
    }


    @Test
    fun elementsOf() {
        expect("ABC").toContain.exactly(1).elementsOf(listOf("A", "B", "C"))
        expect("AAABC").toContain.atMost(3).elementsOf(listOf("A", "B", "C"))
        expect("ABBBCD").toContain.atLeast(1).elementsOf(listOf("A", "B", "C", "D"))
        fails {
            expect("AAAAAABBBB").toContain.atMost(3).elementsOf(listOf("A", "B"))
        }

        fails {
            expect("AAABBBB").toContain.exactly(3).elementsOf(listOf("A", "B"))
        }

        fails {
            expect("AAAAAABBBB").toContain.atLeast(3).elementsOf(listOf("A", "B", "C"))
        }
    }

    @Test
    fun elementsOfIgnoreCase() {
        expect("AbC").toContain.ignoringCase.elementsOf(listOf("A", "B", "c"))
        fails {
            expect("aabaabbb").toContain.ignoringCase.elementsOf(listOf("A", "B", "C"))
        }
    }

    @Test
    fun elementsOfIgnoringCaseWithChecker() {
        expect("ABc").toContain.ignoringCase.exactly(1).elementsOf(listOf("A", "b", "C"))
        expect("AaaBC").toContain.ignoringCase.atMost(3).elementsOf(listOf("A", "B", "c"))
        expect("ABBBcD").toContain.ignoringCase.atLeast(1).elementsOf(listOf("a", "b", "C", "d"))
        fails {
            expect("AAAAAABBBB").toContain.ignoringCase.atMost(3).elementsOf(listOf("a", "b"))
        }

        fails {
            expect("AAABBBB").toContain.ignoringCase.exactly(3).elementsOf(listOf("A", "b"))
        }

        fails {
            expect("AAAAAABBBB").toContain.ignoringCase.atLeast(3).elementsOf(listOf("a", "b", "C"))
        }
    }
}
