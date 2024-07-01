//TODO remove with 2.0.0 at the latest
@file:Suppress("DEPRECATION")
package ch.tutteli.atrium.specs

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

internal enum class DummyTranslatables(override val value: String) : StringBasedTranslatable {
    VERB("verb"),
    EXPECT("expect"),
    EXPECT_THROWN("expect the thrown exception"),
}
