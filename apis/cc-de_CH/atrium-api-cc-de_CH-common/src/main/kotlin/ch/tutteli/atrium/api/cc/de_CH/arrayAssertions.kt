@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.ExpectImpl
import kotlin.jvm.JvmName

/**
 * Turns `Assert<Array<E>>` into `Assert<Iterable<E>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `returnValueOf(Array<out E>::asIterable)` if you want to show the transformation in reporting.
 *
 * @return The newly created [AssertionPlant] for the transformed subject.
 */
@Suppress("DEPRECATION")
fun <E> Assert<Array<out E>>.asIterable(): Assert<Iterable<E>>
    = ExpectImpl.changeSubject(this).unreported { it.asIterable() }

/**
 * Turns `Assert<Array<E>>` into `Assert<Iterable<E>>` and makes the assertion that the assertions the given
 * [assertionCreator] might create hold.
 *
 * The transformation as such is not reflected in reporting.
 * Use `returnValueOf(Array<out E>::asIterable)` if you want to show the transformation in reporting.
 *
 * @return The newly created [AssertionPlant] for the transformed subject.
 */
fun <E> Assert<Array<out E>>.asIterable(assertionCreator: Assert<Iterable<E>>.() -> Unit): Assert<Iterable<E>>
    = asIterable().addAssertionsCreatedBy(assertionCreator)


/**
 * Turns `Assert<CharArray>` into `Assert<Iterable<Byte>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `returnValueOf(ByteArray::asIterable)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Assert] for the transformed subject.
 */
@Suppress("DEPRECATION")
@JvmName("byteArrAsIterable")
fun Assert<ByteArray>.asIterable(): Assert<Iterable<Byte>>
    = ExpectImpl.changeSubject(this).unreported { it.asIterable() }

/**
 * Turns `Assert<CharArray>` into `Assert<Iterable<Byte>>` and makes the assertion that the assertions the given
 * [assertionCreator] might create hold.
 *
 * The transformation as such is not reflected in reporting.
 * Use `returnValueOf(ByteArray::asIterable)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Assert] for the transformed subject.
 */
@JvmName("byteArrAsIterable")
fun Assert<ByteArray>.asIterable(assertionCreator: Assert<Iterable<Byte>>.() -> Unit): Assert<Iterable<Byte>>
    = asIterable().addAssertionsCreatedBy(assertionCreator)


/**
 * Turns `Assert<CharArray>` into `Assert<Iterable<Char>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `returnValueOf(CharArray::asIterable)` if you want to show the transformation in reporting.
 *
 * @return The newly created [AssertionPlant] for the transformed subject.
 */
@Suppress("DEPRECATION")
@JvmName("charArrAsIterable")
fun Assert<CharArray>.asIterable(): Assert<Iterable<Char>>
    = ExpectImpl.changeSubject(this).unreported { it.asIterable() }

/**
 * Turns `Assert<CharArray>` into `Assert<Iterable<Char>>` and makes the assertion that the assertions the given
 * [assertionCreator] might create hold.
 *
 * The transformation as such is not reflected in reporting.
 * Use `returnValueOf(CharArray::asIterable)` if you want to show the transformation in reporting.
 *
 * @return The newly created [AssertionPlant] for the transformed subject.
 */
@JvmName("charArrAsIterable")
fun Assert<CharArray>.asIterable(assertionCreator: Assert<Iterable<Char>>.() -> Unit): Assert<Iterable<Char>>
    = asIterable().addAssertionsCreatedBy(assertionCreator)


/**
 * Turns `Assert<ShortArray>` into `Assert<Iterable<Short>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `returnValueOf(ShortArray::asIterable)` if you want to show the transformation in reporting.
 *
 * @return The newly created [AssertionPlant] for the transformed subject.
 */
@Suppress("DEPRECATION")
@JvmName("shortArrAsIterable")
fun Assert<ShortArray>.asIterable(): Assert<Iterable<Short>>
    = ExpectImpl.changeSubject(this).unreported { it.asIterable() }

/**
 * Turns `Assert<ShortArray>` into `Assert<Iterable<Short>>` and makes the assertion that the assertions the given
 * [assertionCreator] might create hold.
 *
 * The transformation as such is not reflected in reporting.
 * Use `returnValueOf(ShortArray::asIterable)` if you want to show the transformation in reporting.
 *
 * @return The newly created [AssertionPlant] for the transformed subject.
 */
@JvmName("shortArrAsIterable")
fun Assert<ShortArray>.asIterable(assertionCreator: Assert<Iterable<Short>>.() -> Unit): Assert<Iterable<Short>>
    = asIterable().addAssertionsCreatedBy(assertionCreator)


/**
 * Turns `Assert<IntArray>` into `Assert<Iterable<Int>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `returnValueOf(IntArray::asIterable)` if you want to show the transformation in reporting.
 *
 * @return The newly created [AssertionPlant] for the transformed subject.
 */
@Suppress("DEPRECATION")
@JvmName("intArrAsIterable")
fun Assert<IntArray>.asIterable(): Assert<Iterable<Int>>
    = ExpectImpl.changeSubject(this).unreported { it.asIterable() }

