import ch.tutteli.atrium.api.fluent.en_GB.kotlin_1_3.toThrow
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.RootExpect
import kotlinx.coroutines.runBlocking


fun <R, T : suspend () -> R> expect(subject: T): RootExpect<T> = expect(subject)
fun <T> expectBlocking(act: suspend () -> T): Expect<() -> T> = expect { runBlocking { act() } }

suspend fun foo() {
    throw IllegalStateException("asdf")
}

fun bar() {
    throw IllegalStateException("asdf")
}

fun main() {
    // normal lambda
    expect { bar() }.toThrow<IllegalArgumentException>()

    expect { foo() }.toThrow<IllegalArgumentException>() // does not compile as foo cannot be called within a normal lambda

    expect(suspend { foo() }).toThrow<IllegalArgumentException>()  // works
    expectBlocking { foo() }.toThrow<IllegalArgumentException>()   // works

}
