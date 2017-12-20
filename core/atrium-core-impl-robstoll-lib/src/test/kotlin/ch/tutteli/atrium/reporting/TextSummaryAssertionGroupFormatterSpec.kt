package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.assertions.IBulletPointIdentifier
import ch.tutteli.atrium.assertions.ISummaryAssertionGroupType
import ch.tutteli.atrium.assertions.SummaryAssertionGroupType
import ch.tutteli.atrium.reporting.translating.Translator
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import ch.tutteli.atrium.spec.reporting.ToStringObjectFormatter
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include

class TextSummaryAssertionGroupFormatterSpec : Spek({

    include(AtriumsTextSummaryAssertionGroupFormatterSpec)
    include(AtriumsSingleAssertionGroupTypeFormatterSpec)
    include(AtriumsAssertionFormatterSpec)

}) {
    object AtriumsTextSummaryAssertionGroupFormatterSpec : ch.tutteli.atrium.spec.reporting.TextSummaryAssertionGroupFormatterSpec(
        AssertionVerbFactory, factoryWithoutObjectFormatter(), "[Atrium's SummaryGroup...Spec] ")

    object AtriumsSingleAssertionGroupTypeFormatterSpec : ch.tutteli.atrium.spec.reporting.SingleAssertionGroupTypeFormatterSpec<ISummaryAssertionGroupType>(
        AssertionVerbFactory, factory(),
        ISummaryAssertionGroupType::class.java,
        SummaryAssertionGroupType,
        object : ISummaryAssertionGroupType {},
        "[Atrium's SingleAssertionGroupType...Spec] "
    )

    object AtriumsAssertionFormatterSpec : ch.tutteli.atrium.spec.reporting.AssertionFormatterSpec(
        AssertionVerbFactory, factory(), "[Atrium's AssertionFormatterSpec] ")

    companion object {
        fun factoryWithoutObjectFormatter() = { bulletPoints: Map<Class<out IBulletPointIdentifier>, String>, controller: AssertionFormatterController ->
            TextSummaryAssertionGroupFormatter(bulletPoints, controller, TextSameLineAssertionPairFormatter(ToStringObjectFormatter, UsingDefaultTranslator()))
        }

        fun factory() = { bulletPoints: Map<Class<out IBulletPointIdentifier>, String>, controller: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator ->
            TextSummaryAssertionGroupFormatter(bulletPoints, controller, TextSameLineAssertionPairFormatter(objectFormatter, translator))
        }
    }
}
