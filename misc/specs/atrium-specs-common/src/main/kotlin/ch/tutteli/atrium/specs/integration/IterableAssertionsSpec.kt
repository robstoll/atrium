package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageContains
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.specs.Fun0
import ch.tutteli.atrium.specs.SubjectLessSpec
import ch.tutteli.atrium.specs.describeFunTemplate
import ch.tutteli.atrium.specs.forSubjectLess
import ch.tutteli.atrium.specs.include
import ch.tutteli.atrium.specs.lambda
import ch.tutteli.atrium.specs.name
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class IterableAssertionsSpec(
    hasNext: Fun0<Iterable<Int>>,
    hasNotNext: Fun0<Iterable<Int>>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<Iterable<Int>>(
        describePrefix,
        hasNext.forSubjectLess()
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)


    val hasDescriptionBasic = DescriptionBasic.HAS.getDefault()
    val hasNotDescriptionBasic = DescriptionBasic.HAS_NOT.getDefault()
    val nextElement = DescriptionIterableAssertion.NEXT_ELEMENT.getDefault()

    describeFun(hasNext.name) {
        val hasNextFun = hasNext.lambda

        it("does not throw if an iterable has next") {
            expect(listOf(1, 2) as Iterable<Int>).hasNextFun()
        }

        it("throws an AssertionError if an iterable does not have next") {
            expect {
                expect(listOf<Int>() as Iterable<Int>).hasNextFun()
            }.toThrow<AssertionError> { messageContains("$hasDescriptionBasic: $nextElement") }
        }
    }

    describeFun(hasNotNext.name) {
        val hasNotNextFun = hasNotNext.lambda

        it("does not throw if an iterable has not next") {
            expect(listOf<Int>() as Iterable<Int>).hasNotNextFun()
        }

        it("throws an AssertionError if an iterable has next element") {
            expect {
                expect(listOf(1, 2) as Iterable<Int>).hasNotNextFun()
            }.toThrow<AssertionError> { messageContains("$hasNotDescriptionBasic: $nextElement") }
        }
    }
})
