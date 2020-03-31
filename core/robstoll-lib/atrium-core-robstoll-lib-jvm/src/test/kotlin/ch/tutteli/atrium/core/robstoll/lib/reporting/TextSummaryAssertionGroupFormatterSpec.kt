package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.assertions.DefaultSummaryAssertionGroupType
import ch.tutteli.atrium.assertions.SummaryAssertionGroupType
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.translating.Translator
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import ch.tutteli.atrium.specs.reporting.ToStringObjectFormatter
import org.spekframework.spek2.Spek
import kotlin.reflect.KClass

class TextSummaryAssertionGroupFormatterSpec : Spek({

    include(AtriumsTextSummaryAssertionGroupFormatterSpec)
    include(AtriumsSingleAssertionGroupTypeFormatterSpec)
    include(AtriumsAssertionFormatterSpec)

}) {
    object AtriumsTextSummaryAssertionGroupFormatterSpec :
        ch.tutteli.atrium.specs.reporting.TextSummaryAssertionGroupFormatterSpec(
            factoryWithoutObjectFormatter(), "[Atrium's SummaryGroup...Spec] "
        )

    object AtriumsSingleAssertionGroupTypeFormatterSpec :
        ch.tutteli.atrium.specs.reporting.SingleAssertionGroupTypeFormatterSpec<SummaryAssertionGroupType>(
            factory(),
            SummaryAssertionGroupType::class,
            DefaultSummaryAssertionGroupType,
            object : SummaryAssertionGroupType {},
            "[Atrium's SingleAssertionGroupType...Spec] "
        )

    object AtriumsAssertionFormatterSpec : ch.tutteli.atrium.specs.reporting.AssertionFormatterSpec(
        factory(), "[Atrium's AssertionFormatterSpec] "
    )

    companion object {
        private fun factoryWithoutObjectFormatter() =
            { bulletPoints: Map<KClass<out BulletPointIdentifier>, String>, controller: AssertionFormatterController ->
                TextSummaryAssertionGroupFormatter(
                    bulletPoints,
                    controller,
                    TextSameLineAssertionPairFormatter(
                        ToStringObjectFormatter,
                        UsingDefaultTranslator()
                    )
                )
            }

        private fun factory() =
            { bulletPoints: Map<KClass<out BulletPointIdentifier>, String>, controller: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator ->
                TextSummaryAssertionGroupFormatter(
                    bulletPoints,
                    controller,
                    TextSameLineAssertionPairFormatter(
                        objectFormatter,
                        translator
                    )
                )
            }
    }
}
