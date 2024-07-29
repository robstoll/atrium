package custom

import ch.tutteli.atrium._core
import ch.tutteli.atrium._coreAppend
import ch.tutteli.atrium.api.fluent.en_GB.notToExist
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.proofs.buildSimpleProof
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.reportables.Description
import ch.tutteli.atrium.translations.DescriptionBasic.TO_BE
import org.spekframework.spek2.Spek
import java.nio.file.Paths

object SmokeSpec : Spek({
    test("see if `toEqual` can be used") {
        expect(1).toEqual(1)
    }

    test("see if `Path.notToExist` can be used") {
        expect(Paths.get("nonExisting")).notToExist()
    }

    test("see if own expectation function  can be used") {
        expect(2).toBeEven()
        expect(1).toBeOdd()
    }

    test("see if own expectation function with i18n can be used") {
        expect(4).toBeAMultipleOf(2)
    }
})

fun Expect<Int>.toBeEven() =
    _core.createAndAppend("is", Text("an even number")) { it % 2 == 0 }

fun Expect<Int>.toBeOdd() =
    _coreAppend { buildSimpleProof(TO_BE, Text("an odd number")) { it % 2 == 1 } }

fun Expect<Int>.toBeAMultipleOf(base: Int): Expect<Int> = _coreAppend { toBeAMultipleOf(base) }

private fun ProofContainer<Int>.toBeAMultipleOf(base: Int): Proof =
    buildSimpleProof(DescriptionIntAssertions.TO_BE_MULTIPLE_OF, base) { it % base == 0 }

enum class DescriptionIntAssertions(override val string: String) : Description {
    TO_BE_MULTIPLE_OF("to be multiple of")
}

