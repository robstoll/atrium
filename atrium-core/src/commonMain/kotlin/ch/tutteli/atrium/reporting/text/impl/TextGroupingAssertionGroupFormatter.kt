package ch.tutteli.atrium.reporting.text.impl

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.AssertionFormatterParameterObject
import ch.tutteli.atrium.reporting.AssertionPairFormatter
import ch.tutteli.atrium.reporting.text.TextAssertionFormatter
import kotlin.reflect.KClass

/**
 * Represents an [AssertionFormatter] which formats [AssertionGroup]s with a [GroupingAssertionGroupType] by
 * using the given [assertionPairFormatter] to format the group header and uses the bullet point defined for
 * [GroupingAssertionGroupType] as prefix for the group as such and the bullet point defined for
 * [RootAssertionGroupType] for [AssertionGroup.assertions].
 *
 * Its usage is intended for text output (e.g. to the console).
 *
 * @constructor Represents an [AssertionFormatter] which formats [AssertionGroup]s with a [GroupingAssertionGroupType]
 *   by putting each assertion on an own line prefixed with a bullet point.
 * @param bulletPoints The formatter uses the bullet point defined for [GroupingAssertionGroupType]
 *   (`"# "` if absent) as prefix of the group and [RootAssertionGroupType] (`◆ ` if absent)
 *   of the child-[AssertionFormatterParameterObject].
 * @param assertionFormatterController The controller to which this formatter gives back the control
 *   when it comes to format children of an [AssertionGroup].
 * @param assertionPairFormatter The formatter which is used to format assertion pairs.
 *
 * @since 1.1.0
 */
class TextGroupingAssertionGroupFormatter(
    bulletPoints: Map<KClass<out BulletPointIdentifier>, String>,
    private val assertionFormatterController: AssertionFormatterController,
    private val assertionPairFormatter: AssertionPairFormatter
) : NoSpecialChildFormattingSingleAssertionGroupTypeFormatter<GroupingAssertionGroupType>(
    GroupingAssertionGroupType::class,
    assertionFormatterController
),
    TextAssertionFormatter {
    private val groupPrefix = (bulletPoints[GroupingAssertionGroupType::class] ?: "# ")
    private val rootPrefix = bulletPoints[RootAssertionGroupType::class] ?: "◆ "
    private val formatter = TextPrefixBasedAssertionGroupFormatter(rootPrefix)

    override fun formatGroupHeaderAndGetChildParameterObject(
        assertionGroup: AssertionGroup,
        parameterObject: AssertionFormatterParameterObject
    ): AssertionFormatterParameterObject =
        formatter.formatWithGroupName(
            assertionPairFormatter,
            assertionGroup,
            parameterObject.createWithNewPrefix(groupPrefix)
        )
}
