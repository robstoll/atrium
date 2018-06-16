package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl

/**
 * Turns `Assert<Array<E>>` into `Assert<Iterable<E>>`.
 *
 * @return The newly created [AssertionPlant] for the transformed subject.
 */
fun <E> Assert<Array<E>>.asIterable(): AssertionPlant<Iterable<E>>
    = AssertImpl.changeSubject(this) { subject.asIterable() }

/**
 * Turns `Assert<CharArray>` into `Assert<Iterable<Byte>>`.
 *
 * @return The newly created [AssertionPlant] for the transformed subject.
 */
@JvmName("byteArrAsIterable")
fun Assert<ByteArray>.asIterable(): AssertionPlant<Iterable<Byte>>
    = AssertImpl.changeSubject(this) { subject.asIterable() }

/**
 * Turns `Assert<CharArray>` into `Assert<Iterable<Char>>`.
 *
 * @return The newly created [AssertionPlant] for the transformed subject.
 */
@JvmName("charArrAsIterable")
fun Assert<CharArray>.asIterable(): AssertionPlant<Iterable<Char>>
    = AssertImpl.changeSubject(this) { subject.asIterable() }

/**
 * Turns `Assert<ShortArray>` into `Assert<Iterable<Short>>`.
 *
 * @return The newly created [AssertionPlant] for the transformed subject.
 */
@JvmName("shortArrAsIterable")
fun Assert<ShortArray>.asIterable(): AssertionPlant<Iterable<Short>>
    = AssertImpl.changeSubject(this) { subject.asIterable() }

/**
 * Turns `Assert<IntArray>` into `Assert<Iterable<Int>>`.
 *
 * @return The newly created [AssertionPlant] for the transformed subject.
 */
@JvmName("intArrAsIterable")
fun Assert<IntArray>.asIterable(): AssertionPlant<Iterable<Int>>
    = AssertImpl.changeSubject(this) { subject.asIterable() }

/**
 * Turns `Assert<LongArray>` into `Assert<Iterable<Double>>`.
 *
 * @return The newly created [AssertionPlant] for the transformed subject.
 */
@JvmName("longArrAsIterable")
fun Assert<LongArray>.asIterable(): AssertionPlant<Iterable<Long>>
    = AssertImpl.changeSubject(this) { subject.asIterable() }
/**
 * Turns `Assert<FloatArray>` into `Assert<Iterable<Float>>`.
 *
 * @return The newly created [AssertionPlant] for the transformed subject.
 */
@JvmName("floatArrAsIterable")
fun Assert<FloatArray>.asIterable(): AssertionPlant<Iterable<Float>>
    = AssertImpl.changeSubject(this) { subject.asIterable() }

/**
 * Turns `Assert<DoubleArray>` into `Assert<Iterable<Double>>`.
 *
 * @return The newly created [AssertionPlant] for the transformed subject.
 */
@JvmName("doubleArrAsIterable")
fun Assert<DoubleArray>.asIterable(): AssertionPlant<Iterable<Double>>
    = AssertImpl.changeSubject(this) { subject.asIterable() }

/**
 * Turns `Assert<BooleanArray>` into `Assert<Iterable<Boolean>>`.
 *
 * @return The newly created [AssertionPlant] for the transformed subject.
 */
@JvmName("boolArrAsIterable")
fun Assert<BooleanArray>.asIterable(): AssertionPlant<Iterable<Boolean>>
    = AssertImpl.changeSubject(this) { subject.asIterable() }
