package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import kotlin.jvm.JvmName

/**
 * Turns `Expect<Array<E>>` into `Expect<Iterable<E>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(Array<out E>::asIterable)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 */
fun <E> Expect<out Array<out E>>.asIterable(): Expect<Iterable<E>> =
    ExpectImpl.changeSubject.unreported(this) { it.asIterable() }

/**
 * Expects that the subject of the assertion holds all assertions the given [assertionCreator] creates for
 * the subject as [Iterable].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(Array<out E>::asIterable, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 */
fun <E> Expect<Array<E>>.asIterable(assertionCreator: Expect<Iterable<E>>.() -> Unit): Expect<Array<E>> =
    apply { asIterable().addAssertionsCreatedBy(assertionCreator) }

/**
 * Expects that the subject of the assertion holds all assertions the given [assertionCreator] creates for
 * the subject as [Iterable].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(Array<out E>::asIterable, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 */
@JvmName("asIterableArrayOut")
fun <E> Expect<out Array<E>>.asIterable(assertionCreator: Expect<Iterable<E>>.() -> Unit): Expect<out Array<E>> =
    apply { asIterable().addAssertionsCreatedBy(assertionCreator) }

/**
 * Expects that the subject of the assertion holds all assertions the given [assertionCreator] creates for
 * the subject as [Iterable].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(Array<out E>::asIterable, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 */
@JvmName("asIterableEOut")
fun <E> Expect<Array<out E>>.asIterable(assertionCreator: Expect<Iterable<E>>.() -> Unit): Expect<Array<out E>> =
    apply { asIterable().addAssertionsCreatedBy(assertionCreator) }

/**
 * Expects that the subject of the assertion holds all assertions the given [assertionCreator] creates for
 * the subject as [Iterable].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(Array<out E>::asIterable, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 */
@JvmName("asIterableArrayAndEOut")
fun <E> Expect<out Array<out E>>.asIterable(assertionCreator: Expect<Iterable<E>>.() -> Unit): Expect<out Array<out E>> =
    apply { asIterable().addAssertionsCreatedBy(assertionCreator) }


/**
 * Turns `Expect<CharArray>` into `Expect<Iterable<Byte>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(ByteArray::asIterable)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 */
@JvmName("byteArrAsIterable")
fun Expect<ByteArray>.asIterable(): Expect<Iterable<Byte>> =
    ExpectImpl.changeSubject.unreported(this) { it.asIterable() }

/**
 * Expects that the subject of the assertion holds all assertions the given [assertionCreator] creates for
 * the subject as [Iterable].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(ByteArray::asIterable, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 */
@JvmName("byteArrAsIterable")
fun Expect<ByteArray>.asIterable(assertionCreator: Expect<Iterable<Byte>>.() -> Unit): Expect<ByteArray> =
    apply { asIterable().addAssertionsCreatedBy(assertionCreator) }


/**
 * Turns `Expect<CharArray>` into `Expect<Iterable<Char>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(CharArray::asIterable)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 */
@JvmName("charArrAsIterable")
fun Expect<CharArray>.asIterable(): Expect<Iterable<Char>> =
    ExpectImpl.changeSubject.unreported(this) { it.asIterable() }

/**
 * Expects that the subject of the assertion holds all assertions the given [assertionCreator] creates for
 * the subject as [Iterable].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(CharArray::asIterable, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 */
@JvmName("charArrAsIterable")
fun Expect<CharArray>.asIterable(assertionCreator: Expect<Iterable<Char>>.() -> Unit): Expect<CharArray> =
    apply { asIterable().addAssertionsCreatedBy(assertionCreator) }


/**
 * Turns `Expect<ShortArray>` into `Expect<Iterable<Short>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(ShortArray::asIterable)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 */
@JvmName("shortArrAsIterable")
fun Expect<ShortArray>.asIterable(): Expect<Iterable<Short>> =
    ExpectImpl.changeSubject.unreported(this) { it.asIterable() }

/**
 * Expects that the subject of the assertion holds all assertions the given [assertionCreator] creates for
 * the subject as [Iterable].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(ShortArray::asIterable, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 */
