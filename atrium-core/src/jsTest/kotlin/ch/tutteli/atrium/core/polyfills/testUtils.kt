//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.core.polyfills

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.RootAssertionGroupType

internal val objInterface = object : Assertion {
    override fun holds() = true
}
internal val objClass = object : ch.tutteli.atrium.assertions.EmptyNameAndRepresentationAssertionGroup(RootAssertionGroupType, emptyList()) {}

internal inline fun <reified T : Any> type() = T::class
