package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.IBulletPointIdentifier
import ch.tutteli.atrium.assertions.IExplanatoryAssertionGroupType

/**
 * Represents an [IAssertionFormatter] which formats [IAssertionGroup]s with an [IExplanatoryAssertionGroupType] by
 * neglecting the group header and defining an child-[AssertionFormatterMethodObject] which indicates that we are in an
 * explanatory assertion.
 *
 * Furthermore it uses the bullet point defined for [IExplanatoryAssertionGroupType] in `bulletPoints` (see constructor)
 * as prefix for the child-[AssertionFormatterMethodObject] (uses `"  » "` as fallback).
 *
 * Its usage is intended for text output (e.g. to the console).
 *
 * @constructor Represents an [IAssertionFormatter] which formats [IAssertionGroup]s with an
 *              [IExplanatoryAssertionGroupType] by defining only an [AssertionFormatterMethodObject] -- which indicates
 *              that we are in an explanatory assertion group and uses the given [bulletPoint] as prefix -- and
 *              completely ignoring [IAssertionGroup.name] and [IAssertionGroup.subject].
 *
 * @param bulletPoints The formatter uses the bullet point defined for [IExplanatoryAssertionGroupType]
 *        (`"  » "` if absent) as prefix of the child-[AssertionFormatterMethodObject].
 *
 * @param assertionFormatterController The controller to which this formatter gives back the control
 *        when it comes to format children of an [IAssertionGroup].
 */
class TextExplanatoryAssertionGroupFormatter(
    bulletPoints: Map<Class<out IBulletPointIdentifier>, String>,
    assertionFormatterController: IAssertionFormatterController
) : SingleAssertionGroupTypeFormatter<IExplanatoryAssertionGroupType>(IExplanatoryAssertionGroupType::class.java, assertionFormatterController) {
    private val explanatoryBulletPoint = bulletPoints[IExplanatoryAssertionGroupType::class.java] ?: "  » "

    override fun formatGroupHeaderAndGetChildMethodObject(assertionGroup: IAssertionGroup, methodObject: AssertionFormatterMethodObject): AssertionFormatterMethodObject
        = methodObject.createForExplanatoryAssertionGroup().createChildWithNewPrefix(explanatoryBulletPoint)

}
