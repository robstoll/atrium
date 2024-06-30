//TODO remove with 2.0.0 latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.specs

import ch.tutteli.atrium.assertions.*

const val rootBulletPoint = "◆ "
const val listBulletPoint = "• "

const val featureArrow = "▶ "
const val featureBulletPoint = "◾ "

const val successfulBulletPoint = "✔ "
const val failingBulletPoint = "✘ "

const val explanatoryBulletPoint = "» "
const val warningBulletPoint = "❗❗ "
const val informationBulletPoint = "ℹ\uFE0F "
const val hintBulletPoint ="\uD83D\uDCA1\uFE0F "
const val groupingBulletPoint = "# "

val indentRootBulletPoint = " ".repeat(rootBulletPoint.length)
val indentListBulletPoint = " ".repeat(listBulletPoint.length)

val indentFeatureArrow = " ".repeat(featureArrow.length)
val indentFeatureBulletPoint = " ".repeat(featureBulletPoint.length)

val indentSuccessfulBulletPoint = " ".repeat(successfulBulletPoint.length)
val indentFailingBulletPoint = " ".repeat(failingBulletPoint.length)

val indentWarningBulletPoint = " ".repeat(warningBulletPoint.length)
val indentGroupingBulletPointIndent = " ".repeat(groupingBulletPoint.length)

val defaultBulletPoints = mapOf(
    RootAssertionGroupType::class to rootBulletPoint,
    ListAssertionGroupType::class to listBulletPoint,
    PrefixFeatureAssertionGroupHeader::class to featureArrow,
    FeatureAssertionGroupType::class to featureBulletPoint,
    PrefixSuccessfulSummaryAssertion::class to successfulBulletPoint,
    PrefixFailingSummaryAssertion::class to failingBulletPoint,
    ExplanatoryAssertionGroupType::class to explanatoryBulletPoint,
    WarningAssertionGroupType::class to warningBulletPoint,
    InformationAssertionGroupType::class to informationBulletPoint,
    HintAssertionGroupType::class to hintBulletPoint,
    GroupingAssertionGroupType::class to groupingBulletPoint
)

