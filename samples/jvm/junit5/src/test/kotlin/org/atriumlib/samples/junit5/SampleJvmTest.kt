package org.atriumlib.samples.junit5

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.api.verbs.expectGrouped
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExpectGrouping
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.collect
import ch.tutteli.atrium.logic.collectForComposition
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.translations.DescriptionBasic.TO_BE
import org.junit.jupiter.api.Test

class SampleJvmTest {
    @Test
    fun `to equal`() {
        expect(1).toEqual(1)
    }

    @Test
    fun `expect an exception occurred`() {
        expect {
            throw IllegalArgumentException()
        }.toThrow<IllegalArgumentException>()
    }


    @Test
    fun `expect an exception with a message occurred`() {
        expect {
            throw IllegalArgumentException("oho... hello btw")
        }.toThrow<IllegalArgumentException> {
            messageToContain("hello")
        }
    }

    @Test
    fun `use own function`() {
        expect(2).toBeEven()
    }

    fun verifyBar(a: Any) = 1
    fun verifyFoo(a: Any) = 1
    interface SomeTestDsl {
        //...
    }

    fun setupTestDsl(): SomeTestDsl = TODO()

    fun SomeTestDsl.verifyGenericTestLogic(expect: ExpectGrouping) {
        verifyFoo(expect) // might fail but the rest is still evaluated
        verifyBar(expect)
        //...
    }

    fun <T> Expect<T>.extractSubject(
        failureDescription: String = "❗❗ subject extraction not possible, previous expectation failed",
        action: Expect<T>.(T) -> Unit
    ) = _logic.append(
        _logic.maybeSubject.fold({
            assertionBuilder.descriptive.failing.withDescriptionAndRepresentation("subject", Text(failureDescription))
                .build()
        }) { subject ->
            _logic.collect { this.action(subject) }
        }
    )

    @Test
    fun foo() {

        expect {
            setupTestDsl()
        }.notToThrow {
            extractSubject { dsl ->
                dsl.verifyGenericTestLogic(this)
            }
        }
    }
}

fun Expect<Int>.toBeEven() =
    _logic.createAndAppend(TO_BE, Text("an even number")) { it % 2 == 0 }
