package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.IExplanatoryAssertionGroupType

/**
 * Represents an [IAssertionFormatter] which formats [IAssertionGroup]s with an [IExplanatoryAssertionGroupType] by
 * defining only an [AssertionFormatterMethodObject] -- which indicates that we are in an explanatory assertion
 * group and uses the given [bulletPoint] as prefix -- and completely ignoring [IAssertionGroup.name] and
 * [IAssertionGroup.subject].
 *
 * Its usage is intended for text output (e.g. to the console).
 *
 * @constructor Represents an [IAssertionFormatter] which formats [IAssertionGroup]s with an
 *              [IExplanatoryAssertionGroupType] by defining only an [AssertionFormatterMethodObject] -- which indicates
 *              that we are in an explanatory assertion group and uses the given [bulletPoint] as prefix -- and
 *              completely ignoring [IAssertionGroup.name] and [IAssertionGroup.subject].
 * @param assertionFormatterController The controller to which this formatter gives back the control
 *        when it comes to format children of an [IAssertionGroup].
 */
class TextExplanatoryAssertionGroupFormatter(
    private val bulletPoint: String,
    assertionFormatterController: IAssertionFormatterController
) : SingleAssertionGroupTypeFormatter<IExplanatoryAssertionGroupType>(IExplanatoryAssertionGroupType::class.java, assertionFormatterController) {

    override fun formatGroupHeaderAndGetChildMethodObject(assertionGroup: IAssertionGroup, methodObject: AssertionFormatterMethodObject): AssertionFormatterMethodObject
        = methodObject.createForExplanatoryAssertionGroup().createChildWithNewPrefix("  $bulletPoint ")

}
