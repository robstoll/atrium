package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.IAssertionGroupType

/**
 * A base type for [AssertionFormatter] which [canFormat][AssertionFormatter.canFormat] only
 * [IAssertionGroup]s of one specific [IAssertionGroupType] and does nothing special when it comes to formatting
 * [IAssertionGroup.assertions] (merely delegates to [assertionFormatterController]).
 *
 * @param T The [IAssertionGroupType] which the concrete sub class [canFormat][AssertionFormatter.canFormat].
 *
 * @property clazz The [IAssertionGroupType] which the concrete sub class [canFormat][AssertionFormatter.canFormat].
 *
 * @constructor A base type for [AssertionFormatter] which [canFormat][AssertionFormatter.canFormat] only
 *              [IAssertionGroup]s of one specific [IAssertionGroupType].
 * @param clazz The [IAssertionGroupType] which the concrete sub class [canFormat][AssertionFormatter.canFormat].
 * @param assertionFormatterController The controller to which this formatter gives back the control
 *        when it comes to format children of an [IAssertionGroup].
 */
abstract class NoSpecialChildFormattingSingleAssertionGroupTypeFormatter<in T : IAssertionGroupType>(
    clazz: Class<T>,
    private val assertionFormatterController: AssertionFormatterController
) : SingleAssertionGroupTypeFormatter<T>(clazz) {


    override fun formatGroupAssertions(formatAssertions: (AssertionFormatterMethodObject, (IAssertion) -> Unit) -> Unit, childMethodObject: AssertionFormatterMethodObject) {
        formatAssertions(childMethodObject) {
            assertionFormatterController.format(it, childMethodObject)
        }
    }
}
