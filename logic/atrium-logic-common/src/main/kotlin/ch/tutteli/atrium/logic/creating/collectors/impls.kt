package ch.tutteli.atrium.logic.creating.collectors

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.collectors.impl.DefaultAssertionCollector

@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
val <T> AssertionContainer<T>.assertionCollector: AssertionCollector
//TODO 0.16.0 should be via ComponentFactoryContainer, hence move ProofCollector to core
    get() = getImpl(AssertionCollector::class) { DefaultAssertionCollector() }
