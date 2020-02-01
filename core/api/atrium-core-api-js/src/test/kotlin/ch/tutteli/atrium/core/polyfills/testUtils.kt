@file:Suppress("DEPRECATION" /*TODO remove with 1.0.0 */)

package ch.tutteli.atrium.core.polyfills

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.EmptyNameAndRepresentationAssertionGroup
import ch.tutteli.atrium.assertions.RootAssertionGroupType

internal val objInterface = object : Assertion {
    override fun holds() = true
}
internal val objClass = object : EmptyNameAndRepresentationAssertionGroup(RootAssertionGroupType, listOf()) {}

internal inline fun <reified T : Any> type() = T::class
