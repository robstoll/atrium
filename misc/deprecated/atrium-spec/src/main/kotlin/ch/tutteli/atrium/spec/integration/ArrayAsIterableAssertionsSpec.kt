// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")

package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.contains
import ch.tutteli.atrium.api.cc.en_GB.containsExactly
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.include

@Deprecated("Switch from Assert to Expect and use Spec from atrium-specs-common; will be removed with 1.0.0")
abstract class ArrayAsIterableAssertionsSpec(
    verbs: AssertionVerbFactory,
    asIterableFunName: String,
    arr: Assert<Array<Int>>.() -> Assert<Iterable<Int>>,
    arrByte: Assert<ByteArray>.() -> Assert<Iterable<Byte>>,
    arrChar: Assert<CharArray>.() -> Assert<Iterable<Char>>,
    arrShort: Assert<ShortArray>.() -> Assert<Iterable<Short>>,
    arrInt: Assert<IntArray>.() -> Assert<Iterable<Int>>,
    arrLong: Assert<LongArray>.() -> Assert<Iterable<Long>>,
    arrFloat: Assert<FloatArray>.() -> Assert<Iterable<Float>>,
    arrDouble: Assert<DoubleArray>.() -> Assert<Iterable<Double>>,
    arrBoolean: Assert<BooleanArray>.() -> Assert<Iterable<Boolean>>,
    arrWithCreator: Assert<Array<Int>>.(Assert<Iterable<Int>>.() -> Unit) -> Assert<Iterable<Int>>,
    arrByteWithCreator: Assert<ByteArray>.(Assert<Iterable<Byte>>.() -> Unit) -> Assert<Iterable<Byte>>,
    arrCharWithCreator: Assert<CharArray>.(Assert<Iterable<Char>>.() -> Unit) -> Assert<Iterable<Char>>,
    arrShortWithCreator: Assert<ShortArray>.(Assert<Iterable<Short>>.() -> Unit) -> Assert<Iterable<Short>>,
    arrIntWithCreator: Assert<IntArray>.(Assert<Iterable<Int>>.() -> Unit) -> Assert<Iterable<Int>>,
    arrLongWithCreator: Assert<LongArray>.(Assert<Iterable<Long>>.() -> Unit) -> Assert<Iterable<Long>>,
    arrFloatWithCreator: Assert<FloatArray>.(Assert<Iterable<Float>>.() -> Unit) -> Assert<Iterable<Float>>,
    arrDoubleWithCreator: Assert<DoubleArray>.(Assert<Iterable<Double>>.() -> Unit) -> Assert<Iterable<Double>>,
    arrBooleanWithCreator: Assert<BooleanArray>.(Assert<Iterable<Boolean>>.() -> Unit) -> Assert<Iterable<Boolean>>,
    describePrefix: String = "[Atrium] "
) : IterablePredicateSpecBase(verbs, {

    val asIterableWithCreator = "$asIterableFunName with Creator"
    include(@Suppress("DEPRECATION") object : SubjectLessAssertionSpec<Array<Int>>("$describePrefix[arr] ",
        asIterableFunName to mapToCreateAssertion { arr(this) },
        asIterableWithCreator to mapToCreateAssertion { arrWithCreator(this) { contains(1) } }
    ) {})
    include(@Suppress("DEPRECATION") object : SubjectLessAssertionSpec<ByteArray>("$describePrefix[arrByte] ",
        asIterableFunName to mapToCreateAssertion { arrByte(this) },
        asIterableWithCreator to mapToCreateAssertion { arrByteWithCreator(this) { contains(1) } }
    ) {})
    include(@Suppress("DEPRECATION") object : SubjectLessAssertionSpec<CharArray>("$describePrefix[arrChar] ",
        asIterableFunName to mapToCreateAssertion { arrChar(this) },
        asIterableWithCreator to mapToCreateAssertion { arrCharWithCreator(this) { contains('a') } }
    ) {})
    include(@Suppress("DEPRECATION") object : SubjectLessAssertionSpec<ShortArray>("$describePrefix[arrShort] ",
        asIterableFunName to mapToCreateAssertion { arrShort(this) },
        asIterableWithCreator to mapToCreateAssertion { arrShortWithCreator(this) { contains(1.toShort()) } }
    ) {})
    include(@Suppress("DEPRECATION") object : SubjectLessAssertionSpec<IntArray>("$describePrefix[arrInt] ",
        asIterableFunName to mapToCreateAssertion { arrInt(this) },
        asIterableWithCreator to mapToCreateAssertion { arrIntWithCreator(this) { contains(1) } }
    ) {})
    include(@Suppress("DEPRECATION") object : SubjectLessAssertionSpec<LongArray>("$describePrefix[arrLong] ",
        asIterableFunName to mapToCreateAssertion { arrLong(this) },
        asIterableWithCreator to mapToCreateAssertion { arrLongWithCreator(this) { contains(1L) } }
    ) {})
    include(@Suppress("DEPRECATION") object : SubjectLessAssertionSpec<FloatArray>("$describePrefix[arrFloat] ",
        asIterableFunName to mapToCreateAssertion { arrFloat(this) },
        asIterableWithCreator to mapToCreateAssertion { arrFloatWithCreator(this) { contains(1f) } }
    ) {})
    include(@Suppress("DEPRECATION") object : SubjectLessAssertionSpec<DoubleArray>("$describePrefix[arrDouble] ",
        asIterableFunName to mapToCreateAssertion { arrDouble(this) },
        asIterableWithCreator to mapToCreateAssertion { arrDoubleWithCreator(this) { contains(1.0) } }
    ) {})
    include(@Suppress("DEPRECATION") object : SubjectLessAssertionSpec<BooleanArray>("$describePrefix[arrBoolean] ",
        asIterableFunName to mapToCreateAssertion { arrBoolean(this) },
        asIterableWithCreator to mapToCreateAssertion { arrBooleanWithCreator(this) { contains(true) } }
    ) {})

    fun bytes(vararg bytes : Byte) = bytes
    fun chars(vararg chars: Char) = chars
    fun shorts(vararg shorts: Short) = shorts
    fun ints(vararg ints: Int) = ints
    fun longs(vararg longs: Long) = longs
    fun floats(vararg floats: Float) = floats
    fun doubles(vararg doubles: Double) = doubles
    fun booleans(vararg booleans: Boolean) = booleans

    include(@Suppress("DEPRECATION") object : CheckingAssertionSpec<Array<Int>>(verbs, "$describePrefix[arr] ",
        checkingTriple(asIterableFunName, { arr(this).contains(5) }, arrayOf(1, 3, 5, 6), arrayOf(1, 2, 3)),
        checkingTriple(asIterableWithCreator, { arrWithCreator(this) { contains(5) }}, arrayOf(1, 3, 5, 6), arrayOf(1, 2, 3))
    ) {})
    include(@Suppress("DEPRECATION") object : CheckingAssertionSpec<ByteArray>(verbs, "$describePrefix[arrByte] ",
        checkingTriple(asIterableFunName, { arrByte(this).contains(5.toByte()) }, bytes(1.toByte(), 5.toByte()), bytes()),
        checkingTriple(asIterableWithCreator, { arrByteWithCreator(this) { contains(5.toByte()) }}, bytes(1.toByte(), 5.toByte()), bytes())
    ) {})
    include(@Suppress("DEPRECATION") object : CheckingAssertionSpec<CharArray>(verbs, "$describePrefix[arrChar] ",
        checkingTriple(asIterableFunName, { arrChar(this).contains(5.toChar()) }, chars(1.toChar(), 5.toChar()), chars()),
        checkingTriple(asIterableWithCreator, { arrCharWithCreator(this) { contains(5.toChar()) }}, chars(1.toChar(), 5.toChar()), chars())
    ) {})
    include(@Suppress("DEPRECATION") object : CheckingAssertionSpec<ShortArray>(verbs, "$describePrefix[arrShort] ",
        checkingTriple(asIterableFunName, { arrShort(this).contains(5.toShort()) }, shorts(1.toShort(), 5.toShort()), shorts()),
        checkingTriple(asIterableWithCreator, { arrShortWithCreator(this) { contains(5.toShort()) }}, shorts(1.toShort(), 5.toShort()), shorts())
    ) {})
    include(@Suppress("DEPRECATION") object : CheckingAssertionSpec<IntArray>(verbs, "$describePrefix[arrInt] ",
        checkingTriple(asIterableFunName, { arrInt(this).contains(5) }, ints(1, 5), ints()),
        checkingTriple(asIterableWithCreator, { arrIntWithCreator(this) { contains(5) }}, ints(1, 5), ints())
    ) {})
    include(@Suppress("DEPRECATION") object : CheckingAssertionSpec<LongArray>(verbs, "$describePrefix[arrLong] ",
        checkingTriple(asIterableFunName, { arrLong(this).contains(5) }, longs(1, 5), longs()),
        checkingTriple(asIterableWithCreator, { arrLongWithCreator(this) { contains(5) }}, longs(1, 5), longs())
    ) {})
    include(@Suppress("DEPRECATION") object : CheckingAssertionSpec<FloatArray>(verbs, "$describePrefix[arrFloat] ",
        checkingTriple(asIterableFunName, { arrFloat(this).contains(5f) }, floats(1f, 5f), floats()),
        checkingTriple(asIterableWithCreator, { arrFloatWithCreator(this) { contains(5f) }}, floats(1f, 5f), floats())
    ) {})
    include(@Suppress("DEPRECATION") object : CheckingAssertionSpec<DoubleArray>(verbs, "$describePrefix[arrDouble] ",
        checkingTriple(asIterableFunName, { arrDouble(this).contains(5.0) }, doubles(1.0, 5.0), doubles()),
        checkingTriple(asIterableWithCreator, { arrDoubleWithCreator(this) { contains(5.0) }}, doubles(1.0, 5.0), doubles())
    ) {})


    describe("$asIterableFunName arr") {
        test("transformation can be applied and an subsequent assertion made") {
            verbs.checkImmediately(arrayOf(1, 2)).arr().containsExactly(1, 2)
        }
        test("transformation can be applied and a sub-assertion made") {
            verbs.checkImmediately(arrayOf(1, 2)).arrWithCreator { containsExactly(1, 2) }
        }
    }

    describe("$asIterableFunName arrByte") {
        test("transformation can be applied and an subsequent assertion made") {
            verbs.checkImmediately(bytes(1.toByte(), 2.toByte())).arrByte().containsExactly(1.toByte(), 2.toByte())
        }
        test("transformation can be applied and a sub assertion made") {
            verbs.checkImmediately(bytes(1.toByte(), 2.toByte())).arrByteWithCreator{ containsExactly(1.toByte(), 2.toByte()) }
        }
    }
    describe("$asIterableFunName arrChar") {
        test("transformation can be applied and an subsequent assertion made") {
            verbs.checkImmediately(chars(1.toChar(), 2.toChar())).arrChar().containsExactly(1.toChar(), 2.toChar())
        }
        test("transformation can be applied and a sub assertion made") {
            verbs.checkImmediately(chars(1.toChar(), 2.toChar())).arrCharWithCreator{ containsExactly(1.toChar(), 2.toChar()) }
        }
    }
    describe("$asIterableFunName arrShort") {
        test("transformation can be applied and an subsequent assertion made") {
            verbs.checkImmediately(shorts(1, 2)).arrShort().containsExactly(1.toShort(), 2.toShort())
        }
        test("transformation can be applied and a sub assertion made") {
            verbs.checkImmediately(shorts(1, 2)).arrShortWithCreator{ containsExactly(1.toShort(), 2.toShort()) }
        }
    }
    describe("$asIterableFunName arrInt") {
        test("transformation can be applied and an subsequent assertion made") {
            verbs.checkImmediately(ints(1, 2)).arrInt().containsExactly(1, 2)
        }
        test("transformation can be applied and a sub assertion made") {
            verbs.checkImmediately(ints(1, 2)).arrIntWithCreator{ containsExactly(1, 2) }
        }
    }
    describe("$asIterableFunName arrLong") {
        test("transformation can be applied and an subsequent assertion made") {
            verbs.checkImmediately(longs(1, 2)).arrLong().containsExactly(1L, 2L)
        }
        test("transformation can be applied and a sub assertion made") {
            verbs.checkImmediately(longs(1, 2)).arrLongWithCreator{ containsExactly(1L, 2L) }
        }
    }
    describe("$asIterableFunName arrFloat") {
        test("transformation can be applied and an subsequent assertion made") {
            verbs.checkImmediately(floats(1f, 2f)).arrFloat().containsExactly(1f, 2f)
        }
        test("transformation can be applied and a sub assertion made") {
            verbs.checkImmediately(floats(1f, 2f)).arrFloatWithCreator{ containsExactly(1f, 2f) }
        }
    }
    describe("$asIterableFunName arrDouble") {
        test("transformation can be applied and an subsequent assertion made") {
            verbs.checkImmediately(doubles(1.0, 2.0)).arrDouble().containsExactly(1.0, 2.0)
        }
        test("transformation can be applied and a sub assertion made") {
            verbs.checkImmediately(doubles(1.0, 2.0)).arrDoubleWithCreator{ containsExactly(1.0, 2.0) }
        }
    }
    describe("$asIterableFunName arrBoolean") {
        test("transformation can be applied and an subsequent assertion made") {
            verbs.checkImmediately(booleans(true, false)).arrBoolean().containsExactly(true, false)
        }
        test("transformation can be applied and a sub assertion made") {
            verbs.checkImmediately(booleans(true, false)).arrBooleanWithCreator{ containsExactly(true, false) }
        }
    }
})
