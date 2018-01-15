package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType

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
    private val clazz: Class<T>
) : AssertionFormatter {

    /**
     * Returns true if the given [assertion] is an [AssertionGroup] and its [type][AssertionGroup.type]
     * is [T] or a sub type.
     */
    override final fun canFormat(assertion: Assertion)
        = assertion is AssertionGroup && clazz.isAssignableFrom(assertion.type::class.java)

    /**
     * Always throws an [UnsupportedOperationException], because this [AssertionFormatter] can only format
     * [AssertionGroup]s.
     *
     * @throws UnsupportedOperationException always!
     */
    override final fun formatNonGroup(assertion: Assertion, methodObject: AssertionFormatterMethodObject)
        = throw UnsupportedOperationException("supports only ${clazz.name} for which one has to call ${AssertionFormatter::formatGroup.name}")

    /**
     * Checks whether [assertionGroup] is [T] or a sub type and if so, calls [formatGroupHeaderAndGetChildMethodObject]
     * and uses the resulting child-[AssertionFormatterMethodObject] to format [AssertionGroup.assertions].
     *
     * If [assertionGroup] is *not* [T] or a sub type, then it throws an [UnsupportedOperationException].
     *
     * @param assertionGroup The assertion group which should be formatted.
     * @param methodObject The method object which contains inter alia the [sb][AssertionFormatterMethodObject.sb]
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
    override final fun formatGroup(assertionGroup: AssertionGroup, methodObject: AssertionFormatterMethodObject, formatAssertions: (AssertionFormatterMethodObject, (Assertion) -> Unit) -> Unit) = when {
        clazz.isAssignableFrom(assertionGroup.type::class.java) -> formatSpecificGroup(assertionGroup, methodObject, formatAssertions)
        else -> throw UnsupportedOperationException("supports only ${clazz.name}")
    }

    private fun formatSpecificGroup(assertionGroup: AssertionGroup, methodObject: AssertionFormatterMethodObject, formatAssertions: (AssertionFormatterMethodObject, (Assertion) -> Unit) -> Unit) {
        val childMethodObject = formatGroupHeaderAndGetChildMethodObject(assertionGroup, methodObject)
        formatGroupAssertions(formatAssertions, childMethodObject)
    }

    /**
     * Formats the group header of the given [assertionGroup] (with [type][AssertionGroup.type] [T]) -- appends the
     * result to the [sb][AssertionFormatterMethodObject.sb] of the given [methodObject] -- and returns the
     * [AssertionFormatterMethodObject] which shall be used for the [AssertionGroup.assertions].
     *
     * @param assertionGroup The assertion group which should be formatted.
     * @param methodObject The method object which contains inter alia the [sb][AssertionFormatterMethodObject.sb]
     *   to which the result will be appended.
     *
     * @return The [AssertionFormatterMethodObject] which shall be used for the [AssertionGroup.assertions].
     */
    protected abstract fun formatGroupHeaderAndGetChildMethodObject(assertionGroup: AssertionGroup, methodObject: AssertionFormatterMethodObject): AssertionFormatterMethodObject

    /**
     * Formats the [AssertionGroup.assertions] -- has to call the given [formatAssertions] function in order that
     * the [AssertionFormatterController] can steer the process.
     *
     * @param formatAssertions The function which should be called to format the [assertions][AssertionGroup.assertions]
     *   of a given [AssertionGroup]. It itself expects a function which formats single [Assertion]s in the context
     *   of the given [AssertionGroup].
     * @param childMethodObject The method object which shall be used to format [AssertionGroup.assertions] -- contains
     *   inter alia the [sb][AssertionFormatterMethodObject.sb] to which the result will be appended.
     */
    protected abstract fun formatGroupAssertions(formatAssertions: (AssertionFormatterMethodObject, (Assertion) -> Unit) -> Unit, childMethodObject: AssertionFormatterMethodObject)
}
