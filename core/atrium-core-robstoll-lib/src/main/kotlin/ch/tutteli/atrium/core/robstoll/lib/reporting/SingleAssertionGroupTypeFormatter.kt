package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.AssertionFormatterParameterObject
import kotlin.reflect.KClass

/**
 * A base type for [AssertionFormatter] which [canFormat][AssertionFormatter.canFormat] only
 * [AssertionGroup]s of one specific [AssertionGroupType].
 *
 * @param T The [AssertionGroupType] which the concrete sub class [canFormat][AssertionFormatter.canFormat].
 *
 * @property clazz The [AssertionGroupType] which the concrete sub class [canFormat][AssertionFormatter.canFormat].
 *
 * @constructor A base type for [AssertionFormatter] which [canFormat][AssertionFormatter.canFormat] only
 *   [AssertionGroup]s of one specific [AssertionGroupType].
 * @param clazz The [AssertionGroupType] which the concrete sub class [canFormat][AssertionFormatter.canFormat].
 */
abstract class SingleAssertionGroupTypeFormatter<in T : AssertionGroupType>(
    private val clazz: KClass<T>
) : AssertionFormatter {

    /**
     * Returns true if the given [assertion] is an [AssertionGroup] and its [type][AssertionGroup.type]
     * is [T] or a sub type.
     */
    final override fun canFormat(assertion: Assertion)
        = assertion is AssertionGroup && clazz.isInstance(assertion.type)

    /**
     * Always throws an [UnsupportedOperationException], because this [AssertionFormatter] can only format
     * [AssertionGroup]s.
     *
     * @throws UnsupportedOperationException always!
     */
    final override fun formatNonGroup(assertion: Assertion, parameterObject: AssertionFormatterParameterObject)
        = throw UnsupportedOperationException("supports only ${clazz.qualifiedName} for which one has to call ${AssertionFormatter::formatGroup.name}")

    /**
     * Checks whether [assertionGroup] is [T] or a sub type and if so, calls [formatGroupHeaderAndGetChildParameterObject]
     * and uses the resulting child-[AssertionFormatterParameterObject] to format [AssertionGroup.assertions].
     *
     * If [assertionGroup] is *not* [T] or a sub type, then it throws an [UnsupportedOperationException].
     *
     * @param assertionGroup The assertion group which should be formatted.
     * @param parameterObject The parameter object which contains inter alia the [sb][AssertionFormatterParameterObject.sb]
     *   to which the result will be appended.
     * @param formatAssertions The function which should be called to format the
     *   [assertions][AssertionGroup.assertions] of the given [assertionGroup].
     *   It itself expects a function which formats single [Assertion]s in the context of the given
     *   [assertionGroup].
     *
     * @see [AssertionFormatter.formatGroup].
     *
     * @throws UnsupportedOperationException if the given [assertionGroup] is not [T] or a sub type of it.
     */
    final override fun formatGroup(assertionGroup: AssertionGroup, parameterObject: AssertionFormatterParameterObject, formatAssertions: (AssertionFormatterParameterObject, (Assertion) -> Unit) -> Unit) = when {
        clazz.isInstance(assertionGroup.type) -> formatSpecificGroup(assertionGroup, parameterObject, formatAssertions)
        else -> throw UnsupportedOperationException("supports only ${clazz.qualifiedName}")
    }

    private fun formatSpecificGroup(assertionGroup: AssertionGroup, parameterObject: AssertionFormatterParameterObject, formatAssertions: (AssertionFormatterParameterObject, (Assertion) -> Unit) -> Unit) {
        val childParameterObject = formatGroupHeaderAndGetChildParameterObject(assertionGroup, parameterObject)
        formatGroupAssertions(formatAssertions, childParameterObject)
    }

    /**
     * Formats the group header of the given [assertionGroup] (with [type][AssertionGroup.type] [T]) -- appends the
     * result to the [sb][AssertionFormatterParameterObject.sb] of the given [parameterObject] -- and returns the
     * [AssertionFormatterParameterObject] which shall be used for the [AssertionGroup.assertions].
     *
     * @param assertionGroup The assertion group which should be formatted.
     * @param parameterObject The parameter object which contains inter alia the [sb][AssertionFormatterParameterObject.sb]
     *   to which the result will be appended.
     *
     * @return The [AssertionFormatterParameterObject] which shall be used for the [AssertionGroup.assertions].
     */
    protected abstract fun formatGroupHeaderAndGetChildParameterObject(assertionGroup: AssertionGroup, parameterObject: AssertionFormatterParameterObject): AssertionFormatterParameterObject

    /**
     * Formats the [AssertionGroup.assertions] -- has to call the given [formatAssertions] function in order that
     * the [AssertionFormatterController] can steer the process.
     *
     * @param formatAssertions The function which should be called to format the [assertions][AssertionGroup.assertions]
     *   of a given [AssertionGroup]. It itself expects a function which formats single [Assertion]s in the context
     *   of the given [AssertionGroup].
     * @param childParameterObject The parameter object which shall be used to format [AssertionGroup.assertions] -- contains
     *   inter alia the [sb][AssertionFormatterParameterObject.sb] to which the result will be appended.
     */
    protected abstract fun formatGroupAssertions(formatAssertions: (AssertionFormatterParameterObject, (Assertion) -> Unit) -> Unit, childParameterObject: AssertionFormatterParameterObject)
}
