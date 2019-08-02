package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.migration.asAssert
import ch.tutteli.atrium.domain.builders.migration.asExpect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.verbs.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.CONTAINS_NOT
import org.spekframework.spek2.style.specification.Suite

abstract class CharSequenceContainsContainsNotAssertionsSpec(
    verbs: AssertionVerbFactory,
    containsPair: Fun2<CharSequence, String, Array<out String>>,
    containsNotPair: Fun2<CharSequence, String, Array<out String>>,
    rootBulletPoint: String,
    listBulletPoint: String,
    featureArrow: String,
    describePrefix: String = "[Atrium] "
) : CharSequenceContainsSpecBase({

    include(object : SubjectLessSpec<CharSequence>(describePrefix,
        containsPair.first to expectLambda { containsPair.second(this, "hello", arrayOf()) },
        containsNotPair.first to expectLambda { containsNotPair.second(this, "hello", arrayOf()) }
    ) {})

    include(object : CheckingAssertionSpec<CharSequence>(verbs, describePrefix,
        checkingTriple(containsPair.first, { containsPair.second(this, "hello", arrayOf()) }, "hello robert" as CharSequence, "by robert"),
        checkingTriple(containsNotPair.first, { containsNotPair.second(this, "hello", arrayOf()) }, "by robert" as CharSequence, "hello robert")
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val expect = verbs::checkException
    val fluent = verbs.check(text as CharSequence)

    val (containsFunName, containsFunArr) = containsPair
    fun Expect<CharSequence>.containsFun(t: String, vararg tX: String)
        = containsFunArr(t, tX)

    val (containsNot, containsNotFunArr) = containsNotPair
    fun Expect<CharSequence>.containsNotFun(t: String, vararg tX: String)
        = containsNotFunArr(t, tX)

    val indentBulletPoint = " ".repeat(rootBulletPoint.length)
    val valueWithIndent = "$indentBulletPoint$listBulletPoint$value"
    val containsNotDescr = CONTAINS_NOT.getDefault()

    describeFun(containsFunName, containsNot) {
        context("empty string") {
            val fluentEmptyString = verbs.check("" as CharSequence)
            it("$containsFunName 'Hello' throws AssertionError") {
                expect {
                    fluentEmptyString.containsFun("Hello")
                }.toThrow<AssertionError> {
                    messageContains(
                        "$rootBulletPoint$containsDescr: $separator" +
                            "$valueWithIndent: \"Hello\"",
                        "$numberOfOccurrences: 0",
                        "$atLeast: 1"
                    )
                }
            }
            it("$containsNot 'Hello' does not throw") {
                fluentEmptyString.containsNotFun("Hello")
            }
        }

        context("text '$text'") {

            context("search for 'Hello' and 'Robert'") {
                it("$containsFunName 'Hello' does not throw") {
                    fluent.containsFun("Hello")
                }
                it("$containsNot 'Hello' throws AssertionError") {
                    expect {
                        fluent.containsNotFun("Hello")
                    }.toThrow<AssertionError> { messageContains(containsNotDescr, "$valueWithIndent: \"Hello\"") }
                }

                it("$containsFunName 'Hello' and 'Robert' does not throw") {
                    fluent.containsFun("Hello", "Robert")
                }
                it("$containsNot 'Hello' and 'Robert' throws AssertionError") {
                    expect {
                        fluent.containsNotFun("Hello", "Robert")
                    }.toThrow<AssertionError> {
                        messageContains(containsNotDescr, "$valueWithIndent: \"Hello\"", "$valueWithIndent: \"Robert\"")
                    }
                }
            }

            context("search for 'notInThere' and 'neitherInThere'") {
                it("$containsFunName 'notInThere' and 'neitherInThere' throws AssertionError") {
                    expect {
                        fluent.containsFun("notInThere", "neitherInThere")
                    }.toThrow<AssertionError> {
                        messageContains(containsDescr, "$valueWithIndent: \"notInThere\"", "$valueWithIndent: \"neitherInThere\"")
                    }
                }
                it("$containsNot 'notInThere' and 'neitherInThere' does not throw") {
                    fluent.containsNotFun("notInThere", "neitherInThere")
                }
            }

            context("search for 'hello' and 'robert'") {
                it("$containsFunName 'hello' and 'robert' throws AssertionError") {
                    expect {
                        fluent.containsFun("hello", "robert")
                    }.toThrow<AssertionError> {
                        message {
                            contains.exactly(2).values(
                                "$numberOfOccurrences: 0",
                                "$atLeast: 1"
                            )
                            contains.exactly(1).values(
                                "$rootBulletPoint$containsDescr: $separator",
                                "$valueWithIndent: \"hello\"",
                                "$valueWithIndent: \"robert\""
                            )
                        }
                    }
                }
                it("$containsNot 'hello' and 'robert' does not throw") {
                    fluent.containsNotFun("hello", "robert")
                }
            }

            context("search for 'Hello' and 'notInThere'") {
                it("$containsFunName 'notInThere' throws AssertionError") {
                    expect {
                        fluent.containsFun("notInThere")
                    }.toThrow<AssertionError> { messageContains(containsDescr, "$valueWithIndent: \"notInThere\"") }
                }
                it("$containsNot 'notInThere' does not throw") {
                    fluent.containsNotFun("notInThere")
                }

                it("$containsFunName 'Hello' and 'notInThere' throws AssertionError mentioning only 'Hello'") {
                    expect {
                        fluent.containsFun("Hello", "notInThere")
                    }.toThrow<AssertionError> {
                        message {
                            contains(containsDescr, "$valueWithIndent: \"notInThere\"")
                            containsNot("$valueWithIndent: \"Hello\"")
                        }
                    }
                }
                it("$containsNot 'Hello' and 'notInThere' throws AssertionError mentioning only 'notInThere'") {
                    expect {
                        fluent.containsNotFun("Hello", "notInThere")
                    }.toThrow<AssertionError> {
                        message {
                            contains(containsNotDescr, "$valueWithIndent: \"Hello\"")
                            containsNot("$valueWithIndent: \"notInThere\"")
                        }
                    }
                }
            }

            it("$containsFunName 'Hello' and 'Hello' (searching twice in the same assertion) does not throw") {
                fluent.containsFun("Hello", "Hello")
            }

            it("$containsNot 'notInThere' and 'notInThere' does not throw") {
                fluent.containsNotFun("notInThere", "notInThere")
            }
        }

        context("error message") {
            context("feature assertion about a Person's name 'Robert Stoll'") {
                data class Person(val name: CharSequence)

                val person = Person("Robert Stoll")

                val nameWithArrow = "${featureArrow}name"
                it("$containsFunName 'treboR' and 'llotS' - error message contains '$nameWithArrow' exactly once") {
                    expect {
                        verbs.check(person).addAssertionsCreatedBy {
                            feature(Person::name).containsFun("treboR", "llotS")
                        }
                    }.toThrow<AssertionError> {
                        message { contains.exactly(1).value(nameWithArrow) }
                    }
                }
                it("$containsNot 'Robert' and 'Stoll' - error message contains '$nameWithArrow' exactly once") {
                    expect {
                        verbs.check(person).addAssertionsCreatedBy {
                            feature(Person::name).containsNotFun("Robert", "Stoll")
                        }
                    }.toThrow<AssertionError> {
                        message { contains.exactly(1).value(nameWithArrow) }
                    }
                }
            }
        }
    }
})
