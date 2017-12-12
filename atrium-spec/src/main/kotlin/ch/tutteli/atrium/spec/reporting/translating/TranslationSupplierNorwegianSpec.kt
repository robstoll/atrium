package ch.tutteli.atrium.spec.reporting.translating

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.api.cc.en_UK.contains
import ch.tutteli.atrium.api.cc.en_UK.message
import ch.tutteli.atrium.api.cc.en_UK.toThrow
import ch.tutteli.atrium.reporting.IReporter
import ch.tutteli.atrium.reporting.translating.ISimpleTranslatable
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.spec.AssertionVerb
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.prefixedDescribe
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

/**
 * If you use this Spec then your reporter needs to use a translator which uses the following translations
 * -- all
 * ch.tutteli.atrium.spec.reporting.translating.TranslationSupplierNorwegianSpec$TestTranslatable-
 * thus we omit the prefix in the following -- which should be:
 *
 *
 * the primary local: nn_ZZ_WHATEVER
 * A = nn_NZZ_WHATEVER
 *
 * the primary Locale's natural first fallback: nn_ZZ
 * A=A-nn_ZZ
 * B=B-nn_ZZ
 *
 * the primary Locale's natural second fallback: nn
 * A=A-nn
 * B=B-nn
 * C=C-nn
 *
 * the primary Locale's natural third fallback: no_NO_NY
 * A=A-no_NO_NY
 * B=B-no_NO_NY
 * C=C-no_NO_NY
 * D=D-no_NO_NY
 *
 * the primary Locale's natural fourth fallback: no_NO
 * A=A-no_NO
 * B=B-no_NO
 * C=C-no_NO
 * D=D-no_NO
 * E=E-no_NO
 *
 * the primary Locale's natural firth fallback: no
 * A=A-no
 * B=B-no
 * C=C-no
 * D=D-no
 * E=E-no
 * F=F-no
 *
 * the fallback: de_CH
 * A=A-de_CH
 * B=B-de_CH
 * C=C-de_CH
 * D=D-de_CH
 * E=E-de_CH
 * F=F-de_CH
 * G=G-de_CH
 */
