package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup

/**
 * Represents a formatter for [Assertion]s and [AssertionGroup]s.
 */
interface AssertionFormatter {

    /**
     * Denotes whether this [AssertionFormatter] was created to format [Assertion]s such
     * as the given [assertion] or not.
     *
     * This function should be in sync with [format] and [formatGroup]. If [assertion] is an [AssertionGroup] and
     * this method returns `true` then [formatGroup] should be able to format the given [assertion]. On the other hand,
     * if it returns `false` then [formatGroup] should throw an [UnsupportedOperationException].
     * The same applies for [format] where format should additionally throw an [UnsupportedOperationException]
     * if an [AssertionGroup] is passed.
     *
     * @param assertion The [Assertion] which builds the basis to answer the question whether this
     *   [AssertionFormatter] can format such kinds or not.
     *
     * @returns `true` if this [AssertionFormatter] ca [format] the given [assertion]; `false` otherwise.
     */
    fun canFormat(assertion: Assertion): Boolean

    /**
     * Formats the given [assertion] and appends the result to the [sb][AssertionFormatterParameterObject.sb]
     * of the given [parameterObject].
     *
     * This method should not be overridden (unfortunately an interface method cannot yet be final in Kotlin). This
     * default implementation checks whether the given [assertion] is an [AssertionGroup] and calls
     * [throwNotIntendedForAssertionGroups] in case it is; calls [formatNonGroup] with the given [assertion] and
     * [parameterObject] otherwise.
     *
     * This function should be in sync with [canFormat]. If [canFormat] returns `true` then this method should be able
     * to format the given [assertion] without problems. If [canFormat] returns `false` then this method should throw
     * an [UnsupportedOperationException].
     * Moreover, it should throw an [UnsupportedOperationException] in case the [assertion] is an [AssertionGroup]
     * -- use [AssertionFormatter.throwNotIntendedForAssertionGroups] for this purpose.
     *
     * @param assertion The assertion which should be formatted (not an [AssertionGroup]).
     * @param parameterObject The parameter object which contains inter alia the [sb][AssertionFormatterParameterObject.sb]
     *   to which the result will be appended.
     *
     * @throws UnsupportedOperationException in case this [AssertionFormatter] cannot format the given [assertion]
     *   ([canFormat] returns `false`) or if [assertion] is an [AssertionGroup].
     */
    fun format(assertion: Assertion, parameterObject: AssertionFormatterParameterObject) = when (assertion) {
        is AssertionGroup -> AssertionFormatter.throwNotIntendedForAssertionGroups()
        else -> formatNonGroup(assertion, parameterObject)
    }

    /**
     * Formats the given [assertion] and appends the result to the [sb][AssertionFormatterParameterObject.sb]
     * of the given [parameterObject].
     *
     * The callee is responsible that not an [AssertionGroup] is passed to this function for which the outcome is
     * unknown/unspecified. Call [formatGroup] to format an [AssertionGroup]. Call [format] in case you do not know
     * what type [assertion] is.
     *
     * This function should be in sync with [canFormat]. If [canFormat] returns `true` then this method should be able
     * to format the given [assertion] without problems. If [canFormat] returns `false` then this method should throw
     * an [UnsupportedOperationException].
     *
     * @param assertion The assertion which should be formatted (not an [AssertionGroup]).
     * @param parameterObject The parameter object which contains inter alia the [sb][AssertionFormatterParameterObject.sb]
     *   to which the result will be appended.
     *
     * @throws UnsupportedOperationException in case this [AssertionFormatter] cannot format the given [assertion]
     *   ([canFormat] returns `false`).
     */
    fun formatNonGroup(assertion: Assertion, parameterObject: AssertionFormatterParameterObject)

    /**
     * Formats the given [assertionGroup] and appends the result to the [sb][AssertionFormatterParameterObject.sb]
     * of the given [parameterObject].
     *
     * Formatting an [AssertionGroup] makes up of two parts (where the first might be skipped):
     *
     * 1. formatting the group header (e.g. [name][AssertionGroup.name]: [subject][AssertionGroup.name])
     * 2. formatting the [AssertionGroup.assertions] where the control flow for formatting should be steered
     * by the [AssertionFormatterController] for which an [AssertionFormatter] has to call [formatAssertions]
     * and define a child-[AssertionFormatterParameterObject] which inter alia proposes the indent level to use, the
     * prefix which should be for each assertion etc.
     *
     * This function should be in sync with [canFormat]. If [canFormat] returns `true` then this method should be able
     * to format the given [assertionGroup] without problems. If [canFormat] returns `false` then this method should
     * throw an [UnsupportedOperationException].
     *
     * @param assertionGroup The assertion group which should be formatted.
     * @param parameterObject The parameter object which contains inter alia the [sb][AssertionFormatterParameterObject.sb]
     *   to which the result will be appended.
     * @param formatAssertions The function which should be called to format the
     *   [assertions][AssertionGroup.assertions] of the given [assertionGroup].
     *   It itself expects a [AssertionFormatterParameterObject] which is used for the child assertions and a function
     *   which formats the child [Assertion]s in the context of the given [assertionGroup].
     */
    fun formatGroup(assertionGroup: AssertionGroup, parameterObject: AssertionFormatterParameterObject, formatAssertions: (AssertionFormatterParameterObject, (Assertion) -> Unit) -> Unit)

    companion object {
        val CALL_FORMAT_GROUP = "do not use `${AssertionFormatter::format.name}` for " +
            "`${AssertionGroup::class.simpleName}`s, " +
            "use `${AssertionFormatter::formatGroup.name}` instead."

        fun throwNotIntendedForAssertionGroups() {
            throw UnsupportedOperationException(AssertionFormatter.CALL_FORMAT_GROUP)
        }
    }
}
