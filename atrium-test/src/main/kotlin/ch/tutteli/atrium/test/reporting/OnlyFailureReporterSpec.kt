package ch.tutteli.atrium.test.reporting

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.creating.AtriumFactory
import ch.tutteli.atrium.isEmpty
import ch.tutteli.atrium.reporting.IAssertionMessageFormatter
import ch.tutteli.atrium.reporting.IReporter
import ch.tutteli.atrium.test.IAssertionVerbFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

open class OnlyFailureReporterSpec(
    val verbs: IAssertionVerbFactory,
    val testeeFactory: (IAssertionMessageFormatter) -> IReporter
) : Spek({

    describe("format") {
        val sb = StringBuilder()
        val assertion = object : IAssertion {
            override fun holds() = true
        }
        val oneMessageAssertion = OneMessageAssertion("to be", 0, true)
        val multiMessageAssertion = object : IMultiMessageAssertion {
            override val messages = listOf(Message("to be", 0, true), Message("to be", 0, true))
        }

        mapOf(
            IAssertion::class.java to assertion,
            IOneMessageAssertion::class.java to oneMessageAssertion,
            IMultiMessageAssertion::class.java to multiMessageAssertion,
            IAssertionGroup::class.java to AssertionGroup("groupName", 0, listOf(assertion, oneMessageAssertion, multiMessageAssertion))
        ).forEach { clazz, assertion ->
            it("does not append anything if ${clazz.simpleName} holds") {
                val testee = testeeFactory(AtriumFactory.newSameLineAssertionMessageFormatter(AtriumFactory.newDetailedObjectFormatter()))
                testee.format(sb, assertion)
                verbs.checkLazily(sb) {
                    isEmpty()
                }
            }
        }

        context("dependencies") {
            val assertionMessageFormatter = mock<IAssertionMessageFormatter>()
            val testee = testeeFactory(assertionMessageFormatter)

            it("delegates to ${IAssertionMessageFormatter::class.java.simpleName}") {
                testee.format(sb, oneMessageAssertion)
                verify(assertionMessageFormatter).format(eq(sb), eq(oneMessageAssertion), any(), any())
            }
        }
    }
})
