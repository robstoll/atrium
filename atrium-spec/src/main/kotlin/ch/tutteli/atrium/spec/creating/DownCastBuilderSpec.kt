package ch.tutteli.atrium.spec.creating

import ch.tutteli.atrium.api.cc.en_UK.*
import ch.tutteli.atrium.assertions.DescriptionNarrowingAssertion.IS_A
import ch.tutteli.atrium.assertions.DescriptionNumberAssertion
import ch.tutteli.atrium.checking.IAssertionChecker
import ch.tutteli.atrium.creating.IBaseAssertionPlant
import ch.tutteli.atrium.creating.IDownCastBuilder
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.inCaseOf
import ch.tutteli.atrium.spec.prefixedDescribe
import ch.tutteli.atrium.spec.verbs.VerbSpec
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doThrow
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import kotlin.reflect.KClass

abstract class DownCastBuilderSpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: (description: ITranslatable, subClass: KClass<Int>, IBaseAssertionPlant<Number?, *>) -> IDownCastBuilder<Number, Int>,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun prefixedDescribe(description: String, body: SpecBody.() -> Unit) {
        prefixedDescribe(describePrefix, description, body)
    }

    prefixedDescribe("fun ${IDownCastBuilder<Number, Int>::cast.name}") {

        context("subject is not null") {
            context("down-cast can be performed") {
                val subject = 10
                val subjectPlant = verbs.checkNullable(subject)
                var testee = testeeFactory(IS_A, Int::class, subjectPlant)
                beforeEachTest {
                    testee = testeeFactory(IS_A, Int::class, subjectPlant)
                }

                it("it does not throw an exception") {
                    testee.cast()
                }

                //TODO problem here is that lazy assertion lambdas add assertions one by one. In case the plant is an
                //TODO immediate evaluating assertion plant, than one fails immediately or is no longer part of the group if it holds.
                //TODO Thus we have to add an addAssertionBy(createAssertions: IAssertionPlant<*>.() -> Unit) fun to IAsertionPlant which
                //TODO collects the added assertions and then wraps them into an InvisibleAssertionGroup

//                /**
//                 * @see VerbSpec - similar spec for lazy evaluated assertion verb
//                 */
//                it("lazy evaluates additional defined assertions (${IDownCastBuilder<Int, Int>::withLazyAssertions.name}) "
//                    + "which means, if all of them fail then the message contains all failing assertions") {
//                    val less = 0
//                    val greater = 20
//                    verbs.checkException {
//                        testee.withLazyAssertions {
//                            isLessThan(less)
//                            isGreaterThan(greater)
//                        }.cast()
//                    }.toThrow<AssertionError> {
//                        and.message {
//                            contains(DescriptionNumberAssertion.IS_LESS_THAN.getDefault() + ": " + less) //the expected value
//                            contains(DescriptionNumberAssertion.IS_GREATER_THAN.getDefault() + ": " + greater) //the second expected value
//                        }
//                    }
//                }

                it("is possible to define further assertions after the cast using the fluent style api") {
                    testee.cast().toBe(subject)
                }
            }

            context("down-cast cannot be performed") {
                val subjectPlant = verbs.checkNullable(10.1)
                val testee = testeeFactory(IS_A, Int::class, subjectPlant)
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
            val subjectPlant = verbs.checkNullable(null)
            var testee = testeeFactory(IS_A, Int::class, subjectPlant)
            beforeEachTest {
                testee = testeeFactory(IS_A, Int::class, subjectPlant)
            }
            inCaseOf("additional assertions have been defined") {
                val expectFluent = verbs.checkException {
                    testee.withLazyAssertions {
                        isLessThan(0)
                    }
                    testee.cast()
                }
                it("throws an AssertionError") {
                    expectFluent.toThrow<AssertionError>()
                }
                it("does not contain additional failing assertions in the error message") {
                    expectFluent.toThrow<AssertionError>().and.message.containsNot(DescriptionNumberAssertion.IS_LESS_THAN)
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
    }
})
