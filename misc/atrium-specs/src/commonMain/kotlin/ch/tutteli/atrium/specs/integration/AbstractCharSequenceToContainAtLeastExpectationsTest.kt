package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.testfactories.TestFactory
import ch.tutteli.atrium.translations.DescriptionCharSequenceExpectation
import ch.tutteli.atrium.specs.integration.CharSequenceToContainSpecBase.Companion.helloMyNameIsRobert
import ch.tutteli.atrium.specs.integration.CharSequenceToContainSpecBase.Companion.helloWorld

@Suppress("FunctionName")
abstract class AbstractCharSequenceToContainAtLeastExpectationsTest(
    private val toContainAtLeastPair: Pair<(String, String) -> String, Fun3<CharSequence, Int, Any, Array<out Any>>>,
    private val toContainAtLeastIgnoringCasePair: Pair<(String, String) -> String, Fun3<CharSequence, Int, Any, Array<out Any>>>,
    private val toContainAtLeastButAtMostPair: Pair<(String, String, String) -> String, Fun4<CharSequence, Int, Int, Any, Array<out Any>>>,
    private val toContainAtLeastButAtMostIgnoringCasePair: Pair<(String, String, String) -> String, Fun4<CharSequence, Int, Int, Any, Array<out Any>>>,
    private val notToContainPair: Pair<String, (Int) -> String>,
    private val exactlyPair: Pair<String, (Int) -> String>,
    private val errorMsgAtLeastButAtMost: (Int, Int) -> String,
) : ExpectationFunctionBaseTest() {

    val toContainAtLeastSpec = toContainAtLeastPair.second
    val toContainAtLeastIgnoringCaseSpec = toContainAtLeastIgnoringCasePair.second
    val toContainAtLeastButAtMostSpec = toContainAtLeastButAtMostPair.second
    val toContainAtLeastButAtMostIgnoringCaseSpec = toContainAtLeastButAtMostIgnoringCasePair.second

    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        toContainAtLeastSpec.forSubjectLessTest(1, "2.3", arrayOf()),
        toContainAtLeastIgnoringCaseSpec.forSubjectLessTest(1, 'a', arrayOf()),
        toContainAtLeastButAtMostSpec.forSubjectLessTest(1, 2, "aA", arrayOf()),
        toContainAtLeastButAtMostIgnoringCaseSpec.forSubjectLessTest(1, 2, 2.3, arrayOf())
    )

    fun Expect<CharSequence>.toContainAtLeastFun(atLeast: Int, a: Any, vararg aX: Any) =
        toContainAtLeastSpec(this, atLeast, a, aX)

    fun Expect<CharSequence>.toContainAtLeastIgnoringCaseFun(atLeast: Int, a: Any, vararg aX: Any) =
        toContainAtLeastIgnoringCaseSpec(this, atLeast, a, aX)

    fun Expect<CharSequence>.toContainAtLeastButAtMostFun(atLeast: Int, atMost: Int, a: Any, vararg aX: Any) =
        toContainAtLeastButAtMostSpec(this, atLeast, atMost, a, aX)

    fun Expect<CharSequence>.toContainAtLeastButAtMostIgnoringCaseFun(
        atLeast: Int, atMost: Int, a: Any, vararg aX: Any
    ) = toContainAtLeastButAtMostIgnoringCaseSpec(this, atLeast, atMost, a, aX)

    val valueWithIndent = "$indentRootBulletPoint$listBulletPoint$value"

    @TestFactory
    fun toContainAtLeast_toContainAtLeastIgnoringCaseSpec__illegal_subject__throws_an_IllegalArgumentException() =
        testFactoryDescribeSameBehaviour(
            toContainAtLeastSpec, toContainAtLeastIgnoringCaseSpec
        ) { toContainAtLeastSameBehaviourFun ->
            val (notToContain, errorMsgNotToContain) = notToContainPair

            it("for at least -1 -- only positive numbers") {
                expect {
                    expect(helloMyNameIsRobert).toContainAtLeastSameBehaviourFun(-1, "", emptyArray())
                }.toThrow<IllegalArgumentException> { messageToContain("positive number", -1) }
            }

            it("for at least 0 -- points to $notToContain") {
                expect {
                    expect(helloMyNameIsRobert).toContainAtLeastSameBehaviourFun(0, "", emptyArray())
                }.toThrow<IllegalArgumentException> { message { toEqual(errorMsgNotToContain(0)) } }
            }

            it("if an object is passed as first expected") {
                expect {
                    expect(helloMyNameIsRobert).toContainAtLeastSameBehaviourFun(
                        1,
                        expect(helloMyNameIsRobert),
                        emptyArray()
                    )
                }.toThrow<IllegalArgumentException> { messageToContain("CharSequence", "Number", "Char") }
            }

            it("if an object is passed as second expected") {
                expect {
                    expect(helloMyNameIsRobert).toContainAtLeastSameBehaviourFun(
                        1, "that's fine", arrayOf(expect(helloMyNameIsRobert))
                    )
                }.toThrow<IllegalArgumentException> { messageToContain("CharSequence", "Number", "Char") }
            }

            it("searching for an empty String - warns the user that the assertion is useless") {
                expect {
                    expect(helloMyNameIsRobert).toContainAtLeastSameBehaviourFun(
                        1, "that's fine", arrayOf("" /* <- that's not */)
                    )
                }.toThrow<IllegalArgumentException> { messageToContain("empty string", "forgot") }
            }

            it("searching for an empty CharSequence - warns the user that the assertion is useless") {
                expect {
                    expect(helloMyNameIsRobert).toContainAtLeastSameBehaviourFun(
                        1, "that's fine", arrayOf(StringBuilder() /* <- that's not */)
                    )
                }.toThrow<IllegalArgumentException> { messageToContain("empty CharSequence", "forgot") }
            }
        }

    @TestFactory
    fun toContainAtLeastButAtMost__illegal_subject__throws_an_IllegalArgumentException() =
        testFactoryDescribeSameBehaviour(
            toContainAtLeastButAtMostSpec, toContainAtLeastButAtMostIgnoringCaseSpec
        ) { toContainAtLeastButAtMostUnionFun ->
            val (exactly, errorMsgExactly) = exactlyPair

            it("for at least 1 but at most -1 -- since -1 is smaller than 1") {
                expect {
                    expect(helloMyNameIsRobert).toContainAtLeastButAtMostUnionFun(1, -1, "", emptyArray())
                }.toThrow<IllegalArgumentException> { message { toEqual(errorMsgAtLeastButAtMost(1, -1)) } }
            }

            it("for at least 1 but at most 0 -- since 0 is smaller than 1") {
                expect {
                    expect(helloMyNameIsRobert).toContainAtLeastButAtMostUnionFun(1, 0, "", emptyArray())
                }.toThrow<IllegalArgumentException> { message { toEqual(errorMsgAtLeastButAtMost(1, 0)) } }
            }

            it("for at least 2 but at most 1 -- since 1 is smaller than 2") {
                expect {
                    expect(helloMyNameIsRobert).toContainAtLeastButAtMostUnionFun(2, 1, "", emptyArray())
                }.toThrow<IllegalArgumentException> { message { toEqual(errorMsgAtLeastButAtMost(2, 1)) } }
            }

            it("for at least 1 but at most 1 -- points to $exactly") {
                expect {
                    expect(helloMyNameIsRobert).toContainAtLeastButAtMostUnionFun(1, 1, "", emptyArray())
                }.toThrow<IllegalArgumentException> { message { toEqual(errorMsgExactly(1)) } }
            }

            it("if an object is passed as first expected") {
                expect {
                    expect(helloMyNameIsRobert).toContainAtLeastButAtMostUnionFun(
                        1, 2, expect(helloMyNameIsRobert), emptyArray()
                    )
                }.toThrow<IllegalArgumentException> { messageToContain("CharSequence", "Number", "Char") }
            }

            it("if an object is passed as second expected") {
                expect {
                    expect(helloMyNameIsRobert).toContainAtLeastButAtMostUnionFun(
                        1, 2, "that's fine", arrayOf(expect(helloMyNameIsRobert))
                    )
                }.toThrow<IllegalArgumentException> { messageToContain("CharSequence", "Number", "Char") }
            }
            it("searching for an empty String - warns the user that the assertion is useless") {
                expect {
                    expect(helloMyNameIsRobert).toContainAtLeastButAtMostUnionFun(
                        1, 2, "that's fine", arrayOf("" /* <- that's not */)
                    )
                }.toThrow<IllegalArgumentException> { messageToContain("empty string", "forgot") }
            }

            it("searching for an empty CharSequence - warns the user that the assertion is useless") {
                expect {
                    expect(helloMyNameIsRobert).toContainAtLeastButAtMostUnionFun(
                        1, 2, "that's fine", arrayOf(StringBuilder() /* <- that's not */)
                    )
                }.toThrow<IllegalArgumentException> { messageToContain("empty CharSequence", "forgot") }
            }
        }

    @TestFactory
    fun toContainAtLeast__happy_cases() = testFactory(toContainAtLeastSpec) {
        describe("text '$helloWorld'") {
            it("${toContainAtLeastPair.first("'H'", "once")} does not throw") {
                expect(helloWorld).toContainAtLeastFun(1, 'H')
            }

            it("${toContainAtLeastPair.first("'H' and 'e' and 'W'", "once")} does not throw") {
                expect(helloWorld).toContainAtLeastFun(1, 'H', 'e', 'W')
            }

            it("${toContainAtLeastPair.first("'W' and 'H' and 'e'", "once")} does not throw") {
                expect(helloWorld).toContainAtLeastFun(1, 'W', 'H', 'e')
            }
        }
    }

    @TestFactory
    fun toContainAtLeast_toContainAtLeastIgnoringCase__failing_cases() = testFactory(
        toContainAtLeastSpec, toContainAtLeastIgnoringCaseSpec
    ) {
        describe("text '$helloWorld'") {
            it("${toContainAtLeastPair.first("'h'", "once")} throws AssertionError") {
                expect {
                    expect(helloWorld).toContainAtLeastFun(1, 'h')
                }.toThrow<AssertionError> { messageToContain(noMatchFoundDescr, "$valueWithIndent: 'h'") }
            }
            it("${toContainAtLeastIgnoringCasePair.first("'h'", "once")} does not throw") {
                expect(helloWorld).toContainAtLeastIgnoringCaseFun(1, 'h')
            }

            it("${toContainAtLeastPair.first("'H', 'E'", "once")} throws AssertionError") {
                expect {
                    expect(helloWorld).toContainAtLeastFun(1, 'H', 'E')
                }.toThrow<AssertionError> { messageToContain(noMatchFoundDescr, 'E') }
            }
            it("${toContainAtLeastIgnoringCasePair.first("'H', 'E'", "once")} does not throw") {
                expect(helloWorld).toContainAtLeastIgnoringCaseFun(1, 'H', 'E')
            }


            it("${toContainAtLeastPair.first("'E', 'H'", "once")} throws AssertionError mentioning only 'E'") {
                expect {
                    expect(helloWorld).toContainAtLeastFun(1, 'E', 'H')
                }.toThrow<AssertionError> {
                    message {
                        toContain(noMatchFoundDescr, "$valueWithIndent: 'E'")
                        notToContain("$valueWithIndent: 'H'")
                    }
                }
            }
            it("${toContainAtLeastIgnoringCasePair.first("'E', 'H'", "once")} does not throw") {
                expect(helloWorld).toContainAtLeastIgnoringCaseFun(1, 'E', 'H')
            }

            it(
                "${
                    toContainAtLeastPair.first("'H', 'E', 'w' and 'r'", "once")
                } throws AssertionError mentioning 'E' and 'w'"
            ) {
                expect {
                    expect(helloWorld).toContainAtLeastFun(1, 'H', 'E', 'w', 'r')
                }.toThrow<AssertionError> {
                    message {
                        toContain(noMatchFoundDescr, "$valueWithIndent: 'E'", "$valueWithIndent: 'w'")
                        notToContain("$valueWithIndent: 'H'", "$valueWithIndent: 'r'")
                    }
                }
            }
            it("${toContainAtLeastIgnoringCasePair.first("'H', 'E', 'w' and 'r'", "once")} does not throw") {
                expect(helloWorld).toContainAtLeastIgnoringCaseFun(1, 'H', 'E', 'w', 'r')
            }
        }
    }

    @TestFactory
    fun toContainAtLeast_toContainAtLeastIgnoringCase__multiple_occurrences_of_the_search_string() = testFactory(
        toContainAtLeastSpec, toContainAtLeastIgnoringCaseSpec
    ) {
        describe("text '$helloWorld'") {
            it("${toContainAtLeastPair.first("'o'", "once")} does not throw") {
                expect(helloWorld).toContainAtLeastFun(1, 'o')
            }

            it("${toContainAtLeastPair.first("'o'", "twice")} does not throw") {
                expect(helloWorld).toContainAtLeastFun(2, 'o')
            }

            it(
                "${toContainAtLeastPair.first("'o'", "3 times")} throws AssertionError and message contains both, " +
                    "how many times we expected (3) and how many times it actually contained 'o' (2)"
            ) {
                expect {
                    expect(helloWorld).toContainAtLeastFun(3, 'o')
                }.toThrow<AssertionError> {
                    message {
                        toContain(
                            "$rootBulletPoint$toContainDescr: $separator" +
                                "$valueWithIndent: 'o'",
                            "$numberOfOccurrences: 2$separator"
                        )
                        toEndWith("$atLeast: 3")
                    }
                }
            }
            it("${toContainAtLeastIgnoringCasePair.first("'o'", "3 times")} does not throw") {
                expect(helloWorld).toContainAtLeastIgnoringCaseFun(3, 'o')
            }


            it("${toContainAtLeastPair.first("'o' and 'l'", "twice")} does not throw") {
                expect(helloWorld).toContainAtLeastFun(2, 'o', 'l')
            }

            it("${toContainAtLeastPair.first("'l'", "3 times")} does not throw") {
                expect(helloWorld).toContainAtLeastFun(3, 'l')
            }

            it(
                "${toContainAtLeastPair.first("'o' and 'l'", "3 times")} throws AssertionError " +
                    "and message contains both, at least: 3 and how many times it actually contained 'o' (2)"
            ) {
                expect {
                    expect(helloWorld).toContainAtLeastFun(3, 'o', 'l')
                }.toThrow<AssertionError> {
                    message {
                        toContain(
                            "$rootBulletPoint$toContainDescr: $separator" +
                                "$valueWithIndent: 'o'",
                            "$numberOfOccurrences: 2$separator"
                        )
                        toEndWith("$atLeast: 3")
                        notToContain("$valueWithIndent: 'l'")
                    }
                }
            }
            it("${toContainAtLeastIgnoringCasePair.first("'o' and 'l'", "3 times")} does not throw") {
                expect(helloWorld).toContainAtLeastIgnoringCaseFun(3, 'o', 'l')
            }
        }
    }

    @TestFactory
    fun toContainAtLeast__subject_special_cases() = testFactory(toContainAtLeastSpec) {
        describe("text '\\0 hello'") {
            it("${toContainAtLeastPair.first("\"hello\" and '\\0'", "once")} does not throw") {
                expect(('\u0000' + " hello") as CharSequence).toContainAtLeastFun(1, "hello", 0.toChar())
            }
        }

        val aaaa: CharSequence = "aaaa"
        describe("text '$aaaa'") {
            it("${toContainAtLeastPair.first("'a'", "4 times")} does not throw") {
                expect(aaaa).toContainAtLeastFun(4, 'a')
            }

            it("${toContainAtLeastPair.first("'a'", "5 times")} throws AssertionError") {
                expect {
                    expect(aaaa).toContainAtLeastFun(5, 'a')
                }.toThrow<AssertionError> { messageToContain("$atLeast: 5", "$valueWithIndent: 'a'") }
            }
        }
    }

    @TestFactory
    fun toContainAtLeastButAtMost_toContainAtLeastButAtMostIgnoringCase__helloWorld() = testFactory(
        toContainAtLeastButAtMostSpec, toContainAtLeastButAtMostIgnoringCaseSpec
    ) {
        describe("text '$helloWorld'") {
            it("${toContainAtLeastButAtMostPair.first("'o'", "once", "twice")} does not throw") {
                expect(helloWorld).toContainAtLeastButAtMostFun(1, 2, 'o')
            }

            it(
                "${toContainAtLeastButAtMostPair.first("'o' and 'l'", "once", "twice")} throws AssertionError " +
                    "and message contains both, at most: 2 and how many times it actually contained 'l' (3)"
            ) {
                expect {
                    expect(helloWorld).toContainAtLeastButAtMostFun(1, 2, 'o', 'l')
                }.toThrow<AssertionError> {
                    message {
                        toContain(
                            "$rootBulletPoint$toContainDescr: $separator" +
                                "$valueWithIndent: 'l'",
                            "$numberOfOccurrences: 3$separator"
                        )
                        toEndWith("$atMost: 2")
                        notToContain(atLeast, "$valueWithIndent: 'o'")
                    }
                }
            }

            it("${toContainAtLeastButAtMostPair.first("'o' and 'l'", "twice", "3 times")} does not throw") {
                expect(helloWorld).toContainAtLeastButAtMostFun(2, 3, 'o', 'l')
            }
            it("${toContainAtLeastButAtMostIgnoringCasePair.first("'o' and 'l'", "twice", "3 times")} does not throw") {
                expect(helloWorld).toContainAtLeastButAtMostIgnoringCaseFun(2, 3, 'o', 'l')
            }

            it(
                "${toContainAtLeastButAtMostPair.first("'o' and 'l'", "3 times", "4 times")} throws " +
                    "AssertionError and message contains both, at least: 3 and how many times it actually contained 'o' (2)"
            ) {
                expect {
                    expect(helloWorld).toContainAtLeastButAtMostFun(3, 4, 'o', 'l')
                }.toThrow<AssertionError> {
                    message {
                        toContain(
                            "$rootBulletPoint$toContainDescr: $separator" +
                                "$valueWithIndent: 'o'",
                            "$numberOfOccurrences: 2$separator"
                        )
                        toEndWith("$atLeast: 3")
                        notToContain(atMost, "$valueWithIndent: 'l'")
                    }
                }
            }
            it(
                toContainAtLeastButAtMostIgnoringCasePair.first("'o' and 'l'", " 3 times", "4 times") +
                    " does not throw"
            ) {
                expect(helloWorld).toContainAtLeastButAtMostIgnoringCaseFun(3, 4, 'o', 'l')
            }
        }
    }

    companion object {
        val exactly = DescriptionCharSequenceExpectation.EXACTLY.getDefault()
        val atLeast = DescriptionCharSequenceExpectation.AT_LEAST.getDefault()
        val atMost = DescriptionCharSequenceExpectation.AT_MOST.getDefault()
        val numberOfOccurrences = DescriptionCharSequenceExpectation.NUMBER_OF_MATCHES.getDefault()
        val value = DescriptionCharSequenceExpectation.VALUE.getDefault()
        val toContainDescr = DescriptionCharSequenceExpectation.TO_CONTAIN.getDefault()
        val noMatchFoundDescr = DescriptionCharSequenceExpectation.NOT_FOUND.getDefault()
        val separator = lineSeparator
    }
}
