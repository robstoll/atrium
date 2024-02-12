package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.typeutils.CharSequenceOrNumberOrChar
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.causeIsA
import ch.tutteli.atrium.logic.creating.transformers.SubjectChangerBuilder
import kotlin.reflect.KClass

/**
 * Expects that the property [Throwable.message] of the subject of `this` expectation is not null,
 * creates an [Expect] for it and returns it.
 *
 * @return The newly created [Expect] for the property [Throwable.message] of the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.ThrowableFeatureExtractorSamples.message
 */
val <T : Throwable> Expect<T>.message: Expect<String>
    get() = feature(Throwable::message).notToEqualNull()

/**
 * Expects that the property [Throwable.message] of the subject of `this` expectation is not null and
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of `this` expectation.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.ThrowableFeatureExtractorSamples.messageFeature
 */
fun <T : Throwable> Expect<T>.message(assertionCreator: Expect<String>.() -> Unit): Expect<T> =
    feature(Throwable::message) { notToEqualNull(assertionCreator) }

/**
 * Expects that the property [Throwable.message] of the subject of `this` expectation is not null and contains
 * [expected]'s [toString] representation and the [toString] representation of the [otherExpected] (if given),
 * using a non-disjoint search.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed.
 * This function expects [CharSequenceOrNumberOrChar] (which is a typealias for [Any]) for your convenience,
 * so that you can mix [String] and [Int] for instance.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.ThrowableFeatureExtractorSamples.messageToContain
 *
 * @since 0.17.0
 */
fun <T : Throwable> Expect<T>.messageToContain(
    expected: CharSequenceOrNumberOrChar,
    vararg otherExpected: CharSequenceOrNumberOrChar
): Expect<T> = message { toContain(expected, *otherExpected) }

/**
 * Expects that the property [Throwable.cause] of the subject *is a* [ExpectedThrowableT] or a subtype thereof,
 * creates an [Expect] of the [ExpectedThrowableT] type for it and returns it.
 *
 * @return The newly created [Expect] for the property [Throwable.cause] of the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.ThrowableFeatureExtractorSamples.causeFeature
 *
 * @since 0.10.0
 */
inline fun <reified ExpectedThrowableT : Throwable> Expect<out Throwable>.cause(): Expect<ExpectedThrowableT> =
    causeToBeAnInstanceOf(ExpectedThrowableT::class).transform()

@PublishedApi // in order that _logic does not become part of the API we have this extra function
internal fun <ExpectedThrowableT : Throwable> Expect<out Throwable>.causeToBeAnInstanceOf(
    kClass: KClass<ExpectedThrowableT>
): SubjectChangerBuilder.ExecutionStep<Throwable?, ExpectedThrowableT> = _logic.causeIsA(kClass)

/**
 * Expects that the property [Throwable.cause] of the subject *is a* [ExpectedThrowableT] or a subtype thereof and
 * holds all assertions the given [assertionCreator] creates for it.
 *
 * Notice, in contrast to other expectation functions which expect an [assertionCreator], this function returns not
 * [Expect] of the initial type, which was some type `T `, but an [Expect] of the specified type [ExpectedThrowableT].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.ThrowableFeatureExtractorSamples.cause
 *
 * @since 0.10.0
 */
inline fun <reified ExpectedThrowableT : Throwable> Expect<out Throwable>.cause(
    noinline assertionCreator: Expect<ExpectedThrowableT>.() -> Unit
): Expect<ExpectedThrowableT> = causeToBeAnInstanceOf(ExpectedThrowableT::class).transformAndAppend(assertionCreator)
