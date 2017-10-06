package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.AssertionVerbFactory

object TextFeatureAssertionGroupFormatterSpec : ch.tutteli.atrium.spec.reporting.TextFeatureAssertionGroupFormatterSpec(
    AssertionVerbFactory,
    { arrow, featureBulletPoint, controller, objectFormatter, translator ->
        TextFeatureAssertionGroupFormatter(arrow, featureBulletPoint, controller, TextSameLineAssertionPairFormatter(objectFormatter, translator))
    }
)
