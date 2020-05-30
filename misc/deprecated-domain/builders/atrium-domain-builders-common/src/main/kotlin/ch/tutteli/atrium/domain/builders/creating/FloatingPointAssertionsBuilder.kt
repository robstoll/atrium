package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.domain.creating.FloatingPointAssertions
import ch.tutteli.atrium.domain.creating.floatingPointAssertions

/**
 * Delegates inter alia to the implementation of [FloatingPointAssertions].
 * In detail, it implements [FloatingPointAssertions] by delegating to [floatingPointAssertions]
 * which in turn delegates to the implementation via [loadSingleService].
 */
expect object FloatingPointAssertionsBuilder : FloatingPointAssertions