abstract class TranslationSupplierNorwegianSpec(
    verbs: IAssertionVerbFactory,
    reporterNnZzWhatever: IReporter,
    reporterNoNoNy: IReporter,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun prefixedDescribe(description: String, body: SpecBody.() -> Unit)
        = prefixedDescribe(describePrefix, description, body)


    fun checkTranslation(translatable: ITranslatable, expected: String, reporter: IReporter) {
        val expectedMessage = if (expected.isNotBlank()) {
            "${translatable.getDefault()}-$expected:"
        } else {
            translatable.getDefault()
        }
        val plant = AtriumFactory.newReportingPlant(AssertionVerb.ASSERT, 1, reporter)
        verbs.checkException {
            plant.createAndAddAssertion(translatable, 1, { false })
        }.toThrow<AssertionError> { message { contains(expectedMessage) } }
    }

    fun checkTranslationNnZzWhatever(translatable: ITranslatable, expected: String)
        = checkTranslation(translatable, expected, reporterNnZzWhatever)

    fun checkTranslationNoNoNy(translatable: ITranslatable, expected: String)
        = checkTranslation(translatable, expected, reporterNoNoNy)

    val testTranslatable = TestTranslatable::class.simpleName

    val nnZzWhatever = "nn_ZZ_WHATEVER"
    prefixedDescribe("primary locale is '$nnZzWhatever' fallback is 'de_CH'") {

        describe("translation for $testTranslatable.${TestTranslatable.A} is provided for '$nnZzWhatever'") {
            it("uses translation of $nnZzWhatever") {
                checkTranslationNnZzWhatever(TestTranslatable.A, nnZzWhatever)
            }
        }

        val nnZZ = "nn_ZZ"
        describe("translation for $testTranslatable.${TestTranslatable.B} not provided for '$nnZzWhatever' but for $nnZZ") {
            it("uses translation of $nnZZ") {
                checkTranslationNnZzWhatever(TestTranslatable.B, nnZZ)
            }
        }

        val nn = "nn"
        describe("translation for $testTranslatable.${TestTranslatable.C} not provided for '$nnZzWhatever' but for $nn") {
            it("uses translation of $nn") {
                checkTranslationNnZzWhatever(TestTranslatable.C, nn)
            }
        }

        val noNoNy = "no_NO_NY"
        describe("translation for $testTranslatable.${TestTranslatable.D} not provided for '$nnZzWhatever' but for $noNoNy") {
            it("uses translation of $noNoNy") {
                checkTranslationNnZzWhatever(TestTranslatable.D, noNoNy)
            }
        }

        val noNo = "no_NO"
        describe("translation for $testTranslatable.${TestTranslatable.E} not provided for '$nnZzWhatever' but for $noNo") {
            it("uses translation of $noNo") {
                checkTranslationNnZzWhatever(TestTranslatable.E, noNo)
            }
        }

        val no = "no"
        describe("translation for $testTranslatable.${TestTranslatable.F} not provided for '$nnZzWhatever' but for $no") {
            it("uses translation of $no") {
                checkTranslationNnZzWhatever(TestTranslatable.F, no)
            }
        }

        //TODO ResourceBundleBasedTranslationSupplier should look for fallback even if a file for the primary bundle is specified.
//        val deCH = "de_CH"
//        describe("translation for $testTranslatable.${TestTranslatable.H} not provided for '$nnZzWhatever' but for $deCH") {
//            it("uses translation of $deCH") {
//                checkTranslationNnZzWhatever(TestTranslatable.H, deCH)
//            }
//        }

        describe("translation for $testTranslatable.${TestTranslatable.I} not provided at all") {
            it("uses default translation") {
                checkTranslationNnZzWhatever(TestTranslatable.I, "")
            }
        }
    }

    val noNoNy = "no_NO_NY"
    prefixedDescribe("primary locale is '$noNoNy' fallback is 'de_CH'") {

        val nnNo = "nn_NO"
        //TODO does not yet work for ResourceBundleBasedTranslationSupplierSpec due to a bug in JRE
//        describe("translation for $testTranslatable.${TestTranslatable.A} is provided for '$noNoNy' and for $nnNo") {
//            it("uses the translation of $nnNo") {
//                checkTranslationNoNoNy(TestTranslatable.A, nnNo)
//            }
//        }

        val nn = "nn"
        //TODO does not yet work for ResourceBundleBasedTranslationSupplierSpec due to a bug in JRE
//        describe("translation for $testTranslatable.${TestTranslatable.B} is provided for '$noNoNy' and for $nn") {
//            it("uses translation of $nn") {
//                checkTranslationNoNoNy(TestTranslatable.B, nn)
//            }
//        }

        val nnNoNY = "nn_NO_NY"
        describe("translation for $testTranslatable.${TestTranslatable.D} is provided for '$noNoNy' and for $nnNoNY) { //TODO but not for $nnNo nor $nn") {
            it("uses translation of $noNoNy") {
                checkTranslationNoNoNy(TestTranslatable.D, noNoNy)
            }
        }

        val noNo = "no_NO"
        describe("translation for $testTranslatable.${TestTranslatable.E} not provided for '$noNoNy' but for $noNo") {
            it("uses translation of $noNo") {
                checkTranslationNoNoNy(TestTranslatable.E, noNo)
            }
        }

        val no = "no"
        describe("translation for $testTranslatable.${TestTranslatable.F} not provided for '$noNoNy' but for $no") {
            it("uses translation of $no") {
                checkTranslationNoNoNy(TestTranslatable.F, no)
            }
        }

        //TODO ResourceBundleBasedTranslationSupplier should look for fallback even if a file for the primary bundle is specified.
//        val deCH = "de_CH"
//        describe("translation for $testTranslatable.${TestTranslatable.H} not provided for '$noNoNy' but for $deCH") {
//            it("uses translation of $deCH") {
//                checkTranslationNoNoNy(TestTranslatable.H, deCH)
//            }
//        }

        describe("translation for $testTranslatable.${TestTranslatable.I} not provided at all") {
            it("uses default translation") {
                checkTranslationNnZzWhatever(TestTranslatable.I, "")
            }
        }
    }

}) {
    /**
     * Contains [ISimpleTranslatable]s which are used in [TranslationSupplierNorwegianSpec].
     */
    enum class TestTranslatable(override val value: String) : ISimpleTranslatable {
        A("A"),
        B("B"),
        C("C"),
        D("D"),
        E("E"),
        F("F"),
        G("G"),
        H("H"),
        I("I"),
    }
}
