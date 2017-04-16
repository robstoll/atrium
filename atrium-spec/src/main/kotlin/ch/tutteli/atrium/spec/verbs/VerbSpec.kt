package ch.tutteli.atrium.spec.verbs

import ch.tutteli.atrium.*
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import ch.tutteli.atrium.creating.ThrowableFluent
import ch.tutteli.atrium.creating.newCheckLazilyAtTheEnd
import ch.tutteli.atrium.reporting.ReporterBuilder
import ch.tutteli.atrium.spec.creating.DownCastBuilderSpec
import ch.tutteli.atrium.spec.inCaseOf
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

// does not make sense to test the verbs with the verbs themselves. Thus we create our own assertion verbs here
private fun <T : Any> assert(subject: T): IAssertionPlant<T>
    = AtriumFactory.newCheckImmediately("assert", subject, AtriumReporterSupplier.REPORTER)

private inline fun <T : Any> assert(subject: T, createAssertions: IAssertionPlant<T>.() -> Unit): IAssertionPlant<T>
    = AtriumFactory.newCheckLazilyAtTheEnd("assert", subject, AtriumReporterSupplier.REPORTER, createAssertions)

private fun <T : Any?> assert(subject: T): IAssertionPlantNullable<T>
    = AtriumFactory.newNullable("assert", subject, AtriumReporterSupplier.REPORTER)

private fun expect(act: () -> Unit): ThrowableFluent
    = AtriumFactory.newThrowableFluent("expect the thrown exception", act, AtriumReporterSupplier.REPORTER)

private object AtriumReporterSupplier {
    val REPORTER by lazy {
        ReporterBuilder
            .withDetailedObjectFormatter()
            .withSameLineAssertionMessageFormatter()
            .buildOnlyFailureReporting()
    }
}

open class VerbSpec(
    val plantCheckImmediately: Pair<String, (subject: Int) -> IAssertionPlant<Int>>,
    val plantCheckLazily: Pair<String, (subject: Int, createAssertions: IAssertionPlant<Int>.() -> Unit) -> IAssertionPlant<Int>>,
    val plantNullable: Pair<String, (subject: Int?) -> IAssertionPlantNullable<Int?>>,
    val plantExpect: Pair<String, (act: () -> Unit) -> ThrowableFluent>
) : Spek({

    describe("assertion verb '${plantCheckImmediately.first}' which immediately evaluates assertions") {
        val (_, assertionVerb) = plantCheckImmediately

        it("does not throw an exception in case the assertion holds") {
            assertionVerb(1).toBe(1)
        }
        it("throws an AssertionError as soon as one assertion fails") {
            expect {
                assertionVerb(1).isSmallerThan(10).and.isSmallerThan(0).and.isGreaterThan(2)
            }.toThrow<AssertionError> {
                assert(subject.message).isNotNull {
                    //TODO contains not 10
                    contains("1") //the actual value
                    contains("0") //the expected value
                    //TODO contains not 2
                }
            }
        }
    }


    /**
     * @see DownCastBuilderSpec - similar spec for lazy evaluated assertion verb
     */
    describe("assertion verb '${plantCheckImmediately.first}' which lazily evaluates assertions") {
        val (_, assertionVerb) = plantCheckLazily
        it("does not throw an exception in case the assertion holds") {
            assertionVerb(1) { toBe(1) }
        }
        it("evaluates all assertions and then throws an AssertionError") {
            expect {
                assertionVerb(1) {
                    isSmallerThan(0)
                    isGreaterThan(2)
                }
            }.toThrow<AssertionError> {
                and.message {
                    contains("1") //the actual value
                    contains("0") //the expected value
                    contains("2") //the second expected value
                }
            }
        }
    }

    describe("assertion verb '${plantNullable.first}' which supports nullable subjects") {
        inCaseOf("a nullable subject") {
            val (_, assertionVerb) = plantNullable
            it("does not throw an exception when calling isNull") {
                assertionVerb(null).isNull()
            }
            it("throws an AssertionError when calling isNotNull") {
                expect {
                    assertionVerb(null).isNotNull()
                }.toThrow<AssertionError>().and.message.contains("is not", "null")
            }
        }
    }

    describe("assertion verb '${plantExpect.first}' which deals with exceptions") {
        inCaseOf("an IllegalArgumentException occurs") {
            val (_, assertionVerb) = plantExpect
            it("does not throw an exception expecting an IllegalArgumentException") {
                assertionVerb({
                    throw IllegalArgumentException()
                }).toThrow<IllegalArgumentException>()
            }
            it("throws an AssertionError when expecting an UnsupportedOperationException") {
                expect {
                    assertionVerb({
                        throw IllegalArgumentException()
                    }).toThrow<UnsupportedOperationException>()
                }.toThrow<AssertionError> {
                    message.contains("is a",
                        IllegalArgumentException::class.java.name,
                        UnsupportedOperationException::class.java.name)
                }
            }
        }
    }

})
