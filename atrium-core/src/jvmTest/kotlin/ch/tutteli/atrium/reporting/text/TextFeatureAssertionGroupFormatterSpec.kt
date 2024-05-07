package ch.tutteli.atrium.reporting.text

import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.assertions.DefaultFeatureAssertionGroupType
import ch.tutteli.atrium.assertions.FeatureAssertionGroupType
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.text.impl.TextFeatureAssertionGroupFormatter
import ch.tutteli.atrium.reporting.text.impl.TextSameLineAssertionPairFormatter
import org.spekframework.spek2.Spek
import kotlin.reflect.KClass

class TextFeatureAssertionGroupFormatterSpec : Spek({

    include(AtriumsTextFeatureAssertionGroupFormatterSpec)
    include(AtriumsSingleAssertionGroupTypeFormatterSpec)
    include(AtriumsAssertionFormatterSpec)

}) {
    object AtriumsTextFeatureAssertionGroupFormatterSpec :
        ch.tutteli.atrium.specs.reporting.TextFeatureAssertionGroupFormatterSpec(
            factory(), "[Atrium's TextFeature...Spec] "
        )

    object AtriumsSingleAssertionGroupTypeFormatterSpec :
        ch.tutteli.atrium.specs.reporting.SingleAssertionGroupTypeFormatterSpec<FeatureAssertionGroupType>(
            factory(),
            FeatureAssertionGroupType::class,
            object : FeatureAssertionGroupType {},
            DefaultFeatureAssertionGroupType,
            "[Atrium's SingleAssertionGroupType...Spec] "
        )

    object AtriumsAssertionFormatterSpec : ch.tutteli.atrium.specs.reporting.AssertionFormatterSpec(
        factory(), "[Atrium's AssertionFormatterSpec] "
    )


    companion object {
        internal fun factory() =
            { bulletPoints: Map<KClass<out BulletPointIdentifier>, String>, controller: AssertionFormatterController, objectFormatter: ObjectFormatter ->
                TextFeatureAssertionGroupFormatter(
                    bulletPoints,
                    controller,
                    TextSameLineAssertionPairFormatter(
                        objectFormatter,
                    )
                )
            }
    }
}
