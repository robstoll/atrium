package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.IAssertionGroupType

/**
 * A base type for [IAssertionFormatter] which [canFormat][IAssertionFormatter.canFormat] only [IAssertionGroup] of a
 * specific [IAssertionGroupType].
 *
 * @param T The [IAssertionGroupType] which the concrete sub class [canFormat][IAssertionFormatter.canFormat].
 */
abstract class SingleAssertionGroupTypeFormatter<in T : IAssertionGroupType>(
    private val clazz: Class<T>
) : IAssertionFormatter {

    /**
     * Returns true if the given [assertion] is an [IAssertionGroup] and its [type][IAssertionGroup.type]
     * is [T] or a sub type.
     */
    override final fun canFormat(assertion: IAssertion)
        = assertion is IAssertionGroup && clazz.isAssignableFrom(assertion.type::class.java)

    /**
     * Always throws an [UnsupportedOperationException], because this [IAssertionFormatter] can only format
     * [IAssertionGroup]s.
     *
     * @throws UnsupportedOperationException always!
     */
    override final fun formatNonGroup(assertion: IAssertion, methodObject: AssertionFormatterMethodObject)
        = throw UnsupportedOperationException("supports only ${clazz.name} for which one has to call ${IAssertionFormatter::formatGroup.name}")

    /**
     * Checks whether [assertionGroup] is [T] or a sub type and if so, delegates to the concrete implementation;
     * otherwise throws an [UnsupportedOperationException].
     *
     * @see [IAssertionFormatter.formatGroup].
     *
     * @throws UnsupportedOperationException if the given [assertionGroup] is not [T] or a sub type of it.
     */
    override final fun formatGroup(assertionGroup: IAssertionGroup, methodObject: AssertionFormatterMethodObject, formatAssertions: ((IAssertion) -> Unit) -> Unit) = when {
        clazz.isAssignableFrom(assertionGroup.type::class.java) -> formatSpecificGroup(assertionGroup, methodObject, formatAssertions)
        else -> throw UnsupportedOperationException("supports only ${clazz.name}")
    }

    /**
     * Formats the given [assertionGroup] (with [type][IAssertionGroup.type] [T]) and appends the result to the
     * [sb][AssertionFormatterMethodObject.sb] of the given [methodObject].
     *
     * @param assertionGroup The assertion group which should be formatted.
     * @param methodObject The method object which contains inter alia the [sb][AssertionFormatterMethodObject.sb]
     *        to with the result will be appended.
     * @param formatAssertions The function which should be called to format the
     *        [assertions][IAssertionGroup.assertions] of the given [assertionGroup].
     *        It itself expects a function which formats single [IAssertion]s in the context of the given
     *        [assertionGroup].
     */
    abstract fun formatSpecificGroup(assertionGroup: IAssertionGroup, methodObject: AssertionFormatterMethodObject, formatAssertions: ((IAssertion) -> Unit) -> Unit): Unit

}
