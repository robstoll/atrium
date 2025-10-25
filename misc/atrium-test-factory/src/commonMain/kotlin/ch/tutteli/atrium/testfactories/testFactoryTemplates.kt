package ch.tutteli.atrium.testfactories

import ch.tutteli.kbox.glue

/**
 * Template method intended for providers of expectation verbs.
 *
 * See sample for how to use it.
 *
 * @return The platform specific test nodes.
 * @sample ch.tutteli.atrium.testfactories.samples.TestFactorySample.testFactoryTemplate
 *
 * @since 1.3.0
 */
fun <TestExecutableT : TestExecutable> testFactoryTemplate(
    setup: TestFactoryBuilder<TestExecutableT>.() -> Unit,
    testExecutableFactory: () -> TestExecutableT
): PlatformTestNodeContainer<PlatformTestNode> =
    turnTestNodesIntoPlatformSpecificTestFactory(buildTestNodes(setup), testExecutableFactory)

/**
 * Template method intended for providers of expectation verbs.
 *
 * See sample for how to use it.
 *
 * @return The platform specific test nodes.
 * @sample ch.tutteli.atrium.testfactories.samples.TestFactorySample.testFactoryTemplateVarag
 *
 * @since 1.3.0
 */
fun <TestExecutableT : TestExecutable> testFactoryTemplate(
    setup: TestFactoryBuilder<TestExecutableT>.() -> Unit,
    otherSetups: Array<out TestFactoryBuilder<TestExecutableT>.() -> Unit>,
    testExecutableFactory: () -> TestExecutableT
): PlatformTestNodeContainer<PlatformTestNode> = turnTestNodesIntoPlatformSpecificTestFactory(
    (setup glue otherSetups).flatMap(::buildTestNodes), testExecutableFactory
)
