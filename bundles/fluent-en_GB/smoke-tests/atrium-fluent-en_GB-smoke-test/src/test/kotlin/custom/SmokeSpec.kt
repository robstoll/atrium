package custom

import ch.tutteli.atrium.api.fluent.en_GB.notToExist
import ch.tutteli.atrium.api.fluent.en_GB.toBePresent
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.createDescriptiveAssertion
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import ch.tutteli.atrium.translations.DescriptionBasic.TO_BE
import org.spekframework.spek2.Spek
import java.nio.file.Paths

object SmokeSpec : Spek({

    test("see if `toEqual` can be used") {
        expect(1).toEqual(1)
    }

    test("see if `Path.existsNot` can be used") {
        expect(Paths.get("nonExisting")).notToExist()
    }

    test("see if own expectation function without i18n can be used") {
        expect(2).toBeEven()
        expect(1).toBeOdd()
    }

    test("see if own expectation function with i18n can be used") {
        expect(4).toBeAMultipleOf(2)
    }
//
//    listOf(
//        "ch.tutteli.atrium.verbs",
//        "ch.tutteli.atrium.api.fluent.en_GB",
//        "ch.tutteli.atrium.logic"
//    ).forEach {
//        test("module $it is loaded") {
//            val boot = ModuleLayer.boot()
//            expect(1).toEqual(1)
//
//            println("modules: ${boot.modules().joinToString("\n")}")
//            println("parents: ${boot.parents().joinToString("\n")}")
//
//            expect(boot.findModule(it)).toBePresent()
//        }
//    }
})

fun Expect<Int>.toBeEven() =
    _logic.createAndAppend("is", Text("an even number")) { it % 2 == 0 }

fun Expect<Int>.toBeOdd() =
    _logic.append(_logic.createDescriptiveAssertion(TO_BE, Text("an odd number")) { it % 2 == 1 })

fun Expect<Int>.toBeAMultipleOf(base: Int) = _logicAppend { toBeAMultipleOf(base) }

private fun AssertionContainer<Int>.toBeAMultipleOf(base: Int): Assertion =
    createDescriptiveAssertion(DescriptionIntAssertions.TO_BE_MULTIPLE_OF, base) { it % base == 0 }

enum class DescriptionIntAssertions(override val value: String) : StringBasedTranslatable {
    TO_BE_MULTIPLE_OF("to be multiple of")
}
