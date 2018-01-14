package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.assertions._isNotNumericallyEqualTo
import ch.tutteli.atrium.assertions._isNumericallyEqualTo
import ch.tutteli.atrium.assertions._isEqualIncludingScale
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import java.math.BigDecimal

@Deprecated("Verwende `istNumerischGleichWie` wenn du erwartest, dass die folgende Behauptung stimmt:\n" +
    "`esGilt(BigDecimal(\"10\").ist(BigDecimal(\"10.0\"))`\n" +
    "Erwartest du hingegen, dass die Behauptung falsch ist (da `BigDecimal.scale` anders ist), dann verwende `istGleichInklusiveScale`.",
    ReplaceWith("istNumerischGleichWie(expected) or istGleichInklusiveScale(expected)"))
@Suppress("unused")
fun <T : BigDecimal> Assert<T>.ist(expected: T): Nothing
    = throw UnsupportedOperationException("BigDecimal.equals() vergleicht auch BigDecimal.scale, was dir womöglich nicht bewusst war.\n" +
    "Falls doch und du möchtest, dass `scale` verglichen wird, dann verwende `istGleichInklusiveScale`.")

@Deprecated("Verwende `istNichtNichtNumerischGleichWie` wenn du erwartest, dass die folgende Behauptung falsch ist:\n" +
    "`esGilt(BigDecimal(\"10\").istNicht(BigDecimal(\"10.0\"))`\n" +
    "Erwartest du hingegen, dass die Behauptung stimmt (da `BigDecimal.scale` anders ist), dann verwende die Überladung von `istNicht` mit `Any`.",
    ReplaceWith("istNichtNumerischGleichWie(expected) or istNicht(expected as Any)"))
@Suppress("unused")
fun <T : BigDecimal> Assert<T>.istNicht(expected: T): Nothing
    = throw UnsupportedOperationException("BigDecimal.equals() vergleicht auch BigDecimal.scale, was dir womöglich nicht bewusst war.\n" +
    "Falls doch und du möchtest, dass `scale` verglichen wird, dann verwende die Überladung von `istNicht` mit `Any`.")

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


/**
 * Makes the assertion that [AssertionPlant.subject] is (equal to) [expected] including [BigDecimal.scale].
 *
 * Most of the time you want to use [istNumerischGleichWie] which does not compare [BigDecimal.scale]
 * in contrast to this function.
 * Following the two functions compared:
 * - `esGilt(BigDecimal.TEN).istGleichInklusivScale(BigDecimal("10.0"))` does not hold
 * - `esGilt(BigDecimal.TEN).istNumerischGleichWie(BigDecimal("10.0"))` holds.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : BigDecimal> Assert<T>.istGleichInklusivScale(expected: T)
    = addAssertion(_isEqualIncludingScale(this, expected, this::istNumerischGleichWie.name))
