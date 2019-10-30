package ch.tutteli.atrium.specs.verbs

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.reporting.ExpectOptions
import ch.tutteli.atrium.domain.builders.reporting.ReporterBuilder
import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.specs.AssertionVerb
import ch.tutteli.atrium.specs.prefixedDescribeTemplate
import ch.tutteli.atrium.specs.reporting.alwaysTrueAssertionFilter
import ch.tutteli.atrium.specs.toBeDescr
import ch.tutteli.atrium.translations.DescriptionAnyAssertion
import ch.tutteli.atrium.translations.DescriptionComparableAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class VerbSpec(
    forNonNullable: Pair<String, (subject: Int, representation: String?, ExpectOptions) -> Expect<Int>>,
    forNonNullableCreator: Pair<String, (subject: Int, representation: String?, ExpectOptions, assertionCreator: Expect<Int>.() -> Unit) -> Expect<Int>>,
    forNullable: Pair<String, (subject: Int?, representation: String?, ExpectOptions) -> Expect<Int?>>,
    forThrowable: Pair<String, (act: () -> Unit) -> ThrowableThrown.Builder>,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun prefixedDescribe(description: String, body: Suite.() -> Unit) {
        prefixedDescribeTemplate(describePrefix, description, body)
    }

    prefixedDescribe("assertion verb '${forNonNullable.first}' which immediately evaluates assertions") {
        val (_, assertionVerbFun) = forNonNullable
        fun assertionVerb(subject: Int, representation: String? = null, options: ExpectOptions = ExpectOptions()) =
            assertionVerbFun(subject, representation, options)

        testNonNullableSubject { assertionVerb(it) }
        testNonNullableCustomisation { subject, representation, options, assertionCreator ->
            assertionVerbFun(subject, representation, options).addAssertionsCreatedBy(assertionCreator)
        }
    }

    prefixedDescribe("assertion verb '${forNonNullable.first}' which lazily evaluates assertions") {
        val (_, assertionVerbFun) = forNonNullableCreator
        fun assertionVerb(
            subject: Int,
            representation: String? = null,
            options: ExpectOptions = ExpectOptions(),
            assertionCreator: Expect<Int>.() -> Unit
        ) = assertionVerbFun(subject, representation, options, assertionCreator)

        context("the assertions hold") {
            it("does not throw an exception") {
                assertionVerb(1) { toBe(1) }
            }
            context("a subsequent assertion holds") {
                it("does not throw an exception") {
                    assertionVerb(1) { toBe(1) }.isLessThan(2)
                }
            }
            context("a subsequent group of assertions hold") {
                it("does not throw an exception") {
                    assertionVerb(1) { toBe(1) }.and { isLessThan(2) }
                }
            }
            context("a subsequent assertion fails") {
                it("throws an AssertionError") {
                    assert {
                        assertionVerb(1) { toBe(1) }.isLessThan(1)
                    }.toThrow<AssertionError> {
                        message {
                            contains("${DescriptionComparableAssertion.IS_LESS_THAN.getDefault()}: 1")
                            containsNot(toBeDescr)
                        }
                    }
                }
            }

            context("multiple assertions of a subsequent group of assertion fails") {
                it("evaluates all assertions and then throws an AssertionError, reporting only failing") {
                    assert {
                        assertionVerb(1) { toBe(1) }.and { isLessThan(0); toBe(1); isGreaterThan(2) }
                    }.toThrow<AssertionError> {
                        message {
                            contains(
                                ": 1",
                                "${DescriptionComparableAssertion.IS_LESS_THAN.getDefault()}: 0",
                                "${DescriptionComparableAssertion.IS_GREATER_THAN.getDefault()}: 2"
                            )
                            containsNot(toBeDescr)
                        }
                    }
                }
            }
        }

        context("one assertion fails") {
            it("evaluates all assertions and then throws an AssertionError") {
                assert {
                    assertionVerb(1) {
                        isLessThan(0)
                        isGreaterThan(2)
                    }
                }.toThrow<AssertionError> {
                    message {
                        contains(": 1")
                        contains("${DescriptionComparableAssertion.IS_LESS_THAN.getDefault()}: 0")
                        contains("${DescriptionComparableAssertion.IS_GREATER_THAN.getDefault()}: 2")
                    }
                }
            }
        }

        testNonNullableCustomisation(assertionVerbFun)
    }

    prefixedDescribe("assertion verb '${forNullable.first}' which supports nullable subjects") {
        val (_, assertionVerbFun) = forNullable
        fun assertionVerb(subject: Int?, representation: String? = null, options: ExpectOptions = ExpectOptions()) =
            assertionVerbFun(subject, representation, options)

        context("subject is null") {
            it("does not throw an exception when calling toBe(`null`)") {
                assertionVerb(null).toBe(null)
            }
            it("throws an AssertionError when calling notToBeNull") {
                assert {
                    assertionVerb(null).notToBeNull { toBe(1) }
                }.toThrow<AssertionError> {
                    @Suppress("DEPRECATION")
                    messageContains(
                        DescriptionAnyAssertion.IS_A.getDefault(),
                        "Int",
                        "$toBeDescr: 1"
                    )
                }
            }
        }
        context("subject is not null") {
            testNonNullableSubject { subject -> ExpectImpl.changeSubject(assertionVerb(subject)).unreported { it!! } }
        }

        testNonNullableCustomisation { subject, representation, options, assertionCreator ->
            ExpectImpl.changeSubject(assertionVerbFun(subject, representation, options)).unreported { it!! }
                .addAssertionsCreatedBy(assertionCreator)
        }

        context("customisation in case of nullable subjects") {
            context("null representation via ExpectOptions") {
                context("chooser-lambda") {
                    it("does not treat it as RawString") {
                        assert {
                            assertionVerb(
                                null, options = ExpectOptions { withNullRepresentation("<NULL>") }
                            ).toBe(1)
                        }.toThrow<AssertionError> {
                            messageContains(": \"<NULL>\"")
                        }
                    }
                }
                context("constructor") {
                    it("uses the custom representation in case subject is null") {
                        assert {
                            assertionVerb(
                                null, options = ExpectOptions(nullRepresentation = RawString.create("<NULL>"))
                            ).toBe(1)
                        }.toThrow<AssertionError> {
                            messageContains(": <NULL>")
                        }
                    }
                }
            }
        }
    }

    prefixedDescribe("assertion verb '${forThrowable.first}' which deals with exceptions") {
        context("an IllegalArgumentException occurs") {
            val (_, assertionVerb) = forThrowable
            it("does not throw an exception expecting an IllegalArgumentException") {
                assertionVerb {
                    throw IllegalArgumentException("hello")
                }.toThrow<IllegalArgumentException> {
                    message.toBe("hello")
                }
            }
            it("throws an AssertionError when expecting an UnsupportedOperationException") {
                assert {
                    assertionVerb {
                        throw IllegalArgumentException()
                    }.toThrow<UnsupportedOperationException> {}
                }.toThrow<AssertionError> {
                    messageContains(
                        DescriptionAnyAssertion.IS_A.getDefault(),
                        IllegalArgumentException::class.fullName,
                        UnsupportedOperationException::class.fullName
                    )
                }
            }
        }
    }

})

