package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * A default implementation for [DescriptiveAssertion] which lazily evaluates [holds].
 *
 * @constructor Constructor overload with a lazy [BasicDescriptiveAssertion.holds].
 * @param description The [BasicDescriptiveAssertion.description].
 * @param representation The [BasicDescriptiveAssertion.representation].
 * @param test Lazily determines whether [BasicDescriptiveAssertion.holds].
 */
@Deprecated("Use DescriptiveAssertion, do not rely on this specific type, will be made internal with 1.0.0")
class BasicDescriptiveAssertion
@Deprecated(
    "use `AssertImpl.builder.descriptive` instead, will be made `internal` with 1.0.0",
    ReplaceWith(
        "AssertImpl.builder.descriptive.create(description, representation, test)",
        "ch.tutteli.atrium.domain.builders.creating.AssertImpl"
    )
)
constructor(
    override val description: Translatable,
    override val representation: Any,
    private val test: () -> Boolean
) : DescriptiveAssertion {

    /**
     * Constructor overload with an eager [BasicDescriptiveAssertion.holds].
     *
     * If the calculation for [holds] is expensive, then you might want to use the other overload with a lazy test.
     *
     * @param description The [BasicDescriptiveAssertion.description].
     * @param representation The [BasicDescriptiveAssertion.representation].
     * @param holds Determines whether [BasicDescriptiveAssertion.holds] or not
     */
    @Deprecated(
        "use `AssertImpl.builder.descriptive` instead, will be made `internal` with 1.0.0",
        ReplaceWith(
            "AssertImpl.builder.descriptive.create(description, representation, holds)",
            "ch.tutteli.atrium.domain.builders.creating.AssertImpl"
        )
    )
    constructor(description: Translatable, representation: Any, holds: Boolean)
        : this(description, representation, { holds })

    override fun holds() = test()

    /**
     * @suppress
     */
    override fun toString() = "$description: $representation (holds=${holds()})"
}
