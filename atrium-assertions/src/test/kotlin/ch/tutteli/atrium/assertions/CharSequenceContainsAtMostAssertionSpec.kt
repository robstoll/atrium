package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.*
import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion.*
import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator
import ch.tutteli.atrium.assertions.charsequence.contains.builders.*
import ch.tutteli.atrium.assertions.charsequence.contains.decorators.CharSequenceContainsNoOpDecorator
import ch.tutteli.atrium.creating.IAssertionPlant
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import kotlin.reflect.KFunction2
import kotlin.reflect.KProperty

object CharSequenceContainsAtMostAssertionSpec : Spek({

    val text = "Hello my name is Robert"
    val fluent = assert(text)
    val helloWorld = "Hello World, I am Oskar"
    val fluentHelloWorld = assert(helloWorld)

    val containsProp: KProperty<CharSequenceContainsBuilder<String, CharSequenceContainsNoOpDecorator>> = fluent::contains
    val contains = containsProp.name
    val containsNotFun: KFunction2<Any, Array<out Any>, IAssertionPlant<CharSequence>> = fluent::containsNot
    val containsNot = containsNotFun.name
    val exactly = CharSequenceContainsBuilder<String, CharSequenceContainsAssertionCreator.IDecorator>::exactly.name
    val atMost = CharSequenceContainsBuilder<String, CharSequenceContainsAssertionCreator.IDecorator>::atMost.name
    val ignoringCase = CharSequenceContainsBuilder<String, CharSequenceContainsNoOpDecorator>::ignoringCase.name

    val illegalArgumentException = IllegalArgumentException::class.simpleName
    val separator = System.getProperty("line.separator")!!

    describe("fun $contains with specifier $atMost") {

        context("throws an $illegalArgumentException") {
            test("for $atMost(-1) -- only positive numbers") {
                expect {
                    fluent.contains.atMost(-1)
                }.toThrow<IllegalArgumentException>().and.message.contains("positive number", -1)
            }
            test("for $atMost(0) -- points to $containsNot") {
                expect {
                    fluent.contains.atMost(0)
                }.toThrow<IllegalArgumentException>().and.message.contains(containsNot, atMost)
            }
        }

        context("text '$helloWorld'") {
            group("happy case with $atMost once") {
                test("$contains 'H' $atMost once does not throw") {
                    fluentHelloWorld.contains.atMost(1).value('H')
                }
                test("$contains 'H' and 'e' and 'W' $atMost once does not throw") {
                    fluentHelloWorld.contains.atMost(1).values('H', 'e', 'W')
                }
                test("$contains 'W' and 'H' and 'e' $atMost once does not throw") {
                    fluentHelloWorld.contains.atMost(1).values('W', 'H', 'e')
                }
                test("$contains 'x' and 'y' and 'z' $atMost once does not throw") {
                    fluentHelloWorld.contains.atMost(1).values('x', 'y', 'z')
                }
            }

            group("failing assertions; search string at different positions") {
                test("$contains 'l' $atMost once throws AssertionError") {
                    expect {
                        fluentHelloWorld.contains.atMost(1).value('l')
                    }.toThrow<AssertionError>().message.containsDefaultTranslationOf(AT_MOST)
                }
                test("$contains 'H', 'l' $atMost once throws AssertionError") {
                    expect {
                        fluentHelloWorld.contains.atMost(1).values('H', 'l')
                    }.toThrow<AssertionError>().message.contains(AT_MOST.getDefault(), 'l')
                }
                test("$contains 'l', 'H' $exactly once throws AssertionError") {
                    expect {
                        fluentHelloWorld.contains.atMost(1).values('l', 'H')
                    }.toThrow<AssertionError>().message.contains(AT_MOST.getDefault(), 'l')
                }
                test("$contains 'o', 'E', 'W', 'l' $atMost once throws AssertionError") {
                    expect {
                        fluentHelloWorld.contains.atMost(1).values('o', 'E', 'W', 'l')
                    }.toThrow<AssertionError>().message.contains(AT_MOST.getDefault(), 'o', 'l')
                }
            }

            group("multiple occurrences of the search string") {
                test("$contains 'o' $atMost once throws AssertionError and message contains both, how many times we expected (1) and how many times it actually contained 'o' (2)") {
                    expect {
                        fluentHelloWorld.contains.atMost(1).value('o')
                    }.toThrow<AssertionError>().and.message {
                        contains(
                            CONTAINS.getDefault() + ": 'o'",
                            NUMBER_OF_OCCURRENCES.getDefault() + ": 2$separator"
                        )
                        endsWith(AT_MOST.getDefault() + ": 1")
                    }
                }

                test("$contains 'o' $atMost twice does not throw") {
                    fluentHelloWorld.contains.atMost(2).value('o')
                }
                test("$contains $ignoringCase 'o' $atMost twice throws AssertionError") {
                    expect {
                        fluentHelloWorld.contains.ignoringCase.atMost(2).value('o')
                    }.toThrow<AssertionError>().and.message.containsDefaultTranslationOf(AT_MOST)
                }

                test("$contains 'o' $atMost 3 times does not throw") {
                    fluentHelloWorld.contains.atMost(3).value('o')
                }
                test("$contains 'o' and 'l' $atMost twice throws AssertionError and message contains both, how many times we expected (2) and how many times it actually contained 'l' (3)") {
                    expect {
                        fluentHelloWorld.contains.atMost(2).values('o', 'l')
                    }.toThrow<AssertionError>().and.message {
                        contains(
                            CONTAINS.getDefault() + ": 'l'",
                            NUMBER_OF_OCCURRENCES.getDefault() + ": 3$separator"
                        )
                        endsWith(AT_MOST.getDefault() + ": 2")
                        containsNot(CONTAINS.getDefault() + ": 'o'")
                    }
                }
                test("$contains 'l' $atMost 3 times does not throw") {
                    fluentHelloWorld.contains.atMost(3).value('l')
                }
                test("$contains 'o' and 'l' $atMost 3 times does not throw") {
                    fluentHelloWorld.contains.atMost(3).values('o', 'l')
                }

            }
        }
    }
})
