package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.creating.AssertImpl
import java.math.BigDecimal

@Deprecated("Verwende `istNumerischGleichWie` wenn du erwartest, dass die folgende Behauptung stimmt:\n" +
    "`esGilt(BigDecimal(\"10\").ist(BigDecimal(\"10.0\"))`\n" +
    "Erwartest du hingegen, dass die Behauptung falsch ist (da `BigDecimal.scale` anders ist), dann verwende `istGleichInklusiveScale`.",
    ReplaceWith("istNumerischGleichWie(expected) or istGleichInklusiveScale(expected)"))
@Suppress("UNUSED_PARAMETER", "unused")
fun <T : BigDecimal> Assert<T>.ist(expected: T): Nothing
    = throw UnsupportedOperationException("BigDecimal.equals() vergleicht auch BigDecimal.scale, was dir womöglich nicht bewusst war.\n" +
    "Falls doch und du möchtest, dass `scale` verglichen wird, dann verwende `istGleichInklusiveScale`.")

@Deprecated("Verwende `istNichtNichtNumerischGleichWie` wenn du erwartest, dass die folgende Behauptung falsch ist:\n" +
    "`esGilt(BigDecimal(\"10\").istNicht(BigDecimal(\"10.0\"))`\n" +
    "Erwartest du hingegen, dass die Behauptung stimmt (da `BigDecimal.scale` anders ist), dann verwende `istNichtGleichInklusiveScale`.",
    ReplaceWith("istNichtNumerischGleichWie(expected) or istNichtGleichInklusiveScale(expected)"))
@Suppress("UNUSED_PARAMETER", "unused")
fun <T : BigDecimal> Assert<T>.istNicht(expected: T): Nothing
    = throw UnsupportedOperationException("BigDecimal.equals() vergleicht auch BigDecimal.scale, was dir womöglich nicht bewusst war.\n" +
    "Falls doch und du möchtest, dass `scale` verglichen wird, dann verwende `istNichtGleichInklusiveScale`.")

/**
 * Makes the assertion that [AssertionPlant.subject] is numerically equal to [expected].
 *
 * By numerically is meant that it will not compare [BigDecimal.scale] (or in other words,
 * it uses `compareTo(expected) == 0`)
 *
 * Most of the time you want to use this function instead of [istGleichInklusiveScale] because
 * [istGleichInklusiveScale] compares [BigDecimal.scale].
 * Following the two functions compared:
 * - `esGilt(BigDecimal("10")).istGleichInklusiveScale(BigDecimal("10.0"))` does not hold
 * - `esGilt(BigDecimal("10")).istNumerischGleichWie(BigDecimal("10.0"))` holds.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : BigDecimal> Assert<T>.istNumerischGleichWie(expected: T)
    = addAssertion(AssertImpl.bigDecimal.isNumericallyEqualTo(this, expected))

/**
 * Makes the assertion that [AssertionPlant.subject] is not numerically equal to [expected].
 *
 * By numerically is meant that it will not compare [BigDecimal.scale] (or in other words,
 * it uses `compareTo(expected) != 0`)
 *
 * Most of the time you want to use this function instead of [istNichtGleichInklusiveScale] because
 * [istNichtGleichInklusiveScale] compares [BigDecimal.scale].
 * Following the two functions compared:
 * - `esGilt(BigDecimal("10")).istNichtGleichInklusiveScale(BigDecimal("10.0"))` holds
 * - `esGilt(BigDecimal("10")).istNichtNumerischGleichWie(BigDecimal("10.0"))` does not hold.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : BigDecimal> Assert<T>.istNichtNumerischGleichWie(expected: T)
    = addAssertion(AssertImpl.bigDecimal.isNotNumericallyEqualTo(this, expected))


/**
 * Makes the assertion that [AssertionPlant.subject] is equal to [expected] including [BigDecimal.scale].
 *
 * Most of the time you want to use [istNumerischGleichWie] which does not compare [BigDecimal.scale]
 * in contrast to this function.
 * Following the two functions compared:
 * - `esGilt(BigDecimal("10")).istGleichInklusiveScale(BigDecimal("10.0"))` does not hold
 * - `esGilt(BigDecimal("10")).istNumerischGleichWie(BigDecimal("10.0"))` holds.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : BigDecimal> Assert<T>.istGleichInklusiveScale(expected: T)
    = addAssertion(AssertImpl.bigDecimal.isEqualIncludingScale(this, expected, this::istNumerischGleichWie.name))

/**
 * Makes the assertion that [AssertionPlant.subject] is not equal to [expected] including [BigDecimal.scale].
 *
 * Most of the time you want to use [istNichtNumerischGleichWie] which does not compare [BigDecimal.scale]
 * in contrast to this function.
 * Following the two functions compared:
 * - `assert(BigDecimal("10")).istNichtGleichInklusiveScale(BigDecimal("10.0"))` holds.
 * - `assert(BigDecimal("10")).istNichtNumerischGleichWie(BigDecimal("10.0"))`  does not hold.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : BigDecimal> Assert<T>.istNichtGleichInklusiveScale(expected: T)
    = addAssertion(AssertImpl.bigDecimal.isNotEqualIncludingScale(this, expected))
