package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.contains
import ch.tutteli.atrium.api.cc.en_GB.containsExactly
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.include

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
    describePrefix: String = "[Atrium] "
) : IterablePredicateSpecBase(verbs, {

    include(object : SubjectLessAssertionSpec<Array<Int>>("$describePrefix[arr] ",
        asIterableFunName to mapToCreateAssertion { arr(this) }
    ) {})
    include(object : SubjectLessAssertionSpec<ByteArray>("$describePrefix[arrByte] ",
        asIterableFunName to mapToCreateAssertion { arrByte(this) }
    ) {})
    include(object : SubjectLessAssertionSpec<CharArray>("$describePrefix[arrChar] ",
        asIterableFunName to mapToCreateAssertion { arrChar(this) }
    ) {})
    include(object : SubjectLessAssertionSpec<ShortArray>("$describePrefix[arrShort] ",
        asIterableFunName to mapToCreateAssertion { arrShort(this) }
    ) {})
    include(object : SubjectLessAssertionSpec<IntArray>("$describePrefix[arrInt] ",
        asIterableFunName to mapToCreateAssertion { arrInt(this) }
    ) {})
    include(object : SubjectLessAssertionSpec<LongArray>("$describePrefix[arrLong] ",
        asIterableFunName to mapToCreateAssertion { arrLong(this) }
    ) {})
    include(object : SubjectLessAssertionSpec<FloatArray>("$describePrefix[arrFloat] ",
        asIterableFunName to mapToCreateAssertion { arrFloat(this) }
    ) {})
    include(object : SubjectLessAssertionSpec<DoubleArray>("$describePrefix[arrDouble] ",
        asIterableFunName to mapToCreateAssertion { arrDouble(this) }
    ) {})
    include(object : SubjectLessAssertionSpec<BooleanArray>("$describePrefix[arrBoolean] ",
        asIterableFunName to mapToCreateAssertion { arrBoolean(this) }
    ) {})

    fun bytes(vararg bytes : Byte) = bytes
    fun chars(vararg chars: Char) = chars
    fun shorts(vararg shorts: Short) = shorts
    fun ints(vararg ints: Int) = ints
    fun longs(vararg longs: Long) = longs
    fun floats(vararg floats: Float) = floats
    fun doubles(vararg doubles: Double) = doubles
    fun booleans(vararg booleans: Boolean) = booleans

    include(object : CheckingAssertionSpec<Array<Int>>(verbs, "$describePrefix[arr] ",
        checkingTriple("$asIterableFunName arr", { arr(this).contains(5) }, arrayOf(1, 3, 5, 6), arrayOf(1, 2, 3))
    ) {})
    include(object : CheckingAssertionSpec<ByteArray>(verbs, "$describePrefix[arrByte] ",
        checkingTriple("$asIterableFunName arr", { arrByte(this).contains(5.toByte()) }, bytes(1.toByte(), 5.toByte()), bytes())
    ) {})
    include(object : CheckingAssertionSpec<CharArray>(verbs, "$describePrefix[arrChar] ",
        checkingTriple("$asIterableFunName arr", { arrChar(this).contains(5.toChar()) }, chars(1.toChar(), 5.toChar()), chars())
    ) {})
    include(object : CheckingAssertionSpec<ShortArray>(verbs, "$describePrefix[arrShort] ",
        checkingTriple("$asIterableFunName arr", { arrShort(this).contains(5.toShort()) }, shorts(1.toShort(), 5.toShort()), shorts())
    ) {})
    include(object : CheckingAssertionSpec<IntArray>(verbs, "$describePrefix[arrInt] ",
        checkingTriple("$asIterableFunName arr", { arrInt(this).contains(5) }, ints(1, 5), ints())
    ) {})
    include(object : CheckingAssertionSpec<LongArray>(verbs, "$describePrefix[arrLong] ",
        checkingTriple("$asIterableFunName arr", { arrLong(this).contains(5) }, longs(1, 5), longs())
    ) {})
    include(object : CheckingAssertionSpec<FloatArray>(verbs, "$describePrefix[arrFloat] ",
        checkingTriple("$asIterableFunName arr", { arrFloat(this).contains(5f) }, floats(1f, 5f), floats())
    ) {})
    include(object : CheckingAssertionSpec<DoubleArray>(verbs, "$describePrefix[arrDouble] ",
        checkingTriple("$asIterableFunName arr", { arrDouble(this).contains(5.0) }, doubles(1.0, 5.0), doubles())
    ) {})


    describe("$asIterableFunName arr") {
        verbs.checkImmediately(arrayOf(1, 2)).arr().containsExactly(1, 2)
    }

    describe("$asIterableFunName arrByte") {
        test("transformation can be applied and an assertion made") {
            verbs.checkImmediately(bytes(1.toByte(), 2.toByte())).arrByte().containsExactly(1.toByte(), 2.toByte())
        }
    }
    describe("$asIterableFunName arrChar") {
        test("transformation can be applied and an assertion made") {
            verbs.checkImmediately(chars(1.toChar(), 2.toChar())).arrChar().containsExactly(1.toChar(), 2.toChar())
        }
    }
    describe("$asIterableFunName arrShort") {
        test("transformation can be applied and an assertion made") {
            verbs.checkImmediately(shorts(1, 2)).arrShort().containsExactly(1.toShort(), 2.toShort())
        }
    }
    describe("$asIterableFunName arrShort") {
        test("transformation can be applied and an assertion made") {
            verbs.checkImmediately(shorts(1.toShort(), 2.toShort())).arrShort()
                .containsExactly(1.toShort(), 2.toShort())
        }
    }
    describe("$asIterableFunName arrInt") {
        test("transformation can be applied and an assertion made") {
            verbs.checkImmediately(ints(1, 2)).arrInt().containsExactly(1, 2)
        }
    }
    describe("$asIterableFunName arrLong") {
        test("transformation can be applied and an assertion made") {
            verbs.checkImmediately(longs(1, 2)).arrLong().containsExactly(1L, 2L)
        }
    }
    describe("$asIterableFunName arrFloat") {
        test("transformation can be applied and an assertion made") {
            verbs.checkImmediately(floats(1f, 2f)).arrFloat().containsExactly(1f, 2f)
        }
    }
    describe("$asIterableFunName arrDouble") {
        test("transformation can be applied and an assertion made") {
            verbs.checkImmediately(doubles(1.0, 2.0)).arrDouble().containsExactly(1.0, 2.0)
        }
    }
    describe("$asIterableFunName arrBoolean") {
        test("transformation can be applied and an assertion made") {
            verbs.checkImmediately(booleans(true, false)).arrBoolean().containsExactly(true, false)
        }
    }
})
