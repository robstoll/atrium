package ch.tutteli.atrium.specs.reporting

import ch.tutteli.atrium.api.fluent.en_GB.isEmpty
import ch.tutteli.atrium.api.fluent.en_GB.toBeEmpty
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.assertions.RootAssertionGroupType
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.root
import ch.tutteli.atrium.reporting.AssertionFormatterFacade
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.impl.AssertionFormatterControllerBasedFacade
import ch.tutteli.atrium.reporting.impl.DefaultAssertionFormatterController
import ch.tutteli.atrium.reporting.text.TextAssertionPairFormatter
import ch.tutteli.atrium.reporting.text.impl.DefaultTextObjectFormatter
import ch.tutteli.atrium.reporting.text.impl.TextFallbackAssertionFormatter
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import ch.tutteli.atrium.specs.AssertionVerb
import ch.tutteli.atrium.specs.describeFunTemplate
import ch.tutteli.atrium.translations.DescriptionAnyExpectation
import ch.tutteli.atrium.translations.DescriptionAnyExpectation.TO_EQUAL
import io.mockk.*
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class OnlyFailureReporterSpec(
    testeeFactory: (AssertionFormatterFacade) -> Reporter,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val translator = UsingDefaultTranslator()
    val objectFormatter = DefaultTextObjectFormatter(translator)

    val facade = AssertionFormatterControllerBasedFacade(DefaultAssertionFormatterController())
    facade.register {
        TextFallbackAssertionFormatter(
            mapOf(RootAssertionGroupType::class to "[]"),
            it,
            TextAssertionPairFormatter.newSameLine(objectFormatter, translator),
            objectFormatter
        )
    }
    val testee = testeeFactory(facade)

    describeFun(testee::format.name) {
        val sb = StringBuilder()
        val assertion = object : Assertion {
            override fun holds() = true
        }
        val basicAssertion = assertionBuilder.descriptive
            .holding
            .withDescriptionAndRepresentation(TO_EQUAL, 0)
            .build()

        val basicAssertionAnonymous = object : DescriptiveAssertion {
            override val representation = 1
            override val description = AssertionVerb.VERB
            override fun holds() = true
        }

        val assertionGroupAnonymous = object : AssertionGroup {
            override val type = RootAssertionGroupType
            override val description = AssertionVerb.VERB
            override val representation = 0
            override val assertions = listOf(assertion, basicAssertion, basicAssertionAnonymous)
        }
        val assertionGroup = assertionBuilder.root
            .withDescriptionAndRepresentation(AssertionVerb.VERB, 1)
            .withAssertions(listOf(assertion, basicAssertion, basicAssertionAnonymous, assertionGroupAnonymous))
            .build()

        mapOf(
            "object: ${Assertion::class.simpleName}" to assertion,
            "object: ${DescriptiveAssertion::class.simpleName}" to basicAssertionAnonymous,
            "${basicAssertion::class.simpleName}" to basicAssertion,
            "object: ${AssertionGroup::class.simpleName}" to assertionGroupAnonymous,
            "${assertionGroup::class.simpleName}" to assertionGroup
        ).forEach { (typeRepresentation, assertion) ->
            it("does not append anything if $typeRepresentation holds") {
                testee.format(assertion, sb)
                expect(sb).toBeEmpty()
            }
        }

        context("dependencies") {
            val assertionFormatterFacade = mockk<AssertionFormatterFacade>()
            every { assertionFormatterFacade.format(any(), any(), any()) } just Runs

            val testeeWithMockedFacade = testeeFactory(assertionFormatterFacade)

            it("delegates to ${assertionFormatterFacade::class.simpleName}") {
                testeeWithMockedFacade.format(basicAssertion, sb)
                verify { assertionFormatterFacade.format(eq(basicAssertion), eq(sb), any()) }
            }
        }
    }
})
