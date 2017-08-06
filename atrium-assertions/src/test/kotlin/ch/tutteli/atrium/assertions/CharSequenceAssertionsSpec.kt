package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.*
import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion.*
import ch.tutteli.atrium.builders.charsequence.contains.CharSequenceContainsBuilder
import ch.tutteli.atrium.builders.charsequence.contains.atLeast
import ch.tutteli.atrium.builders.charsequence.contains.exactly
import ch.tutteli.atrium.builders.charsequence.contains.values
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.translating.ISimpleTranslatable
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import kotlin.reflect.KFunction2
import kotlin.reflect.KProperty

object CharSequenceAssertionsSpec : Spek({
    val text = "hello my name is robert"
    val fluent = assert(text)

    val containsProp: KProperty<CharSequenceContainsBuilder<String>> = fluent::contains
    val contains = containsProp.name
    val containsNotFun: KFunction2<Any, Array<out Any>, IAssertionPlant<CharSequence>> = fluent::containsNot
    val containsNot = containsNotFun.name
    val atLeast = CharSequenceContainsBuilder<String>::atLeast.name
    val exactly = CharSequenceContainsBuilder<String>::exactly.name

    describe("fun $contains and $containsNot") {
        context("empty string") {
            val fluentEmptyString = assert("")
            test("$contains 'hello' throws AssertionError") {
                expect {
                    fluentEmptyString.contains("hello")
                }.toThrow<AssertionError>().and.message.contains(
                    NUMBER_OF_OCCURRENCES.getDefault() + ": 0",
                    AT_LEAST.getDefault() + ": 1"
                )
            }
            test("$contains 'hello' $atLeast once throws AssertionError") {
                expect {
                    fluentEmptyString.contains.atLeast(1).values("hello")
                }.toThrow<AssertionError>().and.message.contains(
                    NUMBER_OF_OCCURRENCES.getDefault() + ": 0",
                    AT_LEAST.getDefault() + ": 1"
                )
            }
            test("$contains 'hello' $atLeast twice throws AssertionError") {
                expect {
                    fluentEmptyString.contains.atLeast(2).values("hello")
                }.toThrow<AssertionError>().and.message.contains(
                    NUMBER_OF_OCCURRENCES.getDefault() + ": 0",
                    AT_LEAST.getDefault() + ": 2"
                )
            }
            test("$contains 'hello' $exactly once throws AssertionError") {
                expect {
                    fluentEmptyString.contains.exactly(1).values("hello")
                }.toThrow<AssertionError>().and.message.contains(
                    NUMBER_OF_OCCURRENCES.getDefault() + ": 0",
                    EXACTLY.getDefault() + ": 1"
                )
            }
            test("$contains 'hello' $exactly twice throws AssertionError") {
                expect {
                    fluentEmptyString.contains.exactly(2).values("hello")
                }.toThrow<AssertionError>().and.message.contains(
                    NUMBER_OF_OCCURRENCES.getDefault() + ": 0",
                    EXACTLY.getDefault() + ": 2"
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
                test("$contains 'hello' $atLeast once does not throw") {
                    fluent.contains.atLeast(1).values("hello")
                }
                test("$contains 'hello' $atLeast twice throws AssertionError") {
                    expect {
                        fluent.contains.atLeast(2).values("hello")
                    }.toThrow<AssertionError>()
                }
                test("$contains 'hello' $exactly once does not throw") {
                    fluent.contains.exactly(1).values("hello")
                }
                test("$contains 'hello' $exactly twice throws AssertionError") {
                    expect {
                        fluent.contains.exactly(2).values("hello")
                    }.toThrow<AssertionError>()
                }
                test("$containsNot 'hello' throws AssertionError") {
                    expect {
                        fluent.containsNot("hello")
                    }.toThrow<AssertionError>().and.message.containsDefaultTranslationOf(CONTAINS_NOT)
                }

                test("$contains 'hello' and 'robert' does not throw") {
                    fluent.contains("hello", "robert")
                }
                test("$contains 'hello' and 'robert' $atLeast once does not throw") {
                    fluent.contains.atLeast(1).values("hello", "robert")
                }
                test("$contains 'hello' and 'robert' $atLeast twice throws AssertionError") {
                    expect {
                        fluent.contains.atLeast(2).values("hello", "robert")
                    }.toThrow<AssertionError>()
                }
                test("$contains 'hello' and 'robert' $exactly once does not throw") {
                    fluent.contains.exactly(1).values("hello", "robert")
                }
                test("$contains 'hello' and 'robert' $exactly twice throws AssertionError") {
                    expect {
                        fluent.contains.exactly(2).values("hello", "robert")
                    }.toThrow<AssertionError>()
                }
                test("$containsNot 'hello' and 'robert' throws AssertionError") {
                    expect {
                        fluent.containsNot("hello", "robert")
                    }.toThrow<AssertionError>()
                }

                test("$contains 'hello' and 'robert' as Any does not throw") {
                    fluent.contains("hello" as Any, "robert" as Any)
                }
                test("$contains 'hello' and 'robert' as Any $atLeast once does not throw") {
                    fluent.contains.atLeast(1).values("hello" as Any, "robert" as Any)
                }
                test("$contains 'hello' and 'robert' $atLeast twice throws AssertionError") {
                    expect {
                        fluent.contains.atLeast(2).values("hello" as Any, "robert" as Any)
                    }.toThrow<AssertionError>()
                }
                test("$contains 'hello' and 'robert' as Any $exactly once does not throw") {
                    fluent.contains.exactly(1).values("hello" as Any, "robert" as Any)
                }
                test("$contains 'hello' and 'robert' $exactly twice throws AssertionError") {
                    expect {
                        fluent.contains.exactly(2).values("hello" as Any, "robert" as Any)
                    }.toThrow<AssertionError>()
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
                test("$contains 'notInThere' and 'neitherInThere' $atLeast once throws AssertionError") {
                    expect {
                        fluent.contains.atLeast(1).values("notInThere", "neitherInThere")
                    }.toThrow<AssertionError>()
                }
                test("$contains 'notInThere' and 'neitherInThere' $atLeast twice throws AssertionError") {
                    expect {
                        fluent.contains.atLeast(2).values("notInThere", "neitherInThere")
                    }.toThrow<AssertionError>()
                }
                test("$contains 'notInThere' and 'neitherInThere' $exactly once throws AssertionError") {
                    expect {
                        fluent.contains.exactly(1).values("notInThere", "neitherInThere")
                    }.toThrow<AssertionError>()
                }
                test("$contains 'notInThere' and 'neitherInThere' $exactly twice throws AssertionError") {
                    expect {
                        fluent.contains.exactly(2).values("notInThere", "neitherInThere")
                    }.toThrow<AssertionError>()
                }
                test("$containsNot 'notInThere' and 'neitherInThere' does not throw") {
                    fluent.containsNot("notInThere", "neitherInThere")
                }

                test("$contains 'notInThere' and 'neitherInThere' as Any throws AssertionError") {
                    expect {
                        fluent.contains("notInThere" as Any, "neitherInThere" as Any)
                    }.toThrow<AssertionError>().and.message.contains.exactly(2).values(
                        NUMBER_OF_OCCURRENCES.getDefault() + ": 0",
                        AT_LEAST.getDefault() + ": 1"
                    )
                }
                test("$contains 'notInThere' and 'neitherInThere' as Any $atLeast once throws AssertionError") {
                    expect {
                        fluent.contains.atLeast(1).values("notInThere" as Any, "neitherInThere" as Any)
                    }.toThrow<AssertionError>().and.message.contains.exactly(2).values(
                        NUMBER_OF_OCCURRENCES.getDefault() + ": 0",
                        AT_LEAST.getDefault() + ": 1"
                    )
                }
                test("$contains 'notInThere' and 'neitherInThere' as Any $atLeast twice throws AssertionError") {
                    expect {
                        fluent.contains.atLeast(2).values("notInThere" as Any, "neitherInThere" as Any)
                    }.toThrow<AssertionError>()
                }
                test("$contains 'notInThere' and 'neitherInThere' as Any $exactly once throws AssertionError") {
                    expect {
                        fluent.contains.exactly(1).values("notInThere" as Any, "neitherInThere" as Any)
                    }.toThrow<AssertionError>().and.message.contains.exactly(2).values(
                        NUMBER_OF_OCCURRENCES.getDefault() + ": 0",
                        EXACTLY.getDefault() + ": 1"
                    )
                }
                test("$contains 'notInThere' and 'neitherInThere' as Any $exactly twice throws AssertionError") {
                    expect {
                        fluent.contains.exactly(2).values("notInThere" as Any, "neitherInThere" as Any)
                    }.toThrow<AssertionError>()
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
                test("$contains 'notInThere' $atLeast once throws AssertionError") {
                    expect {
                        fluent.contains.atLeast(1).values("notInThere")
                    }.toThrow<AssertionError>()
                }
                test("$contains 'notInThere' $atLeast twice throws AssertionError") {
                    expect {
                        fluent.contains.atLeast(2).values("notInThere")
                    }.toThrow<AssertionError>()
                }
                test("$contains 'notInThere' $exactly once throws AssertionError") {
                    expect {
                        fluent.contains.exactly(1).values("notInThere")
                    }.toThrow<AssertionError>()
                }
                test("$contains 'notInThere' $exactly twice throws AssertionError") {
                    expect {
                        fluent.contains.exactly(2).values("notInThere")
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
                test("$contains 'hello' and 'notInThere' $atLeast once throws AssertionError") {
                    expect {
                        fluent.contains.atLeast(1).values("hello", "notInThere")
                    }.toThrow<AssertionError>()
                }
                test("$contains 'hello' and 'notInThere' $atLeast twice throws AssertionError") {
                    expect {
                        fluent.contains.atLeast(2).values("hello", "notInThere")
                    }.toThrow<AssertionError>()
                }
                test("$contains 'hello' and 'notInThere' $exactly once throws AssertionError") {
                    expect {
                        fluent.contains.exactly(1).values("hello", "notInThere")
                    }.toThrow<AssertionError>()
                }
                test("$contains 'hello' and 'notInThere' $exactly twice throws AssertionError") {
                    expect {
                        fluent.contains.exactly(2).values("hello", "notInThere")
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
                test("$contains 'notInThere' and 'hello' as Any $atLeast once throws AssertionError") {
                    expect {
                        fluent.contains.atLeast(1).values("notInThere" as Any, "hello" as Any)
                    }.toThrow<AssertionError>()
                }
                test("$contains 'notInThere' and 'hello' as Any $atLeast twice throws AssertionError") {
                    expect {
                        fluent.contains.atLeast(2).values("notInThere" as Any, "hello" as Any)
                    }.toThrow<AssertionError>()
                }
                test("$contains 'notInThere' and 'hello' as Any $exactly once throws AssertionError") {
                    expect {
                        fluent.contains.exactly(1).values("notInThere" as Any, "hello" as Any)
                    }.toThrow<AssertionError>()
                }
                test("$contains 'notInThere' and 'hello' as Any $exactly twice throws AssertionError") {
                    expect {
                        fluent.contains.exactly(2).values("notInThere" as Any, "hello" as Any)
                    }.toThrow<AssertionError>()
                }
                test("$containsNot 'notInThere' and 'hello' as Any throws AssertionError") {
                    expect {
                        fluent.containsNot("notInThere" as Any, "hello" as Any)
                    }.toThrow<AssertionError>()
                }
            }
        }

        context("fun $contains with specifier $atLeast") {
            it("throws an ${IllegalArgumentException::class.simpleName} for $atLeast -1") {
                expect {
                    fluent.contains.atLeast(-1)
                }.toThrow<IllegalArgumentException>().and.message.contains("positive number")
            }
            it("throws an ${IllegalArgumentException::class.simpleName} for $atLeast 0") {
                expect {
                    fluent.contains.atLeast(0)
                }.toThrow<IllegalArgumentException>().and.message.contains(containsNot, atLeast)
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
                    fluentHelloWorld.contains.atLeast(1).values("o")
                }

                test("$contains 'o' $atLeast twice does not throw") {
                    fluentHelloWorld.contains.atLeast(2).values("o")
                }

                test("$contains 'o' $atLeast 4 times throws AssertionError and message contains both, how many times we expected (4) and how many times it actually contained 'o' (2)") {
                    expect {
                        fluentHelloWorld.contains.atLeast(4).values("o")
                    }.toThrow<AssertionError>().and.message.contains(
                        NUMBER_OF_OCCURRENCES.getDefault() + ": 2",
                        AT_LEAST.getDefault() + ": 4"
                    )
                }

                test("$contains 'l' $atLeast once does not throw") {
                    fluentHelloWorld.contains.atLeast(1).values("l")
                }

                test("$contains 'l' $atLeast twice does not throw") {
                    fluentHelloWorld.contains.atLeast(2).values("l")
                }

                test("$contains 'o' and 'l' $atLeast twice does not throw") {
                    fluentHelloWorld.contains.atLeast(2).values("o", "l")
                }

                test("$contains 'l' $atLeast 3 times does not throw") {
                    fluentHelloWorld.contains.atLeast(3).values("l")
                }

                test("$contains 'o' and 'l' $atLeast 3 times throws AssertionError") {
                    expect {
                        fluentHelloWorld.contains.atLeast(3).values("o", "l")
                    }.toThrow<AssertionError>().and.message.contains(
                        NUMBER_OF_OCCURRENCES.getDefault() + ": 2",
                        AT_LEAST.getDefault() + ": 3"
                    )
                }
            }
        }

        context("fun $contains with specifier $exactly") {
            it("throws an ${IllegalArgumentException::class.simpleName} for $exactly -1") {
                expect {
                    fluent.contains.exactly(-1)
                }.toThrow<IllegalArgumentException>().and.message.contains("positive number")
            }
            it("throws an ${IllegalArgumentException::class.simpleName} for $exactly 0") {
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
                    fluentHelloWorld.contains.exactly(2).values("o")
                }

                test("$contains 'o' $exactly 4 times throws AssertionError and message contains both, how many times we expected (4) and how many times it actually contained 'o' (2)") {
                    expect {
                        fluentHelloWorld.contains.exactly(4).values("o")
                    }.toThrow<AssertionError>().and.message.contains(
                        NUMBER_OF_OCCURRENCES.getDefault() + ": 2",
                        EXACTLY.getDefault() + ": 4"
                    )
                }

                test("$contains 'l' $exactly 3 times does not throw") {
                    fluentHelloWorld.contains.exactly(3).values("l")
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

        context("error message") {
            context("feature assertion about a Person's name 'Robert Stoll'") {
                data class Person(val name: String)

                val person: Person = Person("Robert Stoll")

                test("$contains 'treboR' and 'llotS' - error message contains '-> name' exactly once") {
                    expect {
                        assert(person) {
                            its(subject::name).contains("treboR", "llotS")
                        }
                    }.toThrow<AssertionError>().and.message.contains.exactly(1).values("-> name")
                }
                test("$containsNot 'Robert' and 'Stoll' - error message contains '-> name' exactly once") {
                    expect {
                        assert(person) {
                            its(subject::name).containsNot("Robert", "Stoll")
                        }
                    }.toThrow<AssertionError>().and.message.contains.exactly(1).values("-> name")
                }
            }
        }
    }

    val containsDefaultTranslationOf = fluent::containsDefaultTranslationOf.name
    val containsNotDefaultTranslationOf = fluent::containsNotDefaultTranslationOf.name
    describe("fun $containsDefaultTranslationOf and $containsNotDefaultTranslationOf") {

        context("text '$text' and translatables ${TestTranslatable.HELLO} (${TestTranslatable.HELLO.getDefault()}) and ${TestTranslatable.WELCOME} (${TestTranslatable.WELCOME.getDefault()})") {
            test("$containsDefaultTranslationOf ${TestTranslatable.HELLO} does not throw") {
                fluent.containsDefaultTranslationOf(TestTranslatable.HELLO)
            }

            test("$containsNotDefaultTranslationOf ${TestTranslatable.HELLO} throws AssertionError") {
                expect {
                    fluent.containsNotDefaultTranslationOf(TestTranslatable.HELLO)
                }.toThrow<AssertionError>()
            }

            test("$containsDefaultTranslationOf ${TestTranslatable.WELCOME} throws AssertionError") {
                expect {
                    fluent.containsDefaultTranslationOf(TestTranslatable.WELCOME)
                }.toThrow<AssertionError>()
            }

            test("$containsNotDefaultTranslationOf ${TestTranslatable.WELCOME} does not throw") {
                fluent.containsNotDefaultTranslationOf(TestTranslatable.WELCOME)
            }

            test("$containsDefaultTranslationOf ${TestTranslatable.HELLO} and ${TestTranslatable.WELCOME}, throws AssertionError") {
                expect {
                    fluent.containsDefaultTranslationOf(TestTranslatable.HELLO, TestTranslatable.WELCOME)
                }.toThrow<AssertionError>().message {
                    contains(DescriptionCharSequenceAssertion.CONTAINS.getDefault() + ": \"" + TestTranslatable.WELCOME.getDefault() + "\"")
                    containsNot(DescriptionCharSequenceAssertion.CONTAINS.getDefault() + ": \"" + TestTranslatable.HELLO.getDefault() + "\"")
                }
            }

            test("$containsNotDefaultTranslationOf ${TestTranslatable.HELLO} and ${TestTranslatable.WELCOME}, throws AssertionError") {
                expect {
                    fluent.containsNotDefaultTranslationOf(TestTranslatable.HELLO, TestTranslatable.WELCOME)
                }.toThrow<AssertionError>().message {
                    contains(DescriptionCharSequenceAssertion.CONTAINS_NOT.getDefault() + ": \"" + TestTranslatable.HELLO.getDefault() + "\"")
                    containsNot(DescriptionCharSequenceAssertion.CONTAINS_NOT.getDefault() + ": \"" + TestTranslatable.WELCOME.getDefault() + "\"")
                }
            }
        }
    }


    describe("fun ${fluent::isEmpty.name} and ${fluent::isNotEmpty.name}") {
        context("string is empty") {
            test("${fluent::isEmpty.name} does not throw") {
                assert("").isEmpty()
                assert(StringBuilder()).isEmpty()
                assert(StringBuffer()).isEmpty()
            }
            test("${fluent::isNotEmpty.name} throws an AssertionError") {
                expect {
                    assert("").isNotEmpty()
                }.toThrow<AssertionError>().and.message.endsWith("${DescriptionBasic.IS_NOT.getDefault()}: empty")
                expect {
                    assert(StringBuilder()).isNotEmpty()
                }.toThrow<AssertionError>().and.message.endsWith("${DescriptionBasic.IS_NOT.getDefault()}: empty")
                expect {
                    assert(StringBuffer()).isNotEmpty()
                }.toThrow<AssertionError>().and.message.endsWith("${DescriptionBasic.IS_NOT.getDefault()}: empty")
            }
        }
        context("string is not empty") {
            val notEmptyString = "not empty string"
            test("${fluent::isEmpty.name} throws an AssertionError") {
                expect {
                    assert(notEmptyString).isEmpty()
                }.toThrow<AssertionError>().and.message.endsWith("${DescriptionBasic.IS.getDefault()}: empty")
                expect {
                    assert(StringBuilder(notEmptyString)).isEmpty()
                }.toThrow<AssertionError>().and.message.endsWith("${DescriptionBasic.IS.getDefault()}: empty")
                expect {
                    assert(StringBuffer(notEmptyString)).isEmpty()
                }.toThrow<AssertionError>().and.message.endsWith("${DescriptionBasic.IS.getDefault()}: empty")
            }
            test("${fluent::isNotEmpty.name} does not throw") {
                assert(notEmptyString).isNotEmpty()
                assert(StringBuilder(notEmptyString)).isNotEmpty()
                assert(StringBuffer(notEmptyString)).isNotEmpty()
            }
        }
    }

    describe("fun ${fluent::startsWith.name} and ${fluent::startsNotWith.name}") {
        context("text '$text'") {
            test("${fluent::startsWith.name} 'hello' does not throw") {
                fluent.startsWith("hello")
            }
            test("${fluent::startsNotWith.name} 'hello' throws an AssertionError") {
                expect {
                    fluent.startsNotWith("hello")
                }.toThrow<AssertionError>().and.message.containsDefaultTranslationOf(STARTS_NOT_WITH)
            }

            test("${fluent::startsWith.name} 'robert' throws an AssertionError") {
                expect {
                    fluent.startsWith("goodbye")
                }.toThrow<AssertionError>().and.message.containsDefaultTranslationOf(STARTS_WITH)
            }
            test("${fluent::startsNotWith.name} 'robert' does not throw") {
                fluent.startsNotWith("goodbye")
            }
        }
    }

    describe("fun ${fluent::endsWith.name} and ${fluent::endsNotWith.name}") {
        context("text '$text'") {
            test("${fluent::endsWith.name} 'hello' throws an AssertionError") {
                expect {
                    fluent.endsWith("hello")
                }.toThrow<AssertionError>().and.message.containsDefaultTranslationOf(ENDS_WITH)
            }
            test("${fluent::endsNotWith.name} 'hello' does not throw") {
                fluent.endsNotWith("hello")
            }

            test("${fluent::endsWith.name} 'robert' does not throw") {
                fluent.endsWith("robert")
            }
            test("${fluent::endsNotWith.name} 'robert' throws an AssertionError") {
                expect {
                    fluent.endsNotWith("robert")
                }.toThrow<AssertionError>().and.message.containsDefaultTranslationOf(ENDS_NOT_WITH)
            }
        }
    }
}) {
    private enum class TestTranslatable(override val value: String) : ISimpleTranslatable {
        HELLO("hello"),
        WELCOME("welcome")
    }
}
