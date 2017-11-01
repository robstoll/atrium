package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.api.cc.en_UK.*
import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion.CONTAINS
import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion.CONTAINS_NOT
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe

abstract class CharSequenceContainsContainsNotAssertionSpec(
    verbs: IAssertionVerbFactory,
    containsPair: Pair<String, IAssertionPlant<CharSequence>.(String, Array<out String>) -> IAssertionPlant<CharSequence>>,
    containsNotPair: Pair<String, IAssertionPlant<CharSequence>.(String, Array<out String>) -> IAssertionPlant<CharSequence>>,
    featureArrow: String
) : CharSequenceContainsSpecBase({

    val assert: (CharSequence) -> IAssertionPlant<CharSequence> = verbs::checkImmediately
    val expect = verbs::checkException
    val fluent = assert(text)

    val (contains, containsFunArr) = containsPair
    fun IAssertionPlant<CharSequence>.containsFun(t: String, vararg tX: String)
        = containsFunArr(t, tX)

    val (containsNot, containsNotFunArr) = containsNotPair
    fun IAssertionPlant<CharSequence>.containsNotFun(t: String, vararg tX: String)
        = containsNotFunArr(t, tX)

    describe("fun $contains and $containsNot") {
        context("empty string") {
            val fluentEmptyString = assert("")
            test("$contains 'Hello' throws AssertionError") {
                expect {
                    fluentEmptyString.containsFun("Hello")
                }.toThrow<AssertionError>().and.message.contains(
                    "$numberOfOccurrences: 0",
                    "$atLeast: 1"
                )
            }
            test("$containsNot 'Hello' does not throw") {
                fluentEmptyString.containsNotFun("Hello")
            }
        }

        context("text '$text'") {

            context("search for 'Hello' and 'Robert'") {
                test("$contains 'Hello' does not throw") {
                    fluent.containsFun("Hello")
                }
                test("$containsNot 'Hello' throws AssertionError") {
                    expect {
                        fluent.containsNotFun("Hello")
                    }.toThrow<AssertionError>().and.message.containsDefaultTranslationOf(CONTAINS_NOT)
                }

                test("$contains 'Hello' and 'Robert' does not throw") {
                    fluent.containsFun("Hello", "Robert")
                }
                test("$containsNot 'Hello' and 'Robert' throws AssertionError") {
                    expect {
                        fluent.containsNotFun("Hello", "Robert")
                    }.toThrow<AssertionError>().message.containsDefaultTranslationOf(CONTAINS_NOT)
                }
            }

            context("search for 'notInThere' and 'neitherInThere'") {
                test("$contains 'notInThere' and 'neitherInThere' throws AssertionError") {
                    expect {
                        fluent.containsFun("notInThere", "neitherInThere")
                    }.toThrow<AssertionError>().message.containsDefaultTranslationOf(CONTAINS)
                }
                test("$containsNot 'notInThere' and 'neitherInThere' does not throw") {
                    fluent.containsNotFun("notInThere", "neitherInThere")
                }
            }

            context("search for 'hello' and 'robert'") {
                test("$contains 'hello' and 'robert' throws AssertionError") {
                    expect {
                        fluent.containsFun("hello", "robert")
                    }.toThrow<AssertionError>().and.message.contains.exactly(2).values(
                        containsDescr,
                        "$numberOfOccurrences: 0",
                        "$atLeast: 1"
                    )
                }
                test("$containsNot 'hello' and 'robert' does not throw") {
                    fluent.containsNotFun("hello", "robert")
                }
            }

            context("search for 'Hello' and 'notInThere'") {
                test("$contains 'notInThere' throws AssertionError") {
                    expect {
                        fluent.containsFun("notInThere")
                    }.toThrow<AssertionError>().message.containsDefaultTranslationOf(CONTAINS)
                }
                test("$containsNot 'notInThere' does not throw") {
                    fluent.containsNotFun("notInThere")
                }

                test("$contains 'Hello' and 'notInThere' throws AssertionError") {
                    expect {
                        fluent.containsFun("Hello", "notInThere")
                    }.toThrow<AssertionError>().message.contains(containsDescr, "notInThere")
                }
                test("$containsNot 'Hello' and 'notInThere' throws AssertionError") {
                    expect {
                        fluent.containsNotFun("Hello", "notInThere")
                    }.toThrow<AssertionError>().message.contains(CONTAINS_NOT.getDefault(), "Hello")
                }
            }

            test("$contains 'Hello' and 'Hello' (searching twice in the same assertion) does not throw") {
                fluent.containsFun("Hello", "Hello")
            }

            test("$containsNot 'notInThere' and 'notInThere' does not throw") {
                fluent.containsNotFun("notInThere", "notInThere")
            }
        }

        context("error message") {
            context("feature assertion about a Person's name 'Robert Stoll'") {
                data class Person(val name: String)

                val person = Person("Robert Stoll")

                val nameWithArrow = "${featureArrow}name"
                test("$contains 'treboR' and 'llotS' - error message contains '$nameWithArrow' exactly once") {
                    expect {
                        verbs.checkLazily(person) {
                            its(subject::name).containsFun("treboR", "llotS")
                        }
                    }.toThrow<AssertionError>().and.message.contains.exactly(1).value(nameWithArrow)
                }
                test("$containsNot 'Robert' and 'Stoll' - error message contains '$nameWithArrow' exactly once") {
                    expect {
                        verbs.checkLazily(person) {
                            its(subject::name).containsNotFun("Robert", "Stoll")
                        }
                    }.toThrow<AssertionError>().and.message.contains.exactly(1).value(nameWithArrow)
                }
            }
        }
    }
})
