package ch.tutteli.atrium.creating.proofs.impl

import ch.tutteli.atrium.creating.proofs.Proof

internal abstract class TestBasedProof(private val test: () -> Boolean) : Proof {

    private val itHolds by lazy(LazyThreadSafetyMode.NONE) { test() }

    override fun holds(): Boolean = itHolds
}
