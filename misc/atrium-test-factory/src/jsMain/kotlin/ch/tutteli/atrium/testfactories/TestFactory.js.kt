package ch.tutteli.atrium.testfactories

import ch.tutteli.atrium.testfactories.mocha.turnIntoDescribeIt

actual typealias TestFactory = kotlin.test.Test

actual fun <TestExecutableT : TestExecutable> turnTestNodesIntoPlatformSpecificTestFactory(
    testNodes: List<TestNode>,
    testExecutableFactory: () -> TestExecutableT
): TestFactoryReturnType = turnIntoDescribeIt(testNodes, testExecutableFactory, isFirstDescribe = true)

actual typealias TestFactoryReturnType = Any
