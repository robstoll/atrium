package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.AssertImpl

/**
 * Turns `Assert<Array<E>>` into `Assert<Iterable<E>>`.
 *
 * @return The newly created [Assert] for the transformed subject.
 */
fun <E> Assert<Array<E>>.asIterable(): Assert<Iterable<E>>
    = AssertImpl.changeSubject(this) { subject.asIterable() }

/**
 * Turns `Assert<CharArray>` into `Assert<Iterable<Byte>>`.
 *
 * @return The newly created [Assert] for the transformed subject.
 */
@JvmName("byteArrAsIterable")
fun Assert<ByteArray>.asIterable(): Assert<Iterable<Byte>>
    = AssertImpl.changeSubject(this) { subject.asIterable() }

/**
 * Turns `Assert<CharArray>` into `Assert<Iterable<Char>>`.
 *
 * @return The newly created [Assert] for the transformed subject.
 */
@JvmName("charArrAsIterable")
fun Assert<CharArray>.asIterable(): Assert<Iterable<Char>>
    = AssertImpl.changeSubject(this) { subject.asIterable() }

/**
 * Turns `Assert<ShortArray>` into `Assert<Iterable<Short>>`.
 *
 * @return The newly created [Assert] for the transformed subject.
 */
@JvmName("shortArrAsIterable")
fun Assert<ShortArray>.asIterable(): Assert<Iterable<Short>>
    = AssertImpl.changeSubject(this) { subject.asIterable() }

/**
 * Turns `Assert<IntArray>` into `Assert<Iterable<Int>>`.
 *
 * @return The newly created [Assert] for the transformed subject.
 */
@JvmName("intArrAsIterable")
fun Assert<IntArray>.asIterable(): Assert<Iterable<Int>>
    = AssertImpl.changeSubject(this) { subject.asIterable() }

/**
 * Turns `Assert<LongArray>` into `Assert<Iterable<Double>>`.
 *
 * @return The newly created [Assert] for the transformed subject.
 */
@JvmName("longArrAsIterable")
fun Assert<LongArray>.asIterable(): Assert<Iterable<Long>>
    = AssertImpl.changeSubject(this) { subject.asIterable() }
/**
 * Turns `Assert<FloatArray>` into `Assert<Iterable<Float>>`.
 *
 * @return The newly created [Assert] for the transformed subject.
 */
@JvmName("floatArrAsIterable")
fun Assert<FloatArray>.asIterable(): Assert<Iterable<Float>>
    = AssertImpl.changeSubject(this) { subject.asIterable() }

/**
 * Turns `Assert<DoubleArray>` into `Assert<Iterable<Double>>`.
 *
 * @return The newly created [Assert] for the transformed subject.
 */
@JvmName("doubleArrAsIterable")
fun Assert<DoubleArray>.asIterable(): Assert<Iterable<Double>>
    = AssertImpl.changeSubject(this) { subject.asIterable() }

/**
 * Turns `Assert<BooleanArray>` into `Assert<Iterable<Boolean>>`.
 *
 * @return The newly created [Assert] for the transformed subject.
 */
@JvmName("boolArrAsIterable")
fun Assert<BooleanArray>.asIterable(): Assert<Iterable<Boolean>>
    = AssertImpl.changeSubject(this) { subject.asIterable() }
