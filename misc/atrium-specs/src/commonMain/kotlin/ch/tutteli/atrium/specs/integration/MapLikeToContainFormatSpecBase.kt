package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCollectionProof
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionMapLikeProof
import ch.tutteli.atrium.specs.*
import org.spekframework.spek2.dsl.Root

abstract class MapLikeToContainFormatSpecBase(spec: Root.() -> Unit) : MapLikeToContainSpecBase(spec) {

    companion object {
        val sizeDescr = DescriptionCollectionProof.SIZE.string
        val additionalEntriesDescr = DescriptionMapLikeProof.WARNING_ADDITIONAL_ENTRIES.string

        val emptyInOrderOnlyReportOptions : InOrderOnlyReportingOptions.() -> Unit = {}

        fun Expect<String>.toContainSize(actual: Int, expected: Int) =
            toContain.exactly(1)
                .regex("\\Q$rootBulletPoint$f$sizeDescr\\E: $actual[^:]+${toEqualDescr}: $expected")


        fun Expect<String>.toContainInAnyOrderOnlyDescr() =
            toContain.exactly(1).value(
                "$rootBulletPoint${
                    DescriptionMapLikeProof.IN_ANY_ORDER_ONLY.string
                        .format(DescriptionMapLikeProof.TO_CONTAIN.string)
                }:"
            )


        fun Expect<String>.toContainInOrderOnlyDescr() =
            toContain.exactly(1).value(
                "$rootBulletPoint${
                    DescriptionIterableLikeProof.IN_ORDER_ONLY.string
                        .format(DescriptionIterableLikeProof.TO_CONTAIN.string)
                }:"
            )

        fun Expect<String>.entrySuccess(key: String, actual: Any?, expected: String): Expect<String> {
            return this.toContain.exactly(1).regex(
                "$indentRoot\\Q$s$f${entry(key)}: $actual\\E.*$lineSeparator" +
                        "$indentRoot$indentSuccess$indentF$featureBulletPoint$expected"
            )
        }

        fun Expect<String>.entryFailing(key: String?, actual: Any?, expected: String): Expect<String> {
            return this.toContain.exactly(1).regex(
                "\\Q$x$f${entry(key)}: $actual\\E.*$lineSeparator" +
                        "$indentRoot$indentSuccess$indentF$featureBulletPoint$expected"
            )
        }

        fun Expect<String>.entryFailingExplaining(key: String?, actual: Any?, expected: String): Expect<String> {
            return this.toContain.exactly(1).regex(
                "\\Q$x$f${entry(key)}: $actual\\E.*$lineSeparator" +
                        "$indentRoot$indentSuccess$indentF$indentFeature$explanatoryBulletPoint$expected"
            )
        }

        fun Expect<String>.entryNonExisting(key: String, expected: String): Expect<String> {
            return this.toContain.exactly(1).regex(
                "\\Q$x$f${entry(key)}: $keyDoesNotExist\\E.*$lineSeparator" +
                        "$indentRoot$indentSuccess$indentF$indentFeature$explanatoryBulletPoint$expected"
            )
        }

        fun Expect<String>.additionalEntries(vararg pairs: Pair<String?, Any>): Expect<String> =
            and {
                val additionalEntries =
                    "\\Q$bb${DescriptionMapLikeProof.WARNING_ADDITIONAL_ENTRIES.string}\\E: $lineSeparator"
                toContain.exactly(1).regex(additionalEntries)
                pairs.forEach { (key, value) ->
                    toContain.exactly(1)
                        .regex(additionalEntries + "(.|$lineSeparator)+$listBulletPoint${entry(key, value)}")
                }
            }


        fun Expect<String>.notToContainEntry(key: String): Expect<String> =
            notToContain.regex("\\Q${DescriptionIterableLikeProof.ELEMENT_WITH_INDEX.string}.*$key=\\E")

        fun Expect<String>.element(
            successFailureBulletPoint: String,
            index: Int,
            actual: Any,
            expectedKey: String?,
            expectedValue: String,
            explaining: Boolean = false,
            explainingValue: Boolean = false,
            withBulletPoint: Boolean = true,
            withKey: Boolean = true,
            withValue: Boolean = true
        ): Expect<String> {
            val indent = " ".repeat(successFailureBulletPoint.length)
            val keyValueBulletPoint = if (explaining) explanatoryBulletPoint else featureBulletPoint
            val indentKeyValueBulletPoint = " ".repeat(keyValueBulletPoint.length)
            val indentToKeyValue =
                "$indentRoot$indent$indentF" + (if (explaining) indentFeature else "")

            return this.toContain.exactly(1).regex(
                "\\Q${if (withBulletPoint) successFailureBulletPoint else ""}$f${
                    IterableToContainSpecBase.elementWithIndex(
                        index
                    )
                }: $actual\\E.*$lineSeparator" +
                        (if (withKey) "$indentToKeyValue$keyValueBulletPoint${f}key:.*$lineSeparator" +
                                "$indentToKeyValue$indentKeyValueBulletPoint$indentF$featureBulletPoint$toEqualDescr: ${if (expectedKey == null) "null" else "\"$expectedKey\""}.*$lineSeparator"
                        else "") +
                    (if (withValue) "$indentToKeyValue$keyValueBulletPoint${f}value:.*$lineSeparator" +
                            "$indentToKeyValue$indentKeyValueBulletPoint$indentF${if (explainingValue) "$indentFeature$explanatoryBulletPoint" else featureBulletPoint}$expectedValue"
                    else "")
            )
        }

    }
}
