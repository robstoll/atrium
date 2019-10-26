package ch.tutteli.atrium.api.infix.en_GB.creating.list.get.builders

import ch.tutteli.atrium.api.infix.en_GB.creating.list.get.builders.impl.ListGetStepImpl
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.SubjectProvider

/**
 * Represents the extension point for another option after a `get index`-step within a
 * sophisticated `get` assertion building process for [List].
 *
 * @param E The element type of the [List].
 * @param T A subtype of [List].
 */
interface ListGetStep<E, T: List<E>> {
    /**
     * The [Expect] for which this assertion is created
     */
    val plant: Expect<T>

    /**
     * The given index which will be used to perform the [List.get].
     */
    val index: Int

    /**
     * Makes the assertion that the given [index] is within the bounds of [Expect.subject][SubjectProvider.subject] and that
     * the corresponding entry holds all assertions the given [assertionCreator] might create for it.
     *
     * @return This plant to support a fluent API.
     * @throws AssertionError Might throw an [AssertionError] if a created [Expect]s (by calling [assertionCreator])
     *   does not hold.
     * @throws IllegalArgumentException in case the given [assertionCreator] did not create a single assertion.
     */
    infix fun assertIt(assertionCreator: Expect<E>.() -> Unit): Expect<T>

    companion object {
        fun <E, T: List<E>> create(plant: Expect<T>, index: Int): ListGetStep<E, T>
            = ListGetStepImpl(plant, index)
    }
}

