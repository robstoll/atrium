package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.assertions.RootAssertionGroupType
import ch.tutteli.atrium.assertions.builders.root
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.translating.Translator
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import ch.tutteli.atrium.specs.lineSeperator
import ch.tutteli.atrium.specs.reporting.ToStringObjectFormatter
import ch.tutteli.atrium.specs.reporting.alwaysTrueAssertionFilter
import ch.tutteli.atrium.translations.DescriptionBasic.NOT_TO_BE
import ch.tutteli.atrium.translations.DescriptionBasic.TO_BE
import ch.tutteli.atrium.api.verbs.internal.AssertionVerb.EXPECT
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.include
import kotlin.reflect.KClass

//TODO #116 migrate spek1 to spek2 - move to common module
class TextFallbackAssertionFormatterSpec : Spek({

    include(AtriumsTextFallbackAssertionFormatterSpec)
    include(AtriumsAssertionFormatterSpec)

    val squarePoint = "▪"

    val facade = coreFactory.newAssertionFormatterFacade(coreFactory.newAssertionFormatterController())
    facade.register {
        TextFallbackAssertionFormatter(
            mapOf(RootAssertionGroupType::class to "$squarePoint "),
            it,
            TextSameLineAssertionPairFormatter(
                ToStringObjectFormatter,
                UsingDefaultTranslator()
            ), ToStringObjectFormatter
        )
    }

    var sb = StringBuilder()
    afterEachTest {
        sb = StringBuilder()
    }
    val separator = lineSeperator

    describe("fun ${TextFallbackAssertionFormatter::format.name}") {
        context("a ${AssertionGroup::class.simpleName} of type ${RootAssertionGroupType::class.simpleName}") {
            it("includes the group ${AssertionGroup::description.name}, its ${AssertionGroup::representation.name} as well as the ${AssertionGroup::assertions.name}") {
                val assertionGroup = with(AssertImpl.builder) {
                    root.withDescriptionAndRepresentation(EXPECT, "subject")
                        .withAssertions(
                            descriptive.failing.withDescriptionAndRepresentation(TO_BE, "bli").build(),
                            descriptive.failing.withDescriptionAndRepresentation(NOT_TO_BE, "bye").build()
                        )
                        .build()
                }
                expect(mapOf("1" to 2).entries)
                facade.format(assertionGroup, sb, alwaysTrueAssertionFilter)
                expect(sb.toString()).toBe(
                    "${EXPECT.getDefault()}: subject$separator" +
                        "$squarePoint ${TO_BE.getDefault()}: bli$separator" +
                        "$squarePoint ${NOT_TO_BE.getDefault()}: bye"
                )
            }
        }
    }
}) {
    object AtriumsTextFallbackAssertionFormatterSpec :
        ch.tutteli.atrium.specs.reporting.TextFallbackAssertionFormatterSpec(
            factory(), "[Atrium's TextFallback..Spec] "
        )

    object AtriumsAssertionFormatterSpec : ch.tutteli.atrium.specs.reporting.AssertionFormatterSpec(
        factory(), "[Atrium's AssertionFormatterSpec] "
    )

    companion object {
        internal fun factory() =
            { bulletPoints: Map<KClass<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator ->
                TextFallbackAssertionFormatter(
                    bulletPoints,
                    assertionFormatterController,
                    TextSameLineAssertionPairFormatter(objectFormatter, translator),
                    objectFormatter
                )
            }
    }
}
