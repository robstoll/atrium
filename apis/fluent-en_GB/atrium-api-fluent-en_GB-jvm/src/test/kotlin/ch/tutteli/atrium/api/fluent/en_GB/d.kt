package test.domain

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.polyfills.cast
import ch.tutteli.atrium.core.trueProvider
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.reporter
import ch.tutteli.atrium.reporting.translating.Untranslatable
import test.AnyAssertions
import test.CD1
import test.Expect
import kotlin.reflect.KClass

interface KotlinDomain<out T> {
    val maybeSubject: Option<T>
    fun <I : Any> getImpl(kClass: KClass<I>, defaultFactory: () -> I): I
    fun addAssertion(a: Assertion): Expect<@UnsafeVariance T>
}

class RootExpect<T>(override val maybeSubject: Option<T>) : Expect<T>,
    KotlinDomain<T> {
    val implFactories: MutableMap<KClass<*>, (() -> Nothing) -> () -> Any> = mutableMapOf()

    inline fun <reified I : Any> withImplFactory(noinline implFactory: (oldFactory: () -> I) -> () -> I): RootExpect<T> {
        implFactories[I::class] = implFactory
        return this
    }

    override fun <I : Any> getImpl(kClass: KClass<I>, defaultFactory: () -> I): I {
        @Suppress("UNCHECKED_CAST")
        val implFactory = implFactories[kClass] as ((() -> I) -> () -> Any)?
        return if (implFactory != null) {
            val impl = implFactory(defaultFactory)()
            kClass.cast(impl)
        } else defaultFactory()
    }

    override fun addAssertion(a: Assertion): Expect<T> =
        if (a.holds()) {
            this
        } else {
            val sb = StringBuilder()
            reporter.format(a, sb)
            throw AssertionError(sb.toString())
        }
}

inline fun <T, R> Expect<T>._domain(provider: KotlinDomain<T>.() -> R): R =
    when (this) {
        is RootExpect<T> -> this.provider()
        else -> throw IllegalStateException("bla")
    }


fun KotlinDomain<Collection<*>>.isEmpty(): Assertion = _impl.isEmpty(this)
fun <E> KotlinDomain<Collection<E>>.first(): Expect<E> = _impl.first(this)

@PublishedApi
internal inline val <E> KotlinDomain<Collection<E>>._impl
    get() = getImpl(CD1::class) { DefaultCollectionAssertions() }

class DefaultCollectionAssertions : CD1 {
    override fun <E> isEmpty(domain: KotlinDomain<Collection<E>>): Assertion =
        ch.tutteli.atrium.assertions.BasicDescriptiveAssertion(Untranslatable("is"), Text("empty")) {
            domain.maybeSubject.fold(trueProvider) { it.isEmpty() }
        }

    override fun <E> first(domain: KotlinDomain<Collection<E>>): RootExpect<E> =
        RootExpect(domain.maybeSubject.flatMap {
            Option.someIf(it.isNotEmpty()) { it.first() }
        })
}

fun <T> KotlinDomain<T>.toBe(t: T) = getImpl(AnyAssertions::class) { DefaultAnyAssertions() }.toBe(this, t)


class DefaultAnyAssertions : AnyAssertions {
    override fun <T> toBe(domain: KotlinDomain<T>, t: T): Assertion =
        ch.tutteli.atrium.assertions.BasicDescriptiveAssertion(Untranslatable("equals"), t ?: Text.NULL) {
            domain.maybeSubject.fold(trueProvider, { it == t })
        }
}
