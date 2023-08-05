package ch.tutteli.atrium.reporting.text.impl

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.AssertionFormatterParameterObject
import ch.tutteli.atrium.reporting.text.TextAssertionFormatter
import kotlin.reflect.KClass

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
 *   that we are in an explanatory expectation-group and uses the `bulletPoints` (passed as argument) as prefix --
 *   and completely ignoring [AssertionGroup.description] and [AssertionGroup.representation].
 *
 * @param bulletPoints The formatter uses the bullet point defined for [WarningAssertionGroupType]
 *   (`"❗❗ "` if absent) or the bullet point defined for [ExplanatoryAssertionGroupType]
 *   (`"» "` if absent) as prefix of the child-[AssertionFormatterParameterObject].
 *
 * @param assertionFormatterController The controller to which this formatter gives back the control
 *   when it comes to format children of an [AssertionGroup].
 */
class TextExplanatoryAssertionGroupFormatter(
    bulletPoints: Map<KClass<out BulletPointIdentifier>, String>,
    assertionFormatterController: AssertionFormatterController
) : NoSpecialChildFormattingSingleAssertionGroupTypeFormatter<ExplanatoryAssertionGroupType>(
    ExplanatoryAssertionGroupType::class,
    assertionFormatterController
), TextAssertionFormatter {
    private val explanatoryBulletPoint = bulletPoints[ExplanatoryAssertionGroupType::class] ?: "» "
    private val warningBulletPoint = bulletPoints[WarningAssertionGroupType::class] ?: "❗❗ "
    private val informationBulletPoint = bulletPoints[InformationAssertionGroupType::class] ?: "ℹ "
    private val hintBulletPoint = bulletPoints[HintAssertionGroupType::class] ?: "\uD83D\uDCA1 "

    override fun formatGroupHeaderAndGetChildParameterObject(
        assertionGroup: AssertionGroup,
        parameterObject: AssertionFormatterParameterObject
    ): AssertionFormatterParameterObject {
        fun withIndent(bulletPoint: String) =
            parameterObject.createForExplanatoryFilterAssertionGroup().createChildWithNewPrefix(bulletPoint)

        fun withOrWithoutIndent(bulletPoint: String, withIndent: Boolean) =
            if (withIndent) withIndent(bulletPoint)
            else parameterObject.createForExplanatoryFilterAssertionGroup(bulletPoint)

        return when (val assertionGroupType = assertionGroup.type) {
            is InformationAssertionGroupType -> withOrWithoutIndent(
                informationBulletPoint,
                assertionGroupType.withIndent
            )
            WarningAssertionGroupType -> withIndent(warningBulletPoint)
            HintAssertionGroupType -> withIndent(hintBulletPoint)
            else -> withIndent(explanatoryBulletPoint)
        }
    }
}
