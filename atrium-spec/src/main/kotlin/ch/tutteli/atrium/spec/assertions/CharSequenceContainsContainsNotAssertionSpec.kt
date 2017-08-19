package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion
import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion.*
import ch.tutteli.atrium.assertions.charsequence.contains.decorators.CharSequenceContainsNoOpDecorator
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsBuilder
import ch.tutteli.atrium.creating.IAssertionPlant
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import kotlin.reflect.KFunction2
import kotlin.reflect.KProperty

object CharSequenceContainsContainsNotAssertionSpec : Spek({

    val text = "Hello my name is Robert"
    val fluent = assert(text)

    val containsProp: KProperty<CharSequenceContainsBuilder<String, CharSequenceContainsNoOpDecorator>> = fluent::contains
    val contains = containsProp.name
    val containsNotFun: KFunction2<Any, Array<out Any>, IAssertionPlant<CharSequence>> = fluent::containsNot
    val containsNot = containsNotFun.name

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
})
