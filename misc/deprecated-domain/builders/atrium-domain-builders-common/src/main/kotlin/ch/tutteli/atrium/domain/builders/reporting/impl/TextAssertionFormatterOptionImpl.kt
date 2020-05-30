package ch.tutteli.atrium.domain.builders.reporting.impl

import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.domain.builders.reporting.AssertionFormatterChosenOptions
import ch.tutteli.atrium.domain.builders.reporting.AtriumErrorAdjusterOption
import ch.tutteli.atrium.domain.builders.reporting.TextAssertionFormatterOption
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.AssertionPairFormatter
import ch.tutteli.kbox.forElementAndForEachIn
import kotlin.reflect.KClass

internal class TextAssertionFormatterOptionImpl(
    override val options: AssertionFormatterChosenOptions,
    override val assertionPairFormatter: AssertionPairFormatter
) : TextAssertionFormatterOption {

    override fun withTextCapabilities(vararg bulletPoints: Pair<KClass<out BulletPointIdentifier>, String>): AtriumErrorAdjusterOption {
        coreFactory.registerTextAssertionFormatterCapabilities(
            bulletPoints.toMap(),
            options.assertionFormatterFacade,
            assertionPairFormatter,
            options.objectFormatter,
            options.translator
        )
        return AtriumErrorAdjusterOption.create(options.assertionFormatterFacade)
    }

    override fun withTextAssertionFormatter(
        factory: (AssertionFormatterChosenOptions) -> (AssertionFormatterController) -> AssertionFormatter,
        vararg otherFactories: (AssertionFormatterChosenOptions) -> (AssertionFormatterController) -> AssertionFormatter
    ): AtriumErrorAdjusterOption {
        forElementAndForEachIn(factory, otherFactories.asIterable()) {
            options.assertionFormatterFacade.register(it(options))
        }
        return AtriumErrorAdjusterOption.create(options.assertionFormatterFacade)
    }
}
