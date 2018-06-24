package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.assertions.DefaultSummaryAssertionGroupType
import ch.tutteli.atrium.assertions.SummaryAssertionGroupType
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.translating.Translator
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import ch.tutteli.atrium.spec.reporting.ToStringObjectFormatter
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include
import kotlin.reflect.KClass

class TextSummaryAssertionGroupFormatterSpec : Spek({

    include(AtriumsTextSummaryAssertionGroupFormatterSpec)
    include(AtriumsSingleAssertionGroupTypeFormatterSpec)
    include(AtriumsAssertionFormatterSpec)

}) {
    object AtriumsTextSummaryAssertionGroupFormatterSpec : ch.tutteli.atrium.spec.reporting.TextSummaryAssertionGroupFormatterSpec(
        AssertionVerbFactory,
        factoryWithoutObjectFormatter(), "[Atrium's SummaryGroup...Spec] ")

    object AtriumsSingleAssertionGroupTypeFormatterSpec : ch.tutteli.atrium.spec.reporting.SingleAssertionGroupTypeFormatterSpec<SummaryAssertionGroupType>(
        AssertionVerbFactory,
        factory(),
        SummaryAssertionGroupType::class,
        DefaultSummaryAssertionGroupType,
        object : SummaryAssertionGroupType {},
        "[Atrium's SingleAssertionGroupType...Spec] "
    )

    object AtriumsAssertionFormatterSpec : ch.tutteli.atrium.spec.reporting.AssertionFormatterSpec(
        AssertionVerbFactory,
        factory(), "[Atrium's AssertionFormatterSpec] ")

    companion object {
        fun factoryWithoutObjectFormatter() = { bulletPoints: Map<KClass<out BulletPointIdentifier>, String>, controller: AssertionFormatterController ->
            TextSummaryAssertionGroupFormatter(
                bulletPoints,
                controller,
                TextSameLineAssertionPairFormatter(
                    ToStringObjectFormatter,
                    UsingDefaultTranslator()
                )
            )
        }

        fun factory() = { bulletPoints: Map<KClass<out BulletPointIdentifier>, String>, controller: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator ->
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
