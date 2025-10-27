package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.CharSequenceToContainSpecBase.Companion.helloMyNameIsRobert
import ch.tutteli.atrium.specs.integration.CharSequenceToContainSpecBase.Companion.helloWorld
import ch.tutteli.atrium.specs.integration.CharSequenceToContainSpecBase.Companion.numberOfOccurrences
import ch.tutteli.atrium.specs.integration.CharSequenceToContainSpecBase.Companion.separator
import ch.tutteli.atrium.specs.integration.CharSequenceToContainSpecBase.Companion.toContainDescr
import ch.tutteli.atrium.specs.integration.CharSequenceToContainSpecBase.Companion.toContainIgnoringCase
import ch.tutteli.atrium.specs.integration.CharSequenceToContainSpecBase.Companion.value
import ch.tutteli.atrium.testfactories.TestFactory
import ch.tutteli.atrium.translations.DescriptionCharSequenceExpectation.EXACTLY
import kotlin.test.Test

@Suppress("FunctionName")
abstract class AbstractCharSequenceToContainExactlyExpectationsTest(
    private val toContainExactlyPair: Pair<(String, String) -> String, Fun3<CharSequence, Int, Any, Array<out Any>>>,
    private val toContainExactlyIgnoringCasePair: Pair<(String, String) -> String, Fun3<CharSequence, Int, Any, Array<out Any>>>,
    private val notToContainPair: Pair<String, (Int) -> String>,
) : ExpectationFunctionBaseTest() {

    val toContainExactlySpec = toContainExactlyPair.second
    val toContainExactlyIgnoringCaseSpec = toContainExactlyIgnoringCasePair.second

    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        toContainExactlySpec.forSubjectLessTest(1, 2.3, arrayOf()),
        toContainExactlyIgnoringCaseSpec.forSubjectLessTest(2, 2.3, arrayOf())
    )

    fun Expect<CharSequence>.toContainExactlyFun(exactly: Int, a: Any, vararg aX: Any) =
        toContainExactlySpec(this, exactly, a, aX)

    fun Expect<CharSequence>.toContainExactlyIgnoringCaseFun(exactly: Int, a: Any, vararg aX: Any) =
        toContainExactlyIgnoringCaseSpec(this, exactly, a, aX)

    val exactlyDescr = EXACTLY.getDefault()
    val valueWithIndent = "$indentRootBulletPoint$listBulletPoint$value"

    @TestFactory
    fun toContainExactly__illegal_subject__throws_an_IllegalArgumentException() = testFactoryDescribeSameBehaviour(
        toContainExactlySpec, toContainExactlyIgnoringCaseSpec
    ) { toContainExactlySameBehaviourFun ->
        val (notToContain, errorMsgContainsNot) = notToContainPair

        it("for exactly -1 -- only positive numbers") {
            expect {
                expect(helloMyNameIsRobert).toContainExactlySameBehaviourFun(-1, "", emptyArray())
            }.toThrow<IllegalArgumentException> { messageToContain("positive number", "-1") }
        }

        it("for exactly 0 -- points to $notToContain") {
            expect {
                expect(helloMyNameIsRobert).toContainExactlySameBehaviourFun(0, "", emptyArray())
            }.toThrow<IllegalArgumentException> { message { toEqual(errorMsgContainsNot(0)) } }
        }

        it("if an object is passed as first expected") {
            expect {
                expect(helloMyNameIsRobert).toContainExactlySameBehaviourFun(
                    1, expect(helloMyNameIsRobert), emptyArray()
                )
            }.toThrow<IllegalArgumentException> { messageToContain("CharSequence", "Number", "Char") }
        }

        it("if an object is passed as second expected") {
            expect {
                expect(helloMyNameIsRobert).toContainExactlySameBehaviourFun(
                    1, "that's fine", arrayOf(expect(helloMyNameIsRobert))
                )
            }.toThrow<IllegalArgumentException> { messageToContain("CharSequence", "Number", "Char") }
        }
    }

    @Test
    fun toContainExactly__aaaa__search_for_aa_finds_3_non_disjoint_matches() {
        expect("aaaa" as CharSequence).toContainExactlyFun(3, "aa")
    }

    @TestFactory
    fun toContainExactly__happy_cases() = testFactory(
        toContainExactlySpec
    ) {
        describe("text '$helloWorld'") {
            it("${toContainExactlyPair.first("'H'", "once")} does not throw") {
                expect(helloWorld).toContainExactlyFun(1, 'H')
            }

            it("${toContainExactlyPair.first("'H' and 'e' and 'W'", "once")} does not throw") {
                expect(helloWorld).toContainExactlyFun(1, 'H', 'e', 'W')
            }

            it("${toContainExactlyPair.first("'W' and 'H' and 'e'", "once")} does not throw") {
                expect(helloWorld).toContainExactlyFun(1, 'W', 'H', 'e')
            }
        }
    }

    @TestFactory
    fun toContainExactly_toContainExactlyIgnoringCase__failing_cases() = testFactory(
        toContainExactlySpec, toContainExactlyIgnoringCaseSpec
    ) {
        describe("text '$helloWorld'") {
            it("${toContainExactlyPair.first("'h'", "once")} throws AssertionError") {
                expect {
                    expect(helloMyNameIsRobert).toContainExactlyFun(1, 'h')
                }.toThrow<AssertionError> { messageToContain("$exactlyDescr: 1", "$valueWithIndent: 'h'") }
            }
            it("${toContainExactlyIgnoringCasePair.first("'h'", "once")} does not throw (case ignored)") {
                expect(helloWorld).toContainExactlyIgnoringCaseFun(1, 'h')
            }

            it("${toContainExactlyPair.first("'H', 'E'", "once")} throws AssertionError mentioning only 'E'") {
                expect {
                    expect(helloWorld).toContainExactlyFun(1, 'H', 'E')
                }.toThrow<AssertionError> {
                    message {
                        toContain("$exactlyDescr: 1", "$valueWithIndent: 'E'")
                        notToContain("$valueWithIndent: 'H'")
                    }
                }
            }
            it("${toContainExactlyIgnoringCasePair.first("'H', 'E'", "once")} does not throw (case ignored)") {
                expect(helloWorld).toContainExactlyIgnoringCaseFun(1, 'H', 'E')
            }

            it("${toContainExactlyPair.first("'E', 'H'", "once")} throws AssertionError mentioning only 'E'") {
                expect {
                    expect(helloWorld).toContainExactlyFun(1, 'E', 'H')
                }.toThrow<AssertionError> {
                    message {
                        toContain("$exactlyDescr: 1", "$valueWithIndent: 'E'")
                        notToContain("$valueWithIndent: 'H'")
                    }
                }
            }
            it("${toContainExactlyIgnoringCasePair.first("'E', 'H'", "once")} does not throw (case ignored)") {
                expect(helloWorld).toContainExactlyIgnoringCaseFun(1, 'E', 'H')
            }

            it("${toContainExactlyPair.first("'H' and 'E' and 'w'", "once")} throws AssertionError") {
                expect {
                    expect(helloWorld).toContainExactlyFun(1, 'H', 'E', 'w')
                }.toThrow<AssertionError> { messageToContain(exactlyDescr, 'E', 'w') }
            }
            it(
                toContainExactlyIgnoringCasePair.first("'H' and 'E' and 'w'", "once") +
                    " does not throw (case ignored)"
            ) {
                expect(helloWorld).toContainExactlyIgnoringCaseFun(1, 'H', 'E', 'w')
            }
        }
    }

    @TestFactory
    fun toContainExactly_toContainExactlyIgnoringCaseSpec__multiple_occurrences_of_search_string() = testFactory(
        toContainExactlySpec, toContainExactlyIgnoringCaseSpec
    ) {
        it("${toContainExactlyPair.first("'o'", "once")} throws AssertionError") {
            expect {
                expect(helloWorld).toContainExactlyFun(1, 'o')
            }.toThrow<AssertionError> { messageToContain("$exactlyDescr: 1", "$valueWithIndent: 'o'") }
        }

        it("${toContainExactlyPair.first("'o'", "twice")} does not throw") {
            expect(helloWorld).toContainExactlyFun(2, 'o')
        }
        it("${toContainExactlyIgnoringCasePair.first("'o'", "twice")} throws") {
            expect {
                expect(helloWorld).toContainExactlyIgnoringCaseFun(2, 'o')
            }.toThrow<AssertionError> {
                message {
                    toContain(
                        "$rootBulletPoint$toContainIgnoringCase: $separator" +
                            "$valueWithIndent: 'o'",
                        "$numberOfOccurrences: 3$separator"
                    )
                    toEndWith("$exactlyDescr: 2")
                }
            }
        }

        it(
            "${toContainExactlyPair.first("'o'", "3 times")} throws AssertionError and message contains both, " +
                "how many times we expected (3) and how many times it actually contained 'o' (2)"
        ) {
            expect {
                expect(helloWorld).toContainExactlyFun(3, 'o')
            }.toThrow<AssertionError> {
                message {
                    toContain(
                        "$rootBulletPoint$toContainDescr: $separator" +
                            "$valueWithIndent: 'o'",
                        "$numberOfOccurrences: 2$separator"
                    )
                    toEndWith("$exactlyDescr: 3")
                }
            }
        }
        it("${toContainExactlyIgnoringCasePair.first("'o'", "3 times")} does not throw") {
            expect(helloWorld).toContainExactlyIgnoringCaseFun(3, 'o')
        }
        it("${toContainExactlyIgnoringCasePair.first("'o' and 'o'", "3 times")} does not throw") {
            expect(helloWorld).toContainExactlyIgnoringCaseFun(3, 'o', 'o')
        }

        it("${toContainExactlyPair.first("'o' and 'l'", "twice")} throws AssertionError") {
            expect {
                expect(helloWorld).toContainExactlyFun(2, 'o', 'l')
            }.toThrow<AssertionError> {
                message {
                    toContain(
                        "$rootBulletPoint$toContainDescr: $separator" +
                            "$valueWithIndent: 'l'",
                        "$numberOfOccurrences: 3$separator"
                    )
                    toEndWith("$exactlyDescr: 2")
                    notToContain("$valueWithIndent: 'o'")
                }
            }
        }

        it("${toContainExactlyPair.first("'l'", "3 times")} does not throw") {
            expect(helloWorld).toContainExactlyFun(3, 'l')
        }

        it(
            toContainExactlyPair.first("'o' and 'l'", "3 times") +
                " throws AssertionError and message contains both, how many times we expected (3) and how many times it actually contained 'o' (2)"
        ) {
            expect {
                expect(helloWorld).toContainExactlyFun(3, 'o', 'l')
            }.toThrow<AssertionError> {
                message {
                    toContain(
                        "$rootBulletPoint$toContainDescr: $separator" +
                            "$valueWithIndent: 'o'",
                        "$numberOfOccurrences: 2$separator"
                    )
                    toEndWith("$exactlyDescr: 3")
                    notToContain("$valueWithIndent: 'l'")
                }
            }
        }
    }
}
