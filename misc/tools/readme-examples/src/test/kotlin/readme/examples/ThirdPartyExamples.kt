package readme.examples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
//@formatter:off
//snippet-logic-import-start
import ch.tutteli.atrium.logic._logic
//snippet-logic-import-end
//@formatter:on
import ch.tutteli.atrium.reporting.Text
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import readme.examples.jupiter.ReadmeTest
import readme.examples.utils.expect
import java.math.BigDecimal
import kotlin.math.sign

/**
 * See [ReadmeTest] on how these tests are written into README.md
 */
class ThirdPartyExamples : ReadmeTest {

    @Test
    fun `ex-third-party-1`() {
        expect(listOf(1, 2, 3, -1)).toHaveElementsAndAll {
            toHoldThirdPartyExpectation("not to be", Text("negative")) { subject ->
                // in the following we use assertJ
                assertThat(subject).isNotNegative()
            }
        }
    }

    @Test
    fun `ex-third-party-2`() {
        fun <T : Number> Expect<T>.notToBeNegative() =
            toHoldThirdPartyExpectation("not to be", Text("negative")) { subject ->
                when (subject) {
                    is Int -> assertThat(subject).isNotNegative()
                    is Long -> assertThat(subject).isNotNegative()
                    is Float -> assertThat(subject).isNotNegative()
                    is Double -> assertThat(subject).isNotNegative()
                    is BigDecimal -> assertThat(subject).isNotNegative()
                    // we might lose precision with toDouble but in most cases it should be OK
                    else -> assertThat(subject.toDouble()).isNotNegative()
                }
            }

        expect(-10).notToBeNegative()
    }

    @Test
    fun `ex-third-party-3`() {
        //snippet-logic-import-insert

        fun <T : Number> Expect<T>.notToBeNegative() =
            _logic.createAndAppend("not to be", Text("negative")) { subject ->
                when (subject) {
                    is Int -> subject.sign >= 0
                    is Long -> subject.sign >= 0
                    is Float -> subject.sign >= 0
                    is Double -> subject.sign >= 0
                    is BigDecimal -> subject.signum() >= 0
                    //  we might lose precision with toDouble but in most cases it should be OK
                    else -> sign(subject.toDouble()) >= 0
                }
            }

        expect(-10).notToBeNegative()
    }

    data class MyDomainModel(val alpha1: Int)

    fun MyDomainModel.validateMinThreshold() {}
    fun MyDomainModel.validateMaxThreshold() {
        throw IllegalStateException("threshold value for alpha1 exceeded, expected <= 1000, was $alpha1")
    }

    @Test
    fun `ex-third-party-10`() {
        fun Expect<MyDomainModel>.toComplyValidation() =
            toHoldThirdPartyExpectation("to comply", Text("validation")) { subject ->
                subject.validateMinThreshold()
                subject.validateMaxThreshold()
                //...
            }

        expect(MyDomainModel(alpha1 = 1204)).toComplyValidation()
    }
}


