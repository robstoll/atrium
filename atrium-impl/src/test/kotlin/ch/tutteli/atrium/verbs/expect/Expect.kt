package ch.tutteli.atrium.verbs.expect

import ch.tutteli.atrium.creating.*

fun expect(act: () -> Unit): ThrowableFluent
    = AssertionPlantFactory.throwableFluent("expect the thrown exception", act)
