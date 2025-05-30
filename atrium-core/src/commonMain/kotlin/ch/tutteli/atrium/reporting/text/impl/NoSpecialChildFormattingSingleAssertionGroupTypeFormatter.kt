//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.reporting.text.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.AssertionFormatterParameterObject
import kotlin.reflect.KClass

/**
 * A base type for [AssertionFormatter] which [canFormat][AssertionFormatter.canFormat] only
 * [AssertionGroup]s of one specific [AssertionGroupType] and does nothing special when it comes to formatting
 * [AssertionGroup.assertions] (merely delegates to [assertionFormatterController]).
 *
 * @param T The [AssertionGroupType] which the concrete subclass [canFormat][AssertionFormatter.canFormat].
 *
 * @property clazz The [AssertionGroupType] which the concrete subclass [canFormat][AssertionFormatter.canFormat].
 *
 * @constructor A base type for [AssertionFormatter] which [canFormat][AssertionFormatter.canFormat] only
 *   [AssertionGroup]s of one specific [AssertionGroupType].
 * @param clazz The [AssertionGroupType] which the concrete subclass [canFormat][AssertionFormatter.canFormat].
 * @param assertionFormatterController The controller to which this formatter gives back the control
 *   when it comes to format children of an [AssertionGroup].
 */
@Deprecated("Switch to Proof based reporting, will be removed with 2.0.0 at the latest")
abstract class NoSpecialChildFormattingSingleAssertionGroupTypeFormatter<in T : AssertionGroupType>(
    clazz: KClass<T>,
    private val assertionFormatterController: AssertionFormatterController
) : SingleAssertionGroupTypeFormatter<T>(clazz) {

    final override fun formatGroupAssertions(
        formatAssertions: (AssertionFormatterParameterObject, (Assertion) -> Unit) -> Unit,
        childParameterObject: AssertionFormatterParameterObject
    ) {
        formatAssertions(childParameterObject) {
            assertionFormatterController.format(it, childParameterObject)
        }
    }
}
