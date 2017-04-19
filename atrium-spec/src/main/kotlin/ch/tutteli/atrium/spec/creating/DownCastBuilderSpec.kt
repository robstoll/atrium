package ch.tutteli.atrium.spec.creating

import ch.tutteli.atrium.*
import ch.tutteli.atrium.DescriptionNarrowingAssertion.IS_A
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.checking.IAssertionChecker
import ch.tutteli.atrium.creating.DownCastBuilder
import ch.tutteli.atrium.creating.IAssertionPlantWithCommonFields.CommonFields
import ch.tutteli.atrium.reporting.ITranslatable
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.inCaseOf
import ch.tutteli.atrium.spec.verbs.VerbSpec
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doThrow
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import kotlin.reflect.KClass

open class DownCastBuilderSpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: (description: ITranslatable, subClass: KClass<Int>, CommonFields<Number?>) -> DownCastBuilder<Number, Int>
) : Spek({


    context("subject is not null") {
        context("down-cast can be performed") {
            val subject = 10
            var testee = testeeFactory(IS_A, Int::class, verbs.checkNullable(subject).commonFields)
            beforeEachTest {
                testee = testeeFactory(IS_A, Int::class, verbs.checkNullable(subject).commonFields)
            }

            it("it does not throw an exception") {
                testee.cast()
            }

            /**
             * @see VerbSpec - similar spec for lazy evaluated assertion verb
             */
            it("lazy evaluates additional defined assertions (${DownCastBuilder<Int, Int>::withLazyAssertions.name}) "
                + "which means, if all of them fail then the message contains all failing assertions") {
                val smaller = 0
                val greater = 20
                verbs.checkException {
                    testee.withLazyAssertions {
                        isSmallerThan(smaller)
                        isGreaterThan(greater)
                    }.cast()
                }.toThrow<AssertionError> {
                    and.message {
                        contains("assert: $subject") //the actual value
                        contains("is smaller than: $smaller") //the expected value
                        contains("is greater than: $greater") //the second expected value
                    }
                }
            }

            it("is possible to define further assertions after the cast using the fluent style api") {
                testee.cast().toBe(subject)
            }
        }

        context("down-cast cannot be performed") {
            val testee = testeeFactory(IS_A, Int::class, verbs.checkNullable(10.1).commonFields)
            it("throws an assertion error") {
                verbs.checkException {
                    testee.cast()
                }.toThrow<AssertionError>().and.message {
                    contains(Int::class.java.name)
                    contains(java.lang.Double::class.java.name)
                }
            }
        }
    }

    context("subject is null") {
        var testee = testeeFactory(IS_A, Int::class, verbs.checkNullable(null).commonFields)
        beforeEachTest {
            testee = testeeFactory(IS_A, Int::class, verbs.checkNullable(null).commonFields)
        }

        inCaseOf("using an own null-representation") {
            val nullRepresentation = "my own representation"
            val expectFluent = verbs.checkException {
                testee.withNullRepresentation(nullRepresentation)
                testee.cast()
            }
            it("throws an AssertionError") {
                expectFluent.toThrow<AssertionError>()
            }
            test("the error message contains the null-representation instead of ${RawString::class.java.simpleName}${RawString.Companion::NULL.name}") {
                expectFluent.toThrow<AssertionError>().and.message {
                    contains(nullRepresentation)
                    containsNot(RawString.NULL.string)
                }
            }
        }
        inCaseOf("additional assertions have been defined") {
            val expectFluent = verbs.checkException {
                testee.withLazyAssertions {
                    isSmallerThan(0)
                }
                testee.cast()
            }
            it("throws an AssertionError") {
                expectFluent.toThrow<AssertionError>()
            }
            it("does not contain additional failing assertions in the error message") {
                expectFluent.toThrow<AssertionError>().and.message.containsNot("is smaller than")
            }
        }
        inCaseOf("nothing in addition was defined (just cast is called)") {
            val expectFluent = verbs.checkException {
                testee.cast()
            }
            it("throws an AssertionError") {
                expectFluent.toThrow<AssertionError>()
            }
            it("the error message uses ${RawString.Companion::NULL.name} as null-representation") {
                expectFluent.toThrow<AssertionError>().and.message.contains(RawString.NULL.string)
            }
        }
    }

    context("dependencies") {

        group("in case the down-cast cannot be performed") {
            val assertionError = AssertionError()
            val assertionVerb = "assert"

            it("uses the AssertionChecker to report a failure") {
                val checker = mock<IAssertionChecker> {
                    on { fail(any<String>(), any(), any<IAssertion>()) }.doThrow(assertionError)
                }
                val testee = testeeFactory(IS_A, Int::class, CommonFields(assertionVerb, null, checker))
                verbs.checkException {
                    testee.cast()
                }.toThrow<AssertionError>().toBe(assertionError)
                verify(checker).fail(any<String>(), any(), any<IAssertion>())
            }

            it("throws an IllegalStateException, if reporting a failure does not throw an exception") {
                val checker = mock<IAssertionChecker>()
                val testee = testeeFactory(IS_A, Int::class, CommonFields(assertionVerb, null, checker))
                verbs.checkException {
                    testee.cast()
                }.toThrow<IllegalStateException>()
            }
        }
    }

})
