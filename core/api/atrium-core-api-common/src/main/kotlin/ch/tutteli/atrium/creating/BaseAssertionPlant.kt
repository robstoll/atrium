package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some

/**
 * Represents a plant for [Assertion]s and offers methods to [addAssertion]s to this plant.
 *
 * It defines what [AssertionPlant] and [AssertionPlantNullable] have in common. However it is typically not used as
 * entry point for assertion functions. Most of the time you want to define an assertion function for [AssertionPlant],
 * [Assert] respectively ([Assert] is a `typealias` of [AssertionPlant]).
 *
 * @param T The type of the [subject] of this [BaseAssertionPlant].
 * @param A A subtype of [BaseAssertionPlant] which is used in the fluent style API and as self type.
 */
interface BaseAssertionPlant<out T : Any?, out A : BaseAssertionPlant<T, A>>: SubjectProvider<T>, AssertionHolder {

    /**
     * The provider which provides [subject].
     */
    val subjectProvider: () -> T

    override val maybeSubject: Option<T>
        get() {
            return try {
                @Suppress("DEPRECATION")
                Some(subject)
            } catch (@Suppress("DEPRECATION") e: PlantHasNoSubjectException) {
                None
            }
        }

    /**
     * Adds the given [assertion] to this plant.
     *
     * @param assertion The assertion which will be added to this plant.
     *
     * @return This plant to support a fluent API.
     *
     * @throws AssertionError Might throw an [AssertionError] in case [Assertion]s are immediately
     *   evaluated (see [ReportingAssertionPlant]).
     */
    override fun addAssertion(assertion: Assertion): A
}
