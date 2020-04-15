@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)

package custom

import ch.tutteli.atrium.api.cc.infix.en_GB.toBe
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.AssertionFailedError
import ch.tutteli.atrium.reporting.AtriumError
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.verbs.expect
import org.jetbrains.spek.api.Spek
import kotlin.test.assertEquals
import kotlin.test.Test

class Test {
    @Test
    fun atrium(){
        expect(1) toBe 2
    }

    @Test
    fun opentest4j_directly(){
        throw AssertionFailedError("oh no", 1, 2)
    }

    @Test
    fun mimic_kotlinTestErrorMessage(){
        throw AtriumError.create("expected: <1> but was: <2>", coreFactory.newNoOpAtriumErrorAdjuster())
    }

    @Test
    fun kotlinTest(){
        assertEquals(1, 2, "bla")
    }
}

//TODO remove with 1.0.0 - no need to migrate to Spek2
object SmokeSpec : Spek({
    test("see if `toBe` can be used") {
        expect(1) toBe 1
    }

    test("see if own assertion function without i18n can be used") {
        expect(2) tobe even
    }

    test("see if own assertion function with i18n can be used") {
        expect(4) isMultipleOf 2
    }
})

@Suppress("ClassName")
object even

@Suppress("DEPRECATION")
infix fun Assert<Int>.tobe(@Suppress("UNUSED_PARAMETER") even: even) =
    createAndAddAssertion(DescriptionBasic.IS, RawString.create("an even number")) { subject % 2 == 0 }

infix fun Assert<Int>.isMultipleOf(base: Int) = addAssertion(_isMultipleOf(this, base))

fun _isMultipleOf(plant: AssertionPlant<Int>, base: Int): Assertion =
    AssertImpl.builder.createDescriptive(plant, DescriptionIntAssertions.IS_MULTIPLE_OF, base) { it % base == 0 }

enum class DescriptionIntAssertions(override val value: String) : StringBasedTranslatable {
    IS_MULTIPLE_OF("is multiple of")
}
