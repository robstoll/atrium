package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.api.infix.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.assertions.RootAssertionGroupType
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.root
import ch.tutteli.atrium.reporting.impl.AssertionFormatterControllerBasedFacade
import ch.tutteli.atrium.reporting.impl.DefaultAssertionFormatterController
import ch.tutteli.atrium.reporting.text.impl.TextFallbackAssertionFormatter
import ch.tutteli.atrium.reporting.text.impl.TextSameLineAssertionPairFormatter
import ch.tutteli.atrium.specs.lineSeparator
import ch.tutteli.atrium.specs.reporting.DummyTranslation.TRANSLATION_KEY
import ch.tutteli.atrium.specs.reporting.ToStringObjectFormatter
import ch.tutteli.atrium.specs.reporting.alwaysTrueAssertionFilter
import ch.tutteli.atrium.specs.toEqualDescr
import ch.tutteli.atrium.translations.DescriptionAnyExpectation.NOT_TO_EQUAL
import ch.tutteli.atrium.translations.DescriptionAnyExpectation.TO_EQUAL
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.reflect.KClass

class TextFallbackAssertionFormatterSpec : Spek({

    include(AtriumsTextFallbackAssertionFormatterSpec)
    include(AtriumsAssertionFormatterSpec)

    val squarePoint = "▪"

    val facade = AssertionFormatterControllerBasedFacade(DefaultAssertionFormatterController())
    facade.register {
        TextFallbackAssertionFormatter(
            mapOf(RootAssertionGroupType::class to "$squarePoint "),
            it,
            TextSameLineAssertionPairFormatter(
                ToStringObjectFormatter,
            ), ToStringObjectFormatter
        )
    }

    var sb = StringBuilder()
    afterEachTest {
        sb = StringBuilder()
    }

    describe("fun ${TextFallbackAssertionFormatter::format.name}") {
        context("a ${AssertionGroup::class.simpleName} of type ${RootAssertionGroupType::class.simpleName}") {
            it("includes the group ${AssertionGroup::description.name}, its ${AssertionGroup::representation.name} as well as the ${AssertionGroup::assertions.name}") {
                val assertionGroup = with(assertionBuilder) {
                    assertionBuilder.root.withDescriptionAndRepresentation(TRANSLATION_KEY, "subject")
                        .withAssertions(
                            descriptive.failing.withDescriptionAndRepresentation(TO_EQUAL, "bli").build(),
                            descriptive.failing.withDescriptionAndRepresentation(NOT_TO_EQUAL, "bye").build()
                        )
                        .build()
                }
                expect(mapOf("1" to 2).entries)
                facade.format(assertionGroup, sb, alwaysTrueAssertionFilter)
                expect(sb.toString()) toEqual
                        "${TRANSLATION_KEY.getDefault()}: subject$lineSeparator" +
                        "$squarePoint $toEqualDescr: bli$lineSeparator" +
                        "$squarePoint ${NOT_TO_EQUAL.getDefault()}: bye"
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
            { bulletPoints: Map<KClass<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter ->
                TextFallbackAssertionFormatter(
                    bulletPoints,
                    assertionFormatterController,
                    TextSameLineAssertionPairFormatter(objectFormatter),
                    objectFormatter
                )
            }
    }
}
