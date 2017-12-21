package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.assertions.IIndentAssertionGroupType

/**
 * Represents an [AssertionFormatter] which formats [AssertionGroup]s with an [IIndentAssertionGroupType] or rather
 * creates a child-[AssertionFormatterMethodObject] which proposes to use the bullet point defined
 * for [IIndentAssertionGroupType] for the [AssertionGroup.assertions].
 *
 * It does not include a group header in its result or in other words, skips the first part of formatting an
 * [AssertionGroup] as defined in [AssertionFormatter.formatGroup].
 *
 * Its usage is intended for text output (e.g. to the console).
 *
 * @constructor Represents an [AssertionFormatter] which formats [AssertionGroup]s with an [IIndentAssertionGroupType]
 *              or rather creates a child-[AssertionFormatterMethodObject] which proposes to use the bullet point
 *              defined for [IIndentAssertionGroupType] for the [IAssertionGroup.assertions].
 * @param bulletPoints The formatter uses the bullet point defined for [IIndentAssertionGroupType]
 *        (`" ⋄ "` if absent) as prefix of the child-[AssertionFormatterMethodObject].
 * @param assertionFormatterController The controller to which this formatter gives back the control
 *        when it comes to format children of an [IAssertionGroup].
 */
class TextIndentAssertionGroupFormatter(
    bulletPoints: Map<Class<out BulletPointIdentifier>, String>,
    assertionFormatterController: AssertionFormatterController
) : NoSpecialChildFormattingSingleAssertionGroupTypeFormatter<IIndentAssertionGroupType>(IIndentAssertionGroupType::class.java, assertionFormatterController) {
    private val bulletPoint = bulletPoints[IIndentAssertionGroupType::class.java] ?: " ⋄ "

    override fun formatGroupHeaderAndGetChildMethodObject(assertionGroup: AssertionGroup, methodObject: AssertionFormatterMethodObject)
        = methodObject.createChildWithNewPrefix(bulletPoint)

}
