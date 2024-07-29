@file:Suppress("ObjectPropertyName", "FunctionName", "Deprecation")

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExpectGrouping
import ch.tutteli.atrium.creating.ExpectInternal
import ch.tutteli.atrium.reporting.BUG_REPORT_URL

//TODO 1.3.0 deprecate everything

/**
 * Appends the [Assertion] the given [assertionCreator] creates based on this [Expect].
 *
 * Use [_logic] for more sophisticated scenarios, like feature extraction.
 */
@Deprecated(
    "Use _coreAppend instead, will be removed with 2.0.0 at the latest",
    ReplaceWith("this._coreAppend { assertionCreator() }", "ch.tutteli.atrium._coreAppend")
)
inline fun <T> Expect<T>._logicAppend(assertionCreator: AssertionContainer<T>.() -> Assertion): Expect<T> =
    _logic.run { append(assertionCreator()) }

/**
 * Entry point to the logic level of Atrium -- which is one level deeper than the API --
 * on which assertion functions do not return [Expect]s but [Assertion]s and the like.
 *
 * Use [_logicAppend] in case you want to create and append an [Assertion] to this [Expect].
 */
@Deprecated(
    "Use _core instead, will be removed with 2.0.0 at the latest",
    ReplaceWith("this._core", "ch.tutteli.atrium._core")
)
inline val <T> Expect<T>._logic: AssertionContainer<T>
    get() = this.toAssertionContainer()

/**
 * Appends the [Assertion] the given [assertionCreator] creates based on this [ExpectGrouping].
 *
 * @since 1.1.0
 */
@Deprecated(
    "Use _coreAppend instead, will be removed with 2.0.0 at the latest",
    ReplaceWith("this._coreAppend { assertionCreator() }", "ch.tutteli.atrium._coreAppend")
)
inline fun ExpectGrouping._logicAppend(assertionCreator: AssertionContainer<*>.() -> Assertion): ExpectGrouping =
    _logic.run { append(assertionCreator()) }.toExpectGrouping()

/**
 * Turns this [ExpectGrouping] into an [AssertionContainer] without known subject type.
 *
 * @since 1.1.0
 */
@Deprecated(
    "Use _core instead, will be removed with 2.0.0 at the latest",
    ReplaceWith("this._core", "ch.tutteli.atrium._core")
)
inline val ExpectGrouping._logic: AssertionContainer<*>
    get() = when (this) {
        is ExpectInternal<*> -> this
        else -> throw UnsupportedOperationException("Unsupported Expect: $this -- please open an issue that a hook shall be implemented: $BUG_REPORT_URL?template=feature_request&title=Hook%20for%20ExpectGrouping.toAssertionContainer")
    }
