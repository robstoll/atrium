package ch.tutteli.assertk.creating

import ch.tutteli.assertk.isNotNull

/**
 * Represents an assertion plant for nullable types.
 *
 * In contrast to a [IAssertionPlant] it does not provide methods for quality assurance ([IAssertionPlant.checkAssertions]) but only
 * one method [isNull] which immediately evaluates if the given [subject] is `null` as expected.
 *
 * @see isNotNull for another assertion factory method which then allows to define further assertions on the guaranteed non-null [subject].
 */
interface IAssertionPlantNullable<out T : Any?> : IAssertionPlantWithCommonFields<T> {
    fun isNull()
}
