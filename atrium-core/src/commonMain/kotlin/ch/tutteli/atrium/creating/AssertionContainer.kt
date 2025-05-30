//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.reporting.BUG_REPORT_URL
import ch.tutteli.atrium.reporting.translating.Translatable
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
@Deprecated("Switch to ProofContainer, will be removed with 2.0.0 at the latest", ReplaceWith("ProofContainer<T>"))
interface AssertionContainer<T> {
    /**
     * Either [Some] wrapping the subject of an [Assertion] or [None] in case a previous subject transformation
     * could not be carried out.
     */
    val maybeSubject: Option<T>

    /**
     * Do not use yet, this is experimental and might change or be removed without prior notice.
     */
    //TODO 1.3.0 maybe it would be better to have proofFactories as val like we have components?
    //TODO 1.3.0 I guess it would make sense to get rid of getImpl and only use the ComponentFactoryContainer approach
    // however, check if extensibility for a library author is still given. We don't want that a consumer of a third-party
    // expectation function collection-library needs to use an own expectation verb
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
    @Deprecated("Switch from Assertion to Proof, AssertionContainer will be removed with 2.0.0 at the latest")
    fun append(assertion: Assertion): Expect<T>

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
    @Deprecated(
        "Use appendAsGroupIndicateIfOneCollected and define the alternative or pass an empty list if you don't have any",
        ReplaceWith(
            "this.appendAsGroupIndicateIfOneCollected(ExpectationCreatorWithUsageHints(usageHintsOverloadWithoutExpectationCreator = listOf(/* ... add a usage hint in case you have an overload which does not expect an expectationCreator */), expectationCreator = assertionCreator)).first",
            "ch.tutteli.atrium.creating.ExpectationCreatorWithUsageHints",
        )
    )
    fun appendAsGroup(assertionCreator: Expect<T>.() -> Unit): Expect<T>

    /**
     * Creates a [DescriptiveAssertion] based on the given [description], [expected] and [test]
     * and [append]s it to the container.
     *
     * @param description The description of the assertion, e.g., `is less than`.
     * @param expected The expected value, e.g., `5`
     * @param test Indicates whether the assertion holds or fails.
     *
     * @return an [Expect] for the subject of `this` expectation.
     */
    //TODO 1.3.0 deprecate
    fun createAndAppend(description: String, expected: Any?, test: (T) -> Boolean): Expect<T> =
        createAndAppend(Untranslatable(description), expected, test)

    /**
     * Creates a [DescriptiveAssertion] based on the given [description], [expected] and [test]
     * and [append]s it to the container.
     *
     * @param description The description of the assertion, e.g., `is less than`.
     * @param expected The expected value, e.g., `5`
     * @param test Indicates whether the assertion holds or fails.
     *
     * @return an [Expect] for the subject of `this` expectation.
     */
    //TODO 1.3.0 deprecate
    //TODO remove SUPPRESS with 1.3.0 once the toExpect function is in core
    @Suppress("UNCHECKED_CAST")
    fun createAndAppend(description: Translatable, expected: Any?, test: (T) -> Boolean): Expect<T> =
        append(
            assertionBuilder.descriptive
                .withTest(this as Expect<T>, test)
                .withDescriptionAndRepresentation(description, expected)
                .build()
        )
}

fun <T> AssertionContainer<T>.toExpect(): Expect<T> =
    when (this) {
        is ExpectInternal<T> -> this
        else -> throw UnsupportedOperationException("Unsupported AssertionContainer: $this -- Please open an issue that a hook shall be implemented: $BUG_REPORT_URL?template=feature_request&title=Hook%20for%20AssertionContainer.toExpect")
    }
