package ch.tutteli.atrium.api.infix.en_GB.creating.feature

import ch.tutteli.atrium.api.infix.en_GB.ExperimentalWithOptions
import ch.tutteli.atrium.api.infix.en_GB.o
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExperimentalExpectConfig
import ch.tutteli.atrium.creating.FeatureExpect
import ch.tutteli.atrium.creating.FeatureExpectConfig
import ch.tutteli.atrium.domain.builders.creating.changers.FeatureExtractorBuilder
import ch.tutteli.atrium.domain.builders.creating.changers.FeatureOptions
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep


infix fun <T, R> FeatureStep<T, R>.it(@Suppress("UNUSED_PARAMETER") o: o): Expect<R> =
    extractedFeaturePostStep.applyOptionsIfGiven().getExpectOfFeature()

/**
 * Returns the newly created [Expect] for the feature, i.e. narrows
 *
 * Workaround to https://youtrack.jetbrains.com/issue/KT-36624
 */
@Suppress("NOTHING_TO_INLINE")
inline infix fun <T, R> FeatureStep<T, R>.it(@Suppress("UNUSED_PARAMETER") o: Expect<*>): Expect<R> =
    extractedFeaturePostStep.applyOptionsIfGiven().getExpectOfFeature()


infix fun <T, R> FeatureStep<T, R>.it(assertionCreator: Expect<R>.() -> Unit): Expect<T> =
    extractedFeaturePostStep.applyOptionsIfGiven().addToInitial(assertionCreator)

data class FeatureStep<T, R>(
    val extractedFeaturePostStep: ExtractedFeaturePostStep<T, R>,
    val options: FeatureOptions<R>? = null
) {

    @ExperimentalWithOptions
    infix fun withRepresentation(textRepresentation: String) = withOptions { withRepresentation(textRepresentation) }

    @ExperimentalWithOptions
    infix fun withOptions(
        configuration: FeatureExtractorBuilder.OptionsChooser<R>.() -> Unit
    ): FeatureStep<T, R> = withOptions(FeatureExtractorBuilder.OptionsChooser.createAndBuild(configuration))

    @ExperimentalWithOptions
    infix fun withOptions(options: FeatureOptions<R>): FeatureStep<T, R> = copy(options = options)

    @PublishedApi
    internal fun ExtractedFeaturePostStep<T, R>.applyOptionsIfGiven() =
        if (options != null) this.withOptions(options)
        else this

    private fun ExtractedFeaturePostStep<T, R>.withOptions(options: FeatureOptions<R>): ExtractedFeaturePostStep<T, R> =
        ExtractedFeaturePostStep(expect,
            extract = { action(this).withOptions(options) },
            extractAndApply = { assertionCreator -> actionAndApply(this, assertionCreator).withOptions(options) }
        )

    //TODO move to domain, used by both APIs
    @UseExperimental(ExperimentalExpectConfig::class)
    private fun <T, R> FeatureExpect<T, R>.withOptions(options: FeatureOptions<R>): FeatureExpect<T, R> =
        coreFactory.newFeatureExpect(
            previousExpect,
            maybeSubject,
            FeatureExpectConfig.create(
                options.description ?: config.description,
                options.representationInsteadOfFeature?.let { provider ->
                    this.maybeSubject.fold({ null }) { provider(it) }
                } ?: this.config.representation
            ),
            getAssertions()
        )


}
