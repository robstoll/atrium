package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.*
import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion.*
import ch.tutteli.atrium.assertions.charsequence.CharSequenceContainsAssertionCreator
import ch.tutteli.atrium.assertions.charsequence.CharSequenceContainsNoOpDecorator
import ch.tutteli.atrium.builders.charsequence.contains.*
import ch.tutteli.atrium.creating.IAssertionPlant
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import kotlin.reflect.KFunction2
import kotlin.reflect.KProperty

object CharSequenceContainsAssertionSpec : Spek({

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
    val atMost = CharSequenceContainsBuilder<String, CharSequenceContainsAssertionCreator.IDecorator>::atMost.name
    val ignoringCase = CharSequenceContainsBuilder<String, CharSequenceContainsNoOpDecorator>::ignoringCase.name

    val illegalArgumentException = IllegalArgumentException::class.simpleName

    describe("fun $contains and $containsNot") {
        context("empty string") {
            val fluentEmptyString = assert("")
            test("$contains 'Hello' throws AssertionError") {
                expect {
                    fluentEmptyString.contains("Hello")
                }.toThrow<AssertionError>().and.message.contains(
                    DescriptionCharSequenceAssertion.NUMBER_OF_OCCURRENCES.getDefault() + ": 0",
                    DescriptionCharSequenceAssertion.AT_LEAST.getDefault() + ": 1"
                )
            }
            test("$containsNot 'Hello' does not throw") {
                fluentEmptyString.containsNot("Hello")
            }
        }

        context("text '$text'") {

            context("search for 'Hello' and 'Robert'") {
                test("$contains 'Hello' does not throw") {
                    fluent.contains("Hello")
                }
                test("$containsNot 'Hello' throws AssertionError") {
                    expect {
                        fluent.containsNot("Hello")
                    }.toThrow<AssertionError>().and.message.containsDefaultTranslationOf(DescriptionCharSequenceAssertion.CONTAINS_NOT)
                }

                test("$contains 'Hello' and 'Robert' does not throw") {
                    fluent.contains("Hello", "Robert")
                }
                test("$containsNot 'Hello' and 'Robert' throws AssertionError") {
                    expect {
                        fluent.containsNot("Hello", "Robert")
                    }.toThrow<AssertionError>().message.containsDefaultTranslationOf(CONTAINS_NOT)
                }
            }

            context("search for 'notInThere' and 'neitherInThere'") {
                test("$contains 'notInThere' and 'neitherInThere' throws AssertionError") {
                    expect {
                        fluent.contains("notInThere", "neitherInThere")
                    }.toThrow<AssertionError>().message.containsDefaultTranslationOf(CONTAINS)
                }
                test("$containsNot 'notInThere' and 'neitherInThere' does not throw") {
                    fluent.containsNot("notInThere", "neitherInThere")
                }
            }

            context("search for 'hello' and 'robert'") {
                test("$contains 'hello' and 'robert' throws AssertionError") {
                    expect {
                        fluent.contains("hello", "robert")
                    }.toThrow<AssertionError>().and.message.contains.exactly(2).values(
                        CONTAINS.getDefault(),
                        NUMBER_OF_OCCURRENCES.getDefault() + ": 0",
                        AT_LEAST.getDefault() + ": 1"
                    )
                }
                test("$containsNot 'hello' and 'robert' does not throw") {
                    fluent.containsNot("hello", "robert")
                }
            }

            context("search for 'Hello' and 'notInThere'") {
                test("$contains 'notInThere' throws AssertionError") {
                    expect {
                        fluent.contains("notInThere")
                    }.toThrow<AssertionError>().message.containsDefaultTranslationOf(CONTAINS)
                }
                test("$containsNot 'notInThere' does not throw") {
                    fluent.containsNot("notInThere")
                }

                test("$contains 'Hello' and 'notInThere' throws AssertionError") {
                    expect {
                        fluent.contains("Hello", "notInThere")
                    }.toThrow<AssertionError>().message.contains(CONTAINS.getDefault(), "notInThere")
                }
                test("$containsNot 'Hello' and 'notInThere' throws AssertionError") {
                    expect {
                        fluent.containsNot("Hello", "notInThere")
                    }.toThrow<AssertionError>().message.contains(CONTAINS_NOT.getDefault(), "Hello")
                }
            }
        }

        context("error message") {
            context("feature assertion about a Person's name 'Robert Stoll'") {
                data class Person(val name: String)

                val person: Person = Person("Robert Stoll")

                test("$contains 'treboR' and 'llotS' - error message contains '-> name' exactly once") {
                    expect {
                        assert(person) {
                            its(subject::name).contains("treboR", "llotS")
                        }
                    }.toThrow<AssertionError>().and.message.contains.exactly(1).value("-> name")
                }
                test("$containsNot 'Robert' and 'Stoll' - error message contains '-> name' exactly once") {
                    expect {
                        assert(person) {
                            its(subject::name).containsNot("Robert", "Stoll")
                        }
                    }.toThrow<AssertionError>().and.message.contains.exactly(1).value("-> name")
                }
            }
        }
    }

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
                    fluentHelloWorld.contains.atLeast(1).value("H")
                }
                test("$contains 'H' and 'e' and 'W' $atLeast once does not throw") {
                    fluentHelloWorld.contains.atLeast(1).values("H", "e", "W")
                }
                test("$contains 'W' and 'H' and 'e' $atLeast once does not throw") {
                    fluentHelloWorld.contains.atLeast(1).values("W", "H", "e")
                }
            }

            group("failing assertions; search string at different positions with $atLeast once") {
                test("$contains 'h' $atLeast once throws AssertionError") {
                    expect {
                        fluentHelloWorld.contains.atLeast(1).value("h")
                    }.toThrow<AssertionError>().message.containsDefaultTranslationOf(AT_LEAST)
                }
                test("$contains $ignoringCase 'h' $atLeast once does not throw") {
                    fluentHelloWorld.contains.ignoringCase.atLeast(1).value("h")
                }

                test("$contains 'H', 'E' $atLeast once throws AssertionError") {
                    expect {
                        fluentHelloWorld.contains.atLeast(1).values("H", "E")
                    }.toThrow<AssertionError>().message.contains(AT_LEAST.getDefault(), "E")
                }
                test("$contains $ignoringCase 'H', 'E' $atLeast once does not throw") {
                    fluentHelloWorld.contains.ignoringCase.atLeast(1).values("H", "E")
                }

                test("$contains 'E', 'H' $atLeast once throws AssertionError") {
                    expect {
                        fluentHelloWorld.contains.atLeast(1).values("E", "H")
                    }.toThrow<AssertionError>().message.contains(AT_LEAST.getDefault(), "E")
                }
                test("$contains $ignoringCase 'E', 'H' $atLeast once does not throw") {
                    fluentHelloWorld.contains.ignoringCase.atLeast(1).values("E", "H")
                }

                test("$contains 'H', 'E', 'w' and 'r' $atLeast once throws AssertionError") {
                    expect {
                        fluentHelloWorld.contains.atLeast(1).values("H", "E", "w", "r")
                    }.toThrow<AssertionError>().message.contains(AT_LEAST.getDefault(), "E", "w")
                }
                test("$contains $ignoringCase 'H', 'E', 'w' and 'r' $atLeast once does not throw") {
                    fluentHelloWorld.contains.ignoringCase.atLeast(1).values("H", "E", "w", "r")
                }
            }

            group("multiple occurrences of the search string") {
                test("$contains 'o' $atLeast once does not throw") {
                    fluentHelloWorld.contains.atLeast(1).value("o")
                }
                test("$contains 'o' $atLeast twice does not throw") {
                    fluentHelloWorld.contains.atLeast(2).value("o")
                }

                test("$contains 'o' $atLeast 3 times throws AssertionError and message contains both, how many times we expected (3) and how many times it actually contained 'o' (2)") {
                    expect {
                        fluentHelloWorld.contains.atLeast(3).value("o")
                    }.toThrow<AssertionError>().and.message.contains(
                        CONTAINS.getDefault() + ": \"o\"",
                        NUMBER_OF_OCCURRENCES.getDefault() + ": 2",
                        AT_LEAST.getDefault() + ": 3"
                    )
                }
                test("$contains $ignoringCase 'o' $atLeast 3 times does not throw") {
                    fluentHelloWorld.contains.ignoringCase.atLeast(3).value("o")
                }

                test("$contains 'o' and 'l' $atLeast twice does not throw") {
                    fluentHelloWorld.contains.atLeast(2).values("o", "l")
                }
                test("$contains 'l' $atLeast 3 times does not throw") {
                    fluentHelloWorld.contains.atLeast(3).value("l")
                }

                test("$contains 'o' and 'l' $atLeast 3 times throws AssertionError and message contains both, at least: 3 and how many times it actually contained 'o' (2)") {
                    expect {
                        fluentHelloWorld.contains.atLeast(3).values("o", "l")
                    }.toThrow<AssertionError>().and.message {
                        contains(
                            CONTAINS.getDefault() + ": \"o\"",
                            NUMBER_OF_OCCURRENCES.getDefault() + ": 2",
                            AT_LEAST.getDefault() + ": 3"
                        )
                        containsNot(CONTAINS.getDefault() + ": \"l\"")
                    }
                }
                test("$contains $ignoringCase 'o' and 'l' $atLeast 3 times does not throw") {
                    fluentHelloWorld.contains.ignoringCase.atLeast(3).values("o", "l")
                }
            }

            group("using specifier $butAtMost") {
                test("$contains 'o' $atLeast once $butAtMost twice does not throw") {
                    fluentHelloWorld.contains.atLeast(1).butAtMost(2).value("o")
                }
                test("$contains 'o' and 'l' $atLeast once $butAtMost twice throws AssertionError and message contains both, at most: 2 and how many times it actually contained 'l' (3)") {
                    expect {
                        fluentHelloWorld.contains.atLeast(1).butAtMost(2).values("o", "l")
                    }.toThrow<AssertionError>().and.message {
                        contains(
                            CONTAINS.getDefault() + ": \"l\"",
                            NUMBER_OF_OCCURRENCES.getDefault() + ": 3",
                            AT_MOST.getDefault() + ": 2"
                        )
                        containsNot(CONTAINS.getDefault() + ": \"o\"")
                        containsNotDefaultTranslationOf(AT_LEAST)
                    }
                }
                test("$contains 'o' and 'l' $atLeast twice $butAtMost 3 times does not throw") {
                    fluentHelloWorld.contains.atLeast(2).butAtMost(3).values("o", "l")
                }
                test("$contains $ignoringCase 'o' and 'l' $atLeast twice $butAtMost 3 times does not throw") {
                    fluentHelloWorld.contains.ignoringCase.atLeast(2).butAtMost(3).values("o", "l")
                }

                test("$contains 'o' and 'l' $atLeast 3 times $butAtMost 4 times throws AssertionError and message contains both, at least: 3 and how many times it actually contained 'o' (2)") {
                    expect {
                        fluentHelloWorld.contains.atLeast(3).butAtMost(4).values("o", "l")
                    }.toThrow<AssertionError>().and.message {
                        contains(
                            CONTAINS.getDefault() + ": \"o\"",
                            NUMBER_OF_OCCURRENCES.getDefault() + ": 2",
                            AT_LEAST.getDefault() + ": 3"
                        )
                        containsNot(CONTAINS.getDefault() + ": \"l\"")
                        containsNotDefaultTranslationOf(AT_MOST)
                    }
                }
                test("$contains $ignoringCase 'o' and 'l' $atLeast 3 times does not throw") {
                    fluentHelloWorld.contains.ignoringCase.atLeast(3).butAtMost(4).values("o", "l")
                }
            }
        }
    }

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
                    fluentHelloWorld.contains.exactly(1).value("H")
                }
                test("$contains 'H' and 'e' and 'W' $exactly once does not throw") {
                    fluentHelloWorld.contains.exactly(1).values("H", "e", "W")
                }
                test("$contains 'W' and 'H' and 'e' $exactly once does not throw") {
                    fluentHelloWorld.contains.exactly(1).values("W", "H", "e")
                }
            }

            group("failing assertions; search string at different positions with $exactly once") {
                test("$contains 'h' $exactly once throws AssertionError") {
                    expect {
                        fluentHelloWorld.contains.exactly(1).value("h")
                    }.toThrow<AssertionError>().message.containsDefaultTranslationOf(EXACTLY)
                }
                test("$contains $ignoringCase 'h' $exactly once throws AssertionError") {
                    fluentHelloWorld.contains.ignoringCase.exactly(1).value("h")
                }

                test("$contains 'H', 'E' $exactly once throws AssertionError") {
                    expect {
                        fluentHelloWorld.contains.exactly(1).values("H", "E")
                    }.toThrow<AssertionError>().message.contains(EXACTLY.getDefault(), "E")
                }
                test("$contains $ignoringCase 'H', 'E' $exactly once throws AssertionError") {
                    fluentHelloWorld.contains.ignoringCase.exactly(1).values("H", "E")
                }

                test("$contains 'E', 'H' $exactly once throws AssertionError") {
                    expect {
                        fluentHelloWorld.contains.exactly(1).values("E", "H")
                    }.toThrow<AssertionError>().message.contains(EXACTLY.getDefault(), "E")
                }
                test("$contains $ignoringCase 'E', 'H' $exactly once throws AssertionError") {
                    fluentHelloWorld.contains.ignoringCase.exactly(1).values("E", "H")
                }

                test("$contains 'H' and 'E' and 'w' $exactly once throws AssertionError") {
                    expect {
                        fluentHelloWorld.contains.exactly(1).values("H", "E", "w")
                    }.toThrow<AssertionError>().message.contains(EXACTLY.getDefault(), "E", "w")
                }
                test("$contains $ignoringCase 'H' and 'E' and 'w' $exactly once throws AssertionError") {
                    fluentHelloWorld.contains.ignoringCase.exactly(1).values("H", "E", "w")
                }
            }

            group("multiple occurrences of the search string") {
                test("$contains 'o' $exactly once throws AssertionError") {
                    expect {
                        fluentHelloWorld.contains.exactly(1).value("o")
                    }.toThrow<AssertionError>().and.message.containsDefaultTranslationOf(EXACTLY)
                }
                test("$contains 'o' $exactly twice does not throw") {
                    fluentHelloWorld.contains.exactly(2).value("o")
                }
                test("$contains $ignoringCase 'o' $exactly twice throws") {
                    expect {
                        fluentHelloWorld.contains.ignoringCase.exactly(2).value("o")
                    }.toThrow<AssertionError>().and.message.contains(
                        String.format(IGNORING_CASE.getDefault(), CONTAINS.getDefault()),
                        NUMBER_OF_OCCURRENCES.getDefault() + ": 3",
                        EXACTLY.getDefault() + ": 2"
                    )
                }

                test("$contains 'o' $exactly 3 times throws AssertionError and message contains both, how many times we expected (3) and how many times it actually contained 'o' (2)") {
                    expect {
                        fluentHelloWorld.contains.exactly(3).value("o")
                    }.toThrow<AssertionError>().and.message.contains(
                        NUMBER_OF_OCCURRENCES.getDefault() + ": 2",
                        EXACTLY.getDefault() + ": 3"
                    )
                }
                test("$contains $ignoringCase 'o' $exactly 3 times does not throw") {
                    fluentHelloWorld.contains.ignoringCase.exactly(3).value("o")
                }

                test("$contains 'o' and 'l' $exactly twice throws AssertionError") {
                    expect {
                        fluentHelloWorld.contains.exactly(2).values("o", "l")
                    }.toThrow<AssertionError>().and.message {
                        contains(
                            CONTAINS.getDefault() + ": \"l\"",
                            NUMBER_OF_OCCURRENCES.getDefault() + ": 3",
                            EXACTLY.getDefault() + ": 2"
                        )
                        containsNot(CONTAINS.getDefault() + ": \"o\"")
                    }
                }
                test("$contains 'l' $exactly 3 times does not throw") {
                    fluentHelloWorld.contains.exactly(3).value("l")
                }
                test("$contains 'o' and 'l' $exactly 3 times throws AssertionError and message contains both, how many times we expected (3) and how many times it actually contained 'o' (2)") {
                    expect {
                        fluentHelloWorld.contains.exactly(3).values("o", "l")
                    }.toThrow<AssertionError>().and.message {
                        contains(
                            CONTAINS.getDefault() + ": \"o\"",
                            NUMBER_OF_OCCURRENCES.getDefault() + ": 2",
                            EXACTLY.getDefault() + ": 3"
                        )
                        containsNot(CONTAINS.getDefault() + ": \"l\"")
                    }
                }
            }
        }
    }

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
                    fluentHelloWorld.contains.atMost(1).value("H")
                }
                test("$contains 'H' and 'e' and 'W' $atMost once does not throw") {
                    fluentHelloWorld.contains.atMost(1).values("H", "e", "W")
                }
                test("$contains 'W' and 'H' and 'e' $atMost once does not throw") {
                    fluentHelloWorld.contains.atMost(1).values("W", "H", "e")
                }
                test("$contains 'x' and 'y' and 'z' $atMost once does not throw") {
                    fluentHelloWorld.contains.atMost(1).values("x", "y", "z")
                }
            }

            group("failing assertions; search string at different positions") {
                test("$contains 'l' $atMost once throws AssertionError") {
                    expect {
                        fluentHelloWorld.contains.atMost(1).value("l")
                    }.toThrow<AssertionError>().message.containsDefaultTranslationOf(AT_MOST)
                }
                test("$contains 'H', 'l' $atMost once throws AssertionError") {
                    expect {
                        fluentHelloWorld.contains.atMost(1).values("H", "l")
                    }.toThrow<AssertionError>().message.contains(AT_MOST.getDefault(), "l")
                }
                test("$contains 'l', 'H' $exactly once throws AssertionError") {
                    expect {
                        fluentHelloWorld.contains.atMost(1).values("l", "H")
                    }.toThrow<AssertionError>().message.contains(AT_MOST.getDefault(), "l")
                }
                test("$contains 'o', 'E', 'W', 'l' $atMost once throws AssertionError") {
                    expect {
                        fluentHelloWorld.contains.atMost(1).values("o", "E", "W", "l")
                    }.toThrow<AssertionError>().message.contains(AT_MOST.getDefault(), "o", "l")
                }
            }

            group("multiple occurrences of the search string") {
                test("$contains 'o' $atMost once throws AssertionError and message contains both, how many times we expected (1) and how many times it actually contained 'o' (2)") {
                    expect {
                        fluentHelloWorld.contains.atMost(1).value("o")
                    }.toThrow<AssertionError>().and.message.contains(
                        NUMBER_OF_OCCURRENCES.getDefault() + ": 2",
                        AT_MOST.getDefault() + ": 1"
                    )
                }

                test("$contains 'o' $atMost twice does not throw") {
                    fluentHelloWorld.contains.atMost(2).value("o")
                }
                test("$contains $ignoringCase 'o' $atMost twice throws AssertionError") {
                    expect {
                        fluentHelloWorld.contains.ignoringCase.atMost(2).value("o")
                    }.toThrow<AssertionError>().and.message.containsDefaultTranslationOf(AT_MOST)
                }

                test("$contains 'o' $atMost 3 times does not throw") {
                    fluentHelloWorld.contains.atMost(3).value("o")
                }
                test("$contains 'o' and 'l' $atMost twice throws AssertionError and message contains both, how many times we expected (2) and how many times it actually contained 'l' (3)") {
                    expect {
                        fluentHelloWorld.contains.atMost(2).values("o", "l")
                    }.toThrow<AssertionError>().and.message {
                        contains(
                            CONTAINS.getDefault() + ": \"l\"",
                            NUMBER_OF_OCCURRENCES.getDefault() + ": 3",
                            AT_MOST.getDefault() + ": 2"
                        )
                        containsNot(CONTAINS.getDefault() + ": \"o\"")
                    }
                }
                test("$contains 'l' $atMost 3 times does not throw") {
                    fluentHelloWorld.contains.atMost(3).value("l")
                }
                test("$contains 'o' and 'l' $atMost 3 times does not throw") {
                    fluentHelloWorld.contains.atMost(3).values("o", "l")
                }
            }
        }
    }
})
