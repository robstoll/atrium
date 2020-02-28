package ch.tutteli.atrium.api.infix.en_GB.creating.feature

import ch.tutteli.atrium.api.infix.en_GB.creating.feature.impl.FeatureOfStepImpl
import ch.tutteli.atrium.creating.Expect
import kotlin.reflect.KCallable

/**
 * Represents the extension point for another step after a `feature of`-step within a
 * sophisticated `feature` assertion building process.
 *
 * @param T the type of the subject of the assertion
 * @param C reflection type representing the extracted feature.
 * @param R the type of the feature
 */
interface FeatureOfStep<T, C : KCallable<R>, R> {
    /**
     * The [Expect] for which this assertion is created
     */
    val expect: Expect<T>

    /**
     * The [KCallable] which extracts the feature
     */
    val callable: C

    /**
     * Makes the assertion that the feature extracted by the [callable]
     * holds all assertions the given [assertionCreator] creates for it.
     *
     * @return This [Expect] to support a fluent API.
     * @throws AssertionError Might throw an [AssertionError] if one of the assertions does not hold.
     * @throws IllegalArgumentException in case the given [assertionCreator] did not create a single assertion.
     */
    infix fun assertIt(o: o): Expect<R>

    /**
     * Makes the assertion that the feature extracted by the [callable]
     * holds all assertions the given [assertionCreator] creates for it.
     *
     * @return This [Expect] to support a fluent API.
     * @throws AssertionError Might throw an [AssertionError] if one of the assertions does not hold.
     * @throws IllegalArgumentException in case the given [assertionCreator] did not create a single assertion.
     */
    infix fun assertIt(assertionCreator: Expect<R>.() -> Unit): Expect<T>

    companion object {
        fun <T, C : KCallable<R>, R> create(expect: Expect<T>, callable: C): FeatureOfStep<T, C, R> =
            FeatureOfStepImpl(expect, callable)
    }
}
