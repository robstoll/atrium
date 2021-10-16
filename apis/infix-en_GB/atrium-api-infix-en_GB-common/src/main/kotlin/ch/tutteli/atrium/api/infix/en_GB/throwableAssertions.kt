package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.Values
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.typeutils.CharSequenceOrNumberOrChar
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.causeIsA
import ch.tutteli.atrium.logic.creating.transformers.SubjectChangerBuilder
import kotlin.reflect.KClass

//TODO move to throwableExpectations.kt with 0.18.0
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

//TODO move to throwableExpectations.kt with 0.18.0
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
 * Expects that the property [Throwable.message] of the subject of `this` expectation is not null and contains
 * [expected]'s [toString] representation using a non-disjoint search.
 **
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed.
 * This function expects [CharSequenceOrNumberOrChar] (which is a typealias for [Any]) for your convenience,
 * so that you can mix [String] and [Int] for instance.
 *
 * @return an [Expect] for the subject of `this` expectation.
 */
@Deprecated(
    "Use messageToContain; will be removed with 1.0.0 at the latest",
    ReplaceWith(
        "this.messageToContain<T>(expected)",
        "ch.tutteli.atrium.api.infix.en_GB.messageToContain"
    )
)
infix fun <T : Throwable> Expect<T>.messageContains(expected: CharSequenceOrNumberOrChar): Expect<T> =
    this messageToContain values(expected)

/**
 * Expects that the property [Throwable.message] of the subject of `this` expectation is not null and contains
 * [values]'s [toString] representation using a non-disjoint search.
 **
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed
 * (this function expects `Any` for your convenience, so that you can mix [String] and [Int] for instance).
 *
 * @param values The values which are expected to be contained within [Throwable.message]
 *   -- use the function `values(t, ...)` to create a [Values].
 *
 * @return an [Expect] for the subject of `this` expectation.
 */
@Deprecated(
    "Use messageToContain; will be removed with 1.0.0 at the latest",
    ReplaceWith(
        "this.messageToContain<T>(values)",
        "ch.tutteli.atrium.api.infix.en_GB.messageToContain"
    )
)
infix fun <T : Throwable> Expect<T>.messageContains(values: Values<Any>): Expect<T> =
    message { toContain(values) }

//TODO move to throwableExpectations.kt with 0.18.0
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
    causeIsA(TExpected::class).transform()

//TODO move to throwableExpectations.kt with 0.18.0 and rename to causeIsInstanceOf
@PublishedApi // in order that _logic does not become part of the API we have this extra function
internal fun <TExpected : Throwable> Expect<out Throwable>.causeIsA(
    kClass: KClass<TExpected>
): SubjectChangerBuilder.ExecutionStep<Throwable?, TExpected> = _logic.causeIsA(kClass)


//TODO move to throwableExpectations.kt with 0.18.0
/**
 *
 * Expects that the property [Throwable.cause] of the subject *is a* [TExpected] (the same type or a sub-type) and
 * holds all assertions the given [assertionCreator] creates for it and returns this assertion container.
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
): Expect<TExpected> = causeIsA(TExpected::class).transformAndAppend(assertionCreator)
