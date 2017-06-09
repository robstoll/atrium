package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.DescriptionAnyAssertion.TO_BE
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.isEmpty
import ch.tutteli.atrium.reporting.IAssertionFormatter
import ch.tutteli.atrium.reporting.IReporter
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import ch.tutteli.atrium.spec.AssertionVerb
import ch.tutteli.atrium.spec.IAssertionVerbFactory
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
    val testeeFactory: (IAssertionFormatter) -> IReporter
) : Spek({

    describe("format") {
        val sb = StringBuilder()
        val assertion = object : IAssertion {
            override fun holds() = true
        }
        val oneMessageAssertion = object : IOneMessageAssertion {
            override val message = Message(TO_BE, 0, true)
        }
        val multiMessageAssertion = object : IMultiMessageAssertion {
            override val messages = listOf(Message(TO_BE, 0, true), Message(TO_BE, 0, true))
        }
        val assertionGroup = object : IAssertionGroup {
            override val name = AssertionVerb.VERB
            override val subject = 0
            override val assertions = listOf(assertion, oneMessageAssertion, multiMessageAssertion)
        }

        mapOf(
            IAssertion::class.java to assertion,
            IOneMessageAssertion::class.java to oneMessageAssertion,
            IMultiMessageAssertion::class.java to multiMessageAssertion,
            IAssertionGroup::class.java to assertionGroup
        ).forEach { (clazz, assertion) ->
            it("does not append anything if ${clazz.simpleName} holds") {
                val translator = UsingDefaultTranslator()
                val testee = testeeFactory(AtriumFactory.newSameLineAssertionFormatter(AtriumFactory.newDetailedObjectFormatter(translator), translator))
                testee.format(sb, assertion)
                verbs.checkLazily(sb) {
                    isEmpty()
                }
            }
        }

        context("dependencies") {
            val assertionMessageFormatter = mock<IAssertionFormatter>()
            val testee = testeeFactory(assertionMessageFormatter)

            it("delegates to ${IAssertionFormatter::class.java.simpleName}") {
                testee.format(sb, oneMessageAssertion)
                verify(assertionMessageFormatter).format(eq(sb), eq(oneMessageAssertion), any(), any())
            }
        }
    }
})
