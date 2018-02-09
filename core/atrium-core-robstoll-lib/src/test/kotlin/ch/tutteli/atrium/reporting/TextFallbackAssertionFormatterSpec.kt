package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.AssertionVerb.ASSERT
import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.CoreFactory
import ch.tutteli.atrium.api.cc.en_UK.toBe
import ch.tutteli.atrium.assert
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.assertions.DescriptionAnyAssertion.NOT_TO_BE
import ch.tutteli.atrium.assertions.DescriptionAnyAssertion.TO_BE
import ch.tutteli.atrium.assertions.RootAssertionGroupType
import ch.tutteli.atrium.assertions.builders.AssertionBuilder
import ch.tutteli.atrium.assertions.builders.root
import ch.tutteli.atrium.reporting.translating.Translator
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import ch.tutteli.atrium.spec.reporting.ToStringObjectFormatter
import ch.tutteli.atrium.spec.reporting.alwaysTrueAssertionFilter
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.include

class TextFallbackAssertionFormatterSpec : Spek({

    include(AtriumsTextFallbackAssertionFormatterSpec)
    include(AtriumsAssertionFormatterSpec)

    val squarePoint = "▪"

    val facade = CoreFactory.newAssertionFormatterFacade(CoreFactory.newAssertionFormatterController())
    facade.register({
        TextFallbackAssertionFormatter(
            mapOf(RootAssertionGroupType::class.java to "$squarePoint "),
            it,
            TextSameLineAssertionPairFormatter(ToStringObjectFormatter, UsingDefaultTranslator()), ToStringObjectFormatter)
    })

    var sb = StringBuilder()
    afterEachTest {
        sb = StringBuilder()
    }
    val separator = System.getProperty("line.separator")!!

    describe("fun ${TextFallbackAssertionFormatter::format.name}") {
        context("a ${AssertionGroup::class.simpleName} of type ${RootAssertionGroupType::class.simpleName}") {
            it("includes the group ${AssertionGroup::name.name}, its ${AssertionGroup::subject.name} as well as the ${AssertionGroup::assertions.name}") {
                facade.format(
                    AssertionBuilder.root.create(ASSERT, "subject",listOf(
                        AssertionBuilder.descriptive.create(TO_BE, "bli", false),
                        AssertionBuilder.descriptive.create(NOT_TO_BE, "bye", false)
                )), sb, alwaysTrueAssertionFilter)
                assert(sb.toString()).toBe("assert: subject$separator" +
                    "$squarePoint ${TO_BE.getDefault()}: bli$separator" +
                    "$squarePoint ${NOT_TO_BE.getDefault()}: bye")
            }
        }
    }
}) {
    object AtriumsTextFallbackAssertionFormatterSpec : ch.tutteli.atrium.spec.reporting.TextFallbackAssertionFormatterSpec(
        AssertionVerbFactory, factory(), "[Atrium's TextFallback..Spec] "
    )

    object AtriumsAssertionFormatterSpec : ch.tutteli.atrium.spec.reporting.AssertionFormatterSpec(
        AssertionVerbFactory, factory(), "[Atrium's AssertionFormatterSpec] "
    )

    companion object {
        internal fun factory() = { bulletPoints: Map<Class<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator ->
            TextFallbackAssertionFormatter(bulletPoints, assertionFormatterController, TextSameLineAssertionPairFormatter(objectFormatter, translator), objectFormatter)
        }
    }
}
