package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageContains
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.verbs.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion
import org.spekframework.spek2.style.specification.Suite

abstract class CharSequenceContainsNotAssertionsSpec(
    verbs: AssertionVerbFactory,
    containsNotPair: Pair<(String) -> String, Fun2<CharSequence, Any, Array<out Any>>>,
    containsNotIgnoringCasePair: Pair<(String) -> String, Fun2<CharSequence, Any, Array<out Any>>>,
    rootBulletPoint: String,
    listBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : CharSequenceContainsSpecBase({

    val containsNot = containsNotPair.second
    val containsNotIgnoringCase = containsNotIgnoringCasePair.second
    
    include(object : SubjectLessSpec<CharSequence>(describePrefix,
        containsNot.forSubjectLess(2.3, arrayOf()),
        containsNotIgnoringCase.forSubjectLess(2.3, arrayOf())
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val expect = verbs::checkException
    val fluent = verbs.check(text as CharSequence)
    val fluentHelloWorld = verbs.check(helloWorld as CharSequence)

    fun Expect<CharSequence>.containsNotFun(a: Any, vararg aX: Any)
        = containsNot(this, a, aX)

    fun Expect<CharSequence>.containsNotIgnoringCaseFun(a: Any, vararg aX: Any)
        = containsNotIgnoringCase(this, a, aX)

    val containsNotDescr = DescriptionCharSequenceAssertion.CONTAINS_NOT.getDefault()
    val containsNotIgnoringCaseDescr = String.format(DescriptionCharSequenceAssertion.IGNORING_CASE.getDefault(), containsNotDescr)

    val indentBulletPoint = " ".repeat(rootBulletPoint.length)
    val valueWithIndent = "$indentBulletPoint$listBulletPoint$value"

    describeFun(containsNot.name, containsNotIgnoringCase.name) {

        context("throws an $illegalArgumentException") {

            it("if an object is passed as first expected") {
                expect {
                    fluent.containsNotFun(fluent)
                }.toThrow<IllegalArgumentException> { messageContains("CharSequence", "Number", "Char") }
            }
            it("if an object is passed as second expected") {
                expect {
                    fluent.containsNotFun("that's fine", fluent)
                }.toThrow<IllegalArgumentException> { messageContains("CharSequence", "Number", "Char") }
            }
            it("if an object is passed as first expected") {
                expect {
                    fluent.containsNotIgnoringCaseFun(fluent)
                }.toThrow<IllegalArgumentException> { messageContains("CharSequence", "Number", "Char") }
            }
            it("if an object is passed as second expected") {
                expect {
                    fluent.containsNotIgnoringCaseFun("that's fine", fluent)
                }.toThrow<IllegalArgumentException> { messageContains("CharSequence", "Number", "Char") }
            }
        }

        context("text '$helloWorld'") {
            context("happy case with $containsNot once") {
                it("${containsNotPair.first("'h'")} does not throw") {
                    fluentHelloWorld.containsNotFun('h')
                }
                it("${containsNotPair.first("'h' and 'E' and 'w'")} does not throw") {
                    fluentHelloWorld.containsNotFun('h', 'E', 'w')
                }
                it("${containsNotPair.first("'w' and 'h' and 'E'")} does not throw") {
                    fluentHelloWorld.containsNotFun('w', 'h', 'E')
                }
                it("${containsNotIgnoringCasePair.first("'x' and 'y' and 'z'")} does not throw") {
                    fluentHelloWorld.containsNotIgnoringCaseFun('x', 'y', 'z')
                }
            }

            context("failing cases; search string at different positions") {
                it("${containsNotPair.first("'l'")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsNotFun('l')
                    }.toThrow<AssertionError> {
                        messageContains(
                            "$rootBulletPoint$containsNotDescr: $separator" +
                                "$valueWithIndent: 'l'",
                            "$numberOfOccurrences: 3",
                            "${DescriptionBasic.IS.getDefault()}: 0"
                        )
                    }
                }
                it("${containsNotPair.first("'H', 'l'")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsNotFun('H', 'l')
                    }.toThrow<AssertionError> { messageContains("$valueWithIndent: 'l'") }
                }
                it("${containsNotPair.first("'l', 'H'")} once throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsNotFun('l', 'H')
                    }.toThrow<AssertionError> { messageContains("$valueWithIndent: 'l'") }
                }
                it("${containsNotIgnoringCasePair.first("'H', 'l'")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsNotIgnoringCaseFun('H', 'l')
                    }.toThrow<AssertionError> {
                        messageContains(
                            "$rootBulletPoint$containsNotIgnoringCaseDescr: $separator" +
                                "$valueWithIndent: 'H'",
                            "$valueWithIndent: 'l'"
                        )
                    }
                }
                it("${containsNotIgnoringCasePair.first("'L', 'H'")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsNotIgnoringCaseFun('L', 'H')
                    }.toThrow<AssertionError> { messageContains('H', 'L') }
                }
                it("${containsNotPair.first("'o', 'E', 'w', 'l'")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsNotFun('o', 'E', 'w', 'l')
                    }.toThrow<AssertionError> { messageContains('o', 'l') }
                }
                it("${containsNotIgnoringCasePair.first("'o', 'E', 'w', 'l'")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsNotIgnoringCaseFun('o', 'E', 'w', 'l')
                    }.toThrow<AssertionError> { messageContains('o', 'E', "w", 'l') }
                }
            }
        }
    }
})
