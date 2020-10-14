package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageContains
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class IteratorAssertionSpec(
    hasNext: Fun0<Iterator<Int>>,
    hasNotNext: Fun0<Iterator<Int>>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<Iterator<Int>>(
        describePrefix,
        hasNext.forSubjectLess()
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    val hasDescriptionBasic = DescriptionBasic.HAS.getDefault()
    val hasNotDescriptionBasic = DescriptionBasic.HAS_NOT.getDefault()
    val nextElement = DescriptionIterableAssertion.NEXT_ELEMENT.getDefault()

    describeFun(hasNext) {
        val hasNextFun = hasNext.lambda

        it("does not throw if an iterator has next") {
            expect(listOf(1, 2).iterator()).hasNextFun()
        }

        it("throws an AssertionError if an iterator does not have next") {
            expect {
                expect(emptyList<Int>().iterator()).hasNextFun()
            }.toThrow<AssertionError> { messageContains("$hasDescriptionBasic: $nextElement") }
        }
    }

    describeFun(hasNotNext) {
        val hasNotNextFun = hasNotNext.lambda

        it("does not throw if an iterator has next") {
            expect {
                expect(emptyList<Int>().iterator()).hasNotNextFun()
            }
        }

        it("throws an AssertionError if an iterator has next") {
            expect {
                expect(listOf(1, 2).iterator()).hasNotNextFun()
            }.toThrow<AssertionError> { messageContains("$hasNotDescriptionBasic: $nextElement") }
        }
    }
})
