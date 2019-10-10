package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageContains
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.specs.Fun0
import ch.tutteli.atrium.specs.describeFunTemplate
import ch.tutteli.atrium.specs.isDescr
import ch.tutteli.atrium.specs.lambda
import ch.tutteli.atrium.specs.name
import ch.tutteli.atrium.translations.DescriptionOptionalAssertion.EMPTY
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite
import java.lang.AssertionError
import java.util.Optional

abstract class OptionalAssertionsSpec (
    isEmpty: Fun0<Optional<Any>>,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    describeFun(isEmpty.name) {
        val isEmptyFun = isEmpty.lambda

        context("is empty") {
            it ("does not throw") {
                val emptyValue = Optional.empty<Any>()
                expect(emptyValue).isEmptyFun()
            }

            it ("throws an AssertionError") {
                val nonEmptyValue: Optional<Any> = Optional.of(2)
                expect {
                    expect(nonEmptyValue).isEmptyFun()
                }.toThrow<AssertionError> {
                    messageContains("$isDescr: ${EMPTY.getDefault()}")
                }
            }
        }
    }
})
