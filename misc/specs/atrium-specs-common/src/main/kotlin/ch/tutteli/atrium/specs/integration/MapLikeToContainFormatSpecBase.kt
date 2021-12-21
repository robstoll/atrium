package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionCollectionExpectation
import ch.tutteli.atrium.translations.DescriptionMapLikeAssertion
import org.spekframework.spek2.dsl.Root

abstract class MapLikeToContainFormatSpecBase(spec: Root.() -> Unit) : MapLikeToContainSpecBase(spec) {

    companion object {
        val sizeDescr = DescriptionCollectionExpectation.SIZE.getDefault()
        val additionalEntriesDescr = DescriptionMapLikeAssertion.WARNING_ADDITIONAL_ENTRIES.getDefault()

        fun Expect<String>.toContainSize(actual: Int, expected: Int) =
            toContain.exactly(1)
                .regex("\\Q$rootBulletPoint$featureArrow$sizeDescr\\E: $actual[^:]+${toEqualDescr}: $expected")


        fun Expect<String>.toContainInAnyOrderOnlyDescr() =
            toContain.exactly(1).value(
                "$rootBulletPoint${
                    DescriptionMapLikeAssertion.IN_ANY_ORDER_ONLY.getDefault()
                        .format(DescriptionMapLikeAssertion.CONTAINS.getDefault())
                }:"
            )


        fun Expect<String>.toContainInOrderOnlyDescr() =
            toContain.exactly(1).value(
                "$rootBulletPoint${
                    DescriptionMapLikeAssertion.IN_ORDER_ONLY.getDefault()
                        .format(DescriptionMapLikeAssertion.CONTAINS.getDefault())
                }:"
            )

        fun Expect<String>.entrySuccess(key: String, actual: Any?, expected: String): Expect<String> {
            return this.toContain.exactly(1).regex(
                "$indentRootBulletPoint\\Q$successfulBulletPoint$featureArrow${entry(key)}: $actual\\E.*${separator}" +
                    "$indentRootBulletPoint$indentSuccessfulBulletPoint$indentFeatureArrow$featureBulletPoint$expected"
            )
        }

        fun Expect<String>.entryFailing(key: String?, actual: Any?, expected: String): Expect<String> {
            return this.toContain.exactly(1).regex(
                "\\Q$failingBulletPoint$featureArrow${entry(key)}: $actual\\E.*${separator}" +
                    "$indentRootBulletPoint$indentSuccessfulBulletPoint$indentFeatureArrow$featureBulletPoint$expected"
            )
        }

        fun Expect<String>.entryFailingExplaining(key: String?, actual: Any?, expected: String): Expect<String> {
            return this.toContain.exactly(1).regex(
                "\\Q$failingBulletPoint$featureArrow${entry(key)}: $actual\\E.*${separator}" +
                    "$indentRootBulletPoint$indentSuccessfulBulletPoint$indentFeatureArrow$indentFeatureBulletPoint$explanatoryBulletPoint$expected"
            )
        }

        fun Expect<String>.entryNonExisting(key: String, expected: String): Expect<String> {
            return this.toContain.exactly(1).regex(
                "\\Q$failingBulletPoint$featureArrow${entry(key)}: $keyDoesNotExist\\E.*${separator}" +
                    "$indentRootBulletPoint$indentSuccessfulBulletPoint$indentFeatureArrow$indentFeatureBulletPoint$explanatoryBulletPoint$expected"
            )
        }

        fun Expect<String>.additionalEntries(vararg pairs: Pair<String?, Any>): Expect<String> =
            and {
                val additionalEntries =
                    "\\Q$warningBulletPoint${DescriptionMapLikeAssertion.WARNING_ADDITIONAL_ENTRIES.getDefault()}\\E: $separator"
                toContain.exactly(1).regex(additionalEntries)
                pairs.forEach { (key, value) ->
                    toContain.exactly(1)
                        .regex(additionalEntries + "(.|$separator)+$listBulletPoint${entry(key, value)}")
                }
            }
    }
}
