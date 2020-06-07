//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.core.robstoll.lib.checking

object DelegatingAssertionCheckerSpec : ch.tutteli.atrium.specs.checking.DelegatingAssertionCheckerSpec(
    ::DelegatingAssertionChecker
)
