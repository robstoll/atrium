package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.toContain
import ch.tutteli.atrium.api.fluent.en_GB.toContainExactly
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.expectationCreator
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof
import ch.tutteli.atrium.specs.integration.utils.ExpectationCreatorTestData
import ch.tutteli.atrium.specs.integration.utils.ExpectationCreatorTriple
import ch.tutteli.atrium.specs.integration.utils.SubjectLessTestData
import ch.tutteli.atrium.testfactories.TestFactory
import kotlin.test.Test

@Suppress("FunctionName")
abstract class AbstractArrayAsListExpectationsTest(
    private val asListFunName: String,
    private val arr: Expect<Array<Int>>.() -> Expect<List<Int>>,
    private val byteArr: Expect<ByteArray>.() -> Expect<List<Byte>>,
    private val charArr: Expect<CharArray>.() -> Expect<List<Char>>,
    private val shortArr: Expect<ShortArray>.() -> Expect<List<Short>>,
    private val intArr: Expect<IntArray>.() -> Expect<List<Int>>,
    private val longArr: Expect<LongArray>.() -> Expect<List<Long>>,
    private val floatArr: Expect<FloatArray>.() -> Expect<List<Float>>,
    private val doubleArr: Expect<DoubleArray>.() -> Expect<List<Double>>,
    private val booleanArr: Expect<BooleanArray>.() -> Expect<List<Boolean>>,
    private val arrWithCreator: Expect<Array<Int>>.(Expect<List<Int>>.() -> Unit) -> Expect<Array<Int>>,
    private val byteArrWithCreator: Expect<ByteArray>.(Expect<List<Byte>>.() -> Unit) -> Expect<ByteArray>,
    private val charArrWithCreator: Expect<CharArray>.(Expect<List<Char>>.() -> Unit) -> Expect<CharArray>,
    private val shortArrWithCreator: Expect<ShortArray>.(Expect<List<Short>>.() -> Unit) -> Expect<ShortArray>,
    private val intArrWithCreator: Expect<IntArray>.(Expect<List<Int>>.() -> Unit) -> Expect<IntArray>,
    private val longArrWithCreator: Expect<LongArray>.(Expect<List<Long>>.() -> Unit) -> Expect<LongArray>,
    private val floatArrWithCreator: Expect<FloatArray>.(Expect<List<Float>>.() -> Unit) -> Expect<FloatArray>,
    private val doubleArrWithCreator: Expect<DoubleArray>.(Expect<List<Double>>.() -> Unit) -> Expect<DoubleArray>,
    private val booleanArrWithCreator: Expect<BooleanArray>.(Expect<List<Boolean>>.() -> Unit) -> Expect<BooleanArray>,
) : ExpectationFunctionBaseTest() {

    //TODO 2.0.0 remove once we use K2
    @Suppress("RemoveExplicitTypeArguments")
    @TestFactory
    fun subjectLessTest(): Any {
        val asListWithCreator = "$asListFunName with Creator"
        return subjectLessTestFactory(
            SubjectLessTestData<Array<Int>>(
                asListFunName to expectationCreator { arr(this) },
                asListWithCreator to expectationCreator { arrWithCreator(this) { toContain(1) } },
                groupPrefix = "Array"
            ),
            SubjectLessTestData<ByteArray>(
                asListFunName to expectationCreator { byteArr(this) },
                asListWithCreator to expectationCreator { byteArrWithCreator(this) { toContain(1) } },
                groupPrefix = "ByteArray"
            ),
            SubjectLessTestData<CharArray>(
                asListFunName to expectationCreator { charArr(this) },
                asListWithCreator to expectationCreator { charArrWithCreator(this) { toContain(1) } },
                groupPrefix = "CharArray"
            ),
            SubjectLessTestData<ShortArray>(
                asListFunName to expectationCreator { shortArr(this) },
                asListWithCreator to expectationCreator { shortArrWithCreator(this) { toContain(1) } },
                groupPrefix = "ShortArray"
            ),
            SubjectLessTestData<IntArray>(
                asListFunName to expectationCreator { intArr(this) },
                asListWithCreator to expectationCreator { intArrWithCreator(this) { toContain(1) } },
                groupPrefix = "IntArray"
            ),
            SubjectLessTestData<LongArray>(
                asListFunName to expectationCreator { longArr(this) },
                asListWithCreator to expectationCreator { longArrWithCreator(this) { toContain(1) } },
                groupPrefix = "LongArray"
            ),
            SubjectLessTestData<FloatArray>(
                asListFunName to expectationCreator { floatArr(this) },
                asListWithCreator to expectationCreator { floatArrWithCreator(this) { toContain(1) } },
                groupPrefix = "FloatArray"
            ),
            SubjectLessTestData<DoubleArray>(
                asListFunName to expectationCreator { doubleArr(this) },
                asListWithCreator to expectationCreator { doubleArrWithCreator(this) { toContain(1) } },
                groupPrefix = "DoubleArray"
            ),
            SubjectLessTestData<BooleanArray>(
                asListFunName to expectationCreator { booleanArr(this) },
                asListWithCreator to expectationCreator { booleanArrWithCreator(this) { toContain(1) } },
                groupPrefix = "BooleanArray"
            )
        )
    }

    @TestFactory
    fun expectationCreatorTest(): Any {
        val anElementWhichEquals = DescriptionIterableLikeProof.AN_ELEMENT_WHICH_EQUALS.string
        return expectationCreatorTestFactory(
            ExpectationCreatorTestData(
                arrayOf(1),
                ExpectationCreatorTriple(
                    asListFunName,
                    anElementWhichEquals,
                    { arrWithCreator.invoke(this) { toContain(1) } },
                    { arrWithCreator.invoke(this) {} }
                ),
                groupPrefix = "Array"
            ),
            ExpectationCreatorTestData(
                byteArrayOf(1),
                ExpectationCreatorTriple(
                    asListFunName,
                    anElementWhichEquals,
                    { byteArrWithCreator.invoke(this) { toContain(1) } },
                    { byteArrWithCreator.invoke(this) {} }
                ),
                groupPrefix = "ByteArray"
            ),
            ExpectationCreatorTestData(
                charArrayOf('a'),
                ExpectationCreatorTriple(
                    asListFunName,
                    anElementWhichEquals,
                    { charArrWithCreator.invoke(this) { toContain('a') } },
                    { charArrWithCreator.invoke(this) {} }
                ),
                groupPrefix = "CharArray"
            ),
            ExpectationCreatorTestData(
                shortArrayOf(1),
                ExpectationCreatorTriple(
                    asListFunName,
                    anElementWhichEquals,
                    { shortArrWithCreator.invoke(this) { toContain(1) } },
                    { shortArrWithCreator.invoke(this) {} }
                ),
                groupPrefix = "ShortArray"
            ),
            ExpectationCreatorTestData(
                intArrayOf(1),
                ExpectationCreatorTriple(
                    asListFunName,
                    anElementWhichEquals,
                    { intArrWithCreator.invoke(this) { toContain(1) } },
                    { intArrWithCreator.invoke(this) {} }
                ),
                groupPrefix = "IntArray"
            ),
            ExpectationCreatorTestData(
                longArrayOf(1),
                ExpectationCreatorTriple(
                    asListFunName,
                    anElementWhichEquals,
                    { longArrWithCreator.invoke(this) { toContain(1) } },
                    { longArrWithCreator.invoke(this) {} }
                ),
                groupPrefix = "LongArray"
            ),
            ExpectationCreatorTestData(
                floatArrayOf(1.0f),
                ExpectationCreatorTriple(
                    asListFunName,
                    anElementWhichEquals,
                    { floatArrWithCreator.invoke(this) { toContain(1.0f) } },
                    { floatArrWithCreator.invoke(this) {} }
                ),
                groupPrefix = "FloatArray"
            ),
            ExpectationCreatorTestData(
                doubleArrayOf(1.0),
                ExpectationCreatorTriple(
                    asListFunName,
                    anElementWhichEquals,
                    { doubleArrWithCreator.invoke(this) { toContain(1.0) } },
                    { doubleArrWithCreator.invoke(this) {} }
                ),
                groupPrefix = "DoubleArray"
            ),
            ExpectationCreatorTestData(
                booleanArrayOf(true),
                ExpectationCreatorTriple(
                    asListFunName,
                    anElementWhichEquals,
                    { booleanArrWithCreator.invoke(this) { toContain(true) } },
                    { booleanArrWithCreator.invoke(this) {} }
                ),
                groupPrefix = "BooleanArray"
            )
        )
    }

    @Test
    fun subject_array__transformation_can_be_applied_and_a_subsequent_expectation_made() {
        expect(arrayOf(1, 2)).arr().toContainExactly(1, 2)
    }

    @Test
    fun subject_array__transformation_can_be_applied_and_a_sub_expectation_made() {
        expect(arrayOf(1, 2)).arrWithCreator { toContainExactly(1, 2) }
    }

    @Test
    fun subject_byteArray__transformation_can_be_applied_and_a_subsequent_expectation_made() {
        expect(byteArrayOf(1, 2)).byteArr().toContainExactly(1, 2)
    }

    @Test
    fun subject_byteArray__transformation_can_be_applied_and_a_sub_expectation_made() {
        expect(byteArrayOf(1, 2)).byteArrWithCreator { toContainExactly(1, 2) }
    }

    @Test
    fun subject_charArray__transformation_can_be_applied_and_a_subsequent_expectation_made() {
        expect(charArrayOf(1.toChar(), 2.toChar())).charArr().toContainExactly(1.toChar(), 2.toChar())
    }

    @Test
    fun subject_charArray__transformation_can_be_applied_and_a_sub_expectation_made() {
        expect(charArrayOf(1.toChar(), 2.toChar())).charArrWithCreator { toContainExactly(1.toChar(), 2.toChar()) }
    }

    @Test
    fun subject_shortArray__transformation_can_be_applied_and_a_subsequent_expectation_made() {
        expect(shortArrayOf(1, 2)).shortArr().toContainExactly(1, 2)
    }

    @Test
    fun subject_shortArray__transformation_can_be_applied_and_a_sub_expectation_made() {
        expect(shortArrayOf(1, 2)).shortArrWithCreator { toContainExactly(1, 2) }
    }

    @Test
    fun subject_intArray__transformation_can_be_applied_and_a_subsequent_expectation_made() {
        expect(intArrayOf(1, 2)).intArr().toContainExactly(1, 2)
    }

    @Test
    fun subject_intArray__transformation_can_be_applied_and_a_sub_expectation_made() {
        expect(intArrayOf(1, 2)).intArrWithCreator { toContainExactly(1, 2) }
    }

    @Test
    fun subject_longArray__transformation_can_be_applied_and_a_subsequent_expectation_made() {
        expect(longArrayOf(1, 2)).longArr().toContainExactly(1L, 2L)
    }

    @Test
    fun subject_longArray__transformation_can_be_applied_and_a_sub_expectation_made() {
        expect(longArrayOf(1, 2)).longArrWithCreator { toContainExactly(1L, 2L) }
    }

    @Test
    fun subject_floatArray__transformation_can_be_applied_and_a_subsequent_expectation_made() {
        expect(floatArrayOf(1f, 2f)).floatArr().toContainExactly(1f, 2f)
    }

    @Test
    fun subject_floatArray__transformation_can_be_applied_and_a_sub_expectation_made() {
        expect(floatArrayOf(1f, 2f)).floatArrWithCreator { toContainExactly(1f, 2f) }
    }

    @Test
    fun subject_doubleArray__transformation_can_be_applied_and_a_subsequent_expectation_made() {
        expect(doubleArrayOf(1.0, 2.0)).doubleArr().toContainExactly(1.0, 2.0)
    }

    @Test
    fun subject_doubleArray__transformation_can_be_applied_and_a_sub_expectation_made() {
        expect(doubleArrayOf(1.0, 2.0)).doubleArrWithCreator { toContainExactly(1.0, 2.0) }
    }

    @Test
    fun subject_booleanArray__transformation_can_be_applied_and_a_subsequent_expectation_made() {
        expect(booleanArrayOf(true, false)).booleanArr().toContainExactly(true, false)
    }

    @Test
    fun subject_booleanArray__transformation_can_be_applied_and_a_sub_expectation_made() {
        expect(booleanArrayOf(true, false)).booleanArrWithCreator { toContainExactly(true, false) }
    }
}
