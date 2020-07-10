package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable

/**
 * DSL Marker for [Expect].
 */
@DslMarker
annotation class ExpectMarker

/**
 * Sole purpose of this interface is to hide [AssertionContainer] from newcomers which usually don't have to deal with
 * this.
 *
 * Moreover, we separate Expect from AssertionContainer so that we can provide extension functions for
 * AssertionContainer which are more or less identical to the ones defined for api-fluent but don't return Expect but
 * [Assertion] etc.
 *
 * See https://github.com/robstoll/atrium-roadmap/wiki/Requirements#personas for more information about the personas.
 */
interface ExpectInternal<T> : Expect<T>, AssertionContainer<T>

/**
 * Represents the extension point for [Assertion] functions and sophisticated builders for subjects of type [T].
 *
 * Note, do not use [SubjectProvider] as this interface is only temporary and will most likely be removed without
 * further notice.
 *
 * @param T The type of the [subject] of the assertion.
 */
@ExpectMarker
interface Expect<T> : SubjectProvider<T> {

    /**
     * Adds the assertions created by the [assertionCreator] lambda to this container and
     * adds a failing assertion to this container in case the [assertionCreator] did not create a single assertion.
     *
     * A failing assertion is added to the container since we assume that the user or assertion function writer
     * did a mistake and forgot to add an assertion. This can happen if one only creates assertions without adding
     * them to the container or if one simply let the lambda empty.
     *
     * @param assertionCreator The receiver function which should create and add assertions to this container.
     *
     * @return An [Expect] for the current subject of the assertion.
     * @throws AssertionError Might throw an [AssertionError] depending on the concrete implementation.
     */
    fun addAssertionsCreatedBy(assertionCreator: Expect<T>.() -> Unit): Expect<T>

    /**
     * Adds the given [assertion] to this container.
     *
     * @param assertion The assertion which will be added to this container.
     *
     * @return An [Expect] for the current subject of the assertion.
     *
     * @throws AssertionError Might throw an [AssertionError] in case [Assertion]s are immediately
     *   evaluated (see [ReportingAssertionContainer]).
     */
    override fun addAssertion(assertion: Assertion): Expect<T>


    /**
     * Creates a [DescriptiveAssertion] based on the given [description], [expected] and [test]
     * and [adds][addAssertion] it to the container.
     *
     * @param description The description of the assertion, e.g., `is less than`.
     * @param expected The expected value, e.g., `5`
     * @param test Indicates whether the assertion holds or fails.
     *
     * @return An [Expect] for the current subject of the assertion.
     * @throws AssertionError Might throw an [AssertionError] in case [Assertion]s are immediately
     *   evaluated (see [ReportingAssertionContainer]).
     */
    fun createAndAddAssertion(description: String, expected: Any?, test: (T) -> Boolean): Expect<T> =
        createAndAddAssertion(Untranslatable(description), expected, test)

    /**
     * Creates a [DescriptiveAssertion] based on the given [description], [expected] and [test]
     * and [adds][addAssertion] it to the container.
     *
     * @param description The description of the assertion in form of a [Translatable].
     * @param expected The expected value, e.g., `5`
     * @param test Indicates whether the assertion holds or fails.
     *
     * @return An [Expect] for the current subject of the assertion.
     * @throws AssertionError Might throw an [AssertionError] in case [Assertion]s are immediately
     *   evaluated (see [ReportingAssertionContainer]).
     */
    fun createAndAddAssertion(description: Translatable, expected: Any?, test: (T) -> Boolean): Expect<T> =
        addAssertion(assertionBuilder.createDescriptive(this, description, expected, test))

}
