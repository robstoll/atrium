package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.CONTAINS_NOT
import org.spekframework.spek2.style.specification.Suite

abstract class CharSequenceContainsContainsNotAssertionsSpec(
    contains: Fun2<CharSequence, Any, Array<out Any>>,
    containsNot: Fun2<CharSequence, Any, Array<out Any>>,
    rootBulletPoint: String,
    listBulletPoint: String,
    featureArrow: String,
    describePrefix: String = "[Atrium] "
) : CharSequenceContainsSpecBase({

    include(object : SubjectLessSpec<CharSequence>(
        describePrefix,
        contains.forSubjectLess("hello", arrayOf()),
        containsNot.forSubjectLess("hello", arrayOf())
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val fluent = expect(text as CharSequence)

    fun Expect<CharSequence>.containsFun(t: String, vararg tX: String) = contains.invoke(this, t, tX)

    fun Expect<CharSequence>.containsNotFun(t: String, vararg tX: String) = containsNot.invoke(this, t, tX)

    val indentBulletPoint = " ".repeat(rootBulletPoint.length)
    val valueWithIndent = "$indentBulletPoint$listBulletPoint$value"
    val containsNotDescr = CONTAINS_NOT.getDefault()

    describeFun(contains.name, containsNot.name) {
        context("empty string") {
            val fluentEmptyString = expect("" as CharSequence)
            it("${contains.name} 'Hello' throws AssertionError") {
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
            it("${containsNot.name} 'Hello' does not throw") {
                fluentEmptyString.containsNotFun("Hello")
            }
        }

        context("text '$text'") {

            context("search for 'Hello' and 'Robert'") {
                it("${contains.name} 'Hello' does not throw") {
                    fluent.containsFun("Hello")
                }
                it("${containsNot.name} 'Hello' throws AssertionError") {
                    expect {
                        fluent.containsNotFun("Hello")
                    }.toThrow<AssertionError> { messageContains(containsNotDescr, "$valueWithIndent: \"Hello\"") }
                }

                it("${contains.name} 'Hello' and 'Robert' does not throw") {
                    fluent.containsFun("Hello", "Robert")
                }
                it("${containsNot.name} 'Hello' and 'Robert' throws AssertionError") {
                    expect {
                        fluent.containsNotFun("Hello", "Robert")
                    }.toThrow<AssertionError> {
                        messageContains(containsNotDescr, "$valueWithIndent: \"Hello\"", "$valueWithIndent: \"Robert\"")
                    }
                }
            }

            context("search for 'notInThere' and 'neitherInThere'") {
                it("${contains.name} 'notInThere' and 'neitherInThere' throws AssertionError") {
                    expect {
                        fluent.containsFun("notInThere", "neitherInThere")
                    }.toThrow<AssertionError> {
                        messageContains(
                            containsDescr,
                            "$valueWithIndent: \"notInThere\"",
                            "$valueWithIndent: \"neitherInThere\""
                        )
                    }
                }
                it("${containsNot.name} 'notInThere' and 'neitherInThere' does not throw") {
                    fluent.containsNotFun("notInThere", "neitherInThere")
                }
            }

            context("search for 'hello' and 'robert'") {
                it("${contains.name} 'hello' and 'robert' throws AssertionError") {
                    expect {
                        fluent.containsFun("hello", "robert")
                    }.toThrow<AssertionError> {
                        message {
                            this.contains.exactly(2).values(
                                "$numberOfOccurrences: 0",
                                "$atLeast: 1"
                            )
                            this.contains.exactly(1).values(
                                "$rootBulletPoint$containsDescr: $separator",
                                "$valueWithIndent: \"hello\"",
                                "$valueWithIndent: \"robert\""
                            )
                        }
                    }
                }
                it("${containsNot.name} 'hello' and 'robert' does not throw") {
                    fluent.containsNotFun("hello", "robert")
                }
            }

            context("search for 'Hello' and 'notInThere'") {
                it("${contains.name} 'notInThere' throws AssertionError") {
                    expect {
                        fluent.containsFun("notInThere")
                    }.toThrow<AssertionError> { messageContains(containsDescr, "$valueWithIndent: \"notInThere\"") }
                }
                it("${containsNot.name} 'notInThere' does not throw") {
                    fluent.containsNotFun("notInThere")
                }

                it("${contains.name} 'Hello' and 'notInThere' throws AssertionError mentioning only 'Hello'") {
                    expect {
                        fluent.containsFun("Hello", "notInThere")
                    }.toThrow<AssertionError> {
                        message {
                            contains(containsDescr, "$valueWithIndent: \"notInThere\"")
                            containsNot("$valueWithIndent: \"Hello\"")
                        }
                    }
                }
                it("${containsNot.name} 'Hello' and 'notInThere' throws AssertionError mentioning only 'notInThere'") {
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

            it("${contains.name} 'Hello' and 'Hello' (searching twice in the same assertion) does not throw") {
                fluent.containsFun("Hello", "Hello")
            }

            it("${containsNot.name} 'notInThere' and 'notInThere' does not throw") {
                fluent.containsNotFun("notInThere", "notInThere")
            }
        }

        context("error message") {
            context("feature assertion about a Person's name 'Robert Stoll'") {
                data class Person(val name: CharSequence)

                val person = Person("Robert Stoll")

                val nameWithArrow = "${featureArrow}name"
                it("${contains.name} 'treboR' and 'llotS' - error message contains '$nameWithArrow' exactly once") {
                    expect {
                        expect(person).addAssertionsCreatedBy {
                            feature(Person::name).containsFun("treboR", "llotS")
                        }
                    }.toThrow<AssertionError> {
                        message { this.contains.exactly(1).value(nameWithArrow) }
                    }
                }
                it("${containsNot.name} 'Robert' and 'Stoll' - error message contains '$nameWithArrow' exactly once") {
                    expect {
                        expect(person).addAssertionsCreatedBy {
                            feature(Person::name).containsNotFun("Robert", "Stoll")
                        }
                    }.toThrow<AssertionError> {
                        message { this.contains.exactly(1).value(nameWithArrow) }
                    }
                }
            }
        }
    }
})
