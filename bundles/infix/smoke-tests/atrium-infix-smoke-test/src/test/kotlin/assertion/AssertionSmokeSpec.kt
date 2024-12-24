//TODO remove with 2.0.0
@file:Suppress("DEPRECATION")

package assertion

import ch.tutteli.atrium.api.infix.en_GB.existing
import ch.tutteli.atrium.api.infix.en_GB.notToBe
import ch.tutteli.atrium.api.infix.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.createDescriptiveAssertion
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import ch.tutteli.atrium.translations.DescriptionBasic
import org.spekframework.spek2.Spek
import java.nio.file.Paths

//TODO 1.3.0 also add SmokeTest with Proof
object AssertionSmokeSpec : Spek({
    test("see if `toEqual` can be used") {
        expect(1) toEqual 1
    }

    test("see if `Path.existsNot` can be used") {
        expect(Paths.get("nonExisting")) notToBe existing
    }

    test("see if own expectation function without i18n can be used") {
        expect(2) toBe even
        expect(1) toBe odd
    }

    test("see if own expectation function with i18n can be used") {
        expect(4) toBeAMultipleOf 2
    }
})


@Suppress("ClassName")
object even

@Suppress("ClassName")
object odd

infix fun Expect<Int>.toBe(@Suppress("UNUSED_PARAMETER", "unused") even: even) =
    _logic.createAndAppend("to be", Text("an even number")) { it % 2 == 0 }

infix fun Expect<Int>.toBe(@Suppress("UNUSED_PARAMETER", "unused") odd: odd) =
    _logic.createAndAppend(DescriptionBasic.TO_BE, Text("an odd number")) { it % 2 == 1 }

infix fun Expect<Int>.toBeAMultipleOf(base: Int): Expect<Int> = _logicAppend { toBeAMultipleOf(base) }

private fun AssertionContainer<Int>.toBeAMultipleOf(base: Int): Assertion =
    createDescriptiveAssertion(DescriptionIntProofs.TO_BE_MULTIPLE_OF, base) { it % base == 0 }

enum class DescriptionIntProofs(override val value: String) : StringBasedTranslatable {
    TO_BE_MULTIPLE_OF("to be multiple of")
}

