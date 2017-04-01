package ch.tutteli.atrium.verbs

import ch.tutteli.atrium.*
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import ch.tutteli.atrium.verbs.assert.assert
import ch.tutteli.atrium.verbs.assertthat.assertThat
import ch.tutteli.atrium.verbs.expect.expect
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class VerbSpec : Spek({
    describe("immediately failing assertion verbs") {
        mapOf(
            "assert" to assert(1),
            "assertThat" to assertThat(1),
            "expect" to expect(1)
        ).forEach { (verb, assertionFluent) ->
            group(verb) {
                it("does not throw an exception in case the assertion holds") {
                    assertionFluent.toBe(1)
                }
                it("throws an AssertionError as soon as one assertion fails") {
                    expect {
                        assertionFluent.isSmallerThan(10).and.isSmallerThan(0).and.isGreaterThan(2)
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
        }

    }

    describe("lazily evaluated assertion verbs") {
        mapOf(
            "assert" to { createAssertions: IAssertionPlant<Int>.() -> Unit -> assert(1, createAssertions) },
            "assertThat" to { createAssertions: IAssertionPlant<Int>.() -> Unit -> assertThat(1, createAssertions) },
            "expect" to { createAssertions: IAssertionPlant<Int>.() -> Unit -> expect(1, createAssertions) }
        ).forEach { (verb, assertionFluent) ->
            group(verb) {
                it("does not throw an exception in case the assertion holds") {
                    assertionFluent { toBe(1) }
                }
                it("evaluates all assertions and then throws an AssertionError") {
                    expect {
                        assertionFluent {
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
        }
    }

    describe("nullable supporting assertion verbs in case subject is null") {
        mapOf<String, IAssertionPlantNullable<Int?>>(
            "assert" to assert(null),
            "assertThat" to assertThat(null),
            "expect" to expect(null)
        ).forEach { (verb, assertionFluent) ->
            group(verb) {
                it("does not throw an exception when calling isNull") {
                    assertionFluent.isNull()
                }
                it("throws an AssertionError when calling isNotNull") {
                    expect {
                        assertionFluent.isNotNull()
                    }.toThrow<AssertionError>().and.message.contains("is not", "null")
                }
            }
        }
    }
})
