package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.api.cc.en_UK.contains
import ch.tutteli.atrium.api.cc.en_UK.message
import ch.tutteli.atrium.api.cc.en_UK.toThrow
import ch.tutteli.atrium.assertions.DescriptionBasic
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.include

abstract class CharSequenceContainsNotAssertionSpec(
    verbs: AssertionVerbFactory,
    containsNotTriple: Triple<String, (String) -> String, AssertionPlant<CharSequence>.(Any, Array<out Any>) -> AssertionPlant<CharSequence>>,
    containsNotIgnoringCaseTriple: Triple<String, (String) -> String, AssertionPlant<CharSequence>.(Any, Array<out Any>) -> AssertionPlant<CharSequence>>,
    describePrefix: String = "[Atrium] "
) : CharSequenceContainsSpecBase({

    include(object : ch.tutteli.atrium.spec.assertions.SubjectLessAssertionSpec<CharSequence>(describePrefix,
        containsNotTriple.first to mapToCreateAssertion { containsNotTriple.third(this, 2.3, arrayOf()) },
        containsNotIgnoringCaseTriple.first to mapToCreateAssertion { containsNotIgnoringCaseTriple.third(this, 2.3, arrayOf()) }
    ) {})

    include(object : ch.tutteli.atrium.spec.assertions.CheckingAssertionSpec<String>(verbs, describePrefix,
        checkingTriple(containsNotTriple.first, { containsNotTriple.third(this, 2.3, arrayOf()) }, "not in there", "2.3,2.3,2.3"),
        checkingTriple(containsNotIgnoringCaseTriple.first, { containsNotIgnoringCaseTriple.third(this, 2.3, arrayOf()) }, "not in there", "2.3,2.3,2.3")
    ) {})

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val assert: (CharSequence) -> AssertionPlant<CharSequence> = verbs::checkImmediately
    val expect = verbs::checkException
    val fluent = assert(text)
    val fluentHelloWorld = assert(helloWorld)

    val (containsNot, containsNotTest, containsNotFunArr) = containsNotTriple
    fun AssertionPlant<CharSequence>.containsNotFun(a: Any, vararg aX: Any)
        = containsNotFunArr(a, aX)

    val (_, containsNotIgnoringCaseTest, containsNotIgnoringCaseFunArr) = containsNotIgnoringCaseTriple
    fun AssertionPlant<CharSequence>.containsNotIgnoringCaseFun(a: Any, vararg aX: Any)
        = containsNotIgnoringCaseFunArr(a, aX)

    describeFun(containsNot) {

        context("throws an $illegalArgumentException") {

            test("if an object is passed to $containsNotTest as first expected") {
                expect {
                    fluent.containsNotFun(fluent)
                }.toThrow<IllegalArgumentException> { message { contains("CharSequence", "Number", "Char") } }
            }
            test("if an object is passed to $containsNotTest as second expected") {
                expect {
                    fluent.containsNotFun("that's fine", fluent)
                }.toThrow<IllegalArgumentException> { message { contains("CharSequence", "Number", "Char") } }
            }
            test("if an object is passed to $containsNotIgnoringCaseTest as first expected") {
                expect {
                    fluent.containsNotIgnoringCaseFun(fluent)
                }.toThrow<IllegalArgumentException> { message { contains("CharSequence", "Number", "Char") } }
            }
            test("if an object is passed to $containsNotIgnoringCaseTest as second expected") {
                expect {
                    fluent.containsNotIgnoringCaseFun("that's fine", fluent)
                }.toThrow<IllegalArgumentException> { message { contains("CharSequence", "Number", "Char") } }
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

            group("failing assertions; search string at different positions") {
                test("${containsNotTest("'l'")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsNotFun('l')
                    }.toThrow<AssertionError> { message { contains("${DescriptionBasic.IS.getDefault()}: 0") } }
                }
                test("${containsNotTest("'H', 'l'")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsNotFun('H', 'l')
                    }.toThrow<AssertionError> { message { contains('l') } }
                }
                test("${containsNotTest("'l', 'H'")} once throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsNotFun('l', 'H')
                    }.toThrow<AssertionError> { message { contains('l') } }
                }
                test("${containsNotIgnoringCaseTest("'H', 'l'")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsNotIgnoringCaseFun('H', 'l')
                    }.toThrow<AssertionError> { message { contains('H', 'l') } }
                }
                test("${containsNotIgnoringCaseTest("'L', 'H'")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsNotIgnoringCaseFun('L', 'H')
                    }.toThrow<AssertionError> { message { contains('H', 'L') } }
                }
                test("${containsNotTest("'o', 'E', 'w', 'l'")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsNotFun('o', 'E', 'w', 'l')
                    }.toThrow<AssertionError> { message { contains('o', 'l') } }
                }
                test("${containsNotIgnoringCaseTest("'o', 'E', 'w', 'l'")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsNotIgnoringCaseFun('o', 'E', 'w', 'l')
                    }.toThrow<AssertionError> { message { contains('o', 'E', "w", 'l') } }
                }
            }
        }
    }
})
