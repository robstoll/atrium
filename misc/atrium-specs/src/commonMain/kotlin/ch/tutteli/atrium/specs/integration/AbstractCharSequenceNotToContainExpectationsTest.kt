package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageToContain
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.CharSequenceToContainSpecBase.Companion.helloWorld
import ch.tutteli.atrium.specs.integration.CharSequenceToContainSpecBase.Companion.helloMyNameIsRobert
import ch.tutteli.atrium.testfactories.TestFactory
import ch.tutteli.atrium.translations.DescriptionCharSequenceExpectation

@Suppress("FunctionName")
abstract class AbstractCharSequenceNotToContainExpectationsTest(
    private val notToContainPair: Pair<(String) -> String, Fun2<CharSequence, Any, Array<out Any>>>,
    private val notToContainIgnoringCasePair: Pair<(String) -> String, Fun2<CharSequence, Any, Array<out Any>>>,
) : ExpectationFunctionBaseTest() {

    private val notToContainSpec = notToContainPair.second
    private val notToContainIgnoringCaseSpec = notToContainIgnoringCasePair.second

    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        notToContainSpec.forSubjectLessTest(2.3, arrayOf()),
        notToContainIgnoringCaseSpec.forSubjectLessTest(2.3, arrayOf()),
    )

    fun Expect<CharSequence>.notToContainFun(a: Any, vararg aX: Any) = notToContainSpec(this, a, aX)

    fun Expect<CharSequence>.notToContainIgnoringCaseFun(a: Any, vararg aX: Any) =
        notToContainIgnoringCaseSpec(this, a, aX)

    val notToContainDescr = DescriptionCharSequenceExpectation.NOT_TO_CONTAIN.getDefault()
    val notToContainIgnoringCaseDescr =
        DescriptionCharSequenceExpectation.IGNORING_CASE.getDefault().format(notToContainDescr)

    val valueWithIndent = "$indentRootBulletPoint$listBulletPoint$value"

    @TestFactory
    fun notToContain_notToContainIgnoringCase__illegal_subject__throws_an_IllegalArgumentException() =
        testFactoryDescribeSameBehaviour(
            notToContainSpec,
            notToContainIgnoringCaseSpec
        ) { notToContainSameBehaviourFun ->
            it("if an object is passed as first expected") {
                expect {
                    expect(helloMyNameIsRobert).notToContainSameBehaviourFun(expect(helloMyNameIsRobert), emptyArray())
                }.toThrow<IllegalArgumentException> { messageToContain("CharSequence", "Number", "Char") }
            }

            it("if an object is passed as second expected") {
                expect {
                    expect(helloMyNameIsRobert).notToContainSameBehaviourFun(
                        "that's fine",
                        arrayOf(expect(helloMyNameIsRobert))
                    )
                }.toThrow<IllegalArgumentException> { messageToContain("CharSequence", "Number", "Char") }
            }
        }

    @TestFactory
    fun notToContain_notToContainIgnoringCase__happy_cases() = testFactory(
        notToContainSpec, notToContainIgnoringCaseSpec
    ) {
        describe("text '$helloWorld'") {
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
    }

    @TestFactory
    fun notToContain_notToContainIgnoringCase__failing_cases() = testFactory(
        notToContainSpec, notToContainIgnoringCaseSpec
    ) {
        describe("text '$helloWorld'") {
            it("${notToContainPair.first("'l'")} throws AssertionError") {
                expect {
                    expect(helloWorld).notToContainFun('l')
                }.toThrow<AssertionError> {
                    messageToContain(
                        "$rootBulletPoint$notToContainDescr: $separator" +
                            "$valueWithIndent: 'l'",
                        "$numberOfOccurrences: 3",
                        "$toEqualDescr: 0"
                    )
                }
            }

            it("${notToContainPair.first("'H', 'l'")} throws AssertionError") {
                expect {
                    expect(helloWorld).notToContainFun('H', 'l')
                }.toThrow<AssertionError> { messageToContain("$valueWithIndent: 'l'") }
            }

            it("${notToContainPair.first("'l', 'H'")} once throws AssertionError") {
                expect {
                    expect(helloWorld).notToContainFun('l', 'H')
                }.toThrow<AssertionError> { messageToContain("$valueWithIndent: 'l'") }
            }

            it("${notToContainIgnoringCasePair.first("'H', 'l'")} throws AssertionError") {
                expect {
                    expect(helloWorld).notToContainIgnoringCaseFun('H', 'l')
                }.toThrow<AssertionError> {
                    messageToContain(
                        "$rootBulletPoint$notToContainIgnoringCaseDescr: $separator" +
                            "$valueWithIndent: 'H'",
                        "$valueWithIndent: 'l'"
                    )
                }
            }

            it("${notToContainIgnoringCasePair.first("'L', 'H'")} throws AssertionError") {
                expect {
                    expect(helloWorld).notToContainIgnoringCaseFun('L', 'H')
                }.toThrow<AssertionError> { messageToContain('H', 'L') }
            }

            it("${notToContainPair.first("'o', 'E', 'w', 'l'")} throws AssertionError") {
                expect {
                    expect(helloWorld).notToContainFun('o', 'E', 'w', 'l')
                }.toThrow<AssertionError> { messageToContain('o', 'l') }
            }

            it("${notToContainIgnoringCasePair.first("'o', 'E', 'w', 'l'")} throws AssertionError") {
                expect {
                    expect(helloWorld).notToContainIgnoringCaseFun('o', 'E', 'w', 'l')
                }.toThrow<AssertionError> { messageToContain('o', 'E', "w", 'l') }
            }
        }
    }

    companion object {
        val numberOfOccurrences = DescriptionCharSequenceExpectation.NUMBER_OF_MATCHES.getDefault()
        val value = DescriptionCharSequenceExpectation.VALUE.getDefault()

        val separator = lineSeparator
    }
}