@JvmName("shortArrAsIterable")
fun Expect<ShortArray>.asIterable(assertionCreator: Expect<Iterable<Short>>.() -> Unit): Expect<ShortArray> =
    apply { asIterable().addAssertionsCreatedBy(assertionCreator) }


/**
 * Turns `Expect<IntArray>` into `Expect<Iterable<Int>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(IntArray::asIterable)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 */
@JvmName("intArrAsIterable")
fun Expect<IntArray>.asIterable(): Expect<Iterable<Int>> = ExpectImpl.changeSubject.unreported(this) { it.asIterable() }

/**
 * Expects that the subject of the assertion holds all assertions the given [assertionCreator] creates for
 * the subject as [Iterable].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(IntArray::asIterable, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 */
@JvmName("intArrAsIterable")
fun Expect<IntArray>.asIterable(assertionCreator: Expect<Iterable<Int>>.() -> Unit): Expect<IntArray> =
    apply { asIterable().addAssertionsCreatedBy(assertionCreator) }


/**
 * Turns `Expect<LongArray>` into `Expect<Iterable<Double>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(LongArray::asIterable)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 */
@JvmName("longArrAsIterable")
fun Expect<LongArray>.asIterable(): Expect<Iterable<Long>> =
    ExpectImpl.changeSubject.unreported(this) { it.asIterable() }

/**
 * Expects that the subject of the assertion holds all assertions the given [assertionCreator] creates for
 * the subject as [Iterable].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(LongArray::asIterable, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 */
@JvmName("longArrAsIterable")
fun Expect<LongArray>.asIterable(assertionCreator: Expect<Iterable<Long>>.() -> Unit): Expect<LongArray> =
    apply { asIterable().addAssertionsCreatedBy(assertionCreator) }


/**
 * Turns `Expect<FloatArray>` into `Expect<Iterable<Float>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(FloatArray::asIterable)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 */
@JvmName("floatArrAsIterable")
fun Expect<FloatArray>.asIterable(): Expect<Iterable<Float>> =
    ExpectImpl.changeSubject.unreported(this) { it.asIterable() }

/**
 * Expects that the subject of the assertion holds all assertions the given [assertionCreator] creates for
 * the subject as [Iterable].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(FloatArray::asIterable, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 */
@JvmName("floatArrAsIterable")
fun Expect<FloatArray>.asIterable(assertionCreator: Expect<Iterable<Float>>.() -> Unit): Expect<FloatArray> =
    apply { asIterable().addAssertionsCreatedBy(assertionCreator) }


/**
 * Turns `Expect<DoubleArray>` into `Expect<Iterable<Double>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(DoubleArray::asIterable)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 */
@JvmName("doubleArrAsIterable")
fun Expect<DoubleArray>.asIterable(): Expect<Iterable<Double>> =
    ExpectImpl.changeSubject.unreported(this) { it.asIterable() }

/**
 * Expects that the subject of the assertion holds all assertions the given [assertionCreator] creates for
 * the subject as [Iterable].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(DoubleArray::asIterable, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 */
@JvmName("doubleArrAsIterable")
fun Expect<DoubleArray>.asIterable(assertionCreator: Expect<Iterable<Double>>.() -> Unit): Expect<DoubleArray> =
    apply { asIterable().addAssertionsCreatedBy(assertionCreator) }


/**
 * Turns `Expect<BooleanArray>` into `Expect<Iterable<Boolean>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(BooleanArray::asIterable)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 */
@JvmName("boolArrAsIterable")
fun Expect<BooleanArray>.asIterable(): Expect<Iterable<Boolean>> =
    ExpectImpl.changeSubject.unreported(this) { it.asIterable() }

/**
 * Expects that the subject of the assertion holds all assertions the given [assertionCreator] creates for
 * the subject as [Iterable].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(BooleanArray::asIterable, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 */
@JvmName("boolArrAsIterable")
fun Expect<BooleanArray>.asIterable(assertionCreator: Expect<Iterable<Boolean>>.() -> Unit): Expect<BooleanArray> =
    apply { asIterable().addAssertionsCreatedBy(assertionCreator) }
