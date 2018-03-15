package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.assertions.ListAssertionGroupType
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.AssertionPairFormatter

/**
 * Represents an [AssertionFormatter] which formats [AssertionGroup]s with a [ListAssertionGroupType] by
 * using the given [assertionPairFormatter] to format the group header and uses the bullet point defined for
 * [ListAssertionGroupType] as prefix for the [AssertionGroup.assertions].
 *
 * Its usage is intended for text output (e.g. to the console).
 *
 * @constructor Represents an [AssertionFormatter] which formats [AssertionGroup]s with a [ListAssertionGroupType]
 *   by putting each assertion on an own line prefixed with a bullet point.
 * @param bulletPoints The formatter uses the bullet point defined for [ListAssertionGroupType]
 *   (`" ⚬ "` if absent) as prefix of the child-[AssertionFormatterParameterObject].
 * @param assertionFormatterController The controller to which this formatter gives back the control
 *   when it comes to format children of an [AssertionGroup].
 * @param assertionPairFormatter The formatter which is used to format assertion pairs.
 */
class TextListAssertionGroupFormatter(
    bulletPoints: Map<Class<out BulletPointIdentifier>, String>,
    assertionFormatterController: AssertionFormatterController,
    assertionPairFormatter: AssertionPairFormatter
) : TextListBasedAssertionGroupFormatter<ListAssertionGroupType>(
    bulletPoints[ListAssertionGroupType::class.java] ?: "⚬ ",
    assertionFormatterController,
    assertionPairFormatter,
    ListAssertionGroupType::class.java)
