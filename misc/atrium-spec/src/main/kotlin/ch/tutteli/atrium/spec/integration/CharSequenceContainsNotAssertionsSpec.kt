@file:Suppress("DEPRECATION" /* will be removed with 0.10.0 */)
package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.messageContains
import ch.tutteli.atrium.api.cc.en_GB.toThrow
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.include

@Deprecated("Switch from Assert to Expect and use Spec from atrium-specs-common; will be removed with 0.10.0")
abstract class CharSequenceContainsNotAssertionsSpec(
    verbs: AssertionVerbFactory,
    containsNotTriple: Triple<String, (String) -> String, Assert<CharSequence>.(Any, Array<out Any>) -> Assert<CharSequence>>,
    containsNotIgnoringCaseTriple: Triple<String, (String) -> String, Assert<CharSequence>.(Any, Array<out Any>) -> Assert<CharSequence>>,
    rootBulletPoint: String,
    listBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : CharSequenceContainsSpecBase({

    include(@Suppress("DEPRECATION") object : SubjectLessAssertionSpec<CharSequence>(describePrefix,
        containsNotTriple.first to mapToCreateAssertion { containsNotTriple.third(this, 2.3, arrayOf()) },
        containsNotIgnoringCaseTriple.first to mapToCreateAssertion { containsNotIgnoringCaseTriple.third(this, 2.3, arrayOf()) }
    ) {})

    include(@Suppress("DEPRECATION") object : CheckingAssertionSpec<String>(verbs, describePrefix,
        checkingTriple(containsNotTriple.first, { containsNotTriple.third(this, 2.3, arrayOf()) }, "not in there", "2.3,2.3,2.3"),
        checkingTriple(containsNotIgnoringCaseTriple.first, { containsNotIgnoringCaseTriple.third(this, 2.3, arrayOf()) }, "not in there", "2.3,2.3,2.3")
    ) {})

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val assert: (CharSequence) -> Assert<CharSequence> = verbs::checkImmediately
    val expect = verbs::checkException
    val fluent = assert(text)
    val fluentHelloWorld = assert(helloWorld)

    val (containsNot, containsNotTest, containsNotFunArr) = containsNotTriple
    fun Assert<CharSequence>.containsNotFun(a: Any, vararg aX: Any)
        = containsNotFunArr(a, aX)

    val (_, containsNotIgnoringCaseTest, containsNotIgnoringCaseFunArr) = containsNotIgnoringCaseTriple
    fun Assert<CharSequence>.containsNotIgnoringCaseFun(a: Any, vararg aX: Any)
        = containsNotIgnoringCaseFunArr(a, aX)

    val containsNotDescr = DescriptionCharSequenceAssertion.CONTAINS_NOT.getDefault()
    val containsNotIgnoringCaseDescr = String.format(DescriptionCharSequenceAssertion.IGNORING_CASE.getDefault(), containsNotDescr)

    val indentBulletPoint = " ".repeat(rootBulletPoint.length)
    val valueWithIndent = "$indentBulletPoint$listBulletPoint$value"

    describeFun(containsNot) {

        context("throws an $illegalArgumentException") {

            test("if an object is passed to $containsNotTest as first expected") {
                expect {
                    fluent.containsNotFun(fluent)
                }.toThrow<IllegalArgumentException> { messageContains("CharSequence", "Number", "Char") }
            }
            test("if an object is passed to $containsNotTest as second expected") {
                expect {
                    fluent.containsNotFun("that's fine", fluent)
                }.toThrow<IllegalArgumentException> { messageContains("CharSequence", "Number", "Char") }
            }
            test("if an object is passed to $containsNotIgnoringCaseTest as first expected") {
                expect {
                    fluent.containsNotIgnoringCaseFun(fluent)
                }.toThrow<IllegalArgumentException> { messageContains("CharSequence", "Number", "Char") }
            }
            test("if an object is passed to $containsNotIgnoringCaseTest as second expected") {
                expect {
                    fluent.containsNotIgnoringCaseFun("that's fine", fluent)
                }.toThrow<IllegalArgumentException> { messageContains("CharSequence", "Number", "Char") }
            }
        }

        context("text '$helloWorld'") {
            group("happy case with $containsNot once") {
                test("${containsNotTest("'h'")} does not throw") {
                    fluentHelloWorld.containsNotFun('h')
                }
                test("${containsNotTest("'h' and 'E' and 'w'")} does not throw") {
                    fluentHelloWorld.containsNotFun('h', 'E', 'w')
                }
                test("${containsNotTest("'w' and 'h' and 'E'")} does not throw") {
                    fluentHelloWorld.containsNotFun('w', 'h', 'E')
                }
                test("${containsNotIgnoringCaseTest("'x' and 'y' and 'z'")} does not throw") {
                    fluentHelloWorld.containsNotIgnoringCaseFun('x', 'y', 'z')
                }
            }

            group("failing cases; search string at different positions") {
                test("${containsNotTest("'l'")} throws AssertionError") {
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
                test("${containsNotTest("'H', 'l'")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsNotFun('H', 'l')
                    }.toThrow<AssertionError> { messageContains("$valueWithIndent: 'l'") }
                }
                test("${containsNotTest("'l', 'H'")} once throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsNotFun('l', 'H')
                    }.toThrow<AssertionError> { messageContains("$valueWithIndent: 'l'") }
                }
                test("${containsNotIgnoringCaseTest("'H', 'l'")} throws AssertionError") {
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
                test("${containsNotIgnoringCaseTest("'L', 'H'")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsNotIgnoringCaseFun('L', 'H')
                    }.toThrow<AssertionError> { messageContains('H', 'L') }
                }
                test("${containsNotTest("'o', 'E', 'w', 'l'")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsNotFun('o', 'E', 'w', 'l')
                    }.toThrow<AssertionError> { messageContains('o', 'l') }
                }
                test("${containsNotIgnoringCaseTest("'o', 'E', 'w', 'l'")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsNotIgnoringCaseFun('o', 'E', 'w', 'l')
                    }.toThrow<AssertionError> { messageContains('o', 'E', "w", 'l') }
                }
            }
        }
    }
})
