package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.IInvisibleAssertionGroupType

/**
 * Represents an [IAssertionFormatter] which formats [IAssertionGroup]s with an [IInvisibleAssertionGroupType].
 *
 * @constructor Represents an [IAssertionFormatter] which formats [IAssertionGroup]s with
 *              an [IInvisibleAssertionGroupType].
 * @param assertionFormatterController The controller to which this formatter gives back the control
 *        when it comes to format children of an [IAssertionGroup].
 */
class InvisibleAssertionGroupFormatter(
    private val assertionFormatterController: IAssertionFormatterController
) : SingleAssertionGroupTypeFormatter<IInvisibleAssertionGroupType>(IInvisibleAssertionGroupType::class.java) {

    override fun formatSpecificGroup(assertionGroup: IAssertionGroup, methodObject: AssertionFormatterMethodObject, formatAssertions: ((IAssertion) -> Unit) -> Unit) {
        formatAssertions {
            assertionFormatterController.format(it, methodObject)
        }
    }
}
