package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionCollectionAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class CollectionAssertionsSpec(
    isEmpty: Fun0<Collection<Int>>,
    isNotEmpty: Fun0<Collection<Int>>,
    sizeFeature: Feature0<Collection<Int>, Int>,
    size: Fun1<Collection<Int>, Expect<Int>.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<Collection<Int>>(
        describePrefix,
        isEmpty.forSubjectLess(),
        isNotEmpty.forSubjectLess(),
        sizeFeature.forSubjectLess().adjustName { "$it feature" },
        size.forSubjectLess { isGreaterThan(2) }
    ) {})

    include(object : AssertionCreatorSpec<Collection<Int>>(
        describePrefix, listOf(1),
        size.forAssertionCreatorSpec("$toBeDescr: 1") { toBe(1) }
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    val isDescr = DescriptionBasic.IS.getDefault()
    val isNotDescr = DescriptionBasic.IS_NOT.getDefault()
    val empty = DescriptionCollectionAssertion.EMPTY.getDefault()
    val fluent = expect(listOf(1, 2) as Collection<Int>)
    val sizeDescr = DescriptionCollectionAssertion.SIZE.getDefault()

    describeFun(isEmpty, isNotEmpty, sizeFeature, size) {
        val isEmptyFun = isEmpty.lambda
        val isNotEmptyFun = isNotEmpty.lambda
        val sizeFunctions = unifySignatures(sizeFeature, size)

        context("collection is empty") {
            it("${isEmpty.name} - does not throw") {
                expect(listOf<Int>() as Collection<Int>).isEmptyFun()
            }
            it("${isNotEmpty.name} - throws an AssertionError") {
                expect {
                    expect(listOf<Int>() as Collection<Int>).isNotEmptyFun()
                }.toThrow<AssertionError> { messageContains("$isNotDescr: $empty") }
            }
        }

        context("collection is not empty") {
            it("${isEmpty.name} - throws an AssertionError") {
                expect {
                    expect(listOf(1, 2) as Collection<Int>).isEmptyFun()
                }.toThrow<AssertionError> { messageContains("$isDescr: $empty") }
            }
            it("${isNotEmpty.name} - does not throw") {
                expect(listOf(1) as Collection<Int>).isNotEmptyFun()
            }
        }

        context("list with two entries") {
            sizeFunctions.forEach { (name, sizeFun, _) ->
                it("$name - is greater than 1 holds") {
                    fluent.sizeFun { isGreaterThan(1) }
                }
                it("$name - is less than 1 fails") {
                    expect {
                        fluent.sizeFun { isLessThan(1) }
                    }.toThrow<AssertionError> {
                        messageContains("$sizeDescr: 2")
                    }
                }
            }
        }

    }
})
