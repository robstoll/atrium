package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.api.cc.en_UK.containsDefaultTranslationOf
import ch.tutteli.atrium.api.cc.en_UK.message
import ch.tutteli.atrium.api.cc.en_UK.toThrow
import ch.tutteli.atrium.assertions.DescriptionBasic
import ch.tutteli.atrium.assertions.DescriptionCollectionAssertion
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.include

abstract class CollectionAssertionsSpec(
    verbs: IAssertionVerbFactory,
    hasSizePair: Pair<String, IAssertionPlant<List<Int>>.(Int) -> IAssertionPlant<List<Int>>>,
    isEmptyPair: Pair<String, IAssertionPlant<List<Int>>.() -> IAssertionPlant<List<Int>>>
) : Spek({

    include(object : ch.tutteli.atrium.spec.assertions.SubjectLessAssertionSpec<List<Int>>(
        hasSizePair.first to mapToCreateAssertion { hasSizePair.second(this, 1) },
        isEmptyPair.first to mapToCreateAssertion { isEmptyPair.second(this) }
    ) {})

    val assert: (List<Int>) -> IAssertionPlant<List<Int>> = verbs::checkImmediately
    val expect = verbs::checkException
    val fluent = assert(listOf(1, 2))

    val (hasSize, hasSizeFun) = hasSizePair
    val (isEmpty, isEmptyFun) = isEmptyPair

    describe("fun $hasSize") {
        context("collection with two entries") {
            test("expect 2 does not throw") {
                fluent.hasSizeFun(2)
            }
            test("expect 1 throws an AssertionError") {
                expect {
                    fluent.hasSizeFun(1)
                }.toThrow<AssertionError>().and.message.containsDefaultTranslationOf(DescriptionCollectionAssertion.HAS_SIZE)
            }
            test("expect 3 throws an AssertionError") {
                expect {
                    fluent.hasSizeFun(3)
                }.toThrow<AssertionError>()
            }
        }
    }

    describe("fun $isEmpty") {
        it("does not throw if a collection is empty") {
            assert(listOf()).isEmptyFun()
        }

        it("throws an AssertionError if a collection is not empty") {
            expect {
                assert(listOf(1, 2)).isEmptyFun()
            }.toThrow<AssertionError>().and.message.containsDefaultTranslationOf(DescriptionBasic.IS)
        }
    }
})
