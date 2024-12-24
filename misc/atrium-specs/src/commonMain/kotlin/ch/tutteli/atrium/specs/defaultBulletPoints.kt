package ch.tutteli.atrium.specs

import ch.tutteli.atrium.assertions.*

const val rootBulletPoint = "◆ "
const val listBulletPoint = "• "
const val featureBulletPoint = "• "

const val s = "✔ "
const val g = "🚩\uFE0F "
const val x = "🚫\uFE0F "
const val f = "▶ "
const val i = "ℹ\uFE0F "
const val d = "🔎\uFE0F"
const val u = "💡\uFE0F "
const val bb = "❗❗ "

const val explanatoryBulletPoint = "» "
const val groupingBulletPoint = "# "

val indentRoot = " ".repeat(rootBulletPoint.length)
val indentList = " ".repeat(listBulletPoint.length)
val indentExplanatory = " ".repeat(explanatoryBulletPoint.length)

val indentFeature = " ".repeat(featureBulletPoint.length)

val indentS = " ".repeat(s.length)
val indentG = " ".repeat(3)
val indentGg = "$indentG$indentG"
val indentX = " ".repeat(3)
val indentF = " ".repeat(f.length)
val indentI = " ".repeat(3)
val indentBb = " ".repeat(4)
val indentGrouping = " ".repeat(4)

//TODO 1.3.0 remove already?
@Suppress("DEPRECATION")
val defaultBulletPoints = mapOf(
    RootAssertionGroupType::class to rootBulletPoint,
    ListAssertionGroupType::class to listBulletPoint,
    PrefixFeatureAssertionGroupHeader::class to f,
    FeatureAssertionGroupType::class to featureBulletPoint,
    PrefixSuccessfulSummaryAssertion::class to s,
    PrefixFailingSummaryAssertion::class to x,
    ExplanatoryAssertionGroupType::class to explanatoryBulletPoint,
    WarningAssertionGroupType::class to bb,
    InformationAssertionGroupType::class to i,
    HintAssertionGroupType::class to u,
    GroupingAssertionGroupType::class to groupingBulletPoint
)

