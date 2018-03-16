package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.assertions.FeatureAssertionGroupType
import ch.tutteli.atrium.assertions.PrefixFeatureAssertionGroupHeader
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.AssertionFormatterParameterObject
import ch.tutteli.atrium.reporting.AssertionPairFormatter
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.reporting.translating.Untranslatable

/**
 * Represents an [AssertionFormatter] which formats [AssertionGroup]s with a [FeatureAssertionGroupType] by
 * using the given [assertionPairFormatter] to format the group header, additionally prefixing it with the
 * "bullet point" (typically an arrow) defined for [PrefixFeatureAssertionGroupHeader] and uses the bullet point
 * defined for [FeatureAssertionGroupType] as prefix for the [AssertionGroup.assertions].
 *
 * Its usage is intended for text output (e.g. to the console).
 *
 * @constructor Represents an [AssertionFormatter] which formats [AssertionGroup]s with an
 *   [FeatureAssertionGroupType] by using the given [assertionPairFormatter] to format the group header, additionally
 * prefixing it with the "bullet point" (typically an arrow) defined for [PrefixFeatureAssertionGroupHeader] and uses
 * the bullet point defined for [FeatureAssertionGroupType] as prefix for the [AssertionGroup.assertions].
 *
 * @param bulletPoints The formatter uses the bullet point defined for [PrefixFeatureAssertionGroupHeader]
 *   (`"▶ "` if absent) as prefix of the group header and [FeatureAssertionGroupType] (`"◾ "` if absent)
 *   as prefix of the child-[AssertionFormatterParameterObject].
 * @param assertionFormatterController The controller to which this formatter gives back the control
 *   when it comes to format children of an [AssertionGroup].
 * @param assertionPairFormatter The formatter which is used to format assertion pairs.
 */
class TextFeatureAssertionGroupFormatter(
    bulletPoints: Map<Class<out BulletPointIdentifier>, String>,
    assertionFormatterController: AssertionFormatterController,
    private val assertionPairFormatter: AssertionPairFormatter
) : NoSpecialChildFormattingSingleAssertionGroupTypeFormatter<FeatureAssertionGroupType>(FeatureAssertionGroupType::class.java, assertionFormatterController) {

    private val prefix = (bulletPoints[FeatureAssertionGroupType::class.java] ?: "◾ ")
    private val arrow = (bulletPoints[PrefixFeatureAssertionGroupHeader::class.java] ?: "▶ ")

    override fun formatGroupHeaderAndGetChildParameterObject(assertionGroup: AssertionGroup, parameterObject: AssertionFormatterParameterObject): AssertionFormatterParameterObject {
        parameterObject.appendLnIndentAndPrefix()
        val translatable = TranslatableWithArgs(Untranslatable("$arrow%s"), assertionGroup.name)
        val group =
            NameDecoratingAssertionGroup(
                translatable,
                assertionGroup
            )
        val newParameterObject = parameterObject.createChildWithNewPrefixAndAdditionalIndent(prefix, arrow.length)
        assertionPairFormatter.formatGroupHeader(parameterObject, group, newParameterObject)
        return newParameterObject
    }

    private class NameDecoratingAssertionGroup(
        newName: Translatable,
        assertionGroup: AssertionGroup
    ) : AssertionGroup by assertionGroup {
        override val name: Translatable = newName
    }
}
