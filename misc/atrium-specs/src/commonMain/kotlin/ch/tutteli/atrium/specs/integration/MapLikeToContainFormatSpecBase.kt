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

        val emptyInOrderOnlyReportOptions: InOrderOnlyReportingOptions.() -> Unit = {}

        fun Expect<String>.toContainSize(actual: Int, expected: Int) =
            toContain.exactly(1)
                .regex("\\Q$g$f$sizeDescr\\E\\s+: $actual[^:]+$toEqualDescr : $expected")

        val toContainInAnyOrderDescr =
            DescriptionMapLikeProof.IN_ANY_ORDER.string.format(DescriptionMapLikeProof.TO_CONTAIN.string)

        val toContainInAnyOrderOnlyDescr =
            DescriptionMapLikeProof.IN_ANY_ORDER_ONLY.string.format(DescriptionMapLikeProof.TO_CONTAIN.string)

        val toContainInOrderOnlyDescr =
            DescriptionMapLikeProof.IN_ORDER_ONLY.string.format(DescriptionMapLikeProof.TO_CONTAIN.string)


        fun Expect<String>.toContainInAnyOrderOnlyDescr(): Expect<String> {
            return toContain.exactly(1).value("$g$toContainInAnyOrderOnlyDescr :")
        }

        fun Expect<String>.toContainInOrderOnlyDescr() =
            toContain.exactly(1).value("$g$toContainInOrderOnlyDescr :")

        fun Expect<String>.entrySuccess(key: String, actual: Any?, expected: String): Expect<String> {
            return this.toContain.exactly(1).regex(
                "$indentG\\Q$s$f\\E${entry(key)}\\s+: $actual$lineSeparator" +
                    "$indentG$indentS$indentF$s$expected"
            )
        }

        fun Expect<String>.entryFailing(key: String?, actual: Any?, expected: String): Expect<String> {
            return this.toContain.exactly(1).regex(
                //TODO 1.3.0 should be $x and not $g
                "$indentG\\Q$g$f\\E${entry(key)}\\s+: $actual$lineSeparator" +
                    "$indentG$indentX$indentF$x$expected"
            )
        }

        fun Expect<String>.entryFailingExplaining(key: String?, actual: Any?, expected: String): Expect<String> {
            return this.toContain.exactly(1).regex(
                //TODO 1.3.0 should be $x and not $g
                "$indentG\\Q$g$f\\E${entry(key)}\\s+: $actual$lineSeparator" +
                    "$indentG$indentX$indentF$explanatoryBulletPoint$expected"
            )
        }

        fun Expect<String>.entryNonExisting(key: String, expected: String): Expect<String> {
            return this.toContain.exactly(1).regex(
                //TODO 1.3.0 should be $x and not $g
                "$indentG\\Q$g$f\\E${entry(key)}\\s+: $keyDoesNotExist$lineSeparator" +
                    "$indentG$indentX$indentF$explanatoryBulletPoint$expected"
            )
        }

        fun Expect<String>.additionalEntries(vararg pairs: Pair<String?, Any>): Expect<String> =
            and {
                val additionalEntries =
                    "$indentG\\Q$bb${DescriptionMapLikeProof.WARNING_ADDITIONAL_ENTRIES.string}\\E : $lineSeparator"
                toContain.exactly(1).regex(additionalEntries)
                pairs.forEach { (key, value) ->
                    toContain.exactly(1)
                        .regex(additionalEntries + "(.|$lineSeparator)+$listBulletPoint${entry(key, value)}")
                }
            }


        fun Expect<String>.notToContainEntry(key: String): Expect<String> =
            notToContain.regex("\\Q${DescriptionIterableLikeProof.ELEMENT_WITH_INDEX.string}.*$key=\\E")

        fun Expect<String>.element(
            indexBulletPoint: String,
            indentIndex: String,
            keyBulletPoint: String,
            indentKey: String,
            keySubBulletPoint: String,
            valueBulletPoint: String,
            indentValue: String,
            valueSubBulletPoint: String,
            index: Int,
            actual: Any,
            expectedKey: String?,
            expectedValue: String,
            withKey: Boolean = true,
            withValue: Boolean = true
        ): Expect<String> {
            val indentToKeyValue = "$indentG$indentIndex$indentF"

            return this.toContain.exactly(1).regex(
                "$indentG\\Q$indexBulletPoint$f${IterableToContainSpecBase.elementWithIndex(index)} : $actual\\E.*$lineSeparator" +
                    (if (withKey) "$indentToKeyValue$keyBulletPoint${f}key\\s+:.*$lineSeparator" +
                        "$indentToKeyValue$indentKey$indentF$keySubBulletPoint$toEqualDescr : ${if (expectedKey == null) "null" else "\"$expectedKey\""}.*$lineSeparator"
                    else "") +
                    (if (withValue) "$indentToKeyValue$valueBulletPoint${f}value\\s+:.*$lineSeparator" +
                        "$indentToKeyValue$indentValue$indentF$valueSubBulletPoint$expectedValue"
                    else "")
            )
        }

    }
}
