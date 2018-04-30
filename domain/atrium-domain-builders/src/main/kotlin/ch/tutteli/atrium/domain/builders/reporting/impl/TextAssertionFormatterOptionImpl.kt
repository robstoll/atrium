package ch.tutteli.atrium.domain.builders.reporting.impl

import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.domain.builders.reporting.AssertionFormatterChosenOptions
import ch.tutteli.atrium.domain.builders.reporting.ReporterOption
import ch.tutteli.atrium.domain.builders.reporting.TextAssertionFormatterOption
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.AssertionPairFormatter

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
