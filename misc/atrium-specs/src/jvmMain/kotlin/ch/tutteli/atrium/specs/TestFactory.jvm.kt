package ch.tutteli.atrium.specs

actual typealias TestFactory = org.junit.jupiter.api.TestFactory

actual typealias DynamicNode = org.junit.jupiter.api.DynamicNode
actual typealias DynamicContainer = org.junit.jupiter.api.DynamicContainer
actual typealias DynamicTest = org.junit.jupiter.api.DynamicTest


actual fun dynamicTest(displayName: String, executable: () -> Unit): DynamicTest =
    DynamicTest.dynamicTest(displayName, executable)

actual fun dynamicContainer(displayName: String, nodes: Iterable<DynamicNode>): DynamicContainer =
    DynamicContainer.dynamicContainer(displayName, nodes)

actual typealias TestContainer = TestFactorySetup
actual typealias TestExecutable = TestFactorySetup

class DefaultTestFactorySetup : TestFactorySetup {
    private val mutableNodes = mutableListOf<DynamicNode>()
    val nodes: List<DynamicNode> get() = mutableNodes.toList()

    override fun it(displayName: String, executable: TestFactorySetup.() -> Unit) {
        mutableNodes.add(DynamicTest.dynamicTest(displayName) { executable(this) })
    }

    override fun describe(displayName: String, setup: TestFactorySetup.() -> Unit) {
        mutableNodes.add(
            DynamicContainer.dynamicContainer(
                displayName,
                DefaultTestFactorySetup().also(setup).mutableNodes
            )
        )
    }
}

actual fun createTestFactorySetup(): TestFactorySetup = DefaultTestFactorySetup()
actual fun createTestFactoryResult2(setup: TestFactorySetup): Any = (setup as DefaultTestFactorySetup).nodes
