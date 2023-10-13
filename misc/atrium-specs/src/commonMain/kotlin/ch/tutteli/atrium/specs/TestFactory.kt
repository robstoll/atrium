package ch.tutteli.atrium.specs


expect annotation class TestFactory()

expect abstract class DynamicNode
expect class DynamicContainer : DynamicNode
expect class DynamicTest : DynamicNode


interface TestFactoryBuilder {
    fun it(displayName: String, executable: () -> Unit)
    fun describe(displayName: String, setup: TestFactoryBuilder.() -> Unit)
}

class DefaultTestFactoryBuilder : TestFactoryBuilder {
    private val mutableNodes = mutableListOf<DynamicNode>()
    val nodes: List<DynamicNode> get() = mutableNodes.toList()

    override fun it(displayName: String, executable: () -> Unit) {
        mutableNodes.add(dynamicTest(displayName, executable))
    }

    override fun describe(displayName: String, setup: TestFactoryBuilder.() -> Unit) {
        mutableNodes.add(dynamicContainer(displayName, DefaultTestFactoryBuilder().also(setup).nodes))
    }
}

fun testFactory(setup: TestFactoryBuilder.() -> Unit): Iterable<DynamicNode> =
    DefaultTestFactoryBuilder().also(setup).nodes

fun testFactories(vararg factories: Iterable<DynamicNode>) = factories.reduce { acc, it -> acc + it }

expect fun dynamicTest(displayName: String, executable: () -> Unit): DynamicTest
expect fun dynamicContainer(displayName: String, nodes: Iterable<DynamicNode>): DynamicContainer


expect interface TestContainer
expect interface TestExecutable
interface TestFactorySetup {
    fun it(displayName: String, executable: TestExecutable.() -> Unit)
    fun describe(displayName: String, setup: TestContainer.() -> Unit)
}

fun testFactory2(setup: TestFactorySetup.() -> Unit): Any =
    createTestFactoryResult2(createTestFactorySetup().also(setup))


expect fun createTestFactorySetup(): TestFactorySetup
expect fun createTestFactoryResult2(setup: TestFactorySetup): Any
