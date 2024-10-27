package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.message
import ch.tutteli.atrium.api.fluent.en_GB.messageToContain
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.reportables.Description
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCharSequenceProof
import ch.tutteli.atrium.specs.*
import org.spekframework.spek2.style.specification.Suite

abstract class CharSequenceNotToContainExpectationsSpec(
    notToContainPair: Pair<(String) -> String, Fun2<CharSequence, Any, Array<out Any>>>,
    notToContainIgnoringCasePair: Pair<(String) -> String, Fun2<CharSequence, Any, Array<out Any>>>,
    describePrefix: String = "[Atrium] "
) : CharSequenceToContainSpecBase({

    val notToContain = notToContainPair.second
    val notToContainIgnoringCase = notToContainIgnoringCasePair.second

    include(object : SubjectLessSpec<CharSequence>(
        describePrefix,
        notToContain.forSubjectLess(2.3, arrayOf()),
        notToContainIgnoringCase.forSubjectLess(2.3, arrayOf())
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    fun Expect<CharSequence>.notToContainFun(a: Any, vararg aX: Any) = notToContain(this, a, aX)

    fun Expect<CharSequence>.notToContainIgnoringCaseFun(a: Any, vararg aX: Any) = notToContainIgnoringCase(this, a, aX)

    val notToContainDescr = DescriptionCharSequenceProof.NOT_TO_CONTAIN.string
    val notToContainIgnoringCaseDescr =
        DescriptionCharSequenceProof.IGNORING_CASE.string.format(notToContainDescr)

    val valueWithIndent = "$indentRootBulletPoint$listBulletPoint$value"

    fun Expect<String>.toContainValue(representation: Any?) =
        toContainDescr(DescriptionCharSequenceProof.VALUE, representation)

    fun Expect<String>.toContainNumberOfMatches(representation: Any?) =
        toContainDescr(DescriptionCharSequenceProof.NUMBER_OF_MATCHES, representation)


    describeFun(notToContain.name, notToContainIgnoringCase.name) {

        context("throws an $illegalArgumentException") {

            it("if an object is passed as first expected") {
                expect {
                    expect(text).notToContainFun(expect(text))
                }.toThrow<IllegalArgumentException> { messageToContain("CharSequence", "Number", "Char") }
            }
            it("if an object is passed as second expected") {
                expect {
                    expect(text).notToContainFun("that's fine", expect(text))
                }.toThrow<IllegalArgumentException> { messageToContain("CharSequence", "Number", "Char") }
            }
            it("if an object is passed as first expected") {
                expect {
                    expect(text).notToContainIgnoringCaseFun(expect(text))
                }.toThrow<IllegalArgumentException> { messageToContain("CharSequence", "Number", "Char") }
            }
            it("if an object is passed as second expected") {
                expect {
                    expect(text).notToContainIgnoringCaseFun("that's fine", expect(text))
                }.toThrow<IllegalArgumentException> { messageToContain("CharSequence", "Number", "Char") }
            }
        }

        context("text '$helloWorld'") {
            context("happy case with $notToContain once") {
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
                            toContainSubject(helloWorld)
                            toContainDescr(DescriptionCharSequenceProof.NOT_TO_CONTAIN, Text.EMPTY)
                            toContainValue('l')
                            toContainNumberOfMatches(3)
                            toContainToEqualDescr(0)
                        }
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
    }
})
