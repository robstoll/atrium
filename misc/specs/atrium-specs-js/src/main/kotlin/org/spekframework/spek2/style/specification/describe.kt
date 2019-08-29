//TODO remove as soon as https://github.com/spekframework/spek/issues/706 is fixed
@file:Suppress("UNUSED_PARAMETER")

package org.spekframework.spek2.style.specification

import org.spekframework.spek2.dsl.GroupBody
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

interface MemoizedValue<out T> {
    operator fun provideDelegate(thisRef: Any?, property: KProperty<*>): ReadOnlyProperty<Any?, T>
}

interface Suite {
    fun it(description: String, body: Suite.() -> Unit) {}
    fun context(description: String, body: Suite.() -> Unit) {}
    fun beforeEachTest(body: Suite.() -> Unit) {}
}

fun GroupBody.describe(description: String, body: Suite.() -> Unit) {}
