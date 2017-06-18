package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.Message

/**
 * Represents a formatter for an [IAssertion] and its [Message]s.
 */
interface IAssertionFormatter {

    /**
     * Denotes whether this [IAssertionFormatter] was created to format [IAssertion]s such
     * as the given [assertion] or not.
     *
     * This function should be in sync with [format] and [formatGroup]. If [assertion] is an [IAssertionGroup] and
     * this method returns `true` then [formatGroup] should be able to format the given [assertion]. On the other hand,
     * if it returns `false` then [formatGroup] should throw an [UnsupportedOperationException].
     * The same applies for [format] where format should additionally throw an [UnsupportedOperationException]
     * if an [IAssertionGroup] is passed.
     *
     * @returns `true` if this [IAssertionFormatter] can [format] the given [assertion]; `false` otherwise.
     */
    fun canFormat(assertion: IAssertion): Boolean

    /**
     * Formats the given [assertion] and appends the result to the [sb][AssertionFormatterMethodObject.sb]
     * of the given [methodObject].
     *
     * This function should be in sync with [canFormat]. If [canFormat] returns `true` then this method should be able
     * to format the given [assertion] without problems. If [canFormat] returns `false` then this method should throw
     * an [UnsupportedOperationException].
     * Moreover, it should throw an [UnsupportedOperationException] in case the [assertion] is an [IAssertionGroup]
     * -- use [IAssertionFormatter.notIntendedForAssertionGroups] for this purpose.
     *
     * @param assertion The assertion which should be formatted (not an [IAssertionGroup]).
     * @param methodObject The method object which contains inter alia the [sb][AssertionFormatterMethodObject.sb]
     *        to with the result will be appended.
     *
     * @throws UnsupportedOperationException in case this [IAssertionFormatter] cannot format the given [assertion]
     *         ([canFormat] returns `false`) or if [assertion] is an [IAssertionGroup].
     */
    fun format(assertion: IAssertion, methodObject: AssertionFormatterMethodObject)

    /**
     * Formats the given [assertionGroup] and appends the result to the [sb][AssertionFormatterMethodObject.sb]
     * of the given [methodObject].
     *
     * This function should be in sync with [canFormat]. If [canFormat] returns `true` then this method should be able
     * to format the given [assertionGroup] without problems. If [canFormat] returns `false` then this method should
     * throw an [UnsupportedOperationException].
     *
     * @param assertionGroup The assertion group which should be formatted.
     * @param methodObject The method object which contains inter alia the [sb][AssertionFormatterMethodObject.sb]
     *        to with the result will be appended.
     * @param formatAssertions The function which should be called to format the
     *        [assertions][IAssertionGroup.assertions] of the given [assertionGroup].
     *        It itself expects a function which formats single [IAssertion]s in the context of the given
     *        [assertionGroup].
     */
    fun formatGroup(assertionGroup: IAssertionGroup, methodObject: AssertionFormatterMethodObject, formatAssertions: ((IAssertion) -> Unit) -> Unit)

    companion object {
        val CALL_FORMAT_GROUP = "do not use `${IAssertionFormatter::format.name}` for " +
            "`${IAssertionGroup::class.simpleName}`s, " +
            "use `${IAssertionFormatter::formatGroup.name}` instead."

        fun notIntendedForAssertionGroups() {
            throw UnsupportedOperationException(IAssertionFormatter.CALL_FORMAT_GROUP)
        }
    }
}
