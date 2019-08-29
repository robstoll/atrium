package org.spekframework.spek2.dsl

import org.spekframework.spek2.lifecycle.CachingMode
import org.spekframework.spek2.style.specification.MemoizedValue

interface Root : GroupBody {
    fun <T> memoized(mode: CachingMode = CachingMode.EACH_GROUP, body: () -> T): MemoizedValue<T> =
        throw IllegalStateException("not implemented")
}
