import ch.tutteli.atrium._core
import ch.tutteli.atrium._coreAppend
import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.ExpectationVerb
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.core.getOrElse
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.proofs.builders.buildSimpleProof
import ch.tutteli.atrium.reporting.AtriumError
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.reportables.Description
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionAnyProof
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionBasic
import kotlin.test.Test

class SmokeTest {
    @Test
    fun toEqual_canBeUsed() {
        expect(1) toEqual 1
    }

    //TODO 1.3.0 naming (also in other SmokeTetss)
    @Test
    fun expectationFunctionWithoutI18nCanBeUsed() {
        expect(2) toBe even
        expect(1) toBe odd
    }

    @Test
    fun expectationFunctionWithI18nCanBeUsed() {
        expect(4) toBeAMultipleOf 2
    }

    @Test
    fun expectWithinExpect() {
        expect {
            expect(1) {
                expect(2) toEqual 1
            }
        }.toThrow<AssertionError> {
            it message {
                it toContainRegex "${ExpectationVerb.EXPECT.string}\\s+: 1"
                it toContainRegex "${ExpectationVerb.EXPECT.string}\\s+: 2"
                it toContainRegex "${DescriptionAnyProof.TO_EQUAL.string}\\s+: 1"
            }
        }
    }

    @Test
    fun expectAnExceptionOccurred() {
        expect {
            throw IllegalArgumentException()
        }.toThrow<IllegalArgumentException>()
    }

    @Test
    fun expectAnExceptionWithAMessageOccurred() {
        expect {
            throw IllegalArgumentException("oho... hello btw")
        }.toThrow<IllegalArgumentException> {
            messageToContain("hello")
        }
    }

    @Test
    fun expectNotToThrow() {
        expect {

        }.notToThrow()
    }
}


@Suppress("ClassName")
object even
@Suppress("ClassName")
object odd

infix fun Expect<Int>.toBe(@Suppress("UNUSED_PARAMETER") even: even) =
    _core.createAndAppend("to be", Text("an even number")) { it % 2 == 0 }

infix fun Expect<Int>.toBe(@Suppress("UNUSED_PARAMETER") odd: odd) =
    _coreAppend { buildSimpleProof(DescriptionBasic.TO_BE, Text("an odd number")) { it % 2 == 1 } }

infix fun Expect<Int>.toBeAMultipleOf(base: Int): Expect<Int> = _coreAppend { toBeAMultipleOf(base) }

private fun ProofContainer<Int>.toBeAMultipleOf(base: Int): Proof =
    buildSimpleProof(DescriptionIntProofs.TO_BE_MULTIPLE_OF, base) { it % base == 0 }

enum class DescriptionIntProofs(override val string: String) : Description {
    TO_BE_MULTIPLE_OF("to be multiple of")
}
