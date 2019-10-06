package ch.tutteli.atrium.api.fluent.en_GB.jdk8

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.zonedDateTime
import java.time.ZonedDateTime

/**
* Creates an [Expect] for the property [ZonedDateTime.year] of the subject of the assertion,
* so that further fluent calls are assertions about it.
*
* @return The newly created [Expect].
*/
val Expect<ZonedDateTime>.year get(): Expect<Int> = ExpectImpl.zonedDateTime.year(this).getExpectOfFeature()

/**
 * Expects that the property [ZonedDateTime.year] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and returns this assertion container.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun Expect<ZonedDateTime>.year(assertionCreator: Expect<Int>.() -> Unit): Expect<ZonedDateTime> =
    ExpectImpl.zonedDateTime.year(this).addToInitial(assertionCreator)
