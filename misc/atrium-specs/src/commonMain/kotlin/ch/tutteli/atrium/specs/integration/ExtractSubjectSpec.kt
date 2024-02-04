package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class ExtractSubjectSpec(

    extractSubject: Fun2<Int, String?, Expect<Int>.(Int) -> Unit>,
    extractSubjectDefaultFailureDescription: String,

    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : AssertionCreatorSpec<Int>(
        // other functions are tested further below as they don't follow the convention of being a FunX
        describePrefix, 1,
        assertionCreatorSpecTriple(extractSubject.name, "$toEqualDescr: 1",
            { extractSubject.invoke(this, "failure description") { toEqual(1) } },
            { extractSubject.invoke(this, "failure description") {} }
        )
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    describeFun(extractSubject) {
        val extractSubjectFun = extractSubject.lambda

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
})
