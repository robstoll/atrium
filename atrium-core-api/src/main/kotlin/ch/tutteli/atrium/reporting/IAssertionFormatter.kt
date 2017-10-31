package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IAssertionGroup

/**
 * Represents a formatter for [IAssertion]s and [IAssertionGroup]s.
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
     * @param assertion The [IAssertion] which builds the basis to answer the question whether this
     *                  [IAssertionFormatter] can format such kinds or not.
     *
     * @returns `true` if this [IAssertionFormatter] can [format] the given [assertion]; `false` otherwise.
     */
    fun canFormat(assertion: IAssertion): Boolean

    /**
     * Formats the given [assertion] and appends the result to the [sb][AssertionFormatterMethodObject.sb]
     * of the given [methodObject].
     *
     * This method should not be overridden (unfortunately an interface method cannot yet be final in Kotlin). This
     * default implementation checks whether the given [assertion] is an [IAssertionGroup] and calls
     * [throwNotIntendedForAssertionGroups] in case it is; calls [formatNonGroup] with the given [assertion] and
     * [methodObject] otherwise.
     *
     * This function should be in sync with [canFormat]. If [canFormat] returns `true` then this method should be able
     * to format the given [assertion] without problems. If [canFormat] returns `false` then this method should throw
     * an [UnsupportedOperationException].
     * Moreover, it should throw an [UnsupportedOperationException] in case the [assertion] is an [IAssertionGroup]
     * -- use [IAssertionFormatter.throwNotIntendedForAssertionGroups] for this purpose.
     *
     * @param assertion The assertion which should be formatted (not an [IAssertionGroup]).
     * @param methodObject The method object which contains inter alia the [sb][AssertionFormatterMethodObject.sb]
     *        to with the result will be appended.
     *
     * @throws UnsupportedOperationException in case this [IAssertionFormatter] cannot format the given [assertion]
     *         ([canFormat] returns `false`) or if [assertion] is an [IAssertionGroup].
     */
    fun format(assertion: IAssertion, methodObject: AssertionFormatterMethodObject) = when (assertion) {
        is IAssertionGroup -> IAssertionFormatter.throwNotIntendedForAssertionGroups()
        else -> formatNonGroup(assertion, methodObject)
    }

    /**
     * Formats the given [assertion] and appends the result to the [sb][AssertionFormatterMethodObject.sb]
     * of the given [methodObject].
     *
     * The callee is responsible that not an [IAssertionGroup] is passed to this function for which the outcome is
     * unknown/unspecified. Call [formatGroup] to format an [IAssertionGroup]. Call [format] in case you do not know
     * what type [assertion] is.
     *
     * This function should be in sync with [canFormat]. If [canFormat] returns `true` then this method should be able
     * to format the given [assertion] without problems. If [canFormat] returns `false` then this method should throw
     * an [UnsupportedOperationException].
     *
     * @param assertion The assertion which should be formatted (not an [IAssertionGroup]).
     * @param methodObject The method object which contains inter alia the [sb][AssertionFormatterMethodObject.sb]
     *        to with the result will be appended.
     *
     * @throws UnsupportedOperationException in case this [IAssertionFormatter] cannot format the given [assertion]
     *         ([canFormat] returns `false`).
     */
    fun formatNonGroup(assertion: IAssertion, methodObject: AssertionFormatterMethodObject)

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
     *        It itself expects a [AssertionFormatterMethodObject] which is used for the child assertions and a function
     *        which formats the child [IAssertion]s in the context of the given [assertionGroup].
     */
    fun formatGroup(assertionGroup: IAssertionGroup, methodObject: AssertionFormatterMethodObject, formatAssertions: (AssertionFormatterMethodObject, (IAssertion) -> Unit) -> Unit)

    companion object {
        val CALL_FORMAT_GROUP = "do not use `${IAssertionFormatter::format.name}` for " +
            "`${IAssertionGroup::class.simpleName}`s, " +
            "use `${IAssertionFormatter::formatGroup.name}` instead."

        fun throwNotIntendedForAssertionGroups() {
            throw UnsupportedOperationException(IAssertionFormatter.CALL_FORMAT_GROUP)
        }
    }
}
