package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageToContain
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion
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

    val fluent = expect(text as CharSequence)
    val fluentHelloWorld = expect(helloWorld as CharSequence)

    fun Expect<CharSequence>.notToContainFun(a: Any, vararg aX: Any) = notToContain(this, a, aX)

    fun Expect<CharSequence>.notToContainIgnoringCaseFun(a: Any, vararg aX: Any) = notToContainIgnoringCase(this, a, aX)

    val notToContainDescr = DescriptionCharSequenceAssertion.CONTAINS_NOT.getDefault()
    val notToContainIgnoringCaseDescr =
        String.format(DescriptionCharSequenceAssertion.IGNORING_CASE.getDefault(), notToContainDescr)

    val valueWithIndent = "$indentRootBulletPoint$listBulletPoint$value"

    describeFun(notToContain.name, notToContainIgnoringCase.name) {

        context("throws an $illegalArgumentException") {

            it("if an object is passed as first expected") {
                expect {
                    fluent.notToContainFun(fluent)
                }.toThrow<IllegalArgumentException> { messageToContain("CharSequence", "Number", "Char") }
            }
            it("if an object is passed as second expected") {
                expect {
                    fluent.notToContainFun("that's fine", fluent)
                }.toThrow<IllegalArgumentException> { messageToContain("CharSequence", "Number", "Char") }
            }
            it("if an object is passed as first expected") {
                expect {
                    fluent.notToContainIgnoringCaseFun(fluent)
                }.toThrow<IllegalArgumentException> { messageToContain("CharSequence", "Number", "Char") }
            }
            it("if an object is passed as second expected") {
                expect {
                    fluent.notToContainIgnoringCaseFun("that's fine", fluent)
                }.toThrow<IllegalArgumentException> { messageToContain("CharSequence", "Number", "Char") }
            }
        }

        context("text '$helloWorld'") {
            context("happy case with $notToContain once") {
                it("${notToContainPair.first("'h'")} does not throw") {
                    fluentHelloWorld.notToContainFun('h')
                }
                it("${notToContainPair.first("'h' and 'E' and 'w'")} does not throw") {
                    fluentHelloWorld.notToContainFun('h', 'E', 'w')
                }
                it("${notToContainPair.first("'w' and 'h' and 'E'")} does not throw") {
                    fluentHelloWorld.notToContainFun('w', 'h', 'E')
                }
                it("${notToContainIgnoringCasePair.first("'x' and 'y' and 'z'")} does not throw") {
                    fluentHelloWorld.notToContainIgnoringCaseFun('x', 'y', 'z')
                }
            }

            context("failing cases; search string at different positions") {
                it("${notToContainPair.first("'l'")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.notToContainFun('l')
                    }.toThrow<AssertionError> {
                        messageToContain(
                            "$rootBulletPoint$notToContainDescr: $separator" +
                                "$valueWithIndent: 'l'",
                            "$numberOfOccurrences: 3",
                            "${DescriptionBasic.IS.getDefault()}: 0"
                        )
                    }
                }
                it("${notToContainPair.first("'H', 'l'")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.notToContainFun('H', 'l')
                    }.toThrow<AssertionError> { messageToContain("$valueWithIndent: 'l'") }
                }
                it("${notToContainPair.first("'l', 'H'")} once throws AssertionError") {
                    expect {
                        fluentHelloWorld.notToContainFun('l', 'H')
                    }.toThrow<AssertionError> { messageToContain("$valueWithIndent: 'l'") }
                }
                it("${notToContainIgnoringCasePair.first("'H', 'l'")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.notToContainIgnoringCaseFun('H', 'l')
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
                        fluentHelloWorld.notToContainIgnoringCaseFun('L', 'H')
                    }.toThrow<AssertionError> { messageToContain('H', 'L') }
                }
                it("${notToContainPair.first("'o', 'E', 'w', 'l'")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.notToContainFun('o', 'E', 'w', 'l')
                    }.toThrow<AssertionError> { messageToContain('o', 'l') }
                }
                it("${notToContainIgnoringCasePair.first("'o', 'E', 'w', 'l'")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.notToContainIgnoringCaseFun('o', 'E', 'w', 'l')
                    }.toThrow<AssertionError> { messageToContain('o', 'E', "w", 'l') }
                }
            }
        }
    }
})
