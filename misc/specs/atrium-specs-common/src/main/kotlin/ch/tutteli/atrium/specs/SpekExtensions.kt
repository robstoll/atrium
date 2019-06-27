package ch.tutteli.atrium.specs

import ch.tutteli.kbox.joinToString
import org.spekframework.spek2.Spek
import org.spekframework.spek2.dsl.GroupBody
import org.spekframework.spek2.dsl.Root
import org.spekframework.spek2.style.specification.Suite
import org.spekframework.spek2.style.specification.describe

fun GroupBody.describeFunTemplate(
    describePrefix: String,
    funNames: Array<out String>,
    funNamePrefix: String = "`",
    funNameSuffix: String = "`",
    body: Suite.() -> Unit
) = prefixedDescribeTemplate(describePrefix, " fun ",
    giveWrappedNames(funNames, funNamePrefix, funNameSuffix), body)

fun GroupBody.describePropertyTemplate(
    describePrefix: String,
    propertyNames: Array<out String>,
    propertyNamePrefix: String = "`",
    propertyNameSuffix: String = "`",
    body: Suite.() -> Unit
) = prefixedDescribeTemplate(
    describePrefix,
    " property ",
    giveWrappedNames(propertyNames, propertyNamePrefix, propertyNameSuffix),
    body
)

private fun giveWrappedNames(names: Array<out String>, prefix: String, postfix: String): String {
    return names.joinToString(", ", " and ") { it, sb ->
        sb.append(prefix).append(it).append(postfix)
    }
}

fun GroupBody.prefixedDescribeTemplate(prefix: String, description: String, body: Suite.() -> Unit) =
    prefixedDescribeTemplate(prefix, "", description, body)

fun GroupBody.prefixedDescribeTemplate(prefix: String, suffix: String, description: String, body: Suite.() -> Unit) =
    describe("${prefix}describe$suffix $description", body = body)

fun Root.include(spek: Spek) = spek.root(this)
