package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.IInvisibleAssertionGroupType

/**
 * Represents an [AssertionFormatter] which formats [IAssertionGroup]s with an [IInvisibleAssertionGroupType] or rather
 * does not format them but only passes on the given [AssertionFormatterMethodObject] as
 * child-[AssertionFormatterMethodObject] for the formatting of the [IAssertionGroup.assertions].
 *
 * It does not include a group header in its result or in other words, skips the first part of formatting an
 * [IAssertionGroup] as defined in [AssertionFormatter.formatGroup].
 *
 * @constructor Represents an [AssertionFormatter] which formats [IAssertionGroup]s with an
 *              [IInvisibleAssertionGroupType] by neglecting [IAssertionGroup.name] and [IAssertionGroup.subject] and
 *              passes on the given [AssertionFormatterMethodObject] which is used to format
 *              [IAssertionGroup.assertions].
 * @param assertionFormatterController The controller to which this formatter gives back the control
 *        when it comes to format children of an [IAssertionGroup].
 */
class InvisibleAssertionGroupFormatter(
    assertionFormatterController: AssertionFormatterController
) : NoSpecialChildFormattingSingleAssertionGroupTypeFormatter<IInvisibleAssertionGroupType>(IInvisibleAssertionGroupType::class.java, assertionFormatterController) {

    override fun formatGroupHeaderAndGetChildMethodObject(assertionGroup: IAssertionGroup, methodObject: AssertionFormatterMethodObject)
        = methodObject
}
