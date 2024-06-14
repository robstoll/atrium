package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.FeatureExpect
import java.sql.SQLException

/**
 * Extracts the [errorCode][SQLException.getErrorCode] property from the subject of the assertion.
 *
 * @since 1.3.0
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.SQLExceptionFeaturesSamples.errorCodeFeature
 */
val <T : SQLException> Expect<T>.errorCode: FeatureExpect<T, Int>
    get() = feature("errorCode", SQLException::getErrorCode)

/**
 * Extracts the [errorCode][SQLException.getErrorCode] property from the subject of the assertion and makes it the new subject.
 *
 * @since 1.3.0
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.SQLExceptionFeaturesSamples.sqlStateFeature
 */
fun <T : SQLException> Expect<T>.errorCode(assertionCreator: Expect<Int>.() -> Unit) =
    feature("errorCode", SQLException::getErrorCode, assertionCreator)

/**
 * Extracts the [sqlState][SQLException.getSQLState] property from the subject of the assertion.
 *
 * @since 1.3.0
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.SQLExceptionFeaturesSamples.errorCode
 */
val <T : SQLException> Expect<T>.sqlState: FeatureExpect<T, String>
    get() = feature("sqlState", SQLException::getSQLState)

/**
 * Extracts the [sqlState][SQLException.getSQLState] property from the subject of the assertion and makes it the new subject.
 *
 * @since 1.3.0
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.SQLExceptionFeaturesSamples.sqlState
 */
fun <T : SQLException> Expect<T>.sqlState(assertionCreator: Expect<String>.() -> Unit) =
    feature("sqlState", SQLException::getSQLState, assertionCreator)
