package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.cc.en_GB.messageContains
import ch.tutteli.atrium.api.cc.en_GB.toThrow
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.verbs.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionCollectionAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class CollectionAssertionsSpec(
    verbs: AssertionVerbFactory,
    isEmpty: Fun0<Collection<Int>>,
    isNotEmpty: Fun0<Collection<Int>>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<Collection<Int>>(
        describePrefix,
        isEmpty.forSubjectLess(),
        isNotEmpty.forSubjectLess()
    ) {})

    include(object : CheckingAssertionSpec<Collection<Int>>(
        verbs, describePrefix,
        isEmpty.forChecking(listOf(), listOf(1, 2)),
        isNotEmpty.forChecking(listOf(2), listOf())
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val expect = verbs::checkException

    val isDescr = DescriptionBasic.IS.getDefault()
    val isNotDescr = DescriptionBasic.IS_NOT.getDefault()
    val empty = DescriptionCollectionAssertion.EMPTY.getDefault()

    describeFun(isEmpty.name) {
        val isEmptyFun = isEmpty.lambda

        it("does not throw if a collection is empty") {
            verbs.check(listOf<Int>() as Collection<Int>).isEmptyFun()
        }

        it("throws an AssertionError if a collection is not empty") {
            expect {
                verbs.check(listOf(1, 2) as Collection<Int>).isEmptyFun()
            }.toThrow<AssertionError> { messageContains("$isDescr: $empty") }
        }
    }

    describeFun(isNotEmpty.name) {
        val isNotEmptyFun = isNotEmpty.lambda
        
        it("does not throw if a collection is not empty") {
            verbs.check(listOf(1) as Collection<Int>).isNotEmptyFun()
        }

        it("throws an AssertionError if a collection is empty") {
            expect {
                verbs.check(listOf<Int>() as Collection<Int>).isNotEmptyFun()
            }.toThrow<AssertionError> { messageContains("$isNotDescr: $empty") }
        }
    }
})
