package test

import ch.tutteli.atrium.assertions.Assertion
import test.domain.*

interface Expect<T>

fun <T> Expect<T>.toBe(t: T): Expect<T> = _domain { addAssertion(toBe(t)) }
fun <T: Collection<*>> Expect<T>.isEmpty() = _domain { addAssertion(isEmpty()) }
fun <T: Collection<*>> Expect<T>.first() = _domain { first() }

// core
interface CD1 {
    fun <E> isEmpty(domain: KotlinDomain<Collection<E>>): Assertion
    fun <E> first(domain: KotlinDomain<Collection<E>>): RootExpect<E>
}

interface AnyAssertions{
    fun <T> toBe(domain: KotlinDomain<T>, t: T): Assertion
}
