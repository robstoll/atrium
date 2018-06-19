package ch.tutteli.atrium.domain.builders.reporting

import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.core.CoreFactory
import ch.tutteli.atrium.domain.builders.reporting.impl.TextAssertionFormatterOptionImpl
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.AssertionFormatterFacade
import ch.tutteli.atrium.reporting.AssertionPairFormatter
import kotlin.reflect.KClass

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
    fun withTextCapabilities(vararg bulletPoints: Pair<KClass<out BulletPointIdentifier>, String>): ReporterOption

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
    @Deprecated("use the overload which expects KClass instead; will be removed with 1.0.0", ReplaceWith("withTextCapabilities(*bulletPoints.asSequence().associate { it.first.kotlin to it.second }.toList().toTypedArray())"))
    fun withDefaultTextCapabilities(vararg bulletPoints: Pair<Class<out BulletPointIdentifier>, String>): ReporterOption
        = withTextCapabilities(*bulletPoints.asSequence().associate { it.first.kotlin to it.second }.toList().toTypedArray())


    /**
     * Uses the given [factory] and [otherFactories] to create and register [AssertionFormatter]s to
     * the specified [AssertionFormatterChosenOptions.assertionFormatterFacade].
     */
    fun withTextAssertionFormatter(
        factory: (AssertionFormatterChosenOptions) -> (AssertionFormatterController) -> AssertionFormatter,
        vararg otherFactories: (AssertionFormatterChosenOptions) -> (AssertionFormatterController) -> AssertionFormatter
    ): ReporterOption

    companion object {
        fun create(
            options: AssertionFormatterChosenOptions,
            assertionPairFormatter: AssertionPairFormatter
        ): TextAssertionFormatterOption = TextAssertionFormatterOptionImpl(options, assertionPairFormatter)
    }
}

