package ch.tutteli.atrium.assertions.builders.impl

import ch.tutteli.atrium.assertions.DefaultExplanatoryAssertionGroupType
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.assertions.WarningAssertionGroupType
import ch.tutteli.atrium.assertions.builders.ExplanatoryAssertionGroupOption

internal object ExplanatoryAssertionGroupOptionImpl : ExplanatoryAssertionGroupOption {

    override val withDefault get()
        = ExplanatoryAssertionGroupBuilderImpl(DefaultExplanatoryAssertionGroupType)

    override val withWarning get()
        = ExplanatoryAssertionGroupBuilderImpl(WarningAssertionGroupType)

    override fun <T: ExplanatoryAssertionGroupType> withType(groupType: T)
        = ExplanatoryAssertionGroupBuilderImpl(groupType)
}
