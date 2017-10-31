package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.IInvisibleAssertionGroupType

/**
 * Represents an [IAssertionFormatter] which formats [IAssertionGroup]s with an [IInvisibleAssertionGroupType] by
 * neglecting [IAssertionGroup.name] and [IAssertionGroup.subject] and passes on the given
 * [AssertionFormatterMethodObject] which is used to format [IAssertionGroup.assertions].
 *
 * @constructor Represents an [IAssertionFormatter] which formats [IAssertionGroup]s with an
 *              [IInvisibleAssertionGroupType] by neglecting [IAssertionGroup.name] and [IAssertionGroup.subject] and
 *              passes on the given [AssertionFormatterMethodObject] which is used to format
 *              [IAssertionGroup.assertions].
 * @param assertionFormatterController The controller to which this formatter gives back the control
 *        when it comes to format children of an [IAssertionGroup].
 */
class InvisibleAssertionGroupFormatter(
    assertionFormatterController: IAssertionFormatterController
) : SingleAssertionGroupTypeFormatter<IInvisibleAssertionGroupType>(IInvisibleAssertionGroupType::class.java, assertionFormatterController) {

    override fun formatGroupHeaderAndGetChildMethodObject(assertionGroup: IAssertionGroup, methodObject: AssertionFormatterMethodObject)
        = methodObject
}
