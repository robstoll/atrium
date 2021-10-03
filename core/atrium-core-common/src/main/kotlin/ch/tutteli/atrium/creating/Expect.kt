package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
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
interface ExpectInternal<T> : Expect<T>, AssertionContainer<T> {
    //TODO remove with 0.18.0 no longer necessary once it only exist in AssertionContainer
    /**
     * Either [Some] wrapping the subject of an [Assertion] or [None] in case a previous subject change could not be
     * carried out.
     */
    override val maybeSubject: Option<T>
}


/**
 * Represents the extension point for [Assertion] functions and sophisticated builders for subjects of type [T].
 *
 * @param T The type of the subject of `this` expectation.
 */
@Suppress("DEPRECATION")
//@ExpectMarker
interface Expect<T> : @kotlin.Suppress("DEPRECATION") SubjectProvider<T> {

    @Deprecated(
        "use _logic.maybeSubject; will be removed with 0.18.0",
        ReplaceWith("this._logic.maybeSubject", "ch.tutteli.atrium.logic._logic")
    )
    override val maybeSubject: Option<T>

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
     * @return an [Expect] for the subject of `this` expectation.
     */
    @Deprecated(
        "use _logic.appendAsGroup; will be removed with 0.18.0",
        ReplaceWith("this._logic.appendAsGroup(assertionCreator)", "ch.tutteli.atrium.logic._logic")
    )
    fun addAssertionsCreatedBy(assertionCreator: Expect<T>.() -> Unit): Expect<T>

    /**
     * Adds the given [assertion] to this container.
     *
     * @param assertion The assertion which will be added to this container.
     *
     * @return an [Expect] for the subject of `this` expectation.
     */
    @Deprecated(
        "use _logic.append; will be removed with 0.18.0",
        ReplaceWith("this._logic.append(assertion)", "ch.tutteli.atrium.logic._logic")
    )
    override fun addAssertion(assertion: Assertion): Expect<T>

    /**
     * Creates a [DescriptiveAssertion] based on the given [description], [expected] and [test]
     * and [adds][addAssertion] it to the container.
     *
     * @param description The description of the assertion, e.g., `is less than`.
     * @param expected The expected value, e.g., `5`
     * @param test Indicates whether the assertion holds or fails.
     *
     * @return an [Expect] for the subject of `this` expectation.
     */
    @Deprecated(
        "use _logic.createAndAppend; will be removed with 0.18.0",
        ReplaceWith(
            "this._logic.createAndAppend(description, expected, test)",
            "ch.tutteli.atrium.logic._logic"
        )
    )
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
     * @return an [Expect] for the subject of `this` expectation.
     */
    @Deprecated(
        "use _logic.createAndAppend; will be removed with 0.18.0",
        ReplaceWith(
            "this._logic.append(this._logic.createDescriptiveAssertion(description, expected, test))",
            "ch.tutteli.atrium.logic._logic",
            "ch.tutteli.atrium.logic.createDescriptiveAssertion"
        )
    )
    fun createAndAddAssertion(description: Translatable, expected: Any?, test: (T) -> Boolean): Expect<T> =
        addAssertion(assertionBuilder.createDescriptive(this, description, expected, test))
}
