package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageContains
import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionListAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class ListFeatureAssertionsSpec(
    getFeature: Feature1<List<Int>, Int, Int>,
    get: Fun2<List<Int>, Int, Expect<Int>.() -> Unit>,
    getNullableFeature: Feature1<List<Int?>, Int, Int?>,
    getNullable: Fun2<List<Int?>, Int, Expect<Int?>.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : Spek({

    val list = listOf(1, 2, 3, 4)

    include(object : SubjectLessSpec<List<Int>>(describePrefix,
        getFeature.forSubjectLess(1),
        get.forSubjectLess(1) { toBe(1) }
    ) {})
    include(object : SubjectLessSpec<List<Int?>>("$describePrefix[nullable Element] ",
        getNullableFeature.forSubjectLess(1),
        getNullable.forSubjectLess(1) { toBe(null) }
    ) {})

    include(object : AssertionCreatorSpec<List<Int>>(
        describePrefix, list,
        get.forAssertionCreatorSpec("$toBeDescr: 2", 1) { toBe(2) }
    ) {})
    include(object : AssertionCreatorSpec<List<Int?>>(
        "$describePrefix[nullable Element] ", list,
        getNullable.forAssertionCreatorSpec("$toBeDescr: 2", 1) { toBe(2) }
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    val fluent = expect(list)
    val listNullable = listOf(1, null, 3, 4)
    val fluentNullable = expect(listNullable)

    val indexOutOfBounds = DescriptionListAssertion.INDEX_OUT_OF_BOUNDS.getDefault()

    describeFun(getFeature, get) {
        val getFunctions = unifySignatures(getFeature, get)
        context("list $list") {
            getFunctions.forEach { (name, getFun, hasExtraHint) ->
                it("$name - can perform sub-assertion on existing index") {
                    fluent.getFun(0) { toBe(1) }
                }
                it("$name - non-existing index throws" + if (hasExtraHint) " but shows intended sub-assertion" else "") {
                    expect {
                        fluent.getFun(4) { toBe(3) }
                    }.toThrow<AssertionError> {
                        messageContains("get(4): $indexOutOfBounds")
                        if (hasExtraHint) messageContains("$toBeDescr: 3")
                    }
                }
            }


        }
    }

    describeFun(getNullableFeature) {
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

    describeFun(getNullableFeature, getNullable) {
        val getFunctions = unifySignatures(getNullableFeature, getNullable)
        context("list $listNullable") {
            getFunctions.forEach { (name, getFun, hasExtraHint) ->
                it("$name - can perform sub-assertion on existing key") {
                    fluentNullable.getFun(0) { toBe(1) }
                }
                it("$name - can perform sub-assertion on existing key with value null") {
                    fluentNullable.getFun(1) { toBe(null) }
                }

                it("$name - non-existing key throws" + if (hasExtraHint) " but shows intended sub-assertion" else "") {
                    expect {
                        fluentNullable.getFun(4) { toBe(null) }
                    }.toThrow<AssertionError> {
                        messageContains("get(4): $indexOutOfBounds")
                        if (hasExtraHint) messageContains("$toBeDescr: null")
                    }
                }
            }
        }
    }
})
