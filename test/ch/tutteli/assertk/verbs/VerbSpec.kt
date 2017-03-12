package ch.tutteli.assertk.verbs

import ch.tutteli.assertk.*
import ch.tutteli.assertk.creating.IAssertionFactory
import ch.tutteli.assertk.creating.IAssertionFactoryNullable
import ch.tutteli.assertk.verbs.assert.assert
import ch.tutteli.assertk.verbs.assertthat.assertThat
import ch.tutteli.assertk.verbs.expect.expect
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it

class VerbSpec : Spek({
    describe("immediately failing assertion verbs") {
        mapOf(
            "assert" to assert(1),
            "assertThat" to assertThat(1),
            "expect" to expect(1)
        ).forEach { (verb, factory) ->
            group(verb) {
                it("does not throw an exception in case the assertion holds") {
                    factory.toBe(1)
                }
                it("throws an AssertionError as soon as one assertion fails") {
                    expect {
                        factory.isSmallerThan(10).and.isSmallerThan(0).and.isGreaterThan(2)
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
            "assert" to { createAssertions: IAssertionFactory<Int>.() -> Unit -> assert(1, createAssertions) },
            "assertThat" to { createAssertions: IAssertionFactory<Int>.() -> Unit -> assertThat(1, createAssertions) },
            "expect" to { createAssertions: IAssertionFactory<Int>.() -> Unit -> expect(1, createAssertions) }
        ).forEach { (verb, factory) ->
            group(verb) {
                it("does not throw an exception in case the assertion holds") {
                    factory { toBe(1) }
                }
                it("evaluates all assertions and then throws an AssertionError") {
                    expect {
                        factory {
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
        mapOf<String, IAssertionFactoryNullable<Int?>>(
            "assert" to assert(null),
            "assertThat" to assertThat(null),
            "expect" to expect(null)
        ).forEach { (verb, factory) ->
            group(verb) {
                it("does not throw an exception when calling isNull") {
                    factory.isNull()
                }
                it("throws an AssertionError when calling isNotNull") {
                    expect {
                        factory.isNotNull()
                    }.toThrow<AssertionError>().and.message.contains("is not", "null")
                }
            }
        }
    }
})
