package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCharSequenceProof.*
import ch.tutteli.atrium.specs.*
import org.spekframework.spek2.style.specification.Suite

abstract class CharSequenceToContainNotToContainExpectationsSpec(
    toContain: Fun2<CharSequence, Any, Array<out Any>>,
    notToContain: Fun2<CharSequence, Any, Array<out Any>>,
    describePrefix: String = "[Atrium] "
) : CharSequenceToContainSpecBase({

    include(object : SubjectLessSpec<CharSequence>(
        describePrefix,
        toContain.forSubjectLessTest("hello", arrayOf()),
        notToContain.forSubjectLessTest("hello", arrayOf())
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    fun Expect<CharSequence>.toContainFun(t: String, vararg tX: String) = toContain.invoke(this, t, tX)

    fun Expect<CharSequence>.notToContainFun(t: String, vararg tX: String) = notToContain.invoke(this, t, tX)

    describeFun(toContain.name, notToContain.name) {
        context("empty string") {
            it("${toContain.name} 'Hello' throws AssertionError") {
                expect {
                    expect("" as CharSequence).toContainFun("Hello")
                }.toThrow<AssertionError> {
                    message {
                        toContainSubject("\"\"")
                        toContainDescr(TO_CONTAIN, "")
                        toContainValue("\"Hello\"")
                        //TODO 1.3.0 expect that it starts with ❗❗ (right now it starts with »)
                        toContain(NOT_FOUND.string)
                    }
                }
            }
            it("${notToContain.name} 'Hello' does not throw") {
                expect("" as CharSequence).notToContainFun("Hello")
            }
        }

        context("text '$text'") {

            context("search for 'Hello' and 'Robert'") {
                it("${toContain.name} 'Hello' does not throw") {
                    expect(text).toContainFun("Hello")
                }
                it("${notToContain.name} 'Hello' throws AssertionError") {
                    expect {
                        expect(text).notToContainFun("Hello")
                    }.toThrow<AssertionError> {
                        message {
                            toContainSubject("\"$text\"")
                            toContainDescr(NOT_TO_CONTAIN, "")
                            toContainValue("\"Hello\"")
                            toContainNumberOfMatches(1)
                            toContainToEqualDescr(0)
                        }
                    }
                }

                it("${toContain.name} 'Hello' and 'Robert' does not throw") {
                    expect(text).toContainFun("Hello", "Robert")
                }
                it("${notToContain.name} 'Hello' and 'Robert' throws AssertionError") {
                    expect {
                        expect(text).notToContainFun("Hello", "Robert")
                    }.toThrow<AssertionError> {
                        message {
                            toContainValue("\"Hello\"")
                            toContainValue("\"Robert\"")
                            toContainNumberOfMatches(1, numOfMatches = 2)
                            toContainToEqualDescr(0, numOfMatches = 2)
                        }
                    }
                }
            }

            context("search for 'notInThere' and 'neitherInThere'") {
                it("${toContain.name} 'notInThere' and 'neitherInThere' throws AssertionError") {
                    expect {
                        expect(text).toContainFun("notInThere", "neitherInThere")
                    }.toThrow<AssertionError> {
                        message {
                            toContainValue("\"notInThere\"")
                            toContainValue("\"neitherInThere\"")
                            //TODO 1.3.0 expect that it starts with ❗❗ (right now it starts with »)
                            this.toContain.exactly(2).value(NOT_FOUND.string)
                        }
                    }
                }
                it("${notToContain.name} 'notInThere' and 'neitherInThere' does not throw") {
                    expect(text).notToContainFun("notInThere", "neitherInThere")
                }
            }

            context("search for 'hello' and 'robert'") {
                it("${toContain.name} 'hello' and 'robert' throws AssertionError") {
                    expect {
                        expect(text).toContainFun("hello", "robert")
                    }.toThrow<AssertionError> {
                        message {
                            toContainValue("\"hello\"")
                            toContainValue("\"robert\"")
                            //TODO 1.3.0 expect that it starts with ❗❗ (right now it starts with »)
                            this.toContain.exactly(2).value(NOT_FOUND.string)
                        }
                    }
                }
                it("${notToContain.name} 'hello' and 'robert' does not throw") {
                    expect(text).notToContainFun("hello", "robert")
                }
            }

            context("search for 'notInThere'") {
                it("${toContain.name} 'notInThere' throws AssertionError") {
                    expect {
                        expect(text).toContainFun("notInThere")
                    }.toThrow<AssertionError> {
                        message {
                            toContainValue("\"notInThere\"")
                            //TODO 1.3.0 expect that it starts with ❗❗ (right now it starts with »)
                            toContain(NOT_FOUND.string)
                        }
                    }
                }
                it("${notToContain.name} 'notInThere' does not throw") {
                    expect(text).notToContainFun("notInThere")
                }

                it("${toContain.name} 'Hello' and 'notInThere' throws AssertionError mentioning only 'Hello'") {
                    expect {
                        expect(text).toContainFun("Hello", "notInThere")
                    }.toThrow<AssertionError> {
                        message {
                            notToContain("\"Hello\"")
                            toContainValue("\"notInThere\"")
                            //TODO 1.3.0 expect that it starts with ❗❗ (right now it starts with »)
                            this.toContain.exactly(1).value(NOT_FOUND.string)
                        }
                    }
                }
                it("${notToContain.name} 'Hello' and 'notInThere' throws AssertionError mentioning only 'notInThere'") {
                    expect {
                        expect(text).notToContainFun("Hello", "notInThere")
                    }.toThrow<AssertionError> {
                        message {
                            toContainValue("\"Hello\"")
                            notToContain("\"notInThere\"")
                            toContainToEqualDescr(0)
                        }
                    }
                }
            }

            it("${toContain.name} 'Hello' and 'Hello' (searching twice in the same assertion) does not throw") {
                expect(text).toContainFun("Hello", "Hello")
            }

            it("${notToContain.name} 'notInThere' and 'notInThere' does not throw") {
                expect(text).notToContainFun("notInThere", "notInThere")
            }
        }

        context("error message") {
            context("feature expectation about a Person's name 'Robert Stoll'") {
                data class Person(val name: CharSequence)

                val person = Person("Robert Stoll")

                val nameWithArrow = "${featureArrow}name"
                it("${toContain.name} 'treboR' and 'llotS' - error message toContain '$nameWithArrow' exactly once") {
                    expect {
                        expect(person).feature(Person::name).toContainFun("treboR", "llotS")
                    }.toThrow<AssertionError> {
                        message {
                            this.toContain.exactly(1).value(nameWithArrow)
                            toContainValue("\"treboR\"")
                            toContainValue("\"llotS\"")
                            //TODO 1.3.0 expect that it starts with ❗❗ (right now it starts with »)
                            this.toContain.exactly(2).value(NOT_FOUND.string)
                        }
                    }
                }
                it("${notToContain.name} 'Robert' and 'Stoll' - error message toContain '$nameWithArrow' exactly once") {
                    expect {
                        expect(person).feature(Person::name).notToContainFun("Robert", "Stoll")
                    }.toThrow<AssertionError> {
                        message { this.toContain.exactly(1).value(nameWithArrow) }
                    }
                }
            }
        }
    }
})
