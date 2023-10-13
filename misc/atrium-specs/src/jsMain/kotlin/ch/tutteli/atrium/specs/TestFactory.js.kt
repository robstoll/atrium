package ch.tutteli.atrium.specs

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.api.verbs.internal.expectGrouped
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExpectGrouping
import ch.tutteli.atrium.creating.ExpectGroupingCollector
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.manualFeature

actual typealias TestFactory = kotlin.test.Test

actual abstract class DynamicNode
actual class DynamicTest : DynamicNode()
actual class DynamicContainer : DynamicNode()


// TODO 1.1.0 switch to ExpectGrouping
actual fun dynamicTest(displayName: String, executable: () -> Unit): DynamicTest {
    executable()
    return DynamicTest()
}

actual fun dynamicContainer(displayName: String, nodes: Iterable<DynamicNode>): DynamicContainer {
    nodes.forEach { _ -> /* no op, we assume nodes will throw if something is wrong */ }
    return DynamicContainer()
}

actual typealias TestContainer = ExpectGrouping
actual typealias TestExecutable = Foo

typealias Foo = Expect<*>

class DefaultTestFactorySetup(private val expect: ExpectGroupingCollector<Unit>) : TestFactorySetup {

    override fun it(displayName: String, executable: Expect<*>.() -> Unit) =
        group("it $displayName", executable)

    override fun describe(displayName: String, setup: TestFactorySetup.() -> Unit) =
        group("describe $displayName", setup)

    private fun group(description: String, setup: TestFactorySetup.() -> Unit) {
        DefaultTestFactorySetup(expect._logic.manualFeature(description) { }.transform()).also(setup)
    }
}

actual fun createTestFactorySetup(): TestFactorySetup = DefaultTestFactorySetup(expectCollector())

actual fun createTestFactoryResult2(setup: TestFactorySetup): Any = setup
