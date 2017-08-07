package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.*
import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion.*
import ch.tutteli.atrium.builders.charsequence.contains.*
import ch.tutteli.atrium.creating.IAssertionPlant
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import kotlin.reflect.KFunction2
import kotlin.reflect.KProperty

object CharSequenceContainsAssertionSpec : Spek({

    val text = "hello my name is robert"
    val fluent = assert(text)

    val containsProp: KProperty<CharSequenceContainsBuilder<String>> = fluent::contains
    val contains = containsProp.name
    val containsNotFun: KFunction2<Any, Array<out Any>, IAssertionPlant<CharSequence>> = fluent::containsNot
    val containsNot = containsNotFun.name
    val atLeast = CharSequenceContainsBuilder<String>::atLeast.name
    val butAtMost = CharSequenceContainsAtLeastCheckerBuilder<String>::butAtMost.name
    val exactly = CharSequenceContainsBuilder<String>::exactly.name
    val atMost = CharSequenceContainsBuilder<String>::atMost.name

    val illegalArgumentException = IllegalArgumentException::class.simpleName

    describe("fun $contains and $containsNot") {
        context("empty string") {
            val fluentEmptyString = assert("")
            test("$contains 'hello' throws AssertionError") {
                expect {
                    fluentEmptyString.contains("hello")
                }.toThrow<AssertionError>().and.message.contains(
                    DescriptionCharSequenceAssertion.NUMBER_OF_OCCURRENCES.getDefault() + ": 0",
                    DescriptionCharSequenceAssertion.AT_LEAST.getDefault() + ": 1"
                )
            }
            test("$containsNot 'hello' does not throw") {
                fluentEmptyString.containsNot("hello")
            }
        }

        context("text '$text'") {
            context("search for 'hello' and 'robert'") {
                test("$contains 'hello' does not throw") {
                    fluent.contains("hello")
                }
                test("$containsNot 'hello' throws AssertionError") {
                    expect {
                        fluent.containsNot("hello")
                    }.toThrow<AssertionError>().and.message.containsDefaultTranslationOf(DescriptionCharSequenceAssertion.CONTAINS_NOT)
                }

                test("$contains 'hello' and 'robert' does not throw") {
                    fluent.contains("hello", "robert")
                }
                test("$containsNot 'hello' and 'robert' throws AssertionError") {
                    expect {
                        fluent.containsNot("hello", "robert")
                    }.toThrow<AssertionError>()
                }

                test("$contains 'hello' and 'robert' as Any does not throw") {
                    fluent.contains("hello" as Any, "robert" as Any)
                }
                test("$containsNot 'hello' and 'robert' as Any throws AssertionError") {
                    expect {
                        fluent.containsNot("hello" as Any, "robert" as Any)
                    }.toThrow<AssertionError>()
                }
            }

            context("search for 'notInThere' and 'neitherInThere'") {
                test("$contains 'notInThere' and 'neitherInThere' throws AssertionError") {
                    expect {
                        fluent.contains("notInThere", "neitherInThere")
                    }.toThrow<AssertionError>()
                }
                test("$containsNot 'notInThere' and 'neitherInThere' does not throw") {
                    fluent.containsNot("notInThere", "neitherInThere")
                }

                test("$contains 'notInThere' and 'neitherInThere' as Any throws AssertionError") {
                    expect {
                        fluent.contains("notInThere" as Any, "neitherInThere" as Any)
                    }.toThrow<AssertionError>().and.message.contains.exactly(2).values(
                        DescriptionCharSequenceAssertion.NUMBER_OF_OCCURRENCES.getDefault() + ": 0",
                        DescriptionCharSequenceAssertion.AT_LEAST.getDefault() + ": 1"
                    )
                }
                test("$containsNot 'notInThere' and 'neitherInThere' as Any does not throw") {
                    fluent.containsNot("notInThere" as Any, "neitherInThere" as Any)
                }
            }

            context("search for 'hello' and 'notInThere'") {
                test("$contains 'notInThere' throws AssertionError") {
                    expect {
                        fluent.contains("notInThere")
                    }.toThrow<AssertionError>()
                }
                test("$containsNot 'notInThere' does not throw") {
                    fluent.containsNot("notInThere")
                }

                test("$contains 'hello' and 'notInThere' throws AssertionError") {
                    expect {
                        fluent.contains("hello", "notInThere")
                    }.toThrow<AssertionError>()
                }
                test("$containsNot 'hello' and 'notInThere' throws AssertionError") {
                    expect {
                        fluent.containsNot("hello", "notInThere")
                    }.toThrow<AssertionError>()
                }

                test("$contains 'notInThere' and 'hello' as Any throws AssertionError") {
                    expect {
                        fluent.contains("notInThere" as Any, "hello" as Any)
                    }.toThrow<AssertionError>()
                }
                test("$containsNot 'notInThere' and 'hello' as Any throws AssertionError") {
                    expect {
                        fluent.containsNot("notInThere" as Any, "hello" as Any)
                    }.toThrow<AssertionError>()
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
        context("illegal arguments") {
            it("throws an $illegalArgumentException for $atLeast(-1)") {
                expect {
                    fluent.contains.atLeast(-1)
                }.toThrow<IllegalArgumentException>().and.message.contains("positive number")
            }
            it("throws an $illegalArgumentException for $atLeast(0) and points to $containsNot") {
                expect {
                    fluent.contains.atLeast(0)
                }.toThrow<IllegalArgumentException>().and.message.toBe("use $containsNot instead of $atLeast(0)")
            }
            group("... and specifier $butAtMost") {
                it("throws an $illegalArgumentException for $atLeast(1).$butAtMost(-1) since -1 is smaller than 1") {
                    expect {
                        fluent.contains.atLeast(1).butAtMost(-1)
                    }.toThrow<IllegalArgumentException>().and.message.toBe("specifying $butAtMost(-1) does not make sense if $atLeast(1) was used before")
                }
                it("throws an $illegalArgumentException for $atLeast(1).$butAtMost(0) since 0 is smaller than 1") {
                    expect {
                        fluent.contains.atLeast(1).butAtMost(0)
                    }.toThrow<IllegalArgumentException>().and.message.toBe("specifying $butAtMost(0) does not make sense if $atLeast(1) was used before")
                }
                it("throws an $illegalArgumentException for $atLeast(2).$butAtMost(1) since 1 is smaller than 2") {
                    expect {
                        fluent.contains.atLeast(2).butAtMost(1)
                    }.toThrow<IllegalArgumentException>().and.message.toBe("specifying $butAtMost(1) does not make sense if $atLeast(2) was used before")
                }
                it("throws an $illegalArgumentException for $atLeast(2).$butAtMost(1) and points to $exactly") {
                    expect {
                        fluent.contains.atLeast(1).butAtMost(1)
                    }.toThrow<IllegalArgumentException>().and.message.toBe("use $exactly(1) instead of $atLeast(1).$butAtMost(1)")
                }
            }
        }

        context("text 'hello world'") {
            val fluentHelloWorld = assert("hello world")

            test("$contains 'h' and 'e' and 'w' $atLeast once does not throw") {
                fluentHelloWorld.contains.atLeast(1).values("h", "e", "w")
            }

            test("$contains 'h' and 'e' and 'w' atLeast 4 times throws AssertionError") {
                expect {
                    fluentHelloWorld.contains.atLeast(4).values("h", "e", "w")
                }.toThrow<AssertionError>()
            }

            test("$contains 'o' $atLeast once does not throw") {
                fluentHelloWorld.contains.atLeast(1).value("o")
            }
            test("$contains 'o' $atLeast once $butAtMost twice does not throw") {
                fluentHelloWorld.contains.atLeast(1).butAtMost(2).value("o")
            }

            test("$contains 'o' $atLeast twice does not throw") {
                fluentHelloWorld.contains.atLeast(2).value("o")
            }

            test("$contains 'o' $atLeast 4 times throws AssertionError and message contains both, how many times we expected (4) and how many times it actually contained 'o' (2)") {
                expect {
                    fluentHelloWorld.contains.atLeast(4).value("o")
                }.toThrow<AssertionError>().and.message.contains(
                    NUMBER_OF_OCCURRENCES.getDefault() + ": 2",
                    AT_LEAST.getDefault() + ": 4"
                )
            }

            test("$contains 'o' and 'l' $atLeast twice does not throw") {
                fluentHelloWorld.contains.atLeast(2).values("o", "l")
            }

            test("$contains 'o' and 'l' $atLeast once $butAtMost twice throws AssertionError and message contains both, at most: 2 and how many times it actually contained 'o' (3)") {
                expect {
                    fluentHelloWorld.contains.atLeast(1).butAtMost(2).values("o", "l")
                }.toThrow<AssertionError>().and.message {
                    contains(
                        DescriptionCharSequenceAssertion.NUMBER_OF_OCCURRENCES.getDefault() + ": 3",
                        AT_MOST.getDefault() + ": 2"
                    )
                    containsNotDefaultTranslationOf(AT_LEAST)
                }
            }
            test("$contains 'o' and 'l' $atLeast twice $butAtMost 3 times does not throw") {
                fluentHelloWorld.contains.atLeast(2).butAtMost(3).values("o", "l")
            }

            test("$contains 'l' $atLeast 3 times does not throw") {
                fluentHelloWorld.contains.atLeast(3).value("l")
            }

            test("$contains 'o' and 'l' $atLeast 3 times throws AssertionError and message contains both, at least: 3 and how many times it actually contained 'o' (2)") {
                expect {
                    fluentHelloWorld.contains.atLeast(3).values("o", "l")
                }.toThrow<AssertionError>().and.message.contains(
                    NUMBER_OF_OCCURRENCES.getDefault() + ": 2",
                    AT_LEAST.getDefault() + ": 3"
                )
            }
        }
    }

    describe("fun $contains with specifier $exactly") {
        it("throws an $illegalArgumentException for $exactly -1") {
            expect {
                fluent.contains.exactly(-1)
            }.toThrow<IllegalArgumentException>().and.message.contains("positive number")
        }
        it("throws an $illegalArgumentException for $exactly 0") {
            expect {
                fluent.contains.exactly(0)
            }.toThrow<IllegalArgumentException>().and.message.contains(containsNot, exactly)
        }

        context("text 'hello world'") {
            val fluentHelloWorld = assert("hello world")

            test("$contains 'h' and 'e' and 'w' exactly once does not throw") {
                fluentHelloWorld.contains.exactly(1).values("h", "e", "w")
            }

            test("$contains 'h' and 'e' and 'w' $exactly 4 times throws AssertionError") {
                expect {
                    fluentHelloWorld.contains.exactly(4).values("h", "e", "w")
                }.toThrow<AssertionError>()
            }

            test("$contains 'o' $exactly twice does not throw") {
                fluentHelloWorld.contains.exactly(2).value("o")
            }

            test("$contains 'o' $exactly 4 times throws AssertionError and message contains both, how many times we expected (4) and how many times it actually contained 'o' (2)") {
                expect {
                    fluentHelloWorld.contains.exactly(4).value("o")
                }.toThrow<AssertionError>().and.message.contains(
                    NUMBER_OF_OCCURRENCES.getDefault() + ": 2",
                    EXACTLY.getDefault() + ": 4"
                )
            }

            test("$contains 'l' $exactly 3 times does not throw") {
                fluentHelloWorld.contains.exactly(3).value("l")
            }

            test("$contains 'o' and 'l' $exactly 3 times throws AssertionError") {
                expect {
                    fluentHelloWorld.contains.exactly(3).values("o", "l")
                }.toThrow<AssertionError>().and.message.contains(
                    NUMBER_OF_OCCURRENCES.getDefault() + ": 2",
                    EXACTLY.getDefault() + ": 3"
                )
            }
        }
    }

    describe("fun $contains with specifier $atMost") {
        it("throws an $illegalArgumentException for $atMost -1") {
            expect {
                fluent.contains.atMost(-1)
            }.toThrow<IllegalArgumentException>().and.message.contains("positive number")
        }
        it("throws an $illegalArgumentException for $atMost 0") {
            expect {
                fluent.contains.atMost(0)
            }.toThrow<IllegalArgumentException>().and.message.contains(containsNot, atMost)
        }

        context("text 'hello world'") {
            val fluentHelloWorld = assert("hello world")

            test("$contains 'h' and 'e' and 'w' $atMost once does not throw") {
                fluentHelloWorld.contains.atMost(1).values("h", "e", "w")
            }

            test("$contains 'h' and 'e' and 'w' atMost 4 times does not throw") {
                fluentHelloWorld.contains.atMost(4).values("h", "e", "w")
            }

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

            test("$contains 'l' $atMost 3 times does not throw") {
                fluentHelloWorld.contains.atMost(3).value("l")
            }

            test("$contains 'o' and 'l' $atMost twice throws AssertionError") {
                expect {
                    fluentHelloWorld.contains.atMost(2).values("o", "l")
                }.toThrow<AssertionError>().and.message.contains(
                    NUMBER_OF_OCCURRENCES.getDefault() + ": 3",
                    AT_MOST.getDefault() + ": 2"
                )
            }
        }
    }
})
