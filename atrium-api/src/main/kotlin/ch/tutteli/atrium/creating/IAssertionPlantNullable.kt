package ch.tutteli.atrium.creating

/**
 * Represents an assertion plant for nullable types.
 *
 * In contrast to a [IAssertionPlant] it does not provide methods for quality assurance ([IAssertionPlant.checkAssertions]) but only
 * one method [isNull] which immediately evaluates if the given [subject] is `null` as expected.
 */
interface IAssertionPlantNullable<out T : Any?> : IAssertionPlantWithCommonFields<T> {
    fun isNull()
}
