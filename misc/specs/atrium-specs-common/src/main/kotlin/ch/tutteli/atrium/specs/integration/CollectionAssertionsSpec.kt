package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageContains
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionCollectionAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class CollectionAssertionsSpec(
    isEmpty: Fun0<Collection<Int>>,
    isNotEmpty: Fun0<Collection<Int>>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<Collection<Int>>(
        describePrefix,
        isEmpty.forSubjectLess(),
        isNotEmpty.forSubjectLess()
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    val isDescr = DescriptionBasic.IS.getDefault()
    val isNotDescr = DescriptionBasic.IS_NOT.getDefault()
    val empty = DescriptionCollectionAssertion.EMPTY.getDefault()

    describeFun(isEmpty, isNotEmpty) {
        val isEmptyFun = isEmpty.lambda
        val isNotEmptyFun = isNotEmpty.lambda

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
    }
})
