package ch.tutteli.atrium.domain.builders.reporting

import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.core.CoreFactory
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.AssertionFormatterFacade
import ch.tutteli.atrium.reporting.AssertionPairFormatter

/**
 * Provides options to register [AssertionFormatter]s to the chosen [AssertionFormatterFacade].
 */
interface TextAssertionFormatterOption {

    /**
     * The so far chosen options which are relevant to create [AssertionFormatter]s.
     */
    val options: AssertionFormatterChosenOptions

    /**
     * The previously chosen [AssertionPairFormatter].
     */
    val assertionPairFormatter: AssertionPairFormatter

    /**
     * Uses [CoreFactory.registerTextAssertionFormatterCapabilities] to register the default [AssertionFormatter]s
     * intended for text output -- using the defined [assertionPairFormatter],
     * [AssertionFormatterChosenOptions.objectFormatter] and [AssertionFormatterChosenOptions.translator]
     * -- to the specified [AssertionFormatterChosenOptions.assertionFormatterFacade] where the given [bulletPoints] can be used to customise
     * the predefined bullet points.
     *
     * Have a look at the sub types of [BulletPointIdentifier] to get a feel for what and how you can customise
     * bullet points.
     */
    fun withDefaultTextCapabilities(vararg bulletPoints: Pair<Class<out BulletPointIdentifier>, String>): ReporterOption

    /**
     * Uses the given [factory] and [otherFactories] to create and register [AssertionFormatter]s to
     * the specified [AssertionFormatterChosenOptions.assertionFormatterFacade].
     */
    fun withTextAssertionFormatter(
        factory: (AssertionFormatterChosenOptions) -> (AssertionFormatterController) -> AssertionFormatter,
        vararg otherFactories: (AssertionFormatterChosenOptions) -> (AssertionFormatterController) -> AssertionFormatter
    ): ReporterOption
}

internal class TextAssertionFormatterOptionImpl(
    override val options: AssertionFormatterChosenOptions,
    override val assertionPairFormatter: AssertionPairFormatter
) : TextAssertionFormatterOption {

    override fun withDefaultTextCapabilities(vararg bulletPoints: Pair<Class<out BulletPointIdentifier>, String>): ReporterOption {
        coreFactory.registerTextAssertionFormatterCapabilities(
            bulletPoints.toMap(), options.assertionFormatterFacade, assertionPairFormatter, options.objectFormatter, options.translator)
        return ReporterOptionImpl(options.assertionFormatterFacade)
    }

    override fun withTextAssertionFormatter(
        factory: (AssertionFormatterChosenOptions) -> (AssertionFormatterController) -> AssertionFormatter,
        vararg otherFactories: (AssertionFormatterChosenOptions) -> (AssertionFormatterController) -> AssertionFormatter
    ): ReporterOption {
        listOf(factory, *otherFactories).forEach {
            options.assertionFormatterFacade.register(it(options))
        }
        return ReporterOptionImpl(options.assertionFormatterFacade)
    }
}