private fun Suite.testNonNullableSubject(assertionVerb: (Int) -> Expect<Int>) {
    it("does not throw an exception in case the assertion holds") {
        assertionVerb(1).toBe(1)
    }
    it("throws an AssertionError as soon as one assertion fails") {
        assert {
            assertionVerb(1).isLessOrEquals(10).and.isLessOrEquals(0).and.isGreaterOrEquals(2)
        }.toThrow<AssertionError> {
            message {
                contains(": 1")
                contains("${DescriptionComparableAssertion.IS_LESS_OR_EQUALS.getDefault()}: 0")
                containsNot("${DescriptionComparableAssertion.IS_GREATER_OR_EQUALS.getDefault()}: 2")
            }
        }
    }
}

private fun Suite.testNonNullableCustomisation(assertionVerbFun: (Int, String?, ExpectOptions, Expect<Int>.() -> Unit) -> Expect<Int>) {
    fun assertionVerb(
        subject: Int,
        representation: String? = null,
        options: ExpectOptions = ExpectOptions(),
        assertionCreator: Expect<Int>.() -> Unit
    ) = assertionVerbFun(subject, representation, options, assertionCreator)

    context("customisation") {
        context("verb via ExpectOptions") {
            context("one can override the previously defined verb via options") {
                context("chooser lambda") {
                    it("string overload") {
                        assert {
                            assertionVerb(1, options = ExpectOptions { withVerb("test verb") }) { toBe(2) }
                        }.toThrow<AssertionError> {
                            messageContains("test verb: 1")
                        }
                    }
                    it("translatable overload") {
                        assert {
                            assertionVerb(1, options = ExpectOptions { withVerb(Untranslatable("test verb")) }) {
                                toBe(2)
                            }
                        }.toThrow<AssertionError> {
                            messageContains("test verb: 1")
                        }
                    }
                    it("last definition wins") {
                        assert {
                            assertionVerb(1, options = ExpectOptions {
                                withVerb(Untranslatable("test verb"))
                                withVerb("another verb")
                            }) { toBe(2) }
                        }.toThrow<AssertionError> {
                            messageContains("another verb: 1")
                        }
                    }
                }
                it("constructor") {
                    assert {
                        assertionVerb(1, options = ExpectOptions(assertionVerb = Untranslatable("test verb"))) {
                            toBe(2)
                        }
                    }.toThrow<AssertionError> {
                        messageContains("test verb: 1")
                    }
                }
            }

        }

        context("representation") {
            context("via argument") {
                it("is treated as RawString") {
                    val representation = "my own representation"
                    assert {
                        assertionVerb(1, representation) { toBe(2) }
                    }.toThrow<AssertionError> {
                        messageContains(": $representation")
                    }
                }
                it("has precedence over a representation defined in ExpectOptions") {
                    val representation = "my own representation"
                    val anotherRepresentation = "another representation"
                    assert {
                        assertionVerb(
                            1,
                            representation,
                            options = ExpectOptions(representation = anotherRepresentation)
                        ) { toBe(2) }
                    }.toThrow<AssertionError> {
                        message {
                            contains(": $representation")
                            containsNot(anotherRepresentation)
                        }
                    }
                }
            }

            context("via ExpectOptions") {
                context("chooser lambda") {
                    it("does *not* treat the representation as RawString") {
                        assert {
                            assertionVerb(1, options = ExpectOptions { withRepresentation("test") }) { toBe(2) }
                        }.toThrow<AssertionError> {
                            messageContains(": \"test\"")
                        }
                    }
                    it("last definition wins") {
                        assert {
                            assertionVerb(1, options = ExpectOptions {
                                withRepresentation(RawString.create("test"))
                                withRepresentation("another representation")
                            }) { toBe(2) }
                        }.toThrow<AssertionError> {
                            messageContains(": \"another representation\"")
                        }
                    }
                }
                context("constructor") {
                    it("does *not* treat the representation as RawString") {
                        assert {
                            assertionVerb(1, options = ExpectOptions(representation = "test")) { toBe(2) }
                        }.toThrow<AssertionError> {
                            messageContains(": \"test\"")
                        }
                    }
                }
            }
        }

        context("reporter via ExpectOptions") {

            val allAssertionsReporter = ReporterBuilder.create()
                .withoutTranslationsUseDefaultLocale()
                .withDetailedObjectFormatter()
                .withDefaultAssertionFormatterController()
                .withDefaultAssertionFormatterFacade()
                .withTextSameLineAssertionPairFormatter()
                .withTextCapabilities()
                .withNoOpAtriumErrorAdjuster()
                .withCustomReporter { facade, adjuster ->
                    object : Reporter {
                        override val atriumErrorAdjuster = adjuster
                        override fun format(assertion: Assertion, sb: StringBuilder) =
                            facade.format(assertion, sb, alwaysTrueAssertionFilter)
                    }
                }
                .build()

            context("chooser lambda") {
                it("reports all assertions if the reporter is defined this way") {
                    assert {
                        assertionVerb(1, options = ExpectOptions { withReporter(allAssertionsReporter) }) {
                            toBe(1)
                            toBe(2)
                        }
                    }.toThrow<AssertionError> {
                        messageContains("$toBeDescr: 1", "$toBeDescr: 2")
                    }
                }
                it("last definition wins") {
                    assert {
                        assertionVerb(1, options = ExpectOptions {
                            withReporter(allAssertionsReporter)
                            withReporter(AtriumReporterSupplier.REPORTER)
                        }) {
                            toBe(1)
                            toBe(2)
                        }
                    }.toThrow<AssertionError> {
                        message {
                            containsNot("$toBeDescr: 1")
                            contains("$toBeDescr: 2")
                        }
                    }
                }
            }
            context("constructor") {
                it("reports all assertions if the reporter is defined this way") {
                    assert {
                        assertionVerb(1, options = ExpectOptions(reporter = allAssertionsReporter)) {
                            toBe(1)
                            toBe(2)
                        }
                    }.toThrow<AssertionError> {
                        messageContains("$toBeDescr: 1", "$toBeDescr: 2")
                    }
                }
            }
        }
    }
}


// does not make sense to test the verbs with the verbs themselves. Thus we create our own assertion verbs here
private fun assert(act: () -> Unit) =
    AssertImpl.throwable.thrownBuilder(AssertionVerb.EXPECT_THROWN, act, AtriumReporterSupplier.REPORTER)

private object AtriumReporterSupplier {
    val REPORTER by lazy {
        ReporterBuilder.create()
            .withoutTranslationsUseDefaultLocale()
            .withDetailedObjectFormatter()
            .withDefaultAssertionFormatterController()
            .withDefaultAssertionFormatterFacade()
            .withTextSameLineAssertionPairFormatter()
            .withTextCapabilities()
            .withDefaultAtriumErrorAdjusters()
            .withOnlyFailureReporter()
            .build()
    }
}
