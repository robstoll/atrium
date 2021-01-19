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
 */
val <T : Throwable> Expect<T>.message: Expect<String>
    get() = feature(Throwable::message).notToBeNull()

/**
 * Expects that the property [Throwable.message] of the subject of `this` expectation is not null and
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of `this` expectation.
 *
 * @return an [Expect] for the subject of `this` expectation.
 */
fun <T : Throwable> Expect<T>.message(assertionCreator: Expect<String>.() -> Unit): Expect<T> =
    feature(Throwable::message) { notToBeNull(assertionCreator) }

/**
 * Expects that the property [Throwable.message] of the subject of `this` expectation is not null and contains
 * [expected]'s [toString] representation and the [toString] representation of the [otherExpected] (if given),
 * using a non disjoint search.
 *
 * It is more or less a shortcut for `message { contains.atLeast(1).values(expected, otherExpected) }`, depending on
 * the implementation though.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed.
 * This function expects [CharSequenceOrNumberOrChar] (which is a typealias for [Any]) for your convenience,
 * so that you can mix [String] and [Int] for instance.
 *
 * @return an [Expect] for the subject of `this` expectation.
 */
fun <T : Throwable> Expect<T>.messageContains(
    expected: CharSequenceOrNumberOrChar,
    vararg otherExpected: CharSequenceOrNumberOrChar
): Expect<T> = message { contains(expected, *otherExpected) }


/**
 * Expects that the property [Throwable.cause] of the subject *is a* [TExpected] (the same type or a sub-type),
 * creates an [Expect] of the [TExpected] type for it and returns it.
 *
 * @return The newly created [Expect] for the property [Throwable.cause] of the subject of `this` expectation.
 *
 * @since 0.10.0
 */
inline fun <reified TExpected : Throwable> Expect<out Throwable>.cause(): Expect<TExpected> =
    causeIsA(TExpected::class).transform()

@PublishedApi // in order that _logic does not become part of the API we have this extra function
internal fun <TExpected : Throwable> Expect<out Throwable>.causeIsA(
    kClass: KClass<TExpected>
): SubjectChangerBuilder.ExecutionStep<Throwable?, TExpected> = _logic.causeIsA(kClass)


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
 * @since 0.10.0
 */
inline fun <reified TExpected : Throwable> Expect<out Throwable>.cause(
    noinline assertionCreator: Expect<TExpected>.() -> Unit
): Expect<TExpected> = causeIsA(TExpected::class).transformAndAppend(assertionCreator)
