//TODO remove with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

import ch.tutteli.atrium._coreAppend
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.proofs.builders.buildSimpleProof
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.createDescriptiveAssertion
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import ch.tutteli.atrium.translations.DescriptionBasic.TO_BE
import kotlin.test.Test

//TODO 1.3.0 SmokeTest with Proof
class AssertionSmokeTest {

    @Test
    fun toEqual_canBeUsed() {
        expect(1).toEqual(1)
    }

    @Test
    fun expectationFunctionWithoutI18nCanBeUsed() {
        expect(2).toBeEvenDeprecated()
        expect(1).toBeOddDeprecated()
    }

    @Test
    fun expectationFunctionWithI18nCanBeUsed() {
        expect(4).toBeAMultipleOfDeprecated(2)
    }

}

fun Expect<Int>.toBeEvenDeprecated() =
    _logic.append(_logic.createDescriptiveAssertion(TO_BE, Text("an even number")) { it % 2 == 0 })

fun Expect<Int>.toBeOddDeprecated() =
    _logic.append(_logic.createDescriptiveAssertion(TO_BE, Text("an odd number")) { it % 2 == 1})

fun Expect<Int>.toBeAMultipleOfDeprecated(base: Int): Expect<Int> = _coreAppend { toBeAMultipleOfDeprecated(base) }

private fun ProofContainer<Int>.toBeAMultipleOfDeprecated(base: Int): Proof =
    buildSimpleProof(DescriptionIntAssertions.TO_BE_MULTIPLE_OF, base) { it % base == 0 }

enum class DescriptionIntAssertions(override val value: String) : StringBasedTranslatable {
    TO_BE_MULTIPLE_OF("to be multiple of")
}
