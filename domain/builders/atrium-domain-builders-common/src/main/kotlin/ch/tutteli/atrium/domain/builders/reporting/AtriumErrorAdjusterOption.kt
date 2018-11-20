package ch.tutteli.atrium.domain.builders.reporting

import ch.tutteli.atrium.domain.builders.reporting.impl.AtriumErrorAdjusterOptionImpl
import ch.tutteli.atrium.domain.builders.reporting.impl.MultipleAdjustersOptionImpl
import ch.tutteli.atrium.reporting.AssertionFormatterFacade
import ch.tutteli.atrium.reporting.AtriumError
import ch.tutteli.atrium.reporting.AtriumErrorAdjuster
import ch.tutteli.atrium.reporting.Reporter

/**
 * Provides options to create an [AtriumErrorAdjusterOption].
 */
interface AtriumErrorAdjusterOption : AtriumErrorAdjusterCommonOption<ReporterOption> {

    /**
     * The previously chosen [AssertionFormatterFacade].
     */
    val assertionFormatterFacade: AssertionFormatterFacade

    /**
     * Uses [withMultipleAdjusters] and applies [withRemoveRunnerAtriumErrorAdjuster]
     * and [withRemoveAtriumFromAtriumErrorAdjuster] -- the default configuration might change in the future.
     */
    fun withDefaultAtriumErrorAdjusters(): ReporterOption =
        withMultipleAdjusters {
            withRemoveAtriumFromAtriumErrorAdjuster()
            withRemoveRunnerAtriumErrorAdjuster()
        }

    /**
     * Uses an [AtriumErrorAdjuster] which does not adjust a given [AtriumError] but functions as an identity function.
     */
    fun withNoOpAtriumErrorAdjuster(): ReporterOption


    /**
     * Uses the [AtriumErrorAdjuster] which are defined via the [configure] lambda.
     *
     * @throws IllegalArgumentException in case less than two [AtriumErrorAdjuster] are defined.
     */
    fun withMultipleAdjusters(configure: MultipleAdjustersOption.() -> Unit): ReporterOption

    @Deprecated(
        "Define an AtriumErrorAdjuster or use withDefaultAtriumErrorAdjusters, will be removed with 1.0.0",
        ReplaceWith("this.withDefaultAtriumErrorAdjusters().withOnlyFailureReporter()")
    )
    fun withOnlyFailureReporter(): ReporterBuilderFinalStep

    @Deprecated(
        "Define an AtriumErrorAdjuster or use withDefaultAtriumErrorAdjusters, will be removed with 1.0.0",
        ReplaceWith("this.withDefaultAtriumErrorAdjusters().withCustomReporter(factory)")
    )
    fun withCustomReporter(factory: (AssertionFormatterFacade) -> Reporter): ReporterBuilderFinalStep


    companion object {
        fun create(assertionFormatterFacade: AssertionFormatterFacade): AtriumErrorAdjusterOption =
            AtriumErrorAdjusterOptionImpl(assertionFormatterFacade)
    }
}

/**
 * Provides options to combine multiple [AtriumErrorAdjuster]s.
 */
interface MultipleAdjustersOption : AtriumErrorAdjusterCommonOption<Unit> {

    val adjusters: List<AtriumErrorAdjuster>

    companion object {
        fun create(): MultipleAdjustersOption = MultipleAdjustersOptionImpl()
    }
}
