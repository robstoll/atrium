package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.AssertionFormatterParameterObject
import ch.tutteli.atrium.reporting.AssertionPairFormatter
import kotlin.reflect.KClass

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
    bulletPoints: Map<KClass<out BulletPointIdentifier>, String>,
    private val assertionFormatterController: AssertionFormatterController,
    private val assertionPairFormatter: AssertionPairFormatter
) : SingleAssertionGroupTypeFormatter<SummaryAssertionGroupType>(SummaryAssertionGroupType::class) {
    private val successful = (bulletPoints[PrefixSuccessfulSummaryAssertion::class] ?: "✔ ")
    private val failing = (bulletPoints[PrefixFailingSummaryAssertion::class] ?: "✘ ")

    override fun formatGroupHeaderAndGetChildParameterObject(
        assertionGroup: AssertionGroup,
        parameterObject: AssertionFormatterParameterObject
    ): AssertionFormatterParameterObject {
        parameterObject.appendLnIndentAndPrefix()
        assertionPairFormatter.format(parameterObject, assertionGroup.description, assertionGroup.representation)
        //the prefix which should be used for assertions is defined in the formatGroupAssertions
        return parameterObject.createForDoNotFilterAssertionGroup()
    }

    override fun formatGroupAssertions(
        formatAssertions: (AssertionFormatterParameterObject, (Assertion) -> Unit) -> Unit,
        childParameterObject: AssertionFormatterParameterObject
    ) {
        val successfulParameterObject = childParameterObject.createChildWithNewPrefix(successful)
        val failingParameterObject = childParameterObject.createChildWithNewPrefix(failing)
        formatAssertions(childParameterObject) {
            if (it.holds()) {
                assertionFormatterController.format(it, successfulParameterObject)
            } else {
                assertionFormatterController.format(it, failingParameterObject)
            }
        }
    }
}
