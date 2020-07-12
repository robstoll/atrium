@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)

package ch.tutteli.atrium.domain.builders.reporting

import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.core.CoreFactory
import ch.tutteli.atrium.domain.builders.reporting.impl.TextAssertionFormatterOptionImpl
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterFacade
import ch.tutteli.atrium.reporting.AssertionPairFormatter

/**
 * Provides options to register [AssertionFormatter]s to the chosen [AssertionFormatterFacade].
 */
actual interface TextAssertionFormatterOption : TextAssertionFormatterOptionCommon {

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
    @Deprecated(
        "Use the overload which expects KClass instead; will be removed with 1.0.0",
        ReplaceWith("this.withTextCapabilities(*bulletPoints.asSequence().associate { it.first.kotlin to it.second }.toList().toTypedArray()).withDefaultAtriumErrorAdjusters()")
    )
    fun withDefaultTextCapabilities(vararg bulletPoints: Pair<Class<out BulletPointIdentifier>, String>): ReporterOption =
        withTextCapabilities(*bulletPoints.asSequence().associate { it.first.kotlin to it.second }.toList().toTypedArray())
            .withDefaultAtriumErrorAdjusters()

    actual companion object {
        actual fun create(
            options: AssertionFormatterChosenOptions,
            assertionPairFormatter: AssertionPairFormatter
        ): TextAssertionFormatterOption = TextAssertionFormatterOptionImpl(options, assertionPairFormatter)
    }
}

