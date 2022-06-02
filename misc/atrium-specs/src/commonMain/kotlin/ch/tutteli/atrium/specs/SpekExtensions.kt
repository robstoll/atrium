package ch.tutteli.atrium.specs

import ch.tutteli.kbox.joinToString
import org.spekframework.spek2.dsl.GroupBody
import org.spekframework.spek2.style.specification.Suite
import org.spekframework.spek2.style.specification.describe

fun GroupBody.describeFunTemplate(
    describePrefix: String,
    funNames: Array<out String>,
    funNamePrefix: String = "`",
    funNameSuffix: String = "`",
    body: Suite.() -> Unit
): Unit = prefixedDescribeTemplate(
    describePrefix, " fun ",
    giveWrappedNames(funNames, funNamePrefix, funNameSuffix), body
)

private fun giveWrappedNames(names: Array<out String>, prefix: String, postfix: String): String {
    return names.joinToString(", ", " and ") { it, sb ->
        sb.append(prefix).append(it).append(postfix)
    }
}

fun GroupBody.prefixedDescribeTemplate(prefix: String, description: String, body: Suite.() -> Unit): Unit =
    prefixedDescribeTemplate(prefix, "", description, body)

fun GroupBody.prefixedDescribeTemplate(
    prefix: String,
    suffix: String,
    description: String,
    body: Suite.() -> Unit
): Unit = describe("${prefix}describe$suffix $description", body = body)



fun <T> Suite.checkGenericNarrowingAssertion(
    description: String, act: (T.() -> Unit) -> Unit, vararg methods: Pair<String, (T.() -> Unit)>
) {
    context(description) {
        mapOf(*methods).forEach { (checkMethod, assertion) ->
            it("in case of $checkMethod evaluation") {
                act(assertion)
            }
        }
    }
}
