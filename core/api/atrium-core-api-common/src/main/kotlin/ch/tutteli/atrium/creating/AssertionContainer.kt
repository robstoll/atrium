package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.reporting.translating.Untranslatable
import kotlin.reflect.KClass

/**
 * Represents the extension point of the logic level for subjects of type [T].
 *
 * In contrast to expectation functions defined for [Expect] which usually return [Expect], functions defined for
 * [AssertionContainer] return [Assertion] so that they can be appended to whatever we want.
 *
 * @param T The type of the subject of `this` expectation.
 */
//TODO 0.18.0 introduce ProofContainer
interface AssertionContainer<T> : @kotlin.Suppress("DEPRECATION") SubjectProvider<T> {
    /**
     * Either [Some] wrapping the subject of an [Assertion] or [None] in case a previous subject transformation
     * could not be carried out.
     */
    //TODO 0.18.0 remove override once we no longer extend SubjectProvider
    override val maybeSubject: Option<T>

    /**
     * Do not use yet, this is experimental and will definitely change in 0.17.0 or 0.18.0.
     *
     * Might be we completely remove it without prior notice.
     */
    //TODO 0.18.0/0.19.0 maybe it would be better to have proofFactories as val like we have components?
    @ExperimentalNewExpectTypes
    fun <I : Any> getImpl(kClass: KClass<I>, defaultFactory: () -> I): I

    /**
     * Do not use yet, this is experimental
     */
    @ExperimentalComponentFactoryContainer
    val components: ComponentFactoryContainer

    /**
     * Appends the given [assertion] to this container and returns an [Expect] which includes it.
     *
     * Whether the returned [Expect] is the same as the initial one is up to the implementation (i.e. if a mutable
     * structure is used or an immutable). Atrium strives for an immutable data structure in the long run and will
     * little by little refactor the code accordingly.
     *
     * @param assertion The assertion which will be appended to this container.
     *
     * @return an [Expect] for the subject of `this` expectation.
     *
     * @throws AssertionError Might throw an [AssertionError] in case [Assertion]s are immediately evaluated.
     */
    fun appendAssertion(assertion: Assertion): Expect<T>

    /**
     * Appends the [Assertion]s the given [assertionCreator] creates to this container and
     * returns an [Expect] which includes them.
     *
     * Whether the returned [Expect] is the same as the initial one is up to the implementation (i.e. if a mutable
     * structure is used or an immutable). Atrium strives for an immutable data structure in the long run and will
     * little by little refactor the code accordingly.
     *
     * @param assertionCreator The lambda which will create assertions.
     *
     * @return an [Expect] for the subject of `this` expectation.
     *
     * @throws AssertionError Might throw an [AssertionError] in case [Assertion]s are immediately evaluated.
     */
    fun appendAssertionsCreatedBy(assertionCreator: Expect<T>.() -> Unit): Expect<T>

    /**
     * Creates a [DescriptiveAssertion] based on the given [description], [expected] and [test]
     * and [appends][appendAssertion] it to the container.
     *
     * @param description The description of the assertion, e.g., `is less than`.
     * @param expected The expected value, e.g., `5`
     * @param test Indicates whether the assertion holds or fails.
     *
     * @return an [Expect] for the subject of `this` expectation.
     */
    //TODO remove SUPPRESS with 0.18.0
    @Suppress("UNCHECKED_CAST", "DEPRECATION")
    fun createAndAppendAssertion(description: String, expected: Any?, test: (T) -> Boolean): Expect<T> =
        appendAssertion(assertionBuilder.createDescriptive(this as Expect<T>, Untranslatable(description),expected, test))
}
