package ch.tutteli.atrium.creating

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.checking.IAssertionChecker
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.reporting.translating.TranslatableRawString
import kotlin.reflect.KClass

/**
 * Provides [toThrow] methods for making assertions about a [Throwable]
 * which one expects was thrown.
 *
 * @property commonFields The [commonFields]'s [subject][IAssertionPlantWithCommonFields.CommonFields.subject]
 *        represents the thrown [Throwable] and will be used in [ThrowableFluent.toThrow]. Its method
 *        [IAssertionPlantWithCommonFields.CommonFields.fail] is used for failure reporting etc.
 *
 * @constructor Use [AtriumFactory.newThrowableFluent] to create an instance.
 * @param commonFields The [commonFields]'s [subject][IAssertionPlantWithCommonFields.CommonFields.subject]
 *        represents the thrown [Throwable] and will be used in [ThrowableFluent.toThrow]. Its method
 *        [IAssertionPlantWithCommonFields.CommonFields.fail] could be used for failure reporting etc.
 */
class ThrowableFluent internal constructor(
    private val assertionVerb: ITranslatable,
    private val throwable: Throwable?,
    private val assertionChecker: IAssertionChecker
) : IThrowableFluent {

    override fun <TExpected : Throwable> toThrow(expectedType: KClass<TExpected>, description: ITranslatable, nullRepresentation: ITranslatable): IAssertionPlant<TExpected> {
        val subjectPlant = AtriumFactory.newReportingPlantNullable(assertionVerb, throwable, assertionChecker, TranslatableRawString(nullRepresentation))
        return AtriumFactory.newDownCastBuilder(description, expectedType, subjectPlant)
            .cast()
    }

    override fun <TExpected : Throwable> toThrow(expectedType: KClass<TExpected>, description: ITranslatable, nullRepresentation: ITranslatable, createAssertions: IAssertionPlant<TExpected>.() -> Unit): IAssertionPlant<TExpected> {
        val subjectPlant = AtriumFactory.newReportingPlantNullable(assertionVerb, throwable, assertionChecker, TranslatableRawString(nullRepresentation))
        return AtriumFactory.newDownCastBuilder(description, expectedType, subjectPlant)
            .withLazyAssertions(createAssertions)
            .cast()
    }

    companion object {
        /**
         * Creates a [ThrowableFluent] using the given [act] to determine a resulting [throwable]
         * which will eventually be used to create an [IAssertionPlant] where it is used
         * as [subject][IAssertionPlant.subject].
         *
         * @return The newly created [ThrowableFluent].
         */
        fun create(assertionVerb: ITranslatable, act: () -> Unit, assertionChecker: IAssertionChecker): ThrowableFluent {
            var throwable: Throwable? = null
            try {
                act()
            } catch (t: Throwable) {
                throwable = t
            }
            return ThrowableFluent(assertionVerb, throwable, assertionChecker)
        }
    }
}
