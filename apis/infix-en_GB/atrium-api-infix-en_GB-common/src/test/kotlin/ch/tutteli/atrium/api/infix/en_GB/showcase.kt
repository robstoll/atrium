import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.creating.Expect

fun <T> notImplemented(): Expect<T> = TODO()

data class Person(val name: String, val firstName: String, val age: Int) {
    fun foo(int: Int) = "a"
    fun bar() = "b"

    fun <T> baz(t: T): T = t
    inline fun <reified T> baz2(): T = 1 as T
}


@ExperimentalWithOptions
fun test() {

    val l: Expect<List<Int>> = notImplemented()

    val p: Expect<Pair<String, Int>> = notImplemented()

    l get 1 toBe 2
//    l get 1 { o toBe 1 } get 2 { o toBe 2 }


    p first o toBe "hello"
    p second o toBe 1
    p and {
        first {
            o feature String::length toBe 1
        }
        first withRepresentation "name" toBe "robert"
        second toBe 1
    }

    val e: Expect<Person> = notImplemented()

    e feature Person::name withRepresentation "first name" toBe "robert"
    e feature Person::name toBe "robert"
    e feature Person::bar toBe "robert"
    e feature of(Person::foo, 1) toBe "a"

    // should work with Kotlin 1.4
//    e feature of(Person::baz, 1) toBe 1
    // workaround
    e feature { f("baz", it.baz(1)) } toBe 1

    // will never work
//    e feature Person::baz2 toBe 1
    // workaround
    e feature { f("baz2", it.baz2<Int>()) } toBe 1

    e feature of(Person::name) { o toBe "robert" }
    e feature of(Person::bar) { o toBe "robert" }
    e feature of(Person::foo, 1) { o toBe "a" }


    e feature { f(it::age) } toBe 20
    e feature { f(it::foo, 1) } toBe "hello"
    e feature { f(it::bar) } toBe "hello"
    // should work with Kotlin 1.4
//    e feature { f(it::baz, 1) } toBe 1
    e feature { f("baz", it.baz(1)) } toBe 1


    // should work with Kotlin 1.4
//    e feature of({ f(it::age) }) { o toBe 1 }
//    e feature of({ f(it::bar) }) { o toBe "a" }
//    e feature of({ f(it::foo, 1) }) { o toBe "a" }
//    e feature of({ f(it::baz, "b") }) { o toBe "a" }

    // workaround
    // not entirely the same as we narrow the fluent API to the feature but since we said
    // we drop this requirement it is fine. Moreover, it has the subtle difference, that the sub-assertions cannot be
    // reported in certain circumstances but I would say that's something we have to life with until Kotlin 1.4 is out
    e feature { f("baz", it.baz(1)) } it { o toBe 1 }
}
