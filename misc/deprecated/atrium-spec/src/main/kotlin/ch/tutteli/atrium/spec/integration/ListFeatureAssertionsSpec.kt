// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")


package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.isGreaterThan
import ch.tutteli.atrium.api.cc.en_GB.messageContains
import ch.tutteli.atrium.api.cc.en_GB.toBe
import ch.tutteli.atrium.api.cc.en_GB.toThrow
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import ch.tutteli.atrium.translations.DescriptionBasic.TO_BE
import ch.tutteli.atrium.translations.DescriptionListAssertion
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.include

@Deprecated("Switch from Assert to Expect and use Spec from atrium-specs-common; will be removed with 1.0.0")
abstract class ListFeatureAssertionsSpec(
    verbs: AssertionVerbFactory,
    getPlantPair: Pair<String, Assert<List<Int>>.(Int) -> Assert<Int>>,
    getPair: Pair<String, Assert<List<Int>>.(Int, assertionCreator: Assert<Int>.() -> Unit) -> Assert<List<Int>>>,
    getNullablePlantPair: Pair<String, Assert<List<Int?>>.(Int) -> AssertionPlantNullable<Int?>>,
    getNullablePair: Pair<String, Assert<List<Int?>>.(Int, assertionCreator: AssertionPlantNullable<Int?>.() -> Unit) -> Assert<List<Int?>>>,
    describePrefix: String = "[Atrium] "
) : Spek({

    //@formatter:off
    include(@Suppress("DEPRECATION") object : SubjectLessAssertionSpec<List<Int>>(describePrefix,
        "${getPlantPair.first} returns plant" to mapToCreateAssertion { getPlantPair.second(this, 1 ).isGreaterThan(1) },
        getPair.first to mapToCreateAssertion { getPair.second(this, 1 ){ isGreaterThan(1) } }
    ){})
    include(@Suppress("DEPRECATION") object : SubjectLessAssertionSpec<List<Int?>>("$describePrefix[nullable Element] ",
        "${getNullablePlantPair.first} returns plant" to mapToCreateAssertion { getNullablePlantPair.second(this, 1 ).toBe(null) },
        getNullablePair.first to mapToCreateAssertion { getNullablePair.second(this, 1 ){ toBe(null) } }
    ) {})

    include(@Suppress("DEPRECATION") object : CheckingAssertionSpec<List<Int>>(verbs, describePrefix,
        checkingTriple("${getPlantPair.first} returns plant" , { getPlantPair.second(this, 0).isGreaterThan(1) }, listOf(2, 1), listOf(1, 2)),
        checkingTriple(getPair.first, { getPair.second(this, 0) { isGreaterThan(1) } }, listOf(2, 1), listOf(1, 2))
    ){})
    include(@Suppress("DEPRECATION") object : CheckingAssertionSpec<List<Int?>>(verbs, "$describePrefix[nullable Element] ",
        checkingTriple("${getNullablePlantPair.first} returns plant", { getNullablePlantPair.second(this, 0).toBe(1) }, listOf(1, null), listOf(2, 1)),
        checkingTriple(getNullablePair.first, { getNullablePair.second(this, 0) { toBe(1) } }, listOf(1, null), listOf(2, 1))
    ) {})
    //@formatter:on

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit) =
        describeFun(describePrefix, funName, body = body)

    val expect = verbs::checkException
    val list = listOf(1, 2, 3, 4)
    val fluent = verbs.checkImmediately(list)
    val listNullable = listOf(1, null, 3, 4)
    val fluentNullable = verbs.checkImmediately(listNullable)

    val (getPlant, getPlantFun) = getPlantPair
    val (get, getFun) = getPair
    val (getNullablePlant, getNullablePlantFun) = getNullablePlantPair
    val (getNullable, getNullableFun) = getNullablePair

    val toBeDescr = TO_BE.getDefault()
    val indexOutOfBounds = DescriptionListAssertion.INDEX_OUT_OF_BOUNDS.getDefault()

    describeFun("$getPlant returns plant") {
        context("list $list") {
            test("can perform sub-assertion on existing index") {
                fluent.getPlantFun(0).toBe(1)
            }
            test("non-existing index throws") {
                expect {
                    fluent.getPlantFun(4).toBe(1)
                }.toThrow<AssertionError> {
                    messageContains("get(4): $indexOutOfBounds")
                }
            }
        }
    }

    describeFun(get) {
        context("list $list") {
            test("can perform sub-assertion on existing index") {
                fluent.getFun(0) { toBe(1) }
            }
            test("non-existing index throws but shows intended sub-assertion") {
                expect {
                    fluent.getFun(4) { toBe(3) }
                }.toThrow<AssertionError> {
                    messageContains("get(4): $indexOutOfBounds", "$toBeDescr: 3")
                }
            }
            // the new infix throws now an AssertionError instead - not going to test it any more (is covered by bc-tests)
//            test("throws if no assertion is made") {
//                expect {
//                    fluent.getFun(1) { }
//                }.toThrow<IllegalStateException> { messageContains("There was not any assertion created") }
//            }
        }
    }

    describeFun("$getNullable for nullable returns plant") {
        context("list $listNullable") {
            test("can perform sub-assertion on existing key") {
                fluentNullable.getNullablePlantFun(0).toBe(1)
            }
            test("can perform sub-assertion on existing key with value null") {
                fluentNullable.getNullablePlantFun(1).toBe(null)
            }

            test("non-existing key throws") {
                expect {
                    fluentNullable.getNullablePlantFun(4).toBe(null)
                }.toThrow<AssertionError> {
                    messageContains("get(4): $indexOutOfBounds")
                }
            }
        }
    }

    describeFun("$getNullablePlant for nullable") {
        context("list $listNullable") {
            test("can perform sub-assertion on existing key") {
                fluentNullable.getNullableFun(0) { toBe(1) }
            }
            test("can perform sub-assertion on existing key with value null") {
                fluentNullable.getNullableFun(1) { toBe(null) }
            }

            //TODO problem with down-cast which is not subject-less
//            test("non-existing key throws but shows intended sub-assertion") {
//                expect {
//                    fluentNullable.getNullableFun(4) { toBe(null) }
//                }.toThrow<AssertionError> {
//                    messageContains("get(4): $indexOutOfBounds", "$toBeDescr: null")
//                }
//            }
        }
    }
})
