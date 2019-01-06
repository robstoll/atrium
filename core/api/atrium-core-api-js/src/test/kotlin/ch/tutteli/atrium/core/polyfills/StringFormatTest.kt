package ch.tutteli.atrium.core.polyfills

import ch.tutteli.atrium.api.cc.infix.en_GB.toBe
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.translating.Locale
import ch.tutteli.atrium.verbs.internal.assert
import kotlin.test.Test

class StringFormatTest {

    @Test
    fun withAndWithoutLocal_shouldNotMatter() {
        assert("dummy subject, see sub assertions") {
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
                AssertImpl.feature.returnValueOf0(
                    this,
                    { string.format(args.first(), *args.drop(1).toTypedArray()) },
                    "($string / $args)"
                ) toBe expected
                AssertImpl.feature.returnValueOf0(
                    this,
                    { string.format(Locale("de"), args.first(), *args.drop(1).toTypedArray()) },
                    "($string / $args)"
                ) toBe expected
            }
        }
    }
}
