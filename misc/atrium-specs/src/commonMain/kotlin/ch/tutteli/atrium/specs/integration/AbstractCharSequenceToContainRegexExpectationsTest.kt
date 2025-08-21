package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.testFactory
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.CharSequenceToContainSpecBase.Companion.noMatchFoundDescr
import ch.tutteli.atrium.specs.integration.CharSequenceToContainSpecBase.Companion.stringMatchingRegex
import ch.tutteli.atrium.testfactories.TestFactory

abstract class AbstractCharSequenceToContainRegexExpectationsTest(
    private val toContainAtLeastPair: Pair<(String, String) -> String, Fun3<CharSequence, Int, String, Array<out String>>>,
    private val toContainAtLeastIgnoringCasePair: Pair<(String, String) -> String, Fun3<CharSequence, Int, String, Array<out String>>>,
    private val toContainShortcutPair: Pair<(String, String) -> String, Fun2<CharSequence, String, Array<out String>>>,
    private val toContainAtMostPair: Pair<(String, String) -> String, Fun3<CharSequence, Int, String, Array<out String>>>,
    private val toContainAtMostIgnoringCasePair: Pair<(String, String) -> String, Fun3<CharSequence, Int, String, Array<out String>>>
) : ExpectationFunctionBaseTest() {

    private val text = "Hello my name is Robert"
    private val hello = "[hH][ea]llo"
    private val roberto = "Roberto?"
    private val regexWithIndent = "$indentRootBulletPoint$listBulletPoint$stringMatchingRegex"

    @OptIn(ExperimentalStdlibApi::class)
    @TestFactory
    fun toContainAtLeast() = testFactory(toContainAtLeastPair.second) { fn ->
        it("contains '$hello' once") {
            fn(expect(text), 1, hello, emptyArray())
        }

        it("contains '$hello' x3") {
            fn(expect(text), 1, hello, arrayOf(hello, hello))
        }

        it("contains '$hello' and '$roberto'") {
            fn(expect(text), 1, hello, arrayOf(roberto))
        }

        it("does not contain '${roberto.lowercase()}' - throws") {
            expect {
                fn(expect(text), 1, roberto.lowercase(), emptyArray())
            }.toThrow<AssertionError> {
                messageToContain(regexWithIndent, roberto.lowercase(), noMatchFoundDescr)
            }
        }
    }

    @OptIn(ExperimentalStdlibApi::class)
    @TestFactory
    fun toContainAtLeastIgnoringCase() = testFactory(toContainAtLeastIgnoringCasePair.second) { fn ->
        it("contains '${roberto.lowercase()}' ignoring case") {
            fn(expect(text), 1, roberto.lowercase(), emptyArray())
        }
    }

    @TestFactory
    fun toContainShortcut() = testFactory(toContainShortcutPair.second) { fn ->
        it("contains '$hello'") {
            fn(expect(text), hello, emptyArray())
        }

        it("contains '$hello' and '$roberto'") {
            fn(expect(text), hello, arrayOf(roberto))
        }
    }

    @TestFactory
    fun toContainAtMost() = testFactory(toContainAtMostPair.second) { fn ->
        it("contains '[a-z]' at most 17 times") {
            fn(expect(text), 17, "[a-z]", emptyArray())
        }

        it("contains '[a-z]' more than 16 times - throws") {
            expect {
                fn(expect(text), 16, "[a-z]", emptyArray())
            }.toThrow<AssertionError> {
                messageToContain(regexWithIndent, "[a-z]", "17", "16")
            }
        }
    }

    @TestFactory
    fun toContainAtMostIgnoringCase() = testFactory(toContainAtMostIgnoringCasePair.second) { fn ->
        it("contains '[a-z]' at most 19 times") {
            fn(expect(text), 19, "[a-z]", emptyArray())
        }

        it("contains '[a-z]' and '[A-Z]' at most 19 times") {
            fn(expect(text), 19, "[a-z]", arrayOf("[A-Z]"))
        }

        it("contains '[a-z]' more than 18 times - throws") {
            expect {
                fn(expect(text), 18, "[a-z]", emptyArray())
            }.toThrow<AssertionError> {
                messageToContain(regexWithIndent, "\"[a-z]\"", "19", "18")
            }
        }
    }

    @TestFactory
    fun invalidRegexPatterns() = testFactory {
        describe("invalid regex patterns") {
            it("atLeast: invalid as first arg") {
                expect {
                    toContainAtLeastPair.second(expect("a"), 1, "notA(validPattern", emptyArray())
                }.toThrow<Throwable>()
            }

            it("atLeast: invalid as second arg") {
                expect {
                    toContainAtLeastPair.second(expect("a"), 1, "valid", arrayOf("notA(validPattern"))
                }.toThrow<Throwable>()
            }

            it("atLeastIgnoringCase: invalid as first arg") {
                expect {
                    toContainAtLeastIgnoringCasePair.second(expect("a"), 1, "notA(validPattern", emptyArray())
                }.toThrow<Throwable>()
            }

            it("atLeastIgnoringCase: invalid as second arg") {
                expect {
                    toContainAtLeastIgnoringCasePair.second(expect("a"), 1, "valid", arrayOf("notA(validPattern"))
                }.toThrow<Throwable>()
            }

            it("shortcut: invalid as first arg") {
                expect {
                    toContainShortcutPair.second(expect("a"), "notA(validPattern", emptyArray())
                }.toThrow<Throwable>()
            }

            it("shortcut: invalid as second arg") {
                expect {
                    toContainShortcutPair.second(expect("a"), "valid", arrayOf("notA(validPattern"))
                }.toThrow<Throwable>()
            }

            it("atMost: invalid as first arg") {
                expect {
                    toContainAtMostPair.second(expect("a"), 2, "notA(validPattern", emptyArray())
                }.toThrow<Throwable>()
            }

            it("atMost: invalid as second arg") {
                expect {
                    toContainAtMostPair.second(expect("a"), 2, "valid", arrayOf("notA(validPattern"))
                }.toThrow<Throwable>()
            }

            it("atMostIgnoringCase: invalid as first arg") {
                expect {
                    toContainAtMostIgnoringCasePair.second(expect("a"), 2, "notA(validPattern", emptyArray())
                }.toThrow<Throwable>()
            }

            it("atMostIgnoringCase: invalid as second arg") {
                expect {
                    toContainAtMostIgnoringCasePair.second(expect("a"), 2, "valid", arrayOf("notA(validPattern"))
                }.toThrow<Throwable>()
            }
        }
    }
}
