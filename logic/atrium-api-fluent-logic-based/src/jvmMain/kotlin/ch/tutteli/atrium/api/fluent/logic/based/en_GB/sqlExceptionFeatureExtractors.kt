package ch.tutteli.atrium.api.fluent.logic.based.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.FeatureExpect
import java.sql.SQLException

/**
 * Creates an [Expect] for the result of calling `getErrorCode()` on the subject of `this` expectation,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.SQLExceptionFeaturesSamples.errorCodeFeature
 *
 * @since 1.3.0
 */
val <T : SQLException> Expect<T>.errorCode: FeatureExpect<T, Int>
    get() = feature("errorCode", SQLException::getErrorCode)

/**
 * Expects that the result of calling `getErrorCode()` on the subject of `this` expectation
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of `this` expectation.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.SQLExceptionFeaturesSamples.sqlStateFeature
 *
 * @since 1.3.0
 */
fun <T : SQLException> Expect<T>.errorCode(assertionCreator: Expect<Int>.() -> Unit) =
    feature("errorCode", SQLException::getErrorCode, assertionCreator)

/**
 * Creates an [Expect] for the result of calling `getSQLState()` on the subject of `this` expectation,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.SQLExceptionFeaturesSamples.errorCode
 *
 * @since 1.3.0
 */
val <T : SQLException> Expect<T>.sqlState: FeatureExpect<T, String>
    get() = feature("sqlState", SQLException::getSQLState)

/**
 * Expects that the result of calling `getSQLState()` on the subject of `this` expectation
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of `this` expectation.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.SQLExceptionFeaturesSamples.sqlState
 *
 * @since 1.3.0
 */
fun <T : SQLException> Expect<T>.sqlState(assertionCreator: Expect<String>.() -> Unit) =
    feature("sqlState", SQLException::getSQLState, assertionCreator)
