package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.domain.builders.creating.changers.FeatureExtractorBuilder
import ch.tutteli.atrium.domain.builders.creating.changers.FeatureOptions
import ch.tutteli.atrium.domain.builders.reporting.ExpectBuilder
import ch.tutteli.atrium.domain.builders.reporting.ExpectOptions
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.reporter

/**
 * Uses the given [textRepresentation] as representation of the subject instead of the current representation
 * (which defaults to the subject itself).
 */
fun <T> RootExpect<T>.withOptions(textRepresentation: String): Expect<T> =
    withOptions(ExpectOptions(representation = RawString.create(textRepresentation)))

/**
 * Uses the given [configuration]-lambda to create an [ExpectOptions] which in turn is used
 * to override (parts) of the existing configuration.
 */
fun <T> RootExpect<T>.withOptions(configuration: ExpectBuilder.OptionsChooser.() -> Unit): Expect<T> =
    withOptions(ExpectBuilder.OptionsChooser.createAndBuild(configuration))

//TODO #280 get rid of AssertionChecker, that's one root of a bug (which is more a nice to have but still) roadmap#11
//in the same go we should get rid of  ReportingAssertionContainer.AssertionCheckerDecorator, rename it respectively.
/**
 * Uses the given [options] to override (parts) of the existing configuration.
 */
fun <T> RootExpect<T>.withOptions(options: ExpectOptions): Expect<T> = coreFactory.newReportingAssertionContainer(
    ReportingAssertionContainer.AssertionCheckerDecorator.create(
        options.assertionVerb ?: this.config.description,
        this.maybeSubject,
        options.representation ?: this.config.representation,
        //TODO #280 reporter should be configurable as well
        coreFactory.newThrowingAssertionChecker(options.reporter ?: reporter)
    )
)

/**
 * Uses the given [textRepresentation] as representation of the subject instead of the current representation
 * (which defaults to the subject itself).
 */
fun <T, R> FeatureExpect<T, R>.withOptions(textRepresentation: String): Expect<R> =
    withOptions { withTextRepresentation(textRepresentation) }

/**
 * Uses the given [configuration]-lambda to create an [ExpectOptions] which in turn is used
 * to override (parts) of the existing configuration.
 */
fun <T, R> FeatureExpect<T, R>.withOptions(configuration: FeatureExtractorBuilder.OptionsChooser<R>.() -> Unit): Expect<R> =
    withOptions(FeatureExtractorBuilder.OptionsChooser.createAndBuild(configuration))

/**
 * Uses the given [options] to override (parts) of the existing configuration.
 */
fun <T, R> FeatureExpect<T, R>.withOptions(options: FeatureOptions<R>): Expect<R> =
    coreFactory.newFeatureExpect(
        previousExpect,
        maybeSubject,
        FeatureExpectConfig.create(
            options.description ?: config.description,
            maybeSubject.fold(
                { config.representation },
                { subject -> options.representationInsteadOfFeature?.invoke(subject) ?: config.representation }
            )
        ),
        getAssertions()
    )
