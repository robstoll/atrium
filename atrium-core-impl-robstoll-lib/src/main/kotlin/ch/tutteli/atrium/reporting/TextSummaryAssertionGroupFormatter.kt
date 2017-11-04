package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.*

/**
 * Represents an [IAssertionFormatter] which formats [IAssertionGroup]s with an [ISummaryAssertionGroupType] by
 * using the given [assertionPairFormatter] to format the group header and uses the bullet point defined
 * for [PrefixSuccessfulSummaryAssertion] as prefix for successful [IAssertionGroup.assertions] and the bullet point
 * defined for [PrefixFeatureAssertionGroupHeader] as prefix for failing [IAssertionGroup.assertions].
 *
 * Its usage is intended for text output (e.g. to the console).
 *
 * @constructor Represents an [IAssertionFormatter] which formats [IAssertionGroup]s with an
 * [ISummaryAssertionGroupType] by using the given [assertionPairFormatter] to format the group header and uses the
 * bullet point defined for [PrefixSuccessfulSummaryAssertion] as prefix for successful [IAssertionGroup.assertions]
 * and the bullet point defined for [PrefixFeatureAssertionGroupHeader] as prefix for failing [IAssertionGroup.assertions].
 *
 * @param bulletPoints The formatter uses the bullet point defined for [PrefixSuccessfulSummaryAssertion]
 * (`"✔ "` if absent) as prefix for successful assertions in [IAssertionGroup.assertions] and the bullet point defined
 * for [PrefixFailingSummaryAssertion] (`"✘ "` if absent) for failing assertions.
 *
 * @param assertionFormatterController The controller to which this formatter gives back the control
 *        when it comes to format children of an [IAssertionGroup].
 * @param assertionPairFormatter The formatter used to format assertion pairs.
 */
class TextSummaryAssertionGroupFormatter(
    bulletPoints: Map<Class<out IBulletPointIdentifier>, String>,
    private val assertionFormatterController: IAssertionFormatterController,
    private val assertionPairFormatter: IAssertionPairFormatter
) : SingleAssertionGroupTypeFormatter<ISummaryAssertionGroupType>(ISummaryAssertionGroupType::class.java) {
    private val successful = (bulletPoints[PrefixSuccessfulSummaryAssertion::class.java] ?: "✔ ")
    private val failing = (bulletPoints[PrefixFailingSummaryAssertion::class.java] ?: "✘ ")

    override fun formatGroupHeaderAndGetChildMethodObject(assertionGroup: IAssertionGroup, methodObject: AssertionFormatterMethodObject): AssertionFormatterMethodObject {
        methodObject.appendLnIndentAndPrefix()
        assertionPairFormatter.format(methodObject, assertionGroup.name, assertionGroup.subject)
        //the prefix which should be used for assertions is defined in the formatGroupAssertions is defined in formatGroupAssertions
        return methodObject.createForDoNotFilterAssertionGroup()
    }

    override fun formatGroupAssertions(formatAssertions: (AssertionFormatterMethodObject, (IAssertion) -> Unit) -> Unit, childMethodObject: AssertionFormatterMethodObject) {
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
