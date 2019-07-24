package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.collectors.assertionCollector

/**
 * Option step which allows to decide what should be done with the extracted feature of type [R].
 */
class ExtractedFeatureOption<T, R>(
    private val assertionContainer: Expect<T>,
    private val extract: Expect<T>.() -> Expect<R>,
    private val extractAndApply: Expect<T>.(Expect<R>.() -> Unit) -> Expect<R>
) {
    /**
     * Returns the newly created [Expect] for the feature.
     */
    fun getExpectOfFeature(): Expect<R> = extract(assertionContainer)

    /**
     * Collects the assertions the given [assertionCreator] might create for the new [Expect] of the feature
     * and returns them as a single [Assertion]
     *
     * @returns An assertion consisting of all assertions the given [assertionCreator] might create
     *   for the new [Expect] of the feature.
     * @throws IllegalStateException in case the given [assertionCreator] does not create a single assertion
     */
    fun collect(assertionCreator: Expect<R>.() -> Unit): Assertion = assertionCollector.collect(assertionContainer) {
        extractAndApply(this, assertionCreator)
    }

    /**
     * Creates a new [Expect] for the feature, adds all assertions the given [assertionCreator] might create it
     * and returns the initial [Expect].
     *
     * @returns An assertion consisting of all assertions the given [assertionCreator] might create
     *   for the new [Expect] of the feature.
     * @throws IllegalStateException in case the given [assertionCreator] does not create a single assertion
     */
    fun addToInitial(assertionCreator: Expect<R>.() -> Unit): Expect<T> {
        // collect also checks that the user specified at least one assertion in the assertionCreator lambda
        return assertionContainer.addAssertion(collect(assertionCreator))
    }
}
