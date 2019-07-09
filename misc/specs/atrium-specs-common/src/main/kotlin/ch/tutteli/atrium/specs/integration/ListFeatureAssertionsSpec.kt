package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.translations.DescriptionAnyAssertion
import ch.tutteli.atrium.translations.DescriptionListAssertion
import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.cc.en_GB.messageContains
import ch.tutteli.atrium.api.cc.en_GB.toThrow
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.migration.asAssert
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.verbs.AssertionVerbFactory
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class ListFeatureAssertionsSpec(
    verbs: AssertionVerbFactory,
    getFeature: Feature1<List<Int>, Int, Int>,
    get: Fun2<List<Int>, Int, Expect<Int>.() -> Unit>,
    getNullableFeature: Feature1<List<Int?>, Int, Int?>,
    getNullable: Fun2<List<Int?>, Int, Expect<Int?>.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : Spek({

    //@formatter:off
    include(object : SubjectLessSpec<List<Int>>(describePrefix,
        getFeature.forSubjectLess(1).adjustName { "$it feature" },
        get.forSubjectLess(1){ toBe(1) }
    ){})
    include(object : SubjectLessSpec<List<Int?>>("$describePrefix[nullable Element] ",
        getNullableFeature.forSubjectLess(1).adjustName { "$it feature" },
        getNullable.forSubjectLess(1){ toBe(null) }
    ) {})

    include(object : CheckingAssertionSpec<List<Int>>(verbs, describePrefix,
        getFeature.forChecking(0, listOf(2), listOf()) { toBe(2) }.adjustName { "$it feature" },
        get.forChecking(0, { asAssert().isGreaterThan(1) }, listOf(2, 1), listOf(1, 2))
    ){})
    include(object : CheckingAssertionSpec<List<Int?>>(verbs, "$describePrefix[nullable Element] ",
        getNullableFeature.forChecking(0, listOf(null), listOf()){ toBe(null) }.adjustName { "$it feature" },
        getNullable.forChecking(0, { toBe(1) },listOf(1, null), listOf(2, 1))
    ) {})
    //@formatter:on

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val expect = verbs::checkException
    val list = listOf(1, 2, 3, 4)
    val fluent = verbs.check(list)
    val listNullable = listOf(1, null, 3, 4)
    val fluentNullable = verbs.check(listNullable)

    val toBeDescr = DescriptionAnyAssertion.TO_BE.getDefault()
    val indexOutOfBounds = DescriptionListAssertion.INDEX_OUT_OF_BOUNDS.getDefault()

    describeFun("${getFeature.name} feature") {
        val getFun = getFeature.lambda
        context("list $list") {
            it("can perform sub-assertion on existing index") {
                fluent.getFun(0).toBe(1)
            }
            it("non-existing index throws") {
                expect {
                    fluent.getFun(4).toBe(1)
                }.toThrow<AssertionError> {
                    messageContains("get(4): $indexOutOfBounds")
                }
            }
        }
    }

    describeFun(get.name) {
        val getFun = get.lambda
        context("list $list") {
            it("can perform sub-assertion on existing index") {
                fluent.getFun(0) { toBe(1) }
            }
            it("non-existing index throws but shows intended sub-assertion") {
                expect {
                    fluent.getFun(4) { toBe(3) }
                }.toThrow<AssertionError> {
                    messageContains("get(4): $indexOutOfBounds", "$toBeDescr: 3")
                }
            }
            it("throws if no assertion is made") {
                expect {
                    fluent.getFun(1) { }
                }.toThrow<IllegalStateException> { messageContains("There was not any assertion created") }
            }
        }
    }

    describeFun("${getNullableFeature.name} nullable feature") {
        val getFun = getNullableFeature.lambda
        context("list $listNullable") {
            it("can perform sub-assertion on existing key") {
                fluentNullable.getFun(0).toBe(1)
            }
            it("can perform sub-assertion on existing key with value null") {
                fluentNullable.getFun(1).toBe(null)
            }

            it("non-existing key throws") {
                expect {
                    fluentNullable.getFun(4).toBe(null)
                }.toThrow<AssertionError> {
                    messageContains("get(4): $indexOutOfBounds")
                }
            }
        }
    }

    describeFun("${getNullable.name} nullable") {
        val getFun = getNullable.lambda
        context("list $listNullable") {
            it("can perform sub-assertion on existing key") {
                fluentNullable.getFun(0) { toBe(1) }
            }
            it("can perform sub-assertion on existing key with value null") {
                fluentNullable.getFun(1) { toBe(null) }
            }

            it("non-existing key throws but shows intended sub-assertion") {
                expect {
                    fluentNullable.getFun(4) { toBe(null) }
                }.toThrow<AssertionError> {
                    messageContains("get(4): $indexOutOfBounds", "$toBeDescr: null")
                }
            }
        }
    }
})
