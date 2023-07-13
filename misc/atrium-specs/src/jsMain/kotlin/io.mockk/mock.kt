@file:Suppress("UNUSED_PARAMETER" /* remove file once mockk supports JS */)

package io.mockk

import kotlin.reflect.KClass

inline fun <reified T : Any> mockk(
    name: String? = null,
    relaxed: Boolean = false,
    vararg moreInterfaces: KClass<*>,
    relaxUnitFun: Boolean = false,
    block: T.() -> Unit = {}
): T = TODO()

//inline fun <reified T : Any> slot(): CapturingSlot<T> = TODO()
open class MockKMatcherScope() {
    inline fun <reified T : Any> any(): T = TODO()
    inline fun <reified T : Any> eq(value: T, inverse: Boolean = false): T = TODO()
}

class MockKStubScope<T, B> {
    infix fun returns(returnValue: T): Any = TODO()
}

infix fun MockKStubScope<Unit, Unit>.just(runs: Runs): Any = TODO()
fun <T> every(stubBlock: MockKMatcherScope.() -> T): MockKStubScope<T, T> = TODO()

class MockKVerificationScope : MockKMatcherScope() {}

fun verify(
    ordering: Ordering = Ordering.UNORDERED,
    inverse: Boolean = false,
    atLeast: Int = 1,
    atMost: Int = Int.MAX_VALUE,
    exactly: Int = -1,
    timeout: Long = 0,
    verifyBlock: MockKVerificationScope.() -> Unit
) {
}

fun confirmVerified(vararg mocks: Any) {}

object Runs
typealias runs = Runs

enum class Ordering {
    /**
     * Order is not important. Some calls just should happen
     */
    UNORDERED,

    /**
     * Order is not important. All calls should happen
     */
    ALL,

    /**
     * Order is important, but not all calls are checked
     */
    ORDERED,

    /**
     * Order is important and all calls should be specified
     */
    SEQUENCE
}
