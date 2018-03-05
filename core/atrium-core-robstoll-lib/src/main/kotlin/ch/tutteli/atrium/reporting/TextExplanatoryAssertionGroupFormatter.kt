package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.assertions.WarningAssertionGroupType

/**
 * Represents an [AssertionFormatter] which formats [AssertionGroup]s with an [ExplanatoryAssertionGroupType] by
 * neglecting the group header and defining an child-[AssertionFormatterParameterObject] which indicates that we are in an
 * explanatory assertion.
 *
 * Furthermore it uses the bullet point defined for [WarningAssertionGroupType] in `bulletPoints` (see constructor)
 * (`"❗❗ "` if absent) as prefix for the child-[AssertionFormatterParameterObject] if the [AssertionGroup.type] is a
 * [WarningAssertionGroupType]. Otherwise it is using the bullet point defined for [ExplanatoryAssertionGroupType]
 * (`"» "` if absent).
 *
 * Its usage is intended for text output (e.g. to the console).
 *
 * @constructor Represents an [AssertionFormatter] which formats [AssertionGroup]s with an
 *   [ExplanatoryAssertionGroupType] by defining only an [AssertionFormatterParameterObject] -- which indicates
 *   that we are in an explanatory assertion group and uses the `bulletPoints` (passed as argument) as prefix --
 *   and completely ignoring [AssertionGroup.name] and [AssertionGroup.subject].
 *
 * @param bulletPoints The formatter uses the bullet point defined for [WarningAssertionGroupType]
 *   (`"❗❗ "` if absent) or the bullet point defined for [ExplanatoryAssertionGroupType]
 *   (`"» "` if absent) as prefix of the child-[AssertionFormatterParameterObject].
 *
 * @param assertionFormatterController The controller to which this formatter gives back the control
 *   when it comes to format children of an [AssertionGroup].
 */
class TextExplanatoryAssertionGroupFormatter(
    bulletPoints: Map<Class<out BulletPointIdentifier>, String>,
    assertionFormatterController: AssertionFormatterController
) : NoSpecialChildFormattingSingleAssertionGroupTypeFormatter<ExplanatoryAssertionGroupType>(ExplanatoryAssertionGroupType::class.java, assertionFormatterController) {
    private val explanatoryBulletPoint = bulletPoints[ExplanatoryAssertionGroupType::class.java] ?: "» "
    private val warningBulletPoint = bulletPoints[WarningAssertionGroupType::class.java] ?: "❗❗ "

    override fun formatGroupHeaderAndGetChildParameterObject(assertionGroup: AssertionGroup, parameterObject: AssertionFormatterParameterObject): AssertionFormatterParameterObject {
        val bulletPoint = when (assertionGroup.type) {
            WarningAssertionGroupType -> warningBulletPoint
            else -> explanatoryBulletPoint
        }
        return parameterObject.createForDoNotFilterAssertionGroup().createChildWithNewPrefix(bulletPoint)
    }

}
