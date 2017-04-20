package ch.tutteli.atrium

import ch.tutteli.atrium.DescriptionCharSequenceAssertion.*
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe

object CharSequenceAssertionsSpec : Spek({
    val subject = "hello my name is robert"
    val fluent = assert(subject)

    val contains = "contains"
    val containsNot = "containsNot"
    describe("fun $contains and $containsNot") {
        context("text '$subject'") {
            context("search for 'hello' and 'robert'") {
                test("$contains 'hello' does not throw") {
                    fluent.contains("hello")
                }
                test("$containsNot 'hello' throws AssertionError") {
                    expect {
                        fluent.containsNot("hello")
                    }.toThrow<AssertionError>()
                }

                test("$contains 'hello' and 'robert' does not throw") {
                    fluent.contains("hello", "robert")
                }
                test("$containsNot 'hello' and 'robert' throws AssertionError") {
                    expect {
                        fluent.containsNot("hello", "robert")
                    }.toThrow<AssertionError>().and.message.contains(CONTAINS_NOT)
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
                    }.toThrow<AssertionError>().and.message.contains(CONTAINS)
                }
                test("$containsNot 'notInThere' and 'neitherInThere' does not throw") {
                    fluent.containsNot("notInThere", "neitherInThere")
                }

                test("$contains 'notInThere' and 'neitherInThere' as Any throws AssertionError") {
                    expect {
                        fluent.contains("notInThere" as Any, "neitherInThere" as Any)
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
                }.toThrow<AssertionError>().and.message.endsWith("${IS_NOT_EMPTY.getDefault()}: empty")
                expect {
                    assert(StringBuilder()).isNotEmpty()
                }.toThrow<AssertionError>().and.message.endsWith("${IS_NOT_EMPTY.getDefault()}: empty")
                expect {
                    assert(StringBuffer()).isNotEmpty()
                }.toThrow<AssertionError>().and.message.endsWith("${IS_NOT_EMPTY.getDefault()}: empty")
            }
        }
        context("string is not empty") {
            val notEmptyString = "not empty string"
            test("${fluent::isEmpty.name} throws an AssertionError") {
                expect {
                    assert(notEmptyString).isEmpty()
                }.toThrow<AssertionError>().and.message.endsWith("${IS_EMPTY.getDefault()}: empty")
                expect {
                    assert(StringBuilder(notEmptyString)).isEmpty()
                }.toThrow<AssertionError>().and.message.endsWith("${IS_EMPTY.getDefault()}: empty")
                expect {
                    assert(StringBuffer(notEmptyString)).isEmpty()
                }.toThrow<AssertionError>().and.message.endsWith("${IS_EMPTY.getDefault()}: empty")
            }
            test("${fluent::isNotEmpty.name} does not throw") {
                assert(notEmptyString).isNotEmpty()
                assert(StringBuilder(notEmptyString)).isNotEmpty()
                assert(StringBuffer(notEmptyString)).isNotEmpty()
            }
        }
    }

    describe("fun ${fluent::startsWith.name} and ${fluent::startsNotWith.name}") {
        context("text '$subject'") {
            test("${fluent::startsWith.name} 'hello' does not throw") {
                fluent.startsWith("hello")
            }
            test("${fluent::startsNotWith.name} 'hello' throws an AssertionError") {
                expect {
                    fluent.startsNotWith("hello")
                }.toThrow<AssertionError>().and.message.contains(STARTS_NOT_WITH)
            }

            test("${fluent::startsWith.name} 'robert' throws an AssertionError") {
                expect {
                    fluent.startsWith("goodbye")
                }.toThrow<AssertionError>().and.message.contains(STARTS_WITH)
            }
            test("${fluent::startsNotWith.name} 'robert' does not throw") {
                fluent.startsNotWith("goodbye")
            }
        }
    }

    describe("fun ${fluent::endsWith.name} and ${fluent::endsNotWith.name}") {
        context("text '$subject'") {
            test("${fluent::endsWith.name} 'hello' throws an AssertionError") {
                expect {
                    fluent.endsWith("hello")
                }.toThrow<AssertionError>().and.message.contains(ENDS_WITH)
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
                }.toThrow<AssertionError>().and.message.contains(ENDS_NOT_WITH)
            }
        }
    }
})
