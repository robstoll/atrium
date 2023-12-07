package ch.tutteli.atrium.core.polyfills

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.reporting.translating.Locale
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.api.verbs.internal.expectGrouped
import kotlin.test.Test

class StringFormatTest {

    @Test
    fun withAndWithoutLocal_shouldNotMatter() {
        expectGrouped {
            val oneArg = listOf("robert")
            val twoArgs = listOf("robert", "stoll")
            val threeArgs = listOf("robert", "stoll", "the Atrium")
            listOf(
                Triple("hello %s", oneArg, "hello robert"),
                Triple("hello %s %s", oneArg, "hello robert %s"),
                Triple("Welcome %s %s here at %s", oneArg, "Welcome robert %s here at %s"),
                Triple("hello %s", twoArgs, "hello robert"),
                Triple("hello %s %s", twoArgs, "hello robert stoll"),
                Triple("Welcome %s %s here at %s", twoArgs, "Welcome robert stoll here at %s"),
                Triple("hello %s", threeArgs, "hello robert"),
                Triple("hello %s %s", threeArgs, "hello robert stoll"),
                Triple("Welcome %s %s here at %s", threeArgs, "Welcome robert stoll here at the Atrium")
            ).forEach { (string, args, expected) ->
                group("$string / $args") {
                    expect(string.format(args.first(), *args.drop(1).toTypedArray())) toEqual expected
                }
                group("Using Locale de: $string / $args") {
                    expect(string.format(Locale("de"), args.first(), *args.drop(1).toTypedArray())) toEqual expected
                }
            }
        }
    }
}
