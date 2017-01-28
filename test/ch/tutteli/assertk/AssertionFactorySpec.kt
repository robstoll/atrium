package ch.tutteli.assertk

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class AssertionFactorySpec : Spek({

    describe("fail") {

        fun <T : Any> failSilently(assertionVerb: String, subject: T, messages: List<Pair<String, String>>) {
            try {
                AssertionFactory.fail(assertionVerb, subject, messages)
            } catch(a: AssertionError) {
                //that's fine we expect an AssertionError
            }
        }

        val assertionVerb = "assert"
        val subject = 123

        context("IObjectFormatter") {
            val objectFormatterBefore = AssertionFactory.objectFormatter
            beforeEachTest {
                AssertionFactory.objectFormatter = mock<IObjectFormatter>()
            }
            afterEachTest {
                AssertionFactory.objectFormatter = objectFormatterBefore
            }

            it("uses the IObjectFormatter to format the subject") {
                //act
                failSilently(assertionVerb, subject, listOf())
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
                failSilently(assertionVerb, subject, listOf())
                //assert
                verify(AssertionFactory.assertionMessageFormatter).format(any<List<Pair<String, String>>>())
            }

            it("appends the assertionVerb and the subject to the messagesList") {
                //act
                failSilently(assertionVerb, subject, listOf())
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

