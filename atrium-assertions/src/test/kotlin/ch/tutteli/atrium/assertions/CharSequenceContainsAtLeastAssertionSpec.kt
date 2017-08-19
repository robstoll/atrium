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

object CharSequenceContainsAtLeastAssertionSpec : Spek({

    val text = "Hello my name is Robert"
    val fluent = assert(text)
    val helloWorld = "Hello World, I am Oskar"
    val fluentHelloWorld = assert(helloWorld)

    val containsProp: KProperty<CharSequenceContainsBuilder<String, CharSequenceContainsNoOpDecorator>> = fluent::contains
    val contains = containsProp.name
    val containsNotFun: KFunction2<Any, Array<out Any>, IAssertionPlant<CharSequence>> = fluent::containsNot
    val containsNot = containsNotFun.name
    val atLeast = CharSequenceContainsBuilder<String, CharSequenceContainsAssertionCreator.IDecorator>::atLeast.name
    val butAtMost = CharSequenceContainsAtLeastCheckerBuilder<String, CharSequenceContainsAssertionCreator.IDecorator>::butAtMost.name
    val exactly = CharSequenceContainsBuilder<String, CharSequenceContainsAssertionCreator.IDecorator>::exactly.name
    val ignoringCase = CharSequenceContainsBuilder<String, CharSequenceContainsNoOpDecorator>::ignoringCase.name

    val illegalArgumentException = IllegalArgumentException::class.simpleName
    val separator = System.getProperty("line.separator")!!

    describe("fun $contains with specifier $atLeast (and sometimes specifier $butAtMost)") {
        context("throws an $illegalArgumentException") {
            test("for $atLeast(-1) -- only positive numbers") {
                expect {
                    fluent.contains.atLeast(-1)
                }.toThrow<IllegalArgumentException>().and.message.contains("positive number", -1)
            }
            test("for $atLeast(0) -- points to $containsNot") {
                expect {
                    fluent.contains.atLeast(0)
                }.toThrow<IllegalArgumentException>().and.message.toBe("use $containsNot instead of $atLeast(0)")
            }
            group("using specifier $butAtMost") {
                test("for $atLeast(1).$butAtMost(-1) -- since -1 is smaller than 1") {
                    expect {
                        fluent.contains.atLeast(1).butAtMost(-1)
                    }.toThrow<IllegalArgumentException>().and.message.toBe("specifying $butAtMost(-1) does not make sense if $atLeast(1) was used before")
                }
                test("for $atLeast(1).$butAtMost(0) -- since 0 is smaller than 1") {
                    expect {
                        fluent.contains.atLeast(1).butAtMost(0)
                    }.toThrow<IllegalArgumentException>().and.message.toBe("specifying $butAtMost(0) does not make sense if $atLeast(1) was used before")
                }
                test("for $atLeast(2).$butAtMost(1) -- since 1 is smaller than 2") {
                    expect {
                        fluent.contains.atLeast(2).butAtMost(1)
                    }.toThrow<IllegalArgumentException>().and.message.toBe("specifying $butAtMost(1) does not make sense if $atLeast(2) was used before")
                }
                test("for $atLeast(1).$butAtMost(1) -- points to $exactly") {
                    expect {
                        fluent.contains.atLeast(1).butAtMost(1)
                    }.toThrow<IllegalArgumentException>().and.message.toBe("use $exactly(1) instead of $atLeast(1).$butAtMost(1)")
                }
            }
        }

        context("text '$helloWorld'") {

            group("happy case with $atLeast once") {
                test("$contains 'H' $atLeast once does not throw") {
                    fluentHelloWorld.contains.atLeast(1).value('H')
                }
                test("$contains 'H' and 'e' and 'W' $atLeast once does not throw") {
                    fluentHelloWorld.contains.atLeast(1).values('H', 'e', 'W')
                }
                test("$contains 'W' and 'H' and 'e' $atLeast once does not throw") {
                    fluentHelloWorld.contains.atLeast(1).values('W', 'H', 'e')
                }
            }

            group("failing assertions; search string at different positions with $atLeast once") {
                test("$contains 'h' $atLeast once throws AssertionError") {
                    expect {
                        fluentHelloWorld.contains.atLeast(1).value('h')
                    }.toThrow<AssertionError>().message.containsDefaultTranslationOf(AT_LEAST)
                }
                test("$contains $ignoringCase 'h' $atLeast once does not throw") {
                    fluentHelloWorld.contains.ignoringCase.atLeast(1).value('h')
                }

                test("$contains 'H', 'E' $atLeast once throws AssertionError") {
                    expect {
                        fluentHelloWorld.contains.atLeast(1).values('H', 'E')
                    }.toThrow<AssertionError>().message.contains(AT_LEAST.getDefault(), 'E')
                }
                test("$contains $ignoringCase 'H', 'E' $atLeast once does not throw") {
                    fluentHelloWorld.contains.ignoringCase.atLeast(1).values('H', 'E')
                }

                test("$contains 'E', 'H' $atLeast once throws AssertionError") {
                    expect {
                        fluentHelloWorld.contains.atLeast(1).values('E', 'H')
                    }.toThrow<AssertionError>().message.contains(AT_LEAST.getDefault(), 'E')
                }
                test("$contains $ignoringCase 'E', 'H' $atLeast once does not throw") {
                    fluentHelloWorld.contains.ignoringCase.atLeast(1).values('E', 'H')
                }

                test("$contains 'H', 'E', 'w' and 'r' $atLeast once throws AssertionError") {
                    expect {
                        fluentHelloWorld.contains.atLeast(1).values('H', 'E', 'w', 'r')
                    }.toThrow<AssertionError>().message.contains(AT_LEAST.getDefault(), 'E', 'w')
                }
                test("$contains $ignoringCase 'H', 'E', 'w' and 'r' $atLeast once does not throw") {
                    fluentHelloWorld.contains.ignoringCase.atLeast(1).values('H', 'E', 'w', 'r')
                }
            }

            group("multiple occurrences of the search string") {
                test("$contains 'o' $atLeast once does not throw") {
                    fluentHelloWorld.contains.atLeast(1).value('o')
                }
                test("$contains 'o' $atLeast twice does not throw") {
                    fluentHelloWorld.contains.atLeast(2).value('o')
                }

                test("$contains 'o' $atLeast 3 times throws AssertionError and message contains both, how many times we expected (3) and how many times it actually contained 'o' (2)") {
                    expect {
                        fluentHelloWorld.contains.atLeast(3).value('o')
                    }.toThrow<AssertionError>().and.message {
                        contains(
                            CONTAINS.getDefault() + ": 'o'",
                            NUMBER_OF_OCCURRENCES.getDefault() + ": 2$separator"
                        )
                        endsWith(AT_LEAST.getDefault() + ": 3")
                    }
                }
                test("$contains $ignoringCase 'o' $atLeast 3 times does not throw") {
                    fluentHelloWorld.contains.ignoringCase.atLeast(3).value('o')
                }

                test("$contains 'o' and 'l' $atLeast twice does not throw") {
                    fluentHelloWorld.contains.atLeast(2).values('o', 'l')
                }
                test("$contains 'l' $atLeast 3 times does not throw") {
                    fluentHelloWorld.contains.atLeast(3).value('l')
                }

                test("$contains 'o' and 'l' $atLeast 3 times throws AssertionError and message contains both, at least: 3 and how many times it actually contained 'o' (2)") {
                    expect {
                        fluentHelloWorld.contains.atLeast(3).values('o', 'l')
                    }.toThrow<AssertionError>().and.message {
                        contains(
                            CONTAINS.getDefault() + ": 'o'",
                            NUMBER_OF_OCCURRENCES.getDefault() + ": 2$separator"
                        )
                        endsWith(AT_LEAST.getDefault() + ": 3")
                        containsNot(CONTAINS.getDefault() + ": 'l'")
                    }
                }
                test("$contains $ignoringCase 'o' and 'l' $atLeast 3 times does not throw") {
                    fluentHelloWorld.contains.ignoringCase.atLeast(3).values('o', 'l')
                }
            }

            group("using specifier $butAtMost") {
                test("$contains 'o' $atLeast once $butAtMost twice does not throw") {
                    fluentHelloWorld.contains.atLeast(1).butAtMost(2).value('o')
                }
                test("$contains 'o' and 'l' $atLeast once $butAtMost twice throws AssertionError and message contains both, at most: 2 and how many times it actually contained 'l' (3)") {
                    expect {
                        fluentHelloWorld.contains.atLeast(1).butAtMost(2).values('o', 'l')
                    }.toThrow<AssertionError>().and.message {
                        contains(
                            CONTAINS.getDefault() + ": 'l'",
                            NUMBER_OF_OCCURRENCES.getDefault() + ": 3$separator"
                        )
                        endsWith(AT_MOST.getDefault() + ": 2")
                        containsNot(CONTAINS.getDefault() + ": 'o'")
                        containsNotDefaultTranslationOf(AT_LEAST)
                    }
                }
                test("$contains 'o' and 'l' $atLeast twice $butAtMost 3 times does not throw") {
                    fluentHelloWorld.contains.atLeast(2).butAtMost(3).values('o', 'l')
                }
                test("$contains $ignoringCase 'o' and 'l' $atLeast twice $butAtMost 3 times does not throw") {
                    fluentHelloWorld.contains.ignoringCase.atLeast(2).butAtMost(3).values('o', 'l')
                }

                test("$contains 'o' and 'l' $atLeast 3 times $butAtMost 4 times throws AssertionError and message contains both, at least: 3 and how many times it actually contained 'o' (2)") {
                    expect {
                        fluentHelloWorld.contains.atLeast(3).butAtMost(4).values('o', 'l')
                    }.toThrow<AssertionError>().and.message {
                        contains(
                            CONTAINS.getDefault() + ": 'o'",
                            NUMBER_OF_OCCURRENCES.getDefault() + ": 2$separator"
                        )
                        endsWith(AT_LEAST.getDefault() + ": 3")
                        containsNot(CONTAINS.getDefault() + ": 'l'")
                        containsNotDefaultTranslationOf(AT_MOST)
                    }
                }
                test("$contains $ignoringCase 'o' and 'l' $atLeast 3 times does not throw") {
                    fluentHelloWorld.contains.ignoringCase.atLeast(3).butAtMost(4).values('o', 'l')
                }
            }
        }
    }
})
