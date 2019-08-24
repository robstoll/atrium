package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.contains
import ch.tutteli.atrium.api.fluent.en_GB.containsExactly
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

abstract class ArrayAsIterableAssertionsSpec(
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
    arrWithCreator: Expect<Array<Int>>.(Expect<Iterable<Int>>.() -> Unit) -> Expect<Array<Int>>,
    arrByteWithCreator: Expect<ByteArray>.(Expect<Iterable<Byte>>.() -> Unit) -> Expect<ByteArray>,
    arrCharWithCreator: Expect<CharArray>.(Expect<Iterable<Char>>.() -> Unit) -> Expect<CharArray>,
    arrShortWithCreator: Expect<ShortArray>.(Expect<Iterable<Short>>.() -> Unit) -> Expect<ShortArray>,
    arrIntWithCreator: Expect<IntArray>.(Expect<Iterable<Int>>.() -> Unit) -> Expect<IntArray>,
    arrLongWithCreator: Expect<LongArray>.(Expect<Iterable<Long>>.() -> Unit) -> Expect<LongArray>,
    arrFloatWithCreator: Expect<FloatArray>.(Expect<Iterable<Float>>.() -> Unit) -> Expect<FloatArray>,
    arrDoubleWithCreator: Expect<DoubleArray>.(Expect<Iterable<Double>>.() -> Unit) -> Expect<DoubleArray>,
    arrBooleanWithCreator: Expect<BooleanArray>.(Expect<Iterable<Boolean>>.() -> Unit) -> Expect<BooleanArray>,
    describePrefix: String = "[Atrium] "
) : Spek({

    val asIterableWithCreator = "$asIterableFunName with Creator"
    include(object : SubjectLessSpec<Array<Int>>("$describePrefix[arr] ",
        asIterableFunName to expectLambda { arr(this) },
        asIterableWithCreator to expectLambda { arrWithCreator(this) { contains(1) } }
    ) {})
    include(object : SubjectLessSpec<ByteArray>("$describePrefix[arrByte] ",
        asIterableFunName to expectLambda { arrByte(this) },
        asIterableWithCreator to expectLambda { arrByteWithCreator(this) { contains(1) } }
    ) {})
    include(object : SubjectLessSpec<CharArray>("$describePrefix[arrChar] ",
        asIterableFunName to expectLambda { arrChar(this) },
        asIterableWithCreator to expectLambda { arrCharWithCreator(this) { contains('a') } }
    ) {})
    include(object : SubjectLessSpec<ShortArray>("$describePrefix[arrShort] ",
        asIterableFunName to expectLambda { arrShort(this) },
        asIterableWithCreator to expectLambda { arrShortWithCreator(this) { contains(1.toShort()) } }
    ) {})
    include(object : SubjectLessSpec<IntArray>("$describePrefix[arrInt] ",
        asIterableFunName to expectLambda { arrInt(this) },
        asIterableWithCreator to expectLambda { arrIntWithCreator(this) { contains(1) } }
    ) {})
    include(object : SubjectLessSpec<LongArray>("$describePrefix[arrLong] ",
        asIterableFunName to expectLambda { arrLong(this) },
        asIterableWithCreator to expectLambda { arrLongWithCreator(this) { contains(1L) } }
    ) {})
    include(object : SubjectLessSpec<FloatArray>("$describePrefix[arrFloat] ",
        asIterableFunName to expectLambda { arrFloat(this) },
        asIterableWithCreator to expectLambda { arrFloatWithCreator(this) { contains(1f) } }
    ) {})
    include(object : SubjectLessSpec<DoubleArray>("$describePrefix[arrDouble] ",
        asIterableFunName to expectLambda { arrDouble(this) },
        asIterableWithCreator to expectLambda { arrDoubleWithCreator(this) { contains(1.0) } }
    ) {})
    include(object : SubjectLessSpec<BooleanArray>("$describePrefix[arrBoolean] ",
        asIterableFunName to expectLambda { arrBoolean(this) },
        asIterableWithCreator to expectLambda { arrBooleanWithCreator(this) { contains(true) } }
    ) {})

    fun bytes(vararg bytes: Byte) = bytes
    fun chars(vararg chars: Char) = chars
    fun shorts(vararg shorts: Short) = shorts
    fun ints(vararg ints: Int) = ints
    fun longs(vararg longs: Long) = longs
    fun floats(vararg floats: Float) = floats
    fun doubles(vararg doubles: Double) = doubles
    fun booleans(vararg booleans: Boolean) = booleans

    val anEntryWhich = DescriptionIterableAssertion.AN_ENTRY_WHICH_IS.getDefault()
    include(object : AssertionCreatorSpec<Array<Int>>(
        "$describePrefix[arr] ", arrayOf(1),
        assertionCreatorSpecTriple(
            asIterableFunName,
            anEntryWhich,
            { arrWithCreator.invoke(this) { contains(1) } },
            { arrWithCreator.invoke(this) {} })
    ) {})
    include(object : AssertionCreatorSpec<ByteArray>(
        "$describePrefix[arrByte] ", bytes(1),
        assertionCreatorSpecTriple(
            asIterableFunName,
            anEntryWhich,
            { arrByteWithCreator.invoke(this) { contains(1) } },
            { arrByteWithCreator.invoke(this) {} })
    ) {})
    include(object : AssertionCreatorSpec<CharArray>(
        "$describePrefix[arrChar] ", chars('a'),
        assertionCreatorSpecTriple(
            asIterableFunName,
            anEntryWhich,
            { arrCharWithCreator.invoke(this) { contains('a') } },
            { arrCharWithCreator.invoke(this) {} })
    ) {})
    include(object : AssertionCreatorSpec<ShortArray>(
        "$describePrefix[arrShort] ", shorts(1),
        assertionCreatorSpecTriple(
            asIterableFunName,
            anEntryWhich,
            { arrShortWithCreator.invoke(this) { contains(1) } },
            { arrShortWithCreator.invoke(this) {} })
    ) {})
    include(object : AssertionCreatorSpec<IntArray>(
        "$describePrefix[arrInt] ", ints(1),
        assertionCreatorSpecTriple(
            asIterableFunName,
            anEntryWhich,
            { arrIntWithCreator.invoke(this) { contains(1) } },
            { arrIntWithCreator.invoke(this) {} })
    ) {})
    include(object : AssertionCreatorSpec<LongArray>(
        "$describePrefix[arrLong] ", longs(1),
        assertionCreatorSpecTriple(
            asIterableFunName,
            anEntryWhich,
            { arrLongWithCreator.invoke(this) { contains(1) } },
            { arrLongWithCreator.invoke(this) {} })
    ) {})
    include(object : AssertionCreatorSpec<FloatArray>(
        "$describePrefix[arrFloat] ", floats(1.0f),
        assertionCreatorSpecTriple(
            asIterableFunName,
            anEntryWhich,
            { arrFloatWithCreator.invoke(this) { contains(1.0f) } },
            { arrFloatWithCreator.invoke(this) {} })
    ) {})
    include(object : AssertionCreatorSpec<DoubleArray>(
        "$describePrefix[arrDouble] ", doubles(1.0),
        assertionCreatorSpecTriple(
            asIterableFunName,
            anEntryWhich,
            { arrDoubleWithCreator.invoke(this) { contains(1.0) } },
            { arrDoubleWithCreator.invoke(this) {} })
    ) {})
    include(object : AssertionCreatorSpec<BooleanArray>(
        "$describePrefix[arrBoolean] ", booleans(true),
        assertionCreatorSpecTriple(
            asIterableFunName,
            anEntryWhich,
            { arrBooleanWithCreator.invoke(this) { contains(true) } },
            { arrBooleanWithCreator.invoke(this) {} })
    ) {})

    describe("$asIterableFunName arr") {
        it("transformation can be applied and a subsequent assertion made") {
            expect(arrayOf(1, 2)).arr().containsExactly(1, 2)
        }
        it("transformation can be applied and a sub-assertion made") {
            expect(arrayOf(1, 2)).arrWithCreator { containsExactly(1, 2) }
        }
    }

    describe("$asIterableFunName arrByte") {
        it("transformation can be applied and a subsequent assertion made") {
            expect(bytes(1.toByte(), 2.toByte())).arrByte().containsExactly(1.toByte(), 2.toByte())
        }
        it("transformation can be applied and a sub assertion made") {
            expect(bytes(1.toByte(), 2.toByte())).arrByteWithCreator { containsExactly(1.toByte(), 2.toByte()) }
        }
    }
    describe("$asIterableFunName arrChar") {
        it("transformation can be applied and a subsequent assertion made") {
            expect(chars(1.toChar(), 2.toChar())).arrChar().containsExactly(1.toChar(), 2.toChar())
        }
        it("transformation can be applied and a sub assertion made") {
            expect(chars(1.toChar(), 2.toChar())).arrCharWithCreator { containsExactly(1.toChar(), 2.toChar()) }
        }
    }
    describe("$asIterableFunName arrShort") {
        it("transformation can be applied and a subsequent assertion made") {
            expect(shorts(1, 2)).arrShort().containsExactly(1.toShort(), 2.toShort())
        }
        it("transformation can be applied and a sub assertion made") {
            expect(shorts(1, 2)).arrShortWithCreator { containsExactly(1.toShort(), 2.toShort()) }
        }
    }
    describe("$asIterableFunName arrInt") {
        it("transformation can be applied and a subsequent assertion made") {
            expect(ints(1, 2)).arrInt().containsExactly(1, 2)
        }
        it("transformation can be applied and a sub assertion made") {
            expect(ints(1, 2)).arrIntWithCreator { containsExactly(1, 2) }
        }
    }
    describe("$asIterableFunName arrLong") {
        it("transformation can be applied and a subsequent assertion made") {
            expect(longs(1, 2)).arrLong().containsExactly(1L, 2L)
        }
        it("transformation can be applied and a sub assertion made") {
            expect(longs(1, 2)).arrLongWithCreator { containsExactly(1L, 2L) }
        }
    }
    describe("$asIterableFunName arrFloat") {
        it("transformation can be applied and a subsequent assertion made") {
            expect(floats(1f, 2f)).arrFloat().containsExactly(1f, 2f)
        }
        it("transformation can be applied and a sub assertion made") {
            expect(floats(1f, 2f)).arrFloatWithCreator { containsExactly(1f, 2f) }
        }
    }
    describe("$asIterableFunName arrDouble") {
        it("transformation can be applied and a subsequent assertion made") {
            expect(doubles(1.0, 2.0)).arrDouble().containsExactly(1.0, 2.0)
        }
        it("transformation can be applied and a sub assertion made") {
            expect(doubles(1.0, 2.0)).arrDoubleWithCreator { containsExactly(1.0, 2.0) }
        }
    }
    describe("$asIterableFunName arrBoolean") {
        it("transformation can be applied and a subsequent assertion made") {
            expect(booleans(true, false)).arrBoolean().containsExactly(true, false)
        }
        it("transformation can be applied and a sub assertion made") {
            expect(booleans(true, false)).arrBooleanWithCreator { containsExactly(true, false) }
        }
    }
})
