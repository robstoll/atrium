package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.assertions._isNotNumericallyEqualTo
import ch.tutteli.atrium.assertions._isNumericallyEqualTo
import ch.tutteli.atrium.assertions._toBe
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import java.math.BigDecimal

/**
 * Makes the assertion that [AssertionPlant.subject] is (equal to) [expected].
 *
 * Most of the time you want to use [istNumerischGleichWie] which does not compare [BigDecimal.scale]
 * in contrast to this function.
 * Following the two functions compared:
 * - `esGilt(BigDecimal.TEN).ist(BigDecimal("10.0"))` does not hold
 * - `esGilt(BigDecimal.TEN).istNumerischGleichWie(BigDecimal("10.0"))` holds.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : BigDecimal> Assert<T>.ist(expected: T)
    = addAssertion(_toBe(this, expected, this::istNumerischGleichWie.name))

@Suppress("unused")
@Deprecated("Verwende `istNichtNumerischGleichWie` wenn du erwartest, dass die folgende Behauptung falsch ist:\n" +
    "`esGilt(BigDecimal(\"10\").istNicht(BigDecimal(\"10.0\"))`\n" +
    "Erwartest du hingegen, dass die Behauptung stimmt, dann ist die Funktion `istNicht` schon die richtige. Verwende in diesem Fall die Überladung mit `Any`.",
    ReplaceWith("istNicht(expected as Any)"))
fun <T : BigDecimal> Assert<T>.istNicht(expected: T): Nothing
    = throw UnsupportedOperationException("BigDecimal::equals vergleicht auch BigDecimal::scale, was dir womöglich nicht bewusst war.\n" +
    "Falls doch und du immer noch `istNicht` anstelle von `istNichtNumerischGleichWie` verwenden möchtest, dann verwende die Überladung mit `Any`.")

/**
 * Makes the assertion that [AssertionPlant.subject] is numerically equal to [expected].
 *
 * By numerically is meant that it will not compare [BigDecimal.scale] (or in other words,
 * it uses `compareTo(expected) == 0`)
 *
 * Most of the time you want to use this function instead of [ist] because [ist] compares [BigDecimal.scale].
 * Following the two functions compared:
 * - `esGilt(BigDecimal.TEN).ist(BigDecimal("10.0"))` does not hold
 * - `esGilt(BigDecimal.TEN).istNumerischGleichWie(BigDecimal("10.0"))` holds.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : BigDecimal> Assert<T>.istNumerischGleichWie(expected: T)
    = addAssertion(_isNumericallyEqualTo(this, expected))

/**
 * Makes the assertion that [AssertionPlant.subject] is not numerically equal to [expected].
 *
 * By numerically is meant that it will not compare [BigDecimal.scale] (or in other words,
 * it uses `compareTo(expected) != 0`)
 * Most of the time you want to use this function instead of [istNicht] because [istNicht] compares [BigDecimal.scale].
 * Following the two functions compared:
 * - `esGilt(BigDecimal.TEN).istNicht(BigDecimal("10.0"))` holds
 * - `esGilt(BigDecimal.TEN).istNichtNumerischGleichWie(BigDecimal("10.0"))` does not hold.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : BigDecimal> Assert<T>.istNichtNumerischGleichWie(expected: T)
    = addAssertion(_isNotNumericallyEqualTo(this, expected))

