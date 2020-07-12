package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.changeSubject
import kotlin.jvm.JvmName

/**
 * Turns `Expect<Array<E>>` into `Expect<List<E>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(Array<out E>::asList)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @since 0.9.0
 */
fun <E> Expect<out Array<out E>>.asList(): Expect<List<E>> =
    _logic.changeSubject.unreported { it.asList() }

/**
 * Expects that the subject of the assertion holds all assertions the given [assertionCreator] creates for
 * the subject as [List].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(Array<out E>::asList, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @since 0.9.0
 */
fun <E> Expect<Array<E>>.asList(assertionCreator: Expect<List<E>>.() -> Unit): Expect<Array<E>> =
    apply { asList().addAssertionsCreatedBy(assertionCreator) }

/**
 * Expects that the subject of the assertion holds all assertions the given [assertionCreator] creates for
 * the subject as [List].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(Array<out E>::asList, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @since 0.9.0
 */
@JvmName("asListEOut")
fun <E> Expect<Array<out E>>.asList(assertionCreator: Expect<List<E>>.() -> Unit): Expect<Array<out E>> =
    apply { asList().addAssertionsCreatedBy(assertionCreator) }

/**
 * Turns `Expect<CharArray>` into `Expect<List<Byte>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(ByteArray::asList)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @since 0.9.0
 */
@JvmName("byteArrAsList")
fun Expect<ByteArray>.asList(): Expect<List<Byte>> =
    _logic.changeSubject.unreported { it.asList() }

/**
 * Expects that the subject of the assertion holds all assertions the given [assertionCreator] creates for
 * the subject as [List].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(ByteArray::asList, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @since 0.9.0
 */
@JvmName("byteArrAsList")
fun Expect<ByteArray>.asList(assertionCreator: Expect<List<Byte>>.() -> Unit): Expect<ByteArray> =
    apply { asList().addAssertionsCreatedBy(assertionCreator) }


/**
 * Turns `Expect<CharArray>` into `Expect<List<Char>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(CharArray::asList)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @since 0.9.0
 */
@JvmName("charArrAsList")
fun Expect<CharArray>.asList(): Expect<List<Char>> =
    _logic.changeSubject.unreported { it.asList() }

/**
 * Expects that the subject of the assertion holds all assertions the given [assertionCreator] creates for
 * the subject as [List].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(CharArray::asList, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @since 0.9.0
 */
@JvmName("charArrAsList")
fun Expect<CharArray>.asList(assertionCreator: Expect<List<Char>>.() -> Unit): Expect<CharArray> =
    apply { asList().addAssertionsCreatedBy(assertionCreator) }


/**
 * Turns `Expect<ShortArray>` into `Expect<List<Short>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(ShortArray::asList)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @since 0.9.0
 */
@JvmName("shortArrAsList")
fun Expect<ShortArray>.asList(): Expect<List<Short>> =
    _logic.changeSubject.unreported { it.asList() }

/**
 * Expects that the subject of the assertion holds all assertions the given [assertionCreator] creates for
 * the subject as [List].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(ShortArray::asList, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @since 0.9.0
 */
@JvmName("shortArrAsList")
fun Expect<ShortArray>.asList(assertionCreator: Expect<List<Short>>.() -> Unit): Expect<ShortArray> =
    apply { asList().addAssertionsCreatedBy(assertionCreator) }


/**
 * Turns `Expect<IntArray>` into `Expect<List<Int>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(IntArray::asList)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @since 0.9.0
 */
@JvmName("intArrAsList")
fun Expect<IntArray>.asList(): Expect<List<Int>> =
    _logic.changeSubject.unreported { it.asList() }

/**
 * Expects that the subject of the assertion holds all assertions the given [assertionCreator] creates for
 * the subject as [List].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(IntArray::asList, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @since 0.9.0
 */
@JvmName("intArrAsList")
fun Expect<IntArray>.asList(assertionCreator: Expect<List<Int>>.() -> Unit): Expect<IntArray> =
    apply { asList().addAssertionsCreatedBy(assertionCreator) }


/**
 * Turns `Expect<LongArray>` into `Expect<List<Double>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(LongArray::asList)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @since 0.9.0
 */
@JvmName("longArrAsList")
fun Expect<LongArray>.asList(): Expect<List<Long>> =
    _logic.changeSubject.unreported { it.asList() }

/**
 * Expects that the subject of the assertion holds all assertions the given [assertionCreator] creates for
 * the subject as [List].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(LongArray::asList, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @since 0.9.0
 */
@JvmName("longArrAsList")
fun Expect<LongArray>.asList(assertionCreator: Expect<List<Long>>.() -> Unit): Expect<LongArray> =
    apply { asList().addAssertionsCreatedBy(assertionCreator) }


/**
 * Turns `Expect<FloatArray>` into `Expect<List<Float>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(FloatArray::asList)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @since 0.9.0
 */
@JvmName("floatArrAsList")
fun Expect<FloatArray>.asList(): Expect<List<Float>> =
    _logic.changeSubject.unreported { it.asList() }

/**
 * Expects that the subject of the assertion holds all assertions the given [assertionCreator] creates for
 * the subject as [List].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(FloatArray::asList, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @since 0.9.0
 */
@JvmName("floatArrAsList")
fun Expect<FloatArray>.asList(assertionCreator: Expect<List<Float>>.() -> Unit): Expect<FloatArray> =
    apply { asList().addAssertionsCreatedBy(assertionCreator) }


/**
 * Turns `Expect<DoubleArray>` into `Expect<List<Double>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(DoubleArray::asList)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @since 0.9.0
 */
@JvmName("doubleArrAsList")
fun Expect<DoubleArray>.asList(): Expect<List<Double>> =
    _logic.changeSubject.unreported { it.asList() }

/**
 * Expects that the subject of the assertion holds all assertions the given [assertionCreator] creates for
 * the subject as [List].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(DoubleArray::asList, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @since 0.9.0
 */
@JvmName("doubleArrAsList")
fun Expect<DoubleArray>.asList(assertionCreator: Expect<List<Double>>.() -> Unit): Expect<DoubleArray> =
    apply { asList().addAssertionsCreatedBy(assertionCreator) }


/**
 * Turns `Expect<BooleanArray>` into `Expect<List<Boolean>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(BooleanArray::asList)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @since 0.9.0
 */
@JvmName("boolArrAsList")
fun Expect<BooleanArray>.asList(): Expect<List<Boolean>> =
    _logic.changeSubject.unreported { it.asList() }

/**
 * Expects that the subject of the assertion holds all assertions the given [assertionCreator] creates for
 * the subject as [List].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(BooleanArray::asList, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @since 0.9.0
 */
@JvmName("boolArrAsList")
fun Expect<BooleanArray>.asList(assertionCreator: Expect<List<Boolean>>.() -> Unit): Expect<BooleanArray> =
    apply { asList().addAssertionsCreatedBy(assertionCreator) }