/**
 * Turns `Assert<IntArray>` into `Assert<Iterable<Int>>` and makes the assertion that the assertions the given
 * [assertionCreator] might create hold.
 *
 * The transformation as such is not reflected in reporting.
 * Use `returnValueOf(IntArray::asIterable)` if you want to show the transformation in reporting.
 *
 * @return The newly created [AssertionPlant] for the transformed subject.
 */
@JvmName("intArrAsIterable")
fun Assert<IntArray>.asIterable(assertionCreator: Assert<Iterable<Int>>.() -> Unit): Assert<Iterable<Int>>
    = asIterable().addAssertionsCreatedBy(assertionCreator)


/**
 * Turns `Assert<LongArray>` into `Assert<Iterable<Double>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `returnValueOf(LongArray::asIterable)` if you want to show the transformation in reporting.
 *
 * @return The newly created [AssertionPlant] for the transformed subject.
 */
@Suppress("DEPRECATION")
@JvmName("longArrAsIterable")
fun Assert<LongArray>.asIterable(): Assert<Iterable<Long>>
    = ExpectImpl.changeSubject(this).unreported { it.asIterable() }

/**
 * Turns `Assert<LongArray>` into `Assert<Iterable<Double>>` and makes the assertion that the assertions the given
 * [assertionCreator] might create hold.
 *
 * The transformation as such is not reflected in reporting.
 * Use `returnValueOf(LongArray::asIterable)` if you want to show the transformation in reporting.
 *
 * @return The newly created [AssertionPlant] for the transformed subject.
 */
@JvmName("longArrAsIterable")
fun Assert<LongArray>.asIterable(assertionCreator: Assert<Iterable<Long>>.() -> Unit): Assert<Iterable<Long>>
    = asIterable().addAssertionsCreatedBy(assertionCreator)


/**
 * Turns `Assert<FloatArray>` into `Assert<Iterable<Float>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `returnValueOf(FloatArray::asIterable)` if you want to show the transformation in reporting.
 *
 * @return The newly created [AssertionPlant] for the transformed subject.
 */
@Suppress("DEPRECATION")
@JvmName("floatArrAsIterable")
fun Assert<FloatArray>.asIterable(): Assert<Iterable<Float>>
    = ExpectImpl.changeSubject(this).unreported { it.asIterable() }

/**
 * Turns `Assert<FloatArray>` into `Assert<Iterable<Float>>` and makes the assertion that the assertions the given
 * [assertionCreator] might create hold.
 *
 * The transformation as such is not reflected in reporting.
 * Use `returnValueOf(FloatArray::asIterable)` if you want to show the transformation in reporting.
 *
 * @return The newly created [AssertionPlant] for the transformed subject.
 */
@JvmName("floatArrAsIterable")
fun Assert<FloatArray>.asIterable(assertionCreator: Assert<Iterable<Float>>.() -> Unit): Assert<Iterable<Float>>
    = asIterable().addAssertionsCreatedBy(assertionCreator)


/**
 * Turns `Assert<DoubleArray>` into `Assert<Iterable<Double>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `returnValueOf(DoubleArray::asIterable)` if you want to show the transformation in reporting.
 *
 * @return The newly created [AssertionPlant] for the transformed subject.
 */
@Suppress("DEPRECATION")
@JvmName("doubleArrAsIterable")
fun Assert<DoubleArray>.asIterable(): Assert<Iterable<Double>>
    = ExpectImpl.changeSubject(this).unreported { it.asIterable() }

/**
 * Turns `Assert<DoubleArray>` into `Assert<Iterable<Double>>` and makes the assertion that the assertions the given
 * [assertionCreator] might create hold.
 *
 * The transformation as such is not reflected in reporting.
 * Use `returnValueOf(DoubleArray::asIterable)` if you want to show the transformation in reporting.
 *
 * @return The newly created [AssertionPlant] for the transformed subject.
 */
@JvmName("doubleArrAsIterable")
fun Assert<DoubleArray>.asIterable(assertionCreator: Assert<Iterable<Double>>.() -> Unit): Assert<Iterable<Double>>
    = asIterable().addAssertionsCreatedBy(assertionCreator)


/**
 * Turns `Assert<BooleanArray>` into `Assert<Iterable<Boolean>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `returnValueOf(BooleanArray::asIterable)` if you want to show the transformation in reporting.
 *
 * @return The newly created [AssertionPlant] for the transformed subject.
 */
@Suppress("DEPRECATION")
@JvmName("boolArrAsIterable")
fun Assert<BooleanArray>.asIterable(): Assert<Iterable<Boolean>>
    = ExpectImpl.changeSubject(this).unreported { it.asIterable() }

/**
 * Turns `Assert<BooleanArray>` into `Assert<Iterable<Boolean>>` and makes the assertion that the assertions the given
 * [assertionCreator] might create hold.
 *
 * The transformation as such is not reflected in reporting.
 * Use `returnValueOf(BooleanArray::asIterable)` if you want to show the transformation in reporting.
 *
 * @return The newly created [AssertionPlant] for the transformed subject.
 */
@JvmName("boolArrAsIterable")
fun Assert<BooleanArray>.asIterable(assertionCreator: Assert<Iterable<Boolean>>.() -> Unit): Assert<Iterable<Boolean>>
    = asIterable().addAssertionsCreatedBy(assertionCreator)
