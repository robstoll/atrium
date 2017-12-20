package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.assertions.FeatureAssertionGroupType
import ch.tutteli.atrium.assertions.IBulletPointIdentifier
import ch.tutteli.atrium.assertions.IFeatureAssertionGroupType
import ch.tutteli.atrium.reporting.translating.Translator
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include

class TextFeatureAssertionGroupFormatterSpec : Spek({

    include(AtriumsTextFeatureAssertionGroupFormatterSpec)
    include(AtriumsSingleAssertionGroupTypeFormatterSpec)
    include(AtriumsAssertionFormatterSpec)

}) {
    object AtriumsTextFeatureAssertionGroupFormatterSpec : ch.tutteli.atrium.spec.reporting.TextFeatureAssertionGroupFormatterSpec(
        AssertionVerbFactory, factory(), "[Atrium's TextFeature...Spec] ")

    object AtriumsSingleAssertionGroupTypeFormatterSpec : ch.tutteli.atrium.spec.reporting.SingleAssertionGroupTypeFormatterSpec<IFeatureAssertionGroupType>(
        AssertionVerbFactory, factory(),
        IFeatureAssertionGroupType::class.java,
        object : IFeatureAssertionGroupType {},
        FeatureAssertionGroupType,
        "[Atrium's SingleAssertionGroupType...Spec] "
    )

    object AtriumsAssertionFormatterSpec : ch.tutteli.atrium.spec.reporting.AssertionFormatterSpec(
        AssertionVerbFactory, factory(), "[Atrium's AssertionFormatterSpec] ")


    companion object {
        internal fun factory() = { bulletPoints: Map<Class<out IBulletPointIdentifier>, String>, controller: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator ->
            TextFeatureAssertionGroupFormatter(bulletPoints, controller, TextSameLineAssertionPairFormatter(objectFormatter, translator))
        }
    }
}
