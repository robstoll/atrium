package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.assertions.DefaultFeatureAssertionGroupType
import ch.tutteli.atrium.assertions.FeatureAssertionGroupType
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.translating.Translator
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include

class TextFeatureAssertionGroupFormatterSpec : Spek({

    include(AtriumsTextFeatureAssertionGroupFormatterSpec)
    include(AtriumsSingleAssertionGroupTypeFormatterSpec)
    include(AtriumsAssertionFormatterSpec)

}) {
    object AtriumsTextFeatureAssertionGroupFormatterSpec : ch.tutteli.atrium.spec.reporting.TextFeatureAssertionGroupFormatterSpec(
        AssertionVerbFactory,
        factory(), "[Atrium's TextFeature...Spec] ")

    object AtriumsSingleAssertionGroupTypeFormatterSpec : ch.tutteli.atrium.spec.reporting.SingleAssertionGroupTypeFormatterSpec<FeatureAssertionGroupType>(
        AssertionVerbFactory,
        factory(),
        FeatureAssertionGroupType::class.java,
        object : FeatureAssertionGroupType {},
        DefaultFeatureAssertionGroupType,
        "[Atrium's SingleAssertionGroupType...Spec] "
    )

    object AtriumsAssertionFormatterSpec : ch.tutteli.atrium.spec.reporting.AssertionFormatterSpec(
        AssertionVerbFactory,
        factory(), "[Atrium's AssertionFormatterSpec] ")


    companion object {
        internal fun factory() = { bulletPoints: Map<Class<out BulletPointIdentifier>, String>, controller: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator ->
            TextFeatureAssertionGroupFormatter(
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
