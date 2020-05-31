@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
package ch.tutteli.atrium.spec

import ch.tutteli.kbox.joinToString
import org.jetbrains.spek.api.dsl.SpecBody

@Deprecated(
    "Switch to atrium-specs and spek2; will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.specs.describeFunTemplate(describePrefix, funNames, funNamePrefix, funNameSuffix, body)")
)
fun SpecBody.describeFun(
    describePrefix: String,
    funNames: Array<out String>,
    funNamePrefix: String = "`",
    funNameSuffix: String = "`",
    body: SpecBody.() -> Unit
) = prefixedDescribe(describePrefix, " fun ", giveWrappedNames(funNames, funNamePrefix, funNameSuffix), body)

@Deprecated("Switch to atrium-specs and spek2; will be removed with 1.0.0")
fun SpecBody.describeProperty(
    describePrefix: String,
    propertyNames: Array<out String>,
    propertyNamePrefix: String = "`",
    propertyNameSuffix: String = "`",
    body: SpecBody.() -> Unit
) = prefixedDescribe(
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

@Deprecated(
    "Switch to atrium-specs and spek2; will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.specs.prefixedDescribeTemplate(prefix, description, body)")
)
fun SpecBody.prefixedDescribe(prefix: String, description: String, body: SpecBody.() -> Unit) =
    prefixedDescribe(prefix, "", description, body)

@Deprecated(
    "Switch to atrium-specs and spek2; will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.specs.prefixedDescribeTemplate(prefix, suffix + description, body)")
)
fun SpecBody.prefixedDescribe(prefix: String, suffix: String, description: String, body: SpecBody.() -> Unit) =
    group("${prefix}describe$suffix $description", body = body)
