package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.testfactories.TestFactory
import ch.tutteli.atrium.translations.DescriptionCharSequenceExpectation

@Suppress("FunctionName")
abstract class AbstractCharSequenceToContainLineSeparatorsHintTest(
    private val toContainPair: Pair<(String) -> String, Fun2<CharSequence, Any, Array<out Any>>>,
    private val toContainIgnoringCasePair: Pair<(String) -> String, Fun2<CharSequence, Any, Array<out Any>>>,
    private val notToContainPair: Pair<(String) -> String, Fun2<CharSequence, Any, Array<out Any>>>,
) : ExpectationFunctionBaseTest() {

    private val toContainSpec = toContainPair.second
    private val toContainIgnoringCaseSpec = toContainIgnoringCasePair.second
    private val notToContainSpec = notToContainPair.second

    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        toContainSpec.forSubjectLessTest("a\nb", arrayOf()),
        toContainIgnoringCaseSpec.forSubjectLessTest("a\nb", arrayOf()),
        notToContainSpec.forSubjectLessTest("a\nb", arrayOf()),
    )

    fun Expect<CharSequence>.toContainFun(a: Any, vararg aX: Any) = toContainSpec(this, a, aX)

    fun Expect<CharSequence>.toContainIgnoringCaseFun(a: Any, vararg aX: Any) =
        toContainIgnoringCaseSpec(this, a, aX)

    fun Expect<CharSequence>.notToContainFun(a: Any, vararg aX: Any) = notToContainSpec(this, a, aX)

    private val textWithCrlf: CharSequence = "Hello World\r\nMy name is Robert"
    private val noMatchFoundDescr = DescriptionCharSequenceExpectation.NOT_FOUND.getDefault()
    private val hint = DescriptionCharSequenceExpectation.WARNING_DIFFERENT_LINE_SEPARATORS.getDefault()

    @TestFactory
    fun toContain_toContainIgnoringCase__failing_due_to_different_line_separators__mentions_hint() =
        testFactory(toContainSpec, toContainIgnoringCaseSpec) {
            describe("text '$textWithCrlf'") {
                it("${toContainPair.first("'Hello World\\n'")} -- mentions the hint") {
                    expect {
                        expect(textWithCrlf).toContainFun("Hello World\n")
                    }.toThrow<AssertionError> { messageToContain(noMatchFoundDescr, hint) }
                }

                it("${toContainPair.first("'Hello World\\nMy name'")} -- mentions the hint") {
                    expect {
                        expect(textWithCrlf).toContainFun("Hello World\nMy name")
                    }.toThrow<AssertionError> { messageToContain(noMatchFoundDescr, hint) }
                }

                it(
                    "${toContainPair.first("'Hello World\\n' and 'My name is Robert'")} -- mentions the hint " +
                        "for the failing search criterion"
                ) {
                    expect {
                        expect(textWithCrlf).toContainFun("Hello World\n", "My name is Robert")
                    }.toThrow<AssertionError> { messageToContain(hint) }
                }

                it("${toContainIgnoringCasePair.first("'HELLO WORLD\\n'")} -- mentions the hint") {
                    expect {
                        expect(textWithCrlf).toContainIgnoringCaseFun("HELLO WORLD\n")
                    }.toThrow<AssertionError> { messageToContain(noMatchFoundDescr, hint) }
                }
            }
        }

    @TestFactory
    fun toContain__failing_for_other_reasons__does_not_mention_hint() = testFactory(toContainSpec) {
        describe("text '$textWithCrlf'") {
                it("${toContainPair.first("'My name is Robert\\n'")} -- does not mention the hint") {
                    expect {
                        expect(textWithCrlf).toContainFun("My name is Robert\n")
                    }.toThrow<AssertionError> {
                        message {
                            toContain(noMatchFoundDescr)
                            notToContain(hint)
                        }
                    }
                }

                it("${toContainPair.first("'Hello World\\r\\nWorld'")} -- does not mention the hint") {
                    expect {
                        expect(textWithCrlf).toContainFun("Hello World\r\nWorld")
                    }.toThrow<AssertionError> {
                        message {
                            toContain(noMatchFoundDescr)
                            notToContain(hint)
                        }
                    }
                }
        }
    }

    @TestFactory
    fun notToContain__failing__does_not_mention_hint() = testFactory(notToContainSpec) {
        describe("text '$textWithCrlf'") {
            it("${notToContainPair.first("'My name is Robert'")} -- does not mention the hint") {
                expect {
                    expect(textWithCrlf).notToContainFun("My name is Robert")
                }.toThrow<AssertionError> {
                    message { notToContain(hint) }
                }
            }
        }
    }

    @TestFactory
    fun happy_cases__no_hint_necessary() = testFactory(toContainSpec, toContainIgnoringCaseSpec, notToContainSpec) {
        it("${toContainPair.first("'Hello World\\r\\n'")} -- does not throw") {
            expect(textWithCrlf).toContainFun("Hello World\r\n")
        }
        it("${toContainIgnoringCasePair.first("'hello world\\r\\n'")} -- does not throw") {
            expect(textWithCrlf).toContainIgnoringCaseFun("hello world\r\n")
        }
        it("${notToContainPair.first("'Hello World\\n'")} -- does not throw") {
            expect(textWithCrlf).notToContainFun("Hello World\n")
        }
    }
}
