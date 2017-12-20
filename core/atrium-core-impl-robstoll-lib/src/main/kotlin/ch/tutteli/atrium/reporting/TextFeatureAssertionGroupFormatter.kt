package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.IBulletPointIdentifier
import ch.tutteli.atrium.assertions.IFeatureAssertionGroupType
import ch.tutteli.atrium.assertions.PrefixFeatureAssertionGroupHeader
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.reporting.translating.Untranslatable

/**
 * Represents an [AssertionFormatter] which formats [IAssertionGroup]s with an [IFeatureAssertionGroupType] by
 * using the given [assertionPairFormatter] to format the group header, additionally prefixing it with the
 * "bullet point" (typically an arrow) defined for [PrefixFeatureAssertionGroupHeader] and uses the bullet point
 * defined for [IFeatureAssertionGroupType] as prefix for the [IAssertionGroup.assertions].
 *
 * Its usage is intended for text output (e.g. to the console).
 *
 * @constructor Represents an [AssertionFormatter] which formats [IAssertionGroup]s with an
 * [IFeatureAssertionGroupType] by using the given [assertionPairFormatter] to format the group header, additionally
 * prefixing it with the "bullet point" (typically an arrow) defined for [PrefixFeatureAssertionGroupHeader] and uses
 * the bullet point defined for [IFeatureAssertionGroupType] as prefix for the [IAssertionGroup.assertions].
 *
 * @param bulletPoints The formatter uses the bullet point defined for [PrefixFeatureAssertionGroupHeader]
 *        (`"▶ "` if absent) as prefix of the group header and [IFeatureAssertionGroupType] (`"◾ "` if absent)
 *        as prefix of the child-[AssertionFormatterMethodObject].
 * @param assertionFormatterController The controller to which this formatter gives back the control
 *        when it comes to format children of an [IAssertionGroup].
 * @param assertionPairFormatter The formatter used to format assertion pairs.
 */
class TextFeatureAssertionGroupFormatter(
    bulletPoints: Map<Class<out IBulletPointIdentifier>, String>,
    assertionFormatterController: AssertionFormatterController,
    private val assertionPairFormatter: AssertionPairFormatter
) : NoSpecialChildFormattingSingleAssertionGroupTypeFormatter<IFeatureAssertionGroupType>(IFeatureAssertionGroupType::class.java, assertionFormatterController) {

    private val prefix = (bulletPoints[IFeatureAssertionGroupType::class.java] ?: "◾ ")
    private val arrow = (bulletPoints[PrefixFeatureAssertionGroupHeader::class.java] ?: "▶ ")

    override fun formatGroupHeaderAndGetChildMethodObject(assertionGroup: IAssertionGroup, methodObject: AssertionFormatterMethodObject): AssertionFormatterMethodObject {
        methodObject.appendLnIndentAndPrefix()
        val translatable = TranslatableWithArgs(Untranslatable("$arrow%s"), assertionGroup.name)
        assertionPairFormatter.format(methodObject, translatable, assertionGroup.subject)
        return methodObject.createChildWithNewPrefixAndAdditionalIndent(prefix, arrow.length)
    }
}
