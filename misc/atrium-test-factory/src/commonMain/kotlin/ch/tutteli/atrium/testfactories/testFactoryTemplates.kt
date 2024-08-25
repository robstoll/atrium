package ch.tutteli.atrium.testfactories

import ch.tutteli.atrium.creating.ExpectationVerbs
import ch.tutteli.kbox.glue

/**
 * Template method intended for providers of expectation verbs.
 *
 * See sample for how to use it.
 *
 * @return The platform specific test factory.
 * @sample ch.tutteli.atrium.testfactories.samples.TestFactorySample.testFactoryTemplate
 *
 * @since 1.3.0
 */
fun testFactoryTemplate(setup: TestFactoryBuilder.() -> Unit, expectationVerbs: ExpectationVerbs): Any =
    turnTestNodesIntoPlatformSpecificTestFactory(buildTestNodes(setup), expectationVerbs)

/**
 * Template method intended for providers of expectation verbs.
 *
 * See sample for how to use it.
 *
 * @return The platform specific test factories.
 * @sample ch.tutteli.atrium.testfactories.samples.TestFactorySample.testFactoryTemplateVarag
 *
 * @since 1.3.0
 */
fun testFactoryTemplate(
    setup: TestFactoryBuilder.() -> Unit,
    otherSetups: Array<out TestFactoryBuilder.() -> Unit>,
    expectationVerbs: ExpectationVerbs
): Any = turnTestNodesIntoPlatformSpecificTestFactory(
    (setup glue otherSetups).flatMap(::buildTestNodes), expectationVerbs
)
