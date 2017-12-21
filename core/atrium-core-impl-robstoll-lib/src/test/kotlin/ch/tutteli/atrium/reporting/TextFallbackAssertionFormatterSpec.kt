package ch.tutteli.atrium.reporting


import ch.tutteli.atrium.AssertionVerb.ASSERT
import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.api.cc.en_UK.toBe
import ch.tutteli.atrium.assert
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.DescriptionAnyAssertion.NOT_TO_BE
import ch.tutteli.atrium.assertions.DescriptionAnyAssertion.TO_BE
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

    val facade = AtriumFactory.newAssertionFormatterFacade(AtriumFactory.newAssertionFormatterController())
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
        context("a ${IAssertionGroup::class.simpleName} of type ${RootAssertionGroupType::class.simpleName}") {
            it("includes the group ${IAssertionGroup::name.name}, its ${IAssertionGroup::subject.name} as well as the ${IAssertionGroup::assertions.name}") {
                facade.format(AssertionGroupBuilder.root.create(ASSERT, "subject",listOf(
                    BasicAssertion(TO_BE, "bli", false),
                    BasicAssertion(NOT_TO_BE, "bye", false)
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
        internal fun factory() = { bulletPoints: Map<Class<out IBulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator ->
            TextFallbackAssertionFormatter(bulletPoints, assertionFormatterController, TextSameLineAssertionPairFormatter(objectFormatter, translator), objectFormatter)
        }
    }
}
