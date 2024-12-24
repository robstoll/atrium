package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.toContain
import ch.tutteli.atrium.api.fluent.en_GB.toContainExactly
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.expectLambda
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof
import ch.tutteli.atrium.specs.*
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

abstract class ArrayAsListExpectationsSpec(
    asListFunName: String,
    arr: Expect<Array<Int>>.() -> Expect<List<Int>>,
    arrByte: Expect<ByteArray>.() -> Expect<List<Byte>>,
    arrChar: Expect<CharArray>.() -> Expect<List<Char>>,
    arrShort: Expect<ShortArray>.() -> Expect<List<Short>>,
    arrInt: Expect<IntArray>.() -> Expect<List<Int>>,
    arrLong: Expect<LongArray>.() -> Expect<List<Long>>,
    arrFloat: Expect<FloatArray>.() -> Expect<List<Float>>,
    arrDouble: Expect<DoubleArray>.() -> Expect<List<Double>>,
    arrBoolean: Expect<BooleanArray>.() -> Expect<List<Boolean>>,
    arrWithCreator: Expect<Array<Int>>.(Expect<List<Int>>.() -> Unit) -> Expect<Array<Int>>,
    arrByteWithCreator: Expect<ByteArray>.(Expect<List<Byte>>.() -> Unit) -> Expect<ByteArray>,
    arrCharWithCreator: Expect<CharArray>.(Expect<List<Char>>.() -> Unit) -> Expect<CharArray>,
    arrShortWithCreator: Expect<ShortArray>.(Expect<List<Short>>.() -> Unit) -> Expect<ShortArray>,
    arrIntWithCreator: Expect<IntArray>.(Expect<List<Int>>.() -> Unit) -> Expect<IntArray>,
    arrLongWithCreator: Expect<LongArray>.(Expect<List<Long>>.() -> Unit) -> Expect<LongArray>,
    arrFloatWithCreator: Expect<FloatArray>.(Expect<List<Float>>.() -> Unit) -> Expect<FloatArray>,
    arrDoubleWithCreator: Expect<DoubleArray>.(Expect<List<Double>>.() -> Unit) -> Expect<DoubleArray>,
    arrBooleanWithCreator: Expect<BooleanArray>.(Expect<List<Boolean>>.() -> Unit) -> Expect<BooleanArray>,
    describePrefix: String = "[Atrium] "
) : Spek({

    val asListWithCreator = "$asListFunName with Creator"
    include(object : SubjectLessSpec<Array<Int>>("$describePrefix[arr] ",
        asListFunName to expectLambda { arr(this) },
        asListWithCreator to expectLambda { arrWithCreator(this) { toContain(1) } }
    ) {})
    include(object : SubjectLessSpec<ByteArray>("$describePrefix[arrByte] ",
        asListFunName to expectLambda { arrByte(this) },
        asListWithCreator to expectLambda { arrByteWithCreator(this) { toContain(1) } }
    ) {})
    include(object : SubjectLessSpec<CharArray>("$describePrefix[arrChar] ",
        asListFunName to expectLambda { arrChar(this) },
        asListWithCreator to expectLambda { arrCharWithCreator(this) { toContain('a') } }
    ) {})
    include(object : SubjectLessSpec<ShortArray>("$describePrefix[arrShort] ",
        asListFunName to expectLambda { arrShort(this) },
        asListWithCreator to expectLambda { arrShortWithCreator(this) { toContain(1.toShort()) } }
    ) {})
    include(object : SubjectLessSpec<IntArray>("$describePrefix[arrInt] ",
        asListFunName to expectLambda { arrInt(this) },
        asListWithCreator to expectLambda { arrIntWithCreator(this) { toContain(1) } }
    ) {})
    include(object : SubjectLessSpec<LongArray>("$describePrefix[arrLong] ",
        asListFunName to expectLambda { arrLong(this) },
        asListWithCreator to expectLambda { arrLongWithCreator(this) { toContain(1L) } }
    ) {})
    include(object : SubjectLessSpec<FloatArray>("$describePrefix[arrFloat] ",
        asListFunName to expectLambda { arrFloat(this) },
        asListWithCreator to expectLambda { arrFloatWithCreator(this) { toContain(1f) } }
    ) {})
    include(object : SubjectLessSpec<DoubleArray>("$describePrefix[arrDouble] ",
        asListFunName to expectLambda { arrDouble(this) },
        asListWithCreator to expectLambda { arrDoubleWithCreator(this) { toContain(1.0) } }
    ) {})
    include(object : SubjectLessSpec<BooleanArray>("$describePrefix[arrBoolean] ",
        asListFunName to expectLambda { arrBoolean(this) },
        asListWithCreator to expectLambda { arrBooleanWithCreator(this) { toContain(true) } }
    ) {})

    fun bytes(vararg bytes: Byte) = bytes
    fun chars(vararg chars: Char) = chars
    fun shorts(vararg shorts: Short) = shorts
    fun ints(vararg ints: Int) = ints
    fun longs(vararg longs: Long) = longs
    fun floats(vararg floats: Float) = floats
    fun doubles(vararg doubles: Double) = doubles
    fun booleans(vararg booleans: Boolean) = booleans

    val anElementWhichEquals = DescriptionIterableLikeProof.AN_ELEMENT_WHICH_EQUALS.string
    include(object : AssertionCreatorSpec<Array<Int>>(
        "$describePrefix[arr] ", arrayOf(1),
        assertionCreatorSpecTriple(
            asListFunName,
            anElementWhichEquals,
            { arrWithCreator.invoke(this) { toContain(1) } },
            { arrWithCreator.invoke(this) {} })
    ) {})
    include(object : AssertionCreatorSpec<ByteArray>(
        "$describePrefix[arrByte] ", bytes(1),
        assertionCreatorSpecTriple(
            asListFunName,
            anElementWhichEquals,
            { arrByteWithCreator.invoke(this) { toContain(1) } },
            { arrByteWithCreator.invoke(this) {} })
    ) {})
    include(object : AssertionCreatorSpec<CharArray>(
        "$describePrefix[arrChar] ", chars('a'),
        assertionCreatorSpecTriple(
            asListFunName,
            anElementWhichEquals,
            { arrCharWithCreator.invoke(this) { toContain('a') } },
            { arrCharWithCreator.invoke(this) {} })
    ) {})
    include(object : AssertionCreatorSpec<ShortArray>(
        "$describePrefix[arrShort] ", shorts(1),
        assertionCreatorSpecTriple(
            asListFunName,
            anElementWhichEquals,
            { arrShortWithCreator.invoke(this) { toContain(1) } },
            { arrShortWithCreator.invoke(this) {} })
    ) {})
    include(object : AssertionCreatorSpec<IntArray>(
        "$describePrefix[arrInt] ", ints(1),
        assertionCreatorSpecTriple(
            asListFunName,
            anElementWhichEquals,
            { arrIntWithCreator.invoke(this) { toContain(1) } },
            { arrIntWithCreator.invoke(this) {} })
    ) {})
    include(object : AssertionCreatorSpec<LongArray>(
        "$describePrefix[arrLong] ", longs(1),
        assertionCreatorSpecTriple(
            asListFunName,
            anElementWhichEquals,
            { arrLongWithCreator.invoke(this) { toContain(1) } },
            { arrLongWithCreator.invoke(this) {} })
    ) {})
    include(object : AssertionCreatorSpec<FloatArray>(
        "$describePrefix[arrFloat] ", floats(1.0f),
        assertionCreatorSpecTriple(
            asListFunName,
            anElementWhichEquals,
            { arrFloatWithCreator.invoke(this) { toContain(1.0f) } },
            { arrFloatWithCreator.invoke(this) {} })
    ) {})
    include(object : AssertionCreatorSpec<DoubleArray>(
        "$describePrefix[arrDouble] ", doubles(1.0),
        assertionCreatorSpecTriple(
            asListFunName,
            anElementWhichEquals,
            { arrDoubleWithCreator.invoke(this) { toContain(1.0) } },
            { arrDoubleWithCreator.invoke(this) {} })
    ) {})
    include(object : AssertionCreatorSpec<BooleanArray>(
        "$describePrefix[arrBoolean] ", booleans(true),
        assertionCreatorSpecTriple(
            asListFunName,
            anElementWhichEquals,
            { arrBooleanWithCreator.invoke(this) { toContain(true) } },
            { arrBooleanWithCreator.invoke(this) {} })
    ) {})

    describe("$asListFunName arr") {
        it("transformation can be applied and a subsequent assertion made") {
            expect(arrayOf(1, 2)).arr().toContainExactly(1, 2)
        }
        it("transformation can be applied and a sub-assertion made") {
            expect(arrayOf(1, 2)).arrWithCreator { toContainExactly(1, 2) }
        }
    }

    describe("$asListFunName arrByte") {
        it("transformation can be applied and a subsequent assertion made") {
            expect(bytes(1.toByte(), 2.toByte())).arrByte().toContainExactly(1.toByte(), 2.toByte())
        }
        it("transformation can be applied and a sub assertion made") {
            expect(bytes(1.toByte(), 2.toByte())).arrByteWithCreator { toContainExactly(1.toByte(), 2.toByte()) }
        }
    }
    describe("$asListFunName arrChar") {
        it("transformation can be applied and a subsequent assertion made") {
            expect(chars(1.toChar(), 2.toChar())).arrChar().toContainExactly(1.toChar(), 2.toChar())
        }
        it("transformation can be applied and a sub assertion made") {
            expect(chars(1.toChar(), 2.toChar())).arrCharWithCreator { toContainExactly(1.toChar(), 2.toChar()) }
        }
    }
    describe("$asListFunName arrShort") {
        it("transformation can be applied and a subsequent assertion made") {
            expect(shorts(1, 2)).arrShort().toContainExactly(1.toShort(), 2.toShort())
        }
        it("transformation can be applied and a sub assertion made") {
            expect(shorts(1, 2)).arrShortWithCreator { toContainExactly(1.toShort(), 2.toShort()) }
        }
    }
    describe("$asListFunName arrInt") {
        it("transformation can be applied and a subsequent assertion made") {
            expect(ints(1, 2)).arrInt().toContainExactly(1, 2)
        }
        it("transformation can be applied and a sub assertion made") {
            expect(ints(1, 2)).arrIntWithCreator { toContainExactly(1, 2) }
        }
    }
    describe("$asListFunName arrLong") {
        it("transformation can be applied and a subsequent assertion made") {
            expect(longs(1, 2)).arrLong().toContainExactly(1L, 2L)
        }
        it("transformation can be applied and a sub assertion made") {
            expect(longs(1, 2)).arrLongWithCreator { toContainExactly(1L, 2L) }
        }
    }
    describe("$asListFunName arrFloat") {
        it("transformation can be applied and a subsequent assertion made") {
            expect(floats(1f, 2f)).arrFloat().toContainExactly(1f, 2f)
        }
        it("transformation can be applied and a sub assertion made") {
            expect(floats(1f, 2f)).arrFloatWithCreator { toContainExactly(1f, 2f) }
        }
    }
    describe("$asListFunName arrDouble") {
        it("transformation can be applied and a subsequent assertion made") {
            expect(doubles(1.0, 2.0)).arrDouble().toContainExactly(1.0, 2.0)
        }
        it("transformation can be applied and a sub assertion made") {
            expect(doubles(1.0, 2.0)).arrDoubleWithCreator { toContainExactly(1.0, 2.0) }
        }
    }
    describe("$asListFunName arrBoolean") {
        it("transformation can be applied and a subsequent assertion made") {
            expect(booleans(true, false)).arrBoolean().toContainExactly(true, false)
        }
        it("transformation can be applied and a sub assertion made") {
            expect(booleans(true, false)).arrBooleanWithCreator { toContainExactly(true, false) }
        }
    }
})
