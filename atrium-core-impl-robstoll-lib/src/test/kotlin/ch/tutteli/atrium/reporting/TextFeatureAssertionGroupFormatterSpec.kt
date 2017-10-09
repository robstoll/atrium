package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.reporting.translating.ITranslator
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include

class TextFeatureAssertionGroupFormatterSpec : Spek({
    include(AtriumsTextFeatureAssertionGroupFormatterSpec)
    include(AtriumsAssertionFormatterSpec)
}) {
    object AtriumsTextFeatureAssertionGroupFormatterSpec : ch.tutteli.atrium.spec.reporting.TextFeatureAssertionGroupFormatterSpec(
        AssertionVerbFactory, factory(), "[Atrium's TextFeature...Spec] ")

    object AtriumsAssertionFormatterSpec : ch.tutteli.atrium.spec.reporting.AssertionFormatterSpec(
        AssertionVerbFactory, factoryWithArrow(), "[Atrium's AssertionFormatterSpec] ")

    companion object {
        internal fun factory() = { arrow: String, featureBulletPoint: String, controller: IAssertionFormatterController, objectFormatter: IObjectFormatter, translator: ITranslator ->
            TextFeatureAssertionGroupFormatter(arrow, featureBulletPoint, controller, TextSameLineAssertionPairFormatter(objectFormatter, translator))
        }

        internal fun factoryWithArrow() = { assertionFormatterController: IAssertionFormatterController, objectFormatter: IObjectFormatter, translator: ITranslator ->
            factory()("=>", "▪", assertionFormatterController, objectFormatter, translator)
        }
    }
}
