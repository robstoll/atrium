package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.IBulletPointIdentifier
import ch.tutteli.atrium.assertions.IListAssertionGroupType

/**
 * Represents an [AssertionFormatter] which formats [IAssertionGroup]s with an [IListAssertionGroupType] by
 * using the given [assertionPairFormatter] to format the group header and uses the bullet point defined for
 * [IListAssertionGroupType] as prefix for the [IAssertionGroup.assertions].
 *
 * Its usage is intended for text output (e.g. to the console).
 *
 * @constructor Represents an [AssertionFormatter] which formats [IAssertionGroup]s with an [IListAssertionGroupType]
 *              by putting each assertion on an own line prefixed with a bullet point.
 * @param bulletPoints The formatter uses the bullet point defined for [IListAssertionGroupType]
 *        (`" ⚬ "` if absent) as prefix of the child-[AssertionFormatterMethodObject].
 * @param assertionFormatterController The controller to which this formatter gives back the control
 *        when it comes to format children of an [IAssertionGroup].
 * @param assertionPairFormatter The formatter used to format assertion pairs.
 */
class TextListAssertionGroupFormatter(
    bulletPoints: Map<Class<out IBulletPointIdentifier>, String>,
    assertionFormatterController: AssertionFormatterController,
    assertionPairFormatter: AssertionPairFormatter
) : TextListBasedAssertionGroupFormatter<IListAssertionGroupType>(
    bulletPoints[IListAssertionGroupType::class.java] ?: "⚬ ",
    assertionFormatterController,
    assertionPairFormatter,
    IListAssertionGroupType::class.java)
