package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageToContain
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.api.verbs.internal.testfactories.ExpectTestExecutableForTests
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.utils.ExpectationCreatorTestData
import ch.tutteli.atrium.testfactories.TestFactory
import ch.tutteli.atrium.testfactories.TestFactoryBuilder
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation

@Suppress("FunctionName")
abstract class AbstractIteratorExpectationsTest(
    private val toHaveNextSpec: Fun0<Iterator<Int>>,
    private val notToHaveNextSpec: Fun0<Iterator<Int>>,
    private val nextFeatureSpec: Feature0<Iterator<Int>, Int>,
    private val nextSpec: Fun1<Iterator<Int>, Expect<Int>.() -> Unit>,
    private val nextFeatureNullableSpec: Feature0<Iterator<Int?>, Int?>,
    private val nextNullableSpec: Fun1<Iterator<Int?>, Expect<Int?>.() -> Unit>,
) : ExpectationFunctionBaseTest() {

    private val list = listOf(1, 2, 3, 4)

    private val toHaveDescr = DescriptionBasic.TO_HAVE.getDefault()
    private val notToHaveDescr = DescriptionBasic.NOT_TO_HAVE.getDefault()
    private val aNextElement = DescriptionIterableLikeExpectation.A_NEXT_ELEMENT.getDefault()
    private val sizeExceededDescr = DescriptionIterableLikeExpectation.SIZE_EXCEEDED.getDefault()

    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        toHaveNextSpec.forSubjectLessTest()
    )

    @TestFactory
    fun expectationCreatorTest() = expectationCreatorTestFactory(
        ExpectationCreatorTestData(
            list.iterator(),
            nextSpec.forExpectationCreatorTest("$toEqualDescr: 1") { toEqual(1) }
        ),
        ExpectationCreatorTestData<Iterator<Int?>>(
            list.iterator(),
            nextNullableSpec.forExpectationCreatorTest("$toEqualDescr: 1") { toEqual(1) },
            groupPrefix = "[nullable Element]"
        )
    )

    @TestFactory
    fun toHaveNext() = testFactory(toHaveNextSpec) { toHaveNextFun ->
        it("does not throw if an iterator has next") {
            expect(listOf(1, 2).iterator()).toHaveNextFun()
        }

        it("throws an AssertionError if an iterator does not have next") {
            expect {
                expect(emptyList<Int>().iterator()).toHaveNextFun()
            }.toThrow<AssertionError> { messageToContain("$toHaveDescr: $aNextElement") }
        }
    }

    @TestFactory
    fun notToHaveNext() = testFactory(notToHaveNextSpec) { notToHaveNextFun ->
        it("does not throw if an iterator does not have next") {
            expect(emptyList<Int>().iterator()).notToHaveNextFun()
        }

        it("throws an AssertionError if an iterator has next") {
            expect {
                expect(listOf(1, 2).iterator()).notToHaveNextFun()
            }.toThrow<AssertionError> { messageToContain("$notToHaveDescr: $aNextElement") }
        }
    }

    @TestFactory
    fun next() = testFactoryForFeatureNonFeature(
        nextFeatureSpec, nextSpec
    ) { name, nextFun, hasExtraHint ->
        verifyNextNonNullable(name, nextFun, hasExtraHint)
    }

    private fun TestFactoryBuilder<ExpectTestExecutableForTests>.verifyNextNonNullable(
        name: String,
        nextFun: Expect<Iterator<Int>>.(Expect<Int>.() -> Unit) -> Expect<Iterator<Int>>,
        hasExtraHint: Boolean
    ) {
        it("$name - can perform sub-assertion if an iterator has next") {
            expect(listOf(1).iterator()).nextFun { toEqual(1) }
        }

        it("$name - throws an AssertionError if an iterator does not have next") {
            expect {
                expect(emptyList<Int>().iterator()).nextFun { toEqual(1) }
            }.toThrow<AssertionError> {
                messageToContain(sizeExceededDescr)
                if (hasExtraHint) messageToContain("$toEqualDescr: 1")
            }
        }
    }

    @TestFactory
    fun next_nullable() = testFactoryForFeatureNonFeature(
        nextFeatureNullableSpec, nextNullableSpec
    ) { name, nextFun, hasExtraHint ->
        val nextFunNonNullable = uncheckedToNonNullable(nextSpec.second, nextFun)[1]
        verifyNextNonNullable(name, nextFunNonNullable, hasExtraHint)

        it("$name - can perform sub-assertion if next value is null") {
            expect(listOf<Int?>(null).iterator()).nextFun { toEqual(null) }
        }
    }
}
