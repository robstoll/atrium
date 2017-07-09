package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.DescriptionAnyAssertion.TO_BE
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.isEmpty
import ch.tutteli.atrium.reporting.IAssertionFormatterFacade
import ch.tutteli.atrium.reporting.IReporter
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import ch.tutteli.atrium.spec.AssertionVerb
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.prefixedDescribe
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it

abstract class OnlyFailureReporterSpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: (IAssertionFormatterFacade) -> IReporter,
    describePrefix : String = "[Atrium] "
) : Spek({

    fun prefixedDescribe(description: String, body: SpecBody.() -> Unit) {
        prefixedDescribe(describePrefix, description, body)
    }

    val translator = UsingDefaultTranslator()
    val facade = AtriumFactory.newAssertionFormatterFacade(AtriumFactory.newAssertionFormatterController())
    facade.register { AtriumFactory.newTextSameLineAssertionFormatter(it, AtriumFactory.newDetailedObjectFormatter(translator), translator) }
    val testee = testeeFactory(facade)

    prefixedDescribe("fun ${testee::format.name}") {
        val sb = StringBuilder()
        val assertion = object : IAssertion {
            override fun holds() = true
        }
        val basicAssertion = BasicAssertion(TO_BE, 0, true)
        val assertionGroup = object : IAssertionGroup {
            override val type = RootAssertionGroupType
            override val name = AssertionVerb.VERB
            override val subject = 0
            override val assertions = listOf(assertion, basicAssertion)
        }

        mapOf(
            IAssertion::class.java to assertion,
            IBasicAssertion::class.java to basicAssertion,
            IAssertionGroup::class.java to assertionGroup
        ).forEach { (clazz, assertion) ->
            it("does not append anything if ${clazz.simpleName} holds") {
                testee.format(assertion, sb)
                verbs.checkLazily(sb) {
                    isEmpty()
                }
            }
        }

        context("dependencies") {
            val assertionFormatterFacade = mock<IAssertionFormatterFacade>()
            val testeeWithMockedFacade = testeeFactory(assertionFormatterFacade)

            it("delegates to ${assertionFormatterFacade::class.java.simpleName}") {
                testeeWithMockedFacade.format(basicAssertion, sb)
                verify(assertionFormatterFacade).format(eq(basicAssertion), eq(sb), any())
            }
        }
    }
})
