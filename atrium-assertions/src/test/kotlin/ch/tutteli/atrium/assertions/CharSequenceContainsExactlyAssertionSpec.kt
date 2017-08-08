package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.*
import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion.*
import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator
import ch.tutteli.atrium.assertions.charsequence.contains.decorators.CharSequenceContainsNoOpDecorator
import ch.tutteli.atrium.builders.charsequence.contains.*
import ch.tutteli.atrium.creating.IAssertionPlant
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import java.util.regex.Pattern
import java.util.regex.PatternSyntaxException
import kotlin.reflect.KFunction2
import kotlin.reflect.KProperty

object CharSequenceContainsExactlyAssertionSpec : Spek({

    val text = "Hello my name is Robert"
    val fluent = assert(text)
    val helloWorld = "Hello World, I am Oskar"
    val fluentHelloWorld = assert(helloWorld)

    val containsProp: KProperty<CharSequenceContainsBuilder<String, CharSequenceContainsNoOpDecorator>> = fluent::contains
    val contains = containsProp.name
    val containsNotFun: KFunction2<Any, Array<out Any>, IAssertionPlant<CharSequence>> = fluent::containsNot
    val containsNot = containsNotFun.name
    val exactly = CharSequenceContainsBuilder<String, CharSequenceContainsAssertionCreator.IDecorator>::exactly.name
    val ignoringCase = CharSequenceContainsBuilder<String, CharSequenceContainsNoOpDecorator>::ignoringCase.name

    val illegalArgumentException = IllegalArgumentException::class.simpleName

    describe("fun $contains with specifier $exactly") {
        context("throws an $illegalArgumentException") {
            test("for $exactly(-1) -- only positive numbers") {
                expect {
                    fluent.contains.exactly(-1)
                }.toThrow<IllegalArgumentException>().and.message.contains("positive number", -1)
            }
            test("for $exactly(0) -- points to $containsNot") {
                expect {
                    fluent.contains.exactly(0)
                }.toThrow<IllegalArgumentException>().and.message.contains(containsNot, exactly)
            }
        }

        context("text '$helloWorld'") {

            group("happy case with $exactly once") {
                test("$contains 'H' $exactly once does not throw") {
                    fluentHelloWorld.contains.exactly(1).value('H')
                }
                test("$contains 'H' and 'e' and 'W' $exactly once does not throw") {
                    fluentHelloWorld.contains.exactly(1).values('H', 'e', 'W')
                }
                test("$contains 'W' and 'H' and 'e' $exactly once does not throw") {
                    fluentHelloWorld.contains.exactly(1).values('W', 'H', 'e')
                }
            }

            group("failing assertions; search string at different positions with $exactly once") {
                test("$contains 'h' $exactly once throws AssertionError") {
                    expect {
                        fluentHelloWorld.contains.exactly(1).value('h')
                    }.toThrow<AssertionError>().message.containsDefaultTranslationOf(EXACTLY)
                }
                test("$contains $ignoringCase 'h' $exactly once throws AssertionError") {
                    fluentHelloWorld.contains.ignoringCase.exactly(1).value('h')
                }

                test("$contains 'H', 'E' $exactly once throws AssertionError") {
                    expect {
                        fluentHelloWorld.contains.exactly(1).values('H', 'E')
                    }.toThrow<AssertionError>().message.contains(EXACTLY.getDefault(), 'E')
                }
                test("$contains $ignoringCase 'H', 'E' $exactly once throws AssertionError") {
                    fluentHelloWorld.contains.ignoringCase.exactly(1).values('H', 'E')
                }

                test("$contains 'E', 'H' $exactly once throws AssertionError") {
                    expect {
                        fluentHelloWorld.contains.exactly(1).values('E', 'H')
                    }.toThrow<AssertionError>().message.contains(EXACTLY.getDefault(), 'E')
                }
                test("$contains $ignoringCase 'E', 'H' $exactly once throws AssertionError") {
                    fluentHelloWorld.contains.ignoringCase.exactly(1).values('E', 'H')
                }

                test("$contains 'H' and 'E' and 'w' $exactly once throws AssertionError") {
                    expect {
                        fluentHelloWorld.contains.exactly(1).values('H', 'E', 'w')
                    }.toThrow<AssertionError>().message.contains(EXACTLY.getDefault(), 'E', 'w')
                }
                test("$contains $ignoringCase 'H' and 'E' and 'w' $exactly once throws AssertionError") {
                    fluentHelloWorld.contains.ignoringCase.exactly(1).values('H', 'E', 'w')
                }
            }

            group("multiple occurrences of the search string") {
                test("$contains 'o' $exactly once throws AssertionError") {
                    expect {
                        fluentHelloWorld.contains.exactly(1).value('o')
                    }.toThrow<AssertionError>().and.message.containsDefaultTranslationOf(EXACTLY)
                }
                test("$contains 'o' $exactly twice does not throw") {
                    fluentHelloWorld.contains.exactly(2).value('o')
                }
                test("$contains $ignoringCase 'o' $exactly twice throws") {
                    expect {
                        fluentHelloWorld.contains.ignoringCase.exactly(2).value('o')
                    }.toThrow<AssertionError>().and.message.contains(
                        String.format(IGNORING_CASE.getDefault(), CONTAINS.getDefault()),
                        NUMBER_OF_OCCURRENCES.getDefault() + ": 3",
                        EXACTLY.getDefault() + ": 2"
                    )
                }

                test("$contains 'o' $exactly 3 times throws AssertionError and message contains both, how many times we expected (3) and how many times it actually contained 'o' (2)") {
                    expect {
                        fluentHelloWorld.contains.exactly(3).value('o')
                    }.toThrow<AssertionError>().and.message.contains(
                        NUMBER_OF_OCCURRENCES.getDefault() + ": 2",
                        EXACTLY.getDefault() + ": 3"
                    )
                }
                test("$contains $ignoringCase 'o' $exactly 3 times does not throw") {
                    fluentHelloWorld.contains.ignoringCase.exactly(3).value('o')
                }

                test("$contains 'o' and 'l' $exactly twice throws AssertionError") {
                    expect {
                        fluentHelloWorld.contains.exactly(2).values('o', 'l')
                    }.toThrow<AssertionError>().and.message {
                        contains(
                            CONTAINS.getDefault() + ": 'l'",
                            NUMBER_OF_OCCURRENCES.getDefault() + ": 3",
                            EXACTLY.getDefault() + ": 2"
                        )
                        containsNot(CONTAINS.getDefault() + ": 'o'")
                    }
                }
                test("$contains 'l' $exactly 3 times does not throw") {
                    fluentHelloWorld.contains.exactly(3).value('l')
                }
                test("$contains 'o' and 'l' $exactly 3 times throws AssertionError and message contains both, how many times we expected (3) and how many times it actually contained 'o' (2)") {
                    expect {
                        fluentHelloWorld.contains.exactly(3).values('o', 'l')
                    }.toThrow<AssertionError>().and.message {
                        contains(
                            CONTAINS.getDefault() + ": 'o'",
                            NUMBER_OF_OCCURRENCES.getDefault() + ": 2",
                            EXACTLY.getDefault() + ": 3"
                        )
                        containsNot(CONTAINS.getDefault() + ": 'l'")
                    }
                }
            }
        }
    }
})
