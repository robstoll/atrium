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
    containsNotTriple: Triple<String, (String) -> String, Expect<CharSequence>.(Any, Array<out Any>) -> Expect<CharSequence>>,
    containsNotIgnoringCaseTriple: Triple<String, (String) -> String, Expect<CharSequence>.(Any, Array<out Any>) -> Expect<CharSequence>>,
    rootBulletPoint: String,
    listBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : CharSequenceContainsSpecBase({

    include(object : SubjectLessSpec<CharSequence>(describePrefix,
        containsNotTriple.first to expectLambda { containsNotTriple.third(this, 2.3, arrayOf()) },
        containsNotIgnoringCaseTriple.first to expectLambda { containsNotIgnoringCaseTriple.third(this, 2.3, arrayOf()) }
    ) {})

    include(object : CheckingAssertionSpec<CharSequence>(verbs, describePrefix,
        checkingTriple(containsNotTriple.first, { containsNotTriple.third(this, 2.3, arrayOf()) }, "not in there" as CharSequence, "2.3,2.3,2.3"),
        checkingTriple(containsNotIgnoringCaseTriple.first, { containsNotIgnoringCaseTriple.third(this, 2.3, arrayOf()) }, "not in there" as CharSequence, "2.3,2.3,2.3")
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val expect = verbs::checkException
    val fluent = verbs.check(text as CharSequence)
    val fluentHelloWorld = verbs.check(helloWorld as CharSequence)

    val (containsNot, containsNotTest, containsNotFunArr) = containsNotTriple
    fun Expect<CharSequence>.containsNotFun(a: Any, vararg aX: Any)
        = containsNotFunArr(a, aX)

    val (_, containsNotIgnoringCaseTest, containsNotIgnoringCaseFunArr) = containsNotIgnoringCaseTriple
    fun Expect<CharSequence>.containsNotIgnoringCaseFun(a: Any, vararg aX: Any)
        = containsNotIgnoringCaseFunArr(a, aX)

    val containsNotDescr = DescriptionCharSequenceAssertion.CONTAINS_NOT.getDefault()
    val containsNotIgnoringCaseDescr = String.format(DescriptionCharSequenceAssertion.IGNORING_CASE.getDefault(), containsNotDescr)

    val indentBulletPoint = " ".repeat(rootBulletPoint.length)
    val valueWithIndent = "$indentBulletPoint$listBulletPoint$value"

    describeFun(containsNot) {

        context("throws an $illegalArgumentException") {

            it("if an object is passed to $containsNotTest as first expected") {
                expect {
                    fluent.containsNotFun(fluent)
                }.toThrow<IllegalArgumentException> { messageContains("CharSequence", "Number", "Char") }
            }
            it("if an object is passed to $containsNotTest as second expected") {
                expect {
                    fluent.containsNotFun("that's fine", fluent)
                }.toThrow<IllegalArgumentException> { messageContains("CharSequence", "Number", "Char") }
            }
            it("if an object is passed to $containsNotIgnoringCaseTest as first expected") {
                expect {
                    fluent.containsNotIgnoringCaseFun(fluent)
                }.toThrow<IllegalArgumentException> { messageContains("CharSequence", "Number", "Char") }
            }
            it("if an object is passed to $containsNotIgnoringCaseTest as second expected") {
                expect {
                    fluent.containsNotIgnoringCaseFun("that's fine", fluent)
                }.toThrow<IllegalArgumentException> { messageContains("CharSequence", "Number", "Char") }
            }
        }

        context("text '$helloWorld'") {
            context("happy case with $containsNot once") {
                it("${containsNotTest("'h'")} does not throw") {
                    fluentHelloWorld.containsNotFun('h')
                }
                it("${containsNotTest("'h' and 'E' and 'w'")} does not throw") {
                    fluentHelloWorld.containsNotFun('h', 'E', 'w')
                }
                it("${containsNotTest("'w' and 'h' and 'E'")} does not throw") {
                    fluentHelloWorld.containsNotFun('w', 'h', 'E')
                }
                it("${containsNotIgnoringCaseTest("'x' and 'y' and 'z'")} does not throw") {
                    fluentHelloWorld.containsNotIgnoringCaseFun('x', 'y', 'z')
                }
            }

            context("failing cases; search string at different positions") {
                it("${containsNotTest("'l'")} throws AssertionError") {
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
                it("${containsNotTest("'H', 'l'")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsNotFun('H', 'l')
                    }.toThrow<AssertionError> { messageContains("$valueWithIndent: 'l'") }
                }
                it("${containsNotTest("'l', 'H'")} once throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsNotFun('l', 'H')
                    }.toThrow<AssertionError> { messageContains("$valueWithIndent: 'l'") }
                }
                it("${containsNotIgnoringCaseTest("'H', 'l'")} throws AssertionError") {
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
                it("${containsNotIgnoringCaseTest("'L', 'H'")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsNotIgnoringCaseFun('L', 'H')
                    }.toThrow<AssertionError> { messageContains('H', 'L') }
                }
                it("${containsNotTest("'o', 'E', 'w', 'l'")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsNotFun('o', 'E', 'w', 'l')
                    }.toThrow<AssertionError> { messageContains('o', 'l') }
                }
                it("${containsNotIgnoringCaseTest("'o', 'E', 'w', 'l'")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsNotIgnoringCaseFun('o', 'E', 'w', 'l')
                    }.toThrow<AssertionError> { messageContains('o', 'E', "w", 'l') }
                }
            }
        }
    }
})
