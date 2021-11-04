package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
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
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ThrowableExpectationSamples.messageFeature
 */
val <T : Throwable> Expect<T>.message: Expect<String>
    get() = it feature Throwable::message notToEqualNull o

/**
 * Expects that the property [Throwable.message] of the subject of `this` expectation is not null and
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of `this` expectation.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ThrowableExpectationSamples.message
 */
infix fun <T : Throwable> Expect<T>.message(assertionCreator: Expect<String>.() -> Unit): Expect<T> =
    it feature of(Throwable::message) { it notToEqualNull assertionCreator }

/**
 * Expects that the property [Throwable.cause] of the subject *is a* [TExpected] (the same type or a sub-type),
 * creates an [Expect] of the [TExpected] type for it and returns it.
 *
 * @return The newly created [Expect] for the property [Throwable.cause] of the subject of `this` expectation
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ThrowableExpectationSamples.causeFeature
 *
 * @since 0.12.0
 */
inline fun <reified TExpected : Throwable> Expect<out Throwable>.cause(): Expect<TExpected> =
    causeToBeAnInstanceOf(TExpected::class).transform()

@PublishedApi // in order that _logic does not become part of the API we have this extra function
internal fun <TExpected : Throwable> Expect<out Throwable>.causeToBeAnInstanceOf(
    kClass: KClass<TExpected>
): SubjectChangerBuilder.ExecutionStep<Throwable?, TExpected> = _logic.causeIsA(kClass)


/**
 *
 * Expects that the property [Throwable.cause] of the subject *is a* [TExpected] (the same type or a sub-type) and
 * holds all assertions the given [assertionCreator] creates for it.
 *
 * Notice, in contrast to other assertion functions which expect an [assertionCreator], this function returns not
 * [Expect] of the initial type, which was some type `T `, but an [Expect] of the specified type [TExpected].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ThrowableExpectationSamples.cause
 *
 * @since 0.12.0
 */
inline infix fun <reified TExpected : Throwable> Expect<out Throwable>.cause(
    noinline assertionCreator: Expect<TExpected>.() -> Unit
): Expect<TExpected> = causeToBeAnInstanceOf(TExpected::class).transformAndAppend(assertionCreator)
