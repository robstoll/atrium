package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.cc.en_GB.contains
import ch.tutteli.atrium.api.cc.en_GB.containsExactly
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.migration.asAssert
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.verbs.AssertionVerbFactory
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

abstract class ArrayAsIterableAssertionsSpec(
    verbs: AssertionVerbFactory,
    asIterableFunName: String,
    arr: Expect<Array<Int>>.() -> Expect<Iterable<Int>>,
    arrByte: Expect<ByteArray>.() -> Expect<Iterable<Byte>>,
    arrChar: Expect<CharArray>.() -> Expect<Iterable<Char>>,
    arrShort: Expect<ShortArray>.() -> Expect<Iterable<Short>>,
    arrInt: Expect<IntArray>.() -> Expect<Iterable<Int>>,
    arrLong: Expect<LongArray>.() -> Expect<Iterable<Long>>,
    arrFloat: Expect<FloatArray>.() -> Expect<Iterable<Float>>,
    arrDouble: Expect<DoubleArray>.() -> Expect<Iterable<Double>>,
    arrBoolean: Expect<BooleanArray>.() -> Expect<Iterable<Boolean>>,
    arrWithCreator: Expect<Array<Int>>.(Expect<Iterable<Int>>.() -> Unit) -> Expect<Iterable<Int>>,
    arrByteWithCreator: Expect<ByteArray>.(Expect<Iterable<Byte>>.() -> Unit) -> Expect<Iterable<Byte>>,
    arrCharWithCreator: Expect<CharArray>.(Expect<Iterable<Char>>.() -> Unit) -> Expect<Iterable<Char>>,
    arrShortWithCreator: Expect<ShortArray>.(Expect<Iterable<Short>>.() -> Unit) -> Expect<Iterable<Short>>,
    arrIntWithCreator: Expect<IntArray>.(Expect<Iterable<Int>>.() -> Unit) -> Expect<Iterable<Int>>,
    arrLongWithCreator: Expect<LongArray>.(Expect<Iterable<Long>>.() -> Unit) -> Expect<Iterable<Long>>,
    arrFloatWithCreator: Expect<FloatArray>.(Expect<Iterable<Float>>.() -> Unit) -> Expect<Iterable<Float>>,
    arrDoubleWithCreator: Expect<DoubleArray>.(Expect<Iterable<Double>>.() -> Unit) -> Expect<Iterable<Double>>,
    arrBooleanWithCreator: Expect<BooleanArray>.(Expect<Iterable<Boolean>>.() -> Unit) -> Expect<Iterable<Boolean>>,
    describePrefix: String = "[Atrium] "
) : Spek({

    val asIterableWithCreator = "$asIterableFunName with Creator"
    include(object : SubjectLessSpec<Array<Int>>("$describePrefix[arr] ",
        asIterableFunName to expectLambda { arr(this) },
        asIterableWithCreator to expectLambda { arrWithCreator(this) { asAssert().contains(1) } }
    ) {})
    include(object : SubjectLessSpec<ByteArray>("$describePrefix[arrByte] ",
        asIterableFunName to expectLambda { arrByte(this) },
        asIterableWithCreator to expectLambda { arrByteWithCreator(this) { asAssert().contains(1) } }
    ) {})
    include(object : SubjectLessSpec<CharArray>("$describePrefix[arrChar] ",
        asIterableFunName to expectLambda { arrChar(this) },
        asIterableWithCreator to expectLambda { arrCharWithCreator(this) { asAssert().contains('a') } }
    ) {})
    include(object : SubjectLessSpec<ShortArray>("$describePrefix[arrShort] ",
        asIterableFunName to expectLambda { arrShort(this) },
        asIterableWithCreator to expectLambda { arrShortWithCreator(this) { asAssert().contains(1.toShort()) } }
    ) {})
    include(object : SubjectLessSpec<IntArray>("$describePrefix[arrInt] ",
        asIterableFunName to expectLambda { arrInt(this) },
        asIterableWithCreator to expectLambda { arrIntWithCreator(this) { asAssert().contains(1) } }
    ) {})
    include(object : SubjectLessSpec<LongArray>("$describePrefix[arrLong] ",
        asIterableFunName to expectLambda { arrLong(this) },
        asIterableWithCreator to expectLambda { arrLongWithCreator(this) { asAssert().contains(1L) } }
    ) {})
    include(object : SubjectLessSpec<FloatArray>("$describePrefix[arrFloat] ",
        asIterableFunName to expectLambda { arrFloat(this) },
        asIterableWithCreator to expectLambda { arrFloatWithCreator(this) { asAssert().contains(1f) } }
    ) {})
    include(object : SubjectLessSpec<DoubleArray>("$describePrefix[arrDouble] ",
        asIterableFunName to expectLambda { arrDouble(this) },
        asIterableWithCreator to expectLambda { arrDoubleWithCreator(this) { asAssert().contains(1.0) } }
    ) {})
    include(object : SubjectLessSpec<BooleanArray>("$describePrefix[arrBoolean] ",
        asIterableFunName to expectLambda { arrBoolean(this) },
        asIterableWithCreator to expectLambda { arrBooleanWithCreator(this) { asAssert().contains(true) } }
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
        checkingTriple(asIterableFunName, { arr(this).asAssert().contains(5) }, arrayOf(1, 3, 5, 6), arrayOf(1, 2, 3)),
        checkingTriple(asIterableWithCreator, { arrWithCreator(this) { asAssert().contains(5) }}, arrayOf(1, 3, 5, 6), arrayOf(1, 2, 3))
    ) {})
    include(object : CheckingAssertionSpec<ByteArray>(verbs, "$describePrefix[arrByte] ",
        checkingTriple(asIterableFunName, { arrByte(this).asAssert().contains(5.toByte()) }, bytes(1.toByte(), 5.toByte()), bytes()),
        checkingTriple(asIterableWithCreator, { arrByteWithCreator(this) { asAssert().contains(5.toByte()) }}, bytes(1.toByte(), 5.toByte()), bytes())
    ) {})
    include(object : CheckingAssertionSpec<CharArray>(verbs, "$describePrefix[arrChar] ",
        checkingTriple(asIterableFunName, { arrChar(this).asAssert().contains(5.toChar()) }, chars(1.toChar(), 5.toChar()), chars()),
        checkingTriple(asIterableWithCreator, { arrCharWithCreator(this) { asAssert().contains(5.toChar()) }}, chars(1.toChar(), 5.toChar()), chars())
    ) {})
    include(object : CheckingAssertionSpec<ShortArray>(verbs, "$describePrefix[arrShort] ",
        checkingTriple(asIterableFunName, { arrShort(this).asAssert().contains(5.toShort()) }, shorts(1.toShort(), 5.toShort()), shorts()),
        checkingTriple(asIterableWithCreator, { arrShortWithCreator(this) { asAssert().contains(5.toShort()) }}, shorts(1.toShort(), 5.toShort()), shorts())
    ) {})
    include(object : CheckingAssertionSpec<IntArray>(verbs, "$describePrefix[arrInt] ",
        checkingTriple(asIterableFunName, { arrInt(this).asAssert().contains(5) }, ints(1, 5), ints()),
        checkingTriple(asIterableWithCreator, { arrIntWithCreator(this) { asAssert().contains(5) }}, ints(1, 5), ints())
    ) {})
    include(object : CheckingAssertionSpec<LongArray>(verbs, "$describePrefix[arrLong] ",
        checkingTriple(asIterableFunName, { arrLong(this).asAssert().contains(5) }, longs(1, 5), longs()),
        checkingTriple(asIterableWithCreator, { arrLongWithCreator(this) { asAssert().contains(5) }}, longs(1, 5), longs())
    ) {})
    include(object : CheckingAssertionSpec<FloatArray>(verbs, "$describePrefix[arrFloat] ",
        checkingTriple(asIterableFunName, { arrFloat(this).asAssert().contains(5f) }, floats(1f, 5f), floats()),
        checkingTriple(asIterableWithCreator, { arrFloatWithCreator(this) { asAssert().contains(5f) }}, floats(1f, 5f), floats())
    ) {})
    include(object : CheckingAssertionSpec<DoubleArray>(verbs, "$describePrefix[arrDouble] ",
        checkingTriple(asIterableFunName, { arrDouble(this).asAssert().contains(5.0) }, doubles(1.0, 5.0), doubles()),
        checkingTriple(asIterableWithCreator, { arrDoubleWithCreator(this) { asAssert().contains(5.0) }}, doubles(1.0, 5.0), doubles())
    ) {})


    describe("$asIterableFunName arr") {
        it("transformation can be applied and an subsequent assertion made") {
            verbs.check(arrayOf(1, 2)).arr().asAssert().containsExactly(1, 2)
        }
        it("transformation can be applied and a sub-assertion made") {
            verbs.check(arrayOf(1, 2)).arrWithCreator { asAssert().containsExactly(1, 2) }
        }
    }

    describe("$asIterableFunName arrByte") {
        it("transformation can be applied and an subsequent assertion made") {
            verbs.check(bytes(1.toByte(), 2.toByte())).arrByte().asAssert().containsExactly(1.toByte(), 2.toByte())
        }
        it("transformation can be applied and a sub assertion made") {
            verbs.check(bytes(1.toByte(), 2.toByte())).arrByteWithCreator{ asAssert().containsExactly(1.toByte(), 2.toByte()) }
        }
    }
    describe("$asIterableFunName arrChar") {
        it("transformation can be applied and an subsequent assertion made") {
            verbs.check(chars(1.toChar(), 2.toChar())).arrChar().asAssert().containsExactly(1.toChar(), 2.toChar())
        }
        it("transformation can be applied and a sub assertion made") {
            verbs.check(chars(1.toChar(), 2.toChar())).arrCharWithCreator{ asAssert().containsExactly(1.toChar(), 2.toChar()) }
        }
    }
    describe("$asIterableFunName arrShort") {
        it("transformation can be applied and an subsequent assertion made") {
            verbs.check(shorts(1, 2)).arrShort().asAssert().containsExactly(1.toShort(), 2.toShort())
        }
        it("transformation can be applied and a sub assertion made") {
            verbs.check(shorts(1, 2)).arrShortWithCreator{ asAssert().containsExactly(1.toShort(), 2.toShort()) }
        }
    }
    describe("$asIterableFunName arrInt") {
        it("transformation can be applied and an subsequent assertion made") {
            verbs.check(ints(1, 2)).arrInt().asAssert().containsExactly(1, 2)
        }
        it("transformation can be applied and a sub assertion made") {
            verbs.check(ints(1, 2)).arrIntWithCreator{ asAssert().containsExactly(1, 2) }
        }
    }
    describe("$asIterableFunName arrLong") {
        it("transformation can be applied and an subsequent assertion made") {
            verbs.check(longs(1, 2)).arrLong().asAssert().containsExactly(1L, 2L)
        }
        it("transformation can be applied and a sub assertion made") {
            verbs.check(longs(1, 2)).arrLongWithCreator{ asAssert().containsExactly(1L, 2L) }
        }
    }
    describe("$asIterableFunName arrFloat") {
        it("transformation can be applied and an subsequent assertion made") {
            verbs.check(floats(1f, 2f)).arrFloat().asAssert().containsExactly(1f, 2f)
        }
        it("transformation can be applied and a sub assertion made") {
            verbs.check(floats(1f, 2f)).arrFloatWithCreator{ asAssert().containsExactly(1f, 2f) }
        }
    }
    describe("$asIterableFunName arrDouble") {
        it("transformation can be applied and an subsequent assertion made") {
            verbs.check(doubles(1.0, 2.0)).arrDouble().asAssert().containsExactly(1.0, 2.0)
        }
        it("transformation can be applied and a sub assertion made") {
            verbs.check(doubles(1.0, 2.0)).arrDoubleWithCreator{ asAssert().containsExactly(1.0, 2.0) }
        }
    }
    describe("$asIterableFunName arrBoolean") {
        it("transformation can be applied and an subsequent assertion made") {
            verbs.check(booleans(true, false)).arrBoolean().asAssert().containsExactly(true, false)
        }
        it("transformation can be applied and a sub assertion made") {
            verbs.check(booleans(true, false)).arrBooleanWithCreator{ asAssert().containsExactly(true, false) }
        }
    }
})
