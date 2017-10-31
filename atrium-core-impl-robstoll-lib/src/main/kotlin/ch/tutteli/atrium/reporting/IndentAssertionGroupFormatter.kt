package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.IIndentAssertionGroupType

/**
 * Represents an [IAssertionFormatter] which formats [IAssertionGroup]s with an [IIndentAssertionGroupType] by adding
 * an extra indent to the [assertions][IAssertionGroup.assertions] starting from [IIndentAssertionGroupType.indentIndex]
 * and prefix them with the given [bulletPoint].
 *
 * @constructor Represents an [IAssertionFormatter] which formats [IAssertionGroup]s with an [IIndentAssertionGroupType]
 *              by adding an extra indent to the [assertions][IAssertionGroup.assertions] starting from
 *              [IIndentAssertionGroupType.indentIndex] and prefix them with the given [bulletPoint].
 * @param bulletPoint The bullet point (might also be more than one character) which shall be used.
 * @param assertionFormatterController The controller to which this formatter gives back the control
 *        when it comes to format children of an [IAssertionGroup].
 */
class IndentAssertionGroupFormatter(
    private val bulletPoint: String,
    assertionFormatterController: IAssertionFormatterController
) : SingleAssertionGroupTypeFormatter<IIndentAssertionGroupType>(IIndentAssertionGroupType::class.java, assertionFormatterController) {

    override fun formatGroupHeaderAndGetChildMethodObject(assertionGroup: IAssertionGroup, methodObject: AssertionFormatterMethodObject)
        = methodObject.createChildWithNewPrefix(" $bulletPoint ")

}
