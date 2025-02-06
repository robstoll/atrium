package ch.tutteli.atrium.api.fluent.logic.based.en_GB.samples

import ch.tutteli.atrium.api.fluent.logic.based.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.expect

fun fails(b: () -> Unit) = expect(b).toThrow<AssertionError>()
