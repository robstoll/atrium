package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.api.cc.en_UK.isEmpty
import ch.tutteli.atrium.api.cc.en_UK.isNotEmpty
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.DescriptionAnyAssertion.TO_BE
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
    describePrefix: String = "[Atrium] "
) : Spek({

    fun prefixedDescribe(description: String, body: SpecBody.() -> Unit) {
        prefixedDescribe(describePrefix, description, body)
    }

    val translator = UsingDefaultTranslator()
    val facade = AtriumFactory.newAssertionFormatterFacade(AtriumFactory.newAssertionFormatterController())
    facade.register { AtriumFactory.newTextFallbackAssertionFormatter("[]", it, AtriumFactory.newDetailedObjectFormatter(translator), translator) }
    val testee = testeeFactory(facade)

    prefixedDescribe("fun ${testee::format.name}") {
        val sb = StringBuilder()
        val assertion = object : IAssertion {
            override fun holds() = true
        }
        val basicAssertion = BasicAssertion(TO_BE, 0, true)
        val basicAssertionAnonymous = object : IBasicAssertion {
            override val expected = 1
            override val description = AssertionVerb.VERB
            override fun holds() = true
        }

        val assertionGroupAnonymous = object : IAssertionGroup {
            override val type = RootAssertionGroupType
            override val name = AssertionVerb.VERB
            override val subject = 0
            override val assertions = listOf(assertion, basicAssertion, basicAssertionAnonymous)
        }
        val assertionGroup = AssertionGroup(RootAssertionGroupType, AssertionVerb.VERB, 1, listOf(assertion, basicAssertion, basicAssertionAnonymous, assertionGroupAnonymous))

        mapOf(
            "object: ${IAssertion::class.simpleName}" to assertion,
            "object: ${IBasicAssertion::class.simpleName}" to basicAssertionAnonymous,
            "${basicAssertion::class.simpleName}" to basicAssertion,
            "object: ${IAssertionGroup::class.simpleName}" to assertionGroupAnonymous,
            "${assertionGroup::class.simpleName}" to assertionGroup
        ).forEach { (typeRepresentation, assertion) ->
            it("does not append anything if $typeRepresentation holds") {
                testee.format(assertion, sb)
                verbs.checkImmediately(sb).isEmpty()
            }
        }

        context("${IAssertionGroup::class.simpleName} is of type ${IExplanatoryAssertionGroupType::class.simpleName}") {
            it("appends something if the assertion group holds") {
                val explanatoryAssertionGroup = AssertionGroup(ExplanatoryAssertionGroupType, AssertionVerb.VERB, 1, listOf(assertion))
                testee.format(explanatoryAssertionGroup, sb)
                verbs.checkImmediately(sb).isNotEmpty()
            }

            it("appends something if the assertion group does not hold") {
                val failingAssertion = object : IAssertion {
                    override fun holds() = false
                }
                val explanatoryAssertionGroup = AssertionGroup(ExplanatoryAssertionGroupType, AssertionVerb.VERB, 1, listOf(failingAssertion))
                testee.format(explanatoryAssertionGroup, sb)
                verbs.checkImmediately(sb).isNotEmpty()
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
