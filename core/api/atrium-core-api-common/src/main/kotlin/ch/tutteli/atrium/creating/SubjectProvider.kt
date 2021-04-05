package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some

//TODO remove with 0.18.0
/**
 * Provides the subject of an [Assertion].
 *
 * Notice, this interface had its mere purpose to facilitate the transition from `Assert` to [Expect] -- `Assert` was
 * removed in 0.16.0 and thus this interface will be removed with 0.18.0.
 */
@Suppress("DEPRECATION")
@Deprecated("Will be removed with 0.18.0 without replacement, switch to Expect or AssertionContainer")
interface SubjectProvider<out T> {


    /**
     * Either [Some] wrapping the subject of an [Assertion] or [None] in case a previous subject change could not be
     * carried out.
     */
    val maybeSubject: Option<T>

    /**
     * Adds the given [assertion] to this holder.
     *
     * @param assertion The assertion which will be added to this holder.
     *
     * @return This holder to support a fluent API.
     * @throws AssertionError Might throw an [AssertionError] in case the [Assertion] does not hold.
     */
    //TODO deprecate and move to AssertionContainer ->
    fun addAssertion(assertion: Assertion): SubjectProvider<T>
}
