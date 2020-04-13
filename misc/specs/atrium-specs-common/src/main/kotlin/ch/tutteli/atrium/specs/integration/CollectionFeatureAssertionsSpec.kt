package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionCollectionAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class CollectionFeatureAssertionsSpec(
    sizeFeature: Feature0<Collection<String>, Int>,
    size: Fun1<Collection<String>, Expect<Int>.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<Collection<String>>(describePrefix,
        sizeFeature.forSubjectLess().adjustName { "$it feature" },
        size.forSubjectLess { isGreaterThan(2) }
    ) {})

    include(object : AssertionCreatorSpec<Collection<String>>(
        describePrefix, listOf("hello"),
        size.forAssertionCreatorSpec("$toBeDescr: 1") { toBe(1) }
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    val fluent = expect(listOf("a", "b") as Collection<String>)
    val sizeDescr = DescriptionCollectionAssertion.SIZE.getDefault()

    describeFun(sizeFeature, size) {
        val sizeFunctions = unifySignatures(sizeFeature, size)

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
