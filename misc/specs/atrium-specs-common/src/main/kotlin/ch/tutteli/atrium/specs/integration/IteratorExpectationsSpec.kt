package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageContains
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class IteratorExpectationsSpec(
    toHaveNext: Fun0<Iterator<Int>>,
    notToHaveNext: Fun0<Iterator<Int>>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<Iterator<Int>>(
        describePrefix,
        toHaveNext.forSubjectLess()
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    val hasDescriptionBasic = DescriptionBasic.HAS.getDefault()
    val hasNotDescriptionBasic = DescriptionBasic.HAS_NOT.getDefault()
    val nextElement = DescriptionIterableAssertion.NEXT_ELEMENT.getDefault()

    describeFun(toHaveNext) {
        val toHaveNextFun = toHaveNext.lambda

        it("does not throw if an iterator has next") {
            expect(listOf(1, 2).iterator()).toHaveNextFun()
        }

        it("throws an AssertionError if an iterator does not have next") {
            expect {
                expect(emptyList<Int>().iterator()).toHaveNextFun()
            }.toThrow<AssertionError> { messageContains("$hasDescriptionBasic: $nextElement") }
        }
    }

    describeFun(notToHaveNext) {
        val notToHaveNextFun = notToHaveNext.lambda

        it("does not throw if an iterator has next") {
            expect {
                expect(emptyList<Int>().iterator()).notToHaveNextFun()
            }
        }

        it("throws an AssertionError if an iterator has next") {
            expect {
                expect(listOf(1, 2).iterator()).notToHaveNextFun()
            }.toThrow<AssertionError> { messageContains("$hasNotDescriptionBasic: $nextElement") }
        }
    }
})
