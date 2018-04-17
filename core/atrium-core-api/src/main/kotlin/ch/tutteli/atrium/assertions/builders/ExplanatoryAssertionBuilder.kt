package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.ExplanatoryAssertion
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs

/**
 * Builder to create an [ExplanatoryAssertion].
 */
interface ExplanatoryAssertionBuilder {

    /**
     * Creates an [ExplanatoryAssertion] using the given [translatable] (using the given [arg] and
     * optionally [otherArgs] as arguments of the [TranslatableWithArgs]) as explanation.
     *
     * It then delegates to the overload which expects a single [Translatable].
     */
    fun create(translatable: Translatable, arg: Any, vararg otherArgs: Any): ExplanatoryAssertion
        = create(TranslatableWithArgs(translatable, arrayOf(arg, *otherArgs)))

    /**
     * Creates an [ExplanatoryAssertion] using the given [translatable] as explanation.
     *
     * In detail, the given [translatable] is turned into a [RawString] so that an [ObjectFormatter] translates the
     * given [translatable] and treats the result as raw string.
     */
    fun create(translatable: Translatable): ExplanatoryAssertion
        = create(RawString.create(translatable))

    /**
     * Creates an [ExplanatoryAssertion] using the given [explanation].
     *
     * In case you want to pass a [String] which should be treated as [RawString] in reporting, then please wrap it
     * into a [RawString] (`RawString.create("Your text..")`.
     */
    fun create(explanation: Any?) : ExplanatoryAssertion
}
