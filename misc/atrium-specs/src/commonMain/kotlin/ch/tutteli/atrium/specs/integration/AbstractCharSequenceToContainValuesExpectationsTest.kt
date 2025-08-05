package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageToContain
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.testfactories.TestFactory

abstract class AbstractCharSequenceToContainValuesExpectationsTest(
    private val toContainAtLeastPair: Pair<(String, String) -> String, Fun3<CharSequence, Int, String, Array<out String>>>,
    private val toContainAtLeastIgnoringCasePair: Pair<(String, String) -> String, Fun3<CharSequence, Int, String, Array<out String>>>,
    private val toContainShortcutPair: Pair<(String, String) -> String, Fun2<CharSequence, String, Array<out String>>>,
    private val toContainAtMostPair: Pair<(String, String) -> String, Fun3<CharSequence, Int, String, Array<out String>>>,
    private val toContainAtMostIgnoringCasePair: Pair<(String, String) -> String, Fun3<CharSequence, Int, String, Array<out String>>>,
) : ExpectationFunctionBaseTest() {

    private val text: CharSequence = "Hello my name is Robert"

    @TestFactory
    fun toContainAtLeast() = testFactory(toContainAtLeastPair.second) { toContainAtLeastFun ->
        it("contains 'Hello' at least once - does not throw") {
            toContainAtLeastFun(expect(text), 1, "Hello", emptyArray())
        }

        it("contains 'Hello', 'Robert' - does not throw") {
            toContainAtLeastFun(expect(text), 1, "Hello", arrayOf("Robert"))
        }

        it("does not contain 'Goodbye' - throws") {
            expect {
                toContainAtLeastFun(expect(text), 1, "Goodbye", emptyArray())
            }.toThrow<AssertionError> {
                messageToContain("to contain")
            }
        }
    }

    @TestFactory
    fun toContainAtLeastIgnoringCase() = testFactory(toContainAtLeastIgnoringCasePair.second) { fn ->
        it("contains 'hello' ignoring case - does not throw") {
            fn(expect(text), 1, "hello", emptyArray())
        }

        it("does not contain 'goodbye' ignoring case - throws") {
            expect {
                fn(expect(text), 1, "goodbye", emptyArray())
            }.toThrow<AssertionError> {
                messageToContain("to contain")
            }
        }
    }

    @TestFactory
    fun toContainShortcut() = testFactory(toContainShortcutPair.second) { fn ->
        it("contains 'Hello' - does not throw") {
            fn(expect(text), "Hello", emptyArray())
        }

        it("contains 'Hello' and 'Robert' - does not throw") {
            fn(expect(text), "Hello", arrayOf("Robert"))
        }

        it("does not contain 'Goodbye' - throws") {
            expect {
                fn(expect(text), "Goodbye", emptyArray())
            }.toThrow<AssertionError> {
                messageToContain("to contain")
            }
        }
    }

    @TestFactory
    fun toContainAtMost() = testFactory(toContainAtMostPair.second) { fn ->
        it("contains 'o' at most 4 times - does not throw") {
            fn(expect(text), 4, "o", emptyArray())
        }

        it("contains 'o' at most 2 times - throws") {
            expect {
                fn(expect(text), 2, "o", emptyArray())
            }.toThrow<AssertionError> {
                messageToContain("to contain")
            }
        }
    }

    @TestFactory
    fun toContainAtMostIgnoringCase() = testFactory(toContainAtMostIgnoringCasePair.second) { fn ->
        it("contains 'o' and 'R' ignoring case at most 5 times - does not throw") {
            fn(expect(text), 5, "o", arrayOf("R"))
        }

        it("contains 'o' ignoring case at most 2 times - throws") {
            expect {
                fn(expect(text), 2, "o", emptyArray())
            }.toThrow<AssertionError> {
                messageToContain("to contain")
            }
        }
    }
}
