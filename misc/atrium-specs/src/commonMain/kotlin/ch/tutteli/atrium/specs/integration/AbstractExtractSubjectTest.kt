package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.nonNullableCases
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class AbstractExtractSubjectTest(

    extractSubject: Fun2<Int, String?, Expect<Int>.(Int) -> Unit>,
    extractSubjectNullable: Fun2<Int?, String?, Expect<Int?>.(Int?) -> Unit>,
    extractSubjectDefaultFailureDescription: String,

    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : AssertionCreatorSpec<Int>(
        describePrefix, 1,
        assertionCreatorSpecTriple(extractSubject.name, "$toEqualDescr: 1",
            { extractSubject.invoke(this, "failure description") { toEqual(1) } },
            { extractSubject.invoke(this, "failure description") {} }
        )
    ) {})

    include(object : AssertionCreatorSpec<Int?>(
        describePrefix, 1 as Int?,
        assertionCreatorSpecTriple(extractSubject.name, "$toEqualDescr: 1",
            { extractSubjectNullable.invoke(this, "failure description") { toEqual(1) } },
            { extractSubjectNullable.invoke(this, "failure description") {} }
        )
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    nonNullableCases(
        describePrefix,
        extractSubject,
        extractSubjectNullable
    ) { extractSubjectFun ->

        context("subject defined") {
            it("extraction successful") {
                expect(1).extractSubjectFun("failure desc irrelevant") { subject ->
                    feature("extracted subject") { subject }.toEqual(1)
                }
            }
        }

        context("subject not defined") {
            it("shows the default failure description if null passed") {
                expect {
                    expect(null as Int?).notToEqualNull {
                        extractSubjectFun(null) { _ ->
                            // should not be evaluated
                            toEqual(1)
                        }
                    }
                }.toThrow<AssertionError> {
                    message {
                        toContain(extractSubjectDefaultFailureDescription)
                        notToContain("$toEqualDescr: 1")
                    }
                }
            }
            it("shows given failure description") {
                val failureDescription = "failure description shown in reporting"
                expect {
                    expect(null as Int?).notToEqualNull {
                        extractSubjectFun(failureDescription) { _ ->
                            // should not be evaluated
                            toEqual(1)
                        }
                    }
                }.toThrow<AssertionError> {
                    message {
                        toContain(failureDescription)
                        notToContain("$toEqualDescr: 1")
                    }
                }
            }
        }
    }

    describeFun(extractSubjectNullable) {
        val extractSubjectFun = extractSubjectNullable.lambda
        context("subject defined") {
            it("extraction also successful in case of null") {
                expect(null as Int?).extractSubjectFun("failure desc irrelevant") { subject ->
                    feature("extracted subject") { subject }.toEqual(null)
                }
            }
        }
    }
})
