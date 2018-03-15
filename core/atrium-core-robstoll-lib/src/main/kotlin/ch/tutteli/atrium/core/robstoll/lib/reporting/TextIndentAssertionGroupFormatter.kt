package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.assertions.IndentAssertionGroupType
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.AssertionFormatterParameterObject

/**
 * Represents an [AssertionFormatter] which formats [AssertionGroup]s with an [IndentAssertionGroupType] or rather
 * creates a child-[AssertionFormatterParameterObject] which proposes to use the bullet point defined
 * for [IndentAssertionGroupType] for the [AssertionGroup.assertions].
 *
 * It does not include a group header in its result or in other words, skips the first part of formatting an
 * [AssertionGroup] as defined in [AssertionFormatter.formatGroup].
 *
 * Its usage is intended for text output (e.g. to the console).
 *
 * @constructor Represents an [AssertionFormatter] which formats [AssertionGroup]s with an [IndentAssertionGroupType]
 *   or rather creates a child-[AssertionFormatterParameterObject] which proposes to use the bullet point
 *   defined for [IndentAssertionGroupType] for the [AssertionGroup.assertions].
 * @param bulletPoints The formatter uses the bullet point defined for [IndentAssertionGroupType]
 *   (`" ⋄ "` if absent) as prefix of the child-[AssertionFormatterParameterObject].
 * @param assertionFormatterController The controller to which this formatter gives back the control
 *   when it comes to format children of an [AssertionGroup].
 */
@Deprecated("So far indentation was achieved by grouping (which is the solution to go). Will be removed with 1.0.0")
class TextIndentAssertionGroupFormatter(
    bulletPoints: Map<Class<out BulletPointIdentifier>, String>,
    assertionFormatterController: AssertionFormatterController
) : NoSpecialChildFormattingSingleAssertionGroupTypeFormatter<IndentAssertionGroupType>(IndentAssertionGroupType::class.java, assertionFormatterController) {
    private val bulletPoint = bulletPoints[IndentAssertionGroupType::class.java] ?: " ⋄ "

    override fun formatGroupHeaderAndGetChildParameterObject(assertionGroup: AssertionGroup, parameterObject: AssertionFormatterParameterObject)
        = parameterObject.createChildWithNewPrefix(bulletPoint)

}
