package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType

/**
 * A base type for [AssertionFormatter] which [canFormat][AssertionFormatter.canFormat] only
 * [AssertionGroup]s of one specific [AssertionGroupType] and does nothing special when it comes to formatting
 * [AssertionGroup.assertions] (merely delegates to [assertionFormatterController]).
 *
 * @param T The [AssertionGroupType] which the concrete sub class [canFormat][AssertionFormatter.canFormat].
 *
 * @property clazz The [AssertionGroupType] which the concrete sub class [canFormat][AssertionFormatter.canFormat].
 *
 * @constructor A base type for [AssertionFormatter] which [canFormat][AssertionFormatter.canFormat] only
 *              [AssertionGroup]s of one specific [AssertionGroupType].
 * @param clazz The [AssertionGroupType] which the concrete sub class [canFormat][AssertionFormatter.canFormat].
 * @param assertionFormatterController The controller to which this formatter gives back the control
 *        when it comes to format children of an [AssertionGroup].
 */
abstract class NoSpecialChildFormattingSingleAssertionGroupTypeFormatter<in T : AssertionGroupType>(
    clazz: Class<T>,
    private val assertionFormatterController: AssertionFormatterController
) : SingleAssertionGroupTypeFormatter<T>(clazz) {


    override fun formatGroupAssertions(formatAssertions: (AssertionFormatterMethodObject, (Assertion) -> Unit) -> Unit, childMethodObject: AssertionFormatterMethodObject) {
        formatAssertions(childMethodObject) {
            assertionFormatterController.format(it, childMethodObject)
        }
    }
}
