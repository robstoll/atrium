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

inline fun <reified T : Any> slot(): CapturingSlot<T> = TODO()

fun <T> every(stubBlock: MockKMatcherScope.() -> T): MockKStubScope<T, T> = TODO()

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
