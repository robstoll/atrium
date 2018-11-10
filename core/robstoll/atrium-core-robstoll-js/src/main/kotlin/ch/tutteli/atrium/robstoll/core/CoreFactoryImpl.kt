package ch.tutteli.atrium.robstoll.core

import ch.tutteli.atrium.core.CoreFactory
import ch.tutteli.atrium.core.robstoll.lib.reporting.RemoveRunnerAtriumErrorAdjuster
import ch.tutteli.atrium.reporting.AtriumErrorAdjuster

class CoreFactoryImpl : CoreFactoryCommonImpl(), CoreFactory {
    override fun newRemoveRunnerAtriumErrorAdjuster(): AtriumErrorAdjuster = RemoveRunnerAtriumErrorAdjuster()
}
