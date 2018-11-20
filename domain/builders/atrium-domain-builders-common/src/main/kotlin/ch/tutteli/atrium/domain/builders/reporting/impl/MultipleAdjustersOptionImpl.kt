package ch.tutteli.atrium.domain.builders.reporting.impl

import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.domain.builders.reporting.MultipleAdjustersOption
import ch.tutteli.atrium.reporting.AtriumErrorAdjuster

internal class MultipleAdjustersOptionImpl : MultipleAdjustersOption {

    private val _adjusters = mutableListOf<AtriumErrorAdjuster>()
    override val adjusters: List<AtriumErrorAdjuster>
        get() = _adjusters

    override fun withRemoveRunnerAtriumErrorAdjuster() {
        _adjusters.add(coreFactory.newRemoveRunnerAtriumErrorAdjuster())
    }

    override fun withRemoveAtriumFromAtriumErrorAdjuster() {
        _adjusters.add(coreFactory.newRemoveAtriumFromAtriumErrorAdjuster())
    }

    override fun withAtriumErrorAdjuster(adjuster: AtriumErrorAdjuster) {
        _adjusters.add(adjuster)
    }
}
