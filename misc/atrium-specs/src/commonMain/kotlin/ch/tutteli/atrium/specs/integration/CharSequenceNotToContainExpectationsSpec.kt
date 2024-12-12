package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCharSequenceProof.Companion.IGNORING_CASE
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCharSequenceProof.NOT_TO_CONTAIN
import ch.tutteli.atrium.specs.*
import org.spekframework.spek2.style.specification.Suite

abstract class CharSequenceNotToContainExpectationsSpec(
    notToContainPair: Pair<(String) -> String, Fun2<CharSequence, Any, Array<out Any>>>,
    notToContainIgnoringCasePair: Pair<(String) -> String, Fun2<CharSequence, Any, Array<out Any>>>,
    describePrefix: String = "[Atrium] "
) : CharSequenceToContainSpecBase({

    val notToContainFunArr = notToContainPair.second
    val notToContainIgnoringCaseFunArr = notToContainIgnoringCasePair.second

    include(object : SubjectLessSpec<CharSequence>(
        describePrefix,
        notToContainFunArr.forSubjectLessTest(2.3, arrayOf()),
        notToContainIgnoringCaseFunArr.forSubjectLessTest(2.3, arrayOf())
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    fun Expect<CharSequence>.notToContainFun(a: Any, vararg aX: Any) = notToContainFunArr(this, a, aX)

    fun Expect<CharSequence>.notToContainIgnoringCaseFun(a: Any, vararg aX: Any) =
        notToContainIgnoringCaseFunArr(this, a, aX)

    describeFun(notToContainFunArr.name, notToContainIgnoringCaseFunArr.name) {

        context("throws an $illegalArgumentException") {

            it("if an object is passed as first expected") {
                expect {
                    expect(text).notToContainFun(expect(text))
                }.toThrow<IllegalArgumentException> { messageToContain(ERROR_MESSAGE_ONLY_CHARSEQUENCE_NUMBER_CHAR) }
            }
            it("if an object is passed as second expected") {
                expect {
                    expect(text).notToContainFun("that's fine", expect(text))
                }.toThrow<IllegalArgumentException> { messageToContain(ERROR_MESSAGE_ONLY_CHARSEQUENCE_NUMBER_CHAR) }
            }
            it("if an object is passed as first expected") {
                expect {
                    expect(text).notToContainIgnoringCaseFun(expect(text))
                }.toThrow<IllegalArgumentException> { messageToContain(ERROR_MESSAGE_ONLY_CHARSEQUENCE_NUMBER_CHAR) }
            }
            it("if an object is passed as second expected") {
                expect {
                    expect(text).notToContainIgnoringCaseFun("that's fine", expect(text))
                }.toThrow<IllegalArgumentException> { messageToContain(ERROR_MESSAGE_ONLY_CHARSEQUENCE_NUMBER_CHAR) }
            }
        }

        context("text '$helloWorld'") {
            context("happy case with $notToContainFunArr once") {
                it("${notToContainPair.first("'h'")} does not throw") {
                    expect(helloWorld).notToContainFun('h')
                }
                it("${notToContainPair.first("'h' and 'E' and 'w'")} does not throw") {
                    expect(helloWorld).notToContainFun('h', 'E', 'w')
                }
                it("${notToContainPair.first("'w' and 'h' and 'E'")} does not throw") {
                    expect(helloWorld).notToContainFun('w', 'h', 'E')
                }
                it("${notToContainIgnoringCasePair.first("'x' and 'y' and 'z'")} does not throw") {
                    expect(helloWorld).notToContainIgnoringCaseFun('x', 'y', 'z')
                }
            }

            context("failing cases; search string at different positions") {
                it("${notToContainPair.first("'l'")} throws AssertionError") {
                    expect {
                        expect(helloWorld).notToContainFun('l')
                    }.toThrow<AssertionError> {
                        message {
                            toContainSubject("\"$helloWorld\"")
                            //TODO 1.4.0 once migrated to proof, I would like to see
                            // 🚩️ not to contain :
                            //    🚫️ value : 'l'
                            //       🔎 number of matches : 3
//                            toContain.exactly(1).matchFor(Regex(
//                                "$groupFailingBulletPoint${NOT_TO_CONTAIN.string} : $lineSeparator" +
//                                    "${indentGroupFailingBulletPoint}${failingBulletPoint}${DescriptionCharSequenceProof.VALUE.string}\\s+: 'l'$lineSeparator"+
//                                    "${indentGroupFailingBulletPoint}${indentFailingBulletPoint}${debugBulletPoint}${DescriptionCharSequenceProof.NUMBER_OF_MATCHES.string} : 3"
//                            ))
                            toContainValue("'l'")
                            toContainNumberOfMatches(3)
                            toContainToEqualDescr(0)
                        }
                    }
                }
                it("${notToContainPair.first("'H', 'l', 'A'")} throws AssertionError") {
                    expect {
                        expect(helloWorld).notToContainFun('H', 'l', 'A')
                    }.toThrow<AssertionError> {
                        message {
                            //TODO 1.3.0 once migrated to proof, I would like to see
                            // 🚩️ not to contain :
                            //    🚫️ value : 'H'
                            //       🔎 number of matches : 1
                            //    🚫️ value : 'l'
                            //       🔎 number of matches : 3
                            toContainValue("'H'")
                            toContainNumberOfMatches(1)
                            toContainValue("'l'")
                            toContainNumberOfMatches(3)
                            toContainToEqualDescr(0, numOfMatches = 2)
                            notToContain("'A'")
                        }
                    }
                }
                it("${notToContainPair.first("'l', 'H'")} throws AssertionError") {
                    expect {
                        expect(helloWorld).notToContainFun('l', 'H')
                    }.toThrow<AssertionError> {
                        message {
                            toContainValue("'H'")
                            toContainNumberOfMatches(1)
                            toContainValue("'l'")
                            toContainNumberOfMatches(3)
                            toContainToEqualDescr(0, numOfMatches = 2)
                        }
                    }
                }
                it("${notToContainIgnoringCasePair.first("'H', 'l', 'A', 'b'")} throws AssertionError") {
                    expect {
                        expect(helloWorld).notToContainIgnoringCaseFun('H', 'l', 'A', 'b')
                    }.toThrow<AssertionError> {
                        message {
                            toContainSubject("\"$helloWorld\"")
                            toContainDescr(NOT_TO_CONTAIN.IGNORING_CASE, "")
                            toContainValue("'H'")
                            toContainNumberOfMatches(1)
                            toContainValue("'l'")
                            toContainNumberOfMatches(3)
                            toContainValue("'A'")
                            toContainNumberOfMatches(2)
                            toContainToEqualDescr(0, numOfMatches = 3)
                            notToContain("'b'")
                        }
                    }
                }
                it("${notToContainIgnoringCasePair.first("'L', 'H'")} throws AssertionError") {
                    expect {
                        expect(helloWorld).notToContainIgnoringCaseFun('L', 'H')
                    }.toThrow<AssertionError> {
                        message {
                            toContainValue("'L'")
                            toContainNumberOfMatches(3)
                            toContainValue("'H'")
                            toContainNumberOfMatches(1)
                            toContainToEqualDescr(0, numOfMatches = 2)
                        }
                    }
                }
                it("${notToContainPair.first("'o', 'E', 'w', 'l'")} throws AssertionError") {
                    expect {
                        expect(helloWorld).notToContainFun('o', 'E', 'w', 'l')
                    }.toThrow<AssertionError> {
                        message {
                            toContainValue("'o'")
                            toContainValue("'l'")
                            notToContain("'E'", "'w'")
                        }
                    }
                }
                it("${notToContainIgnoringCasePair.first("'o', 'E', 'w', 'l'")} throws AssertionError") {
                    expect {
                        expect(helloWorld).notToContainIgnoringCaseFun('o', 'E', 'w', 'l')
                    }.toThrow<AssertionError> {
                        message {
                            toContainValue("'o'")
                            toContainValue("'E'")
                            toContainValue("'w'")
                            toContainValue("'l'")
                        }
                    }
                }
            }
        }
    }
})
