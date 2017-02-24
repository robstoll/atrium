package ch.tutteli.assertk

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it

class OnlyFailureReporterSpec : Spek({

    describe("format") {
        val sb = StringBuilder()
        val assertion = DescriptionExpectedAssertion("to be", "0", { true })
        it("does not append anything if assertion holds") {
            val testee = OnlyFailureReporter(SameLineAssertionMessageFormatter(DetailedObjectFormatter()))
            testee.format(sb, assertion)
            assert(sb).isEmpty()
        }

        context("dependencies") {
            val assertionMessageFormatter = mock<IAssertionMessageFormatter>()
            val testee = OnlyFailureReporter(assertionMessageFormatter)

            it("delegates to ${assertionMessageFormatter::class.java.simpleName}") {
                testee.format(sb, assertion)
                verify(assertionMessageFormatter).format(eq(sb), eq(assertion), any())
            }
        }
    }
})
