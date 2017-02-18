package ch.tutteli.assertk

import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it

class OnlyFailureReporterSpec : Spek({

    describe("format") {
        val sb = StringBuilder()
        it("does not append anything if assertion holds") {
            val testee = OnlyFailureReporter(SameLineAssertionMessageFormatter())
            testee.format(sb, DescriptionExpectedAssertion("to be", "0", { true }))
            assert(sb).isEmpty()
        }

        context("dependencies") {
            val assertionMessageFormatter = mock<IAssertionMessageFormatter>()
            val testee = OnlyFailureReporter(assertionMessageFormatter)

            it("delegates to ${IAssertionMessageFormatter::class.java.simpleName}") {
                val assertion = DescriptionExpectedAssertion("to be", "0", { false })
                testee.format(sb, assertion)
                val captor = argumentCaptor<List<Pair<String,String>>>()
                verify(assertionMessageFormatter).format(captor.capture())
                val pair = captor.firstValue[0]
                assert(pair.first).toBe("to be")
            }
        }
    }
})
