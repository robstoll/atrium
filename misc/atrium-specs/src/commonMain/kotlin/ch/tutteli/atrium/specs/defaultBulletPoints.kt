package ch.tutteli.atrium.specs

import ch.tutteli.atrium.assertions.*

const val rootBulletPoint = "◆ "
const val listBulletPoint = "• "

const val featureArrow = "▶ "
const val featureBulletPoint = "• "

const val successfulBulletPoint = "✔ "
const val g = "🚩\uFE0F "
const val x = "🚫\uFE0F "

const val explanatoryBulletPoint = "» "
const val warningBulletPoint = "❗❗ "
const val informationBulletPoint = "ℹ\uFE0F "
const val hintBulletPoint ="💡\uFE0F "
const val groupingBulletPoint = "# "
const val debugBulletPoint="🔎\uFE0F"

val indentRootBulletPoint = " ".repeat(rootBulletPoint.length)
val indentListBulletPoint = " ".repeat(listBulletPoint.length)

val indentFeatureArrow = " ".repeat(featureArrow.length)
val indentFeatureBulletPoint = " ".repeat(featureBulletPoint.length)

val indentSuccessfulBulletPoint = " ".repeat(successfulBulletPoint.length)
val indentG = " ".repeat(3)
val indentX = " ".repeat(3)

val indentWarningBulletPoint = " ".repeat(warningBulletPoint.length)
val indentGroupingBulletPointIndent = " ".repeat(groupingBulletPoint.length)

//TODO 1.3.0 remove already?
@Suppress("DEPRECATION")
val defaultBulletPoints = mapOf(
    RootAssertionGroupType::class to rootBulletPoint,
    ListAssertionGroupType::class to listBulletPoint,
    PrefixFeatureAssertionGroupHeader::class to featureArrow,
    FeatureAssertionGroupType::class to featureBulletPoint,
    PrefixSuccessfulSummaryAssertion::class to successfulBulletPoint,
    PrefixFailingSummaryAssertion::class to x,
    ExplanatoryAssertionGroupType::class to explanatoryBulletPoint,
    WarningAssertionGroupType::class to warningBulletPoint,
    InformationAssertionGroupType::class to informationBulletPoint,
    HintAssertionGroupType::class to hintBulletPoint,
    GroupingAssertionGroupType::class to groupingBulletPoint
)

