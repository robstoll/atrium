package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.*

/**
 * Represents an [AssertionFormatter] which formats [AssertionGroup]s with a [SummaryAssertionGroupType] by
 * using the given [assertionPairFormatter] to format the group header and uses the bullet point defined
 * for [PrefixSuccessfulSummaryAssertion] as prefix for successful [AssertionGroup.assertions] and the bullet point
 * defined for [PrefixFeatureAssertionGroupHeader] as prefix for failing [AssertionGroup.assertions].
 *
 * Its usage is intended for text output (e.g. to the console).
 *
 * @constructor Represents an [AssertionFormatter] which formats [AssertionGroup]s with an
 *   [SummaryAssertionGroupType] by using the given [assertionPairFormatter] to format the group header and uses the
 * bullet point defined for [PrefixSuccessfulSummaryAssertion] as prefix for successful [AssertionGroup.assertions]
 * and the bullet point defined for [PrefixFeatureAssertionGroupHeader] as prefix for failing [AssertionGroup.assertions].
 *
 * @param bulletPoints The formatter uses the bullet point defined for [PrefixSuccessfulSummaryAssertion]
 *   (`"✔ "` if absent) as prefix for successful assertions in [AssertionGroup.assertions] and the bullet point defined
 * for [PrefixFailingSummaryAssertion] (`"✘ "` if absent) for failing assertions.
 *
 * @param assertionFormatterController The controller to which this formatter gives back the control
 *   when it comes to format children of an [AssertionGroup].
 * @param assertionPairFormatter The formatter which is used to format assertion pairs.
 */
class TextSummaryAssertionGroupFormatter(
    bulletPoints: Map<Class<out BulletPointIdentifier>, String>,
    private val assertionFormatterController: AssertionFormatterController,
    private val assertionPairFormatter: AssertionPairFormatter
) : SingleAssertionGroupTypeFormatter<SummaryAssertionGroupType>(SummaryAssertionGroupType::class.java) {
    private val successful = (bulletPoints[PrefixSuccessfulSummaryAssertion::class.java] ?: "✔ ")
    private val failing = (bulletPoints[PrefixFailingSummaryAssertion::class.java] ?: "✘ ")

    override fun formatGroupHeaderAndGetChildMethodObject(assertionGroup: AssertionGroup, methodObject: AssertionFormatterMethodObject): AssertionFormatterMethodObject {
        methodObject.appendLnIndentAndPrefix()
        assertionPairFormatter.format(methodObject, assertionGroup.name, assertionGroup.subject)
        //the prefix which should be used for assertions is defined in the formatGroupAssertions is defined in formatGroupAssertions
        return methodObject.createForDoNotFilterAssertionGroup()
    }

    override fun formatGroupAssertions(formatAssertions: (AssertionFormatterMethodObject, (Assertion) -> Unit) -> Unit, childMethodObject: AssertionFormatterMethodObject) {
        val successfulMethodObject = childMethodObject.createChildWithNewPrefix(successful)
        val failingMethodObject = childMethodObject.createChildWithNewPrefix(failing)
        formatAssertions(childMethodObject) {
            if (it.holds()) {
                assertionFormatterController.format(it, successfulMethodObject)
            } else {
                assertionFormatterController.format(it, failingMethodObject)
            }
        }
    }
}
