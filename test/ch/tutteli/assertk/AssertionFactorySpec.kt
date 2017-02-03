package ch.tutteli.assertk

import com.nhaarman.mockito_kotlin.*
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

class AssertionFactorySpec : Spek({
    val assertionVerb = "my custom assertion verb"

    describe("lazy evaluation") {
        val factory = AssertionFactory.new(assertionVerb, 1)
        context(IAssertionFactory<Any>::createAndAddAssertion.name) {

            val a = 1
            val description = "description of the assert"
            val expected = "expected behaviour"
            action("in case of assertion which holds") {
                factory.createAndAddAssertion(description, expected, { a == 1 })
                it("does not throw an Exception when checking") {
                    factory.checkAssertions()
                }
            }

            group("in case of assertion which fails") {
                factory.createAndAddAssertion(description, expected, { a == 0 })
                val expectFun = expect {
                    factory.checkAssertions()
                }
                group("throws an AssertionError when checking") {
                    expectFun.toThrow<AssertionError>()
                    context("exception message") {
                        val message = expectFun.throwable!!.message!!
                        it("contains the assertionVerb") {
                            assert(message).contains(assertionVerb)
                        }
                        it("contains the 'description' and the 'expected'") {
                            assert(message).contains(description, expected)
                        }
                    }
                    on("re-checking the assertions") {
                        expectFun.toThrow<AssertionError>()
                        it("does not re-throw the exception") {
                            factory.checkAssertions()
                        }
                    }
                }
            }

        }

        context(IAssertionFactory<Any>::addAssertion.name) {
            action("in case of a custom assertion which holds") {
                factory.addAssertion(object : IAssertion {
                    override fun holds() = true
                    override fun logMessages() = listOf("a" to "b")
                })
                it("does not throw an Exception when checking") {
                    factory.checkAssertions()
                }
            }

            group("in case of a custom assertion which fails") {
                factory.addAssertion(object : IAssertion {
                    override fun holds() = false
                    override fun logMessages() = listOf("a" to "b", "c" to "d")
                })
                val expectFun = expect {
                    factory.checkAssertions()
                }
                group("throws an AssertionError") {
                    expectFun.toThrow<AssertionError>()
                    context("exception message") {
                        val message = expectFun.throwable!!.message!!
                        it("contains the log messages of the custom assertion") {
                            assert(message).contains("a", "b", "c", "d")
                        }
                        it("contains the assertionVerb") {
                            assert(message).contains(assertionVerb)
                        }
                    }
                    on("re-checking the assertions") {
                        expectFun.toThrow<AssertionError>()
                        it("does not re-throw the exception") {
                            factory.checkAssertions()
                        }
                    }
                }
            }
        }
    }


    describe("fail") {

        fun <T : Any> expectAssertionError(assertionVerb: String, subject: T, messages: List<Pair<String, String>>) {
            expect {
                AssertionFactory.fail(assertionVerb, subject, messages)
            }.toThrow<AssertionError>()
        }
        val subject = 123

        context("IObjectFormatter") {
            val objectFormatterBefore = AssertionFactory.objectFormatter
            beforeEachTest {
                AssertionFactory.objectFormatter = mock<IObjectFormatter> {
                    on { format(any<Any>()) } doReturn ""
                }
            }
            afterEachTest {
                AssertionFactory.objectFormatter = objectFormatterBefore
            }

            it("uses the IObjectFormatter to format the subject") {
                //act
                expectAssertionError(assertionVerb, subject, listOf())
                //assert
                verify(AssertionFactory.objectFormatter).format(subject)
            }
        }

        context("IAssertionMessageFormatter") {
            val formatterBefore = AssertionFactory.assertionMessageFormatter
            beforeEachTest {
                AssertionFactory.assertionMessageFormatter = mock<IAssertionMessageFormatter>()
            }
            afterEachTest {
                AssertionFactory.assertionMessageFormatter = formatterBefore
            }

            it("uses the IAssertionMessageFormatter to format the messages") {
                //act
                expectAssertionError(assertionVerb, subject, listOf())
                //assert
                verify(AssertionFactory.assertionMessageFormatter).format(any<List<Pair<String, String>>>())
            }

            it("appends the assertionVerb and the subject to the messagesList") {
                //act
                expectAssertionError(assertionVerb, subject, listOf())
                //assert
                val captor = argumentCaptor<List<Pair<String, String>>>()
                verify(AssertionFactory.assertionMessageFormatter).format(captor.capture())
                //reset formatter in order that we see some meaningful error message
                AssertionFactory.assertionMessageFormatter = formatterBefore
                //TODO rewrite to use containsOnly as soon as supported
                assert(captor.firstValue.size).toBe(1)
                assert(captor.firstValue[0].first).toBe(assertionVerb)
                assert(captor.firstValue[0].second).contains(subject.toString())
            }
        }
    }
})
