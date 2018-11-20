package ch.tutteli.atrium.domain.builders.reporting

import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.core.CoreFactory
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.AssertionFormatterFacade
import ch.tutteli.atrium.reporting.AssertionPairFormatter
import kotlin.reflect.KClass

/**
 * Provides options to register [AssertionFormatter]s to the chosen [AssertionFormatterFacade] -- the platform
 * specific interface might provide further options.
 */
expect interface TextAssertionFormatterOption : TextAssertionFormatterOptionCommon{
    companion object {
        fun create(
            options: AssertionFormatterChosenOptions,
            assertionPairFormatter: AssertionPairFormatter
        ): TextAssertionFormatterOption
    }
}


/**
 * Provides options to register [AssertionFormatter]s to the chosen [AssertionFormatterFacade] -- those options
 * have to be provided on all platforms.
 */
interface TextAssertionFormatterOptionCommon {

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
    fun withTextCapabilities(vararg bulletPoints: Pair<KClass<out BulletPointIdentifier>, String>): AtriumErrorAdjusterOption

    /**
     * Uses the given [factory] and [otherFactories] to create and register [AssertionFormatter]s to
     * the specified [AssertionFormatterChosenOptions.assertionFormatterFacade].
     */
    fun withTextAssertionFormatter(
        factory: (AssertionFormatterChosenOptions) -> (AssertionFormatterController) -> AssertionFormatter,
        vararg otherFactories: (AssertionFormatterChosenOptions) -> (AssertionFormatterController) -> AssertionFormatter
    ): AtriumErrorAdjusterOption
}

