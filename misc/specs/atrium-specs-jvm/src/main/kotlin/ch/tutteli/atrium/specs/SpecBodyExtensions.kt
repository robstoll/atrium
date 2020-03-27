// //TODO remove file as soon as all specs have been transformed to spek2
// package ch.tutteli.atrium.specs

// import ch.tutteli.kbox.joinToString
// // import org.jetbrains.spek.api.dsl.SpecBody
// import org.spekframework.spek2.style.specification.Suite
// import org.spekframework.spek2.dsl.ScopeBody


// //TODO #116 remove once there aren't any spek1 specs anymore
// fun ScopeBody.describeFun(
//     describePrefix: String,
//     funNames: Array<out String>,
//     funNamePrefix: String = "`",
//     funNameSuffix: String = "`",
//     body: Suite.() -> Unit
// ) = prefixedDescribe(describePrefix, " fun ", giveWrappedNames(funNames, funNamePrefix, funNameSuffix), body)

// private fun giveWrappedNames(names: Array<out String>, prefix: String, postfix: String): String {
//     return names.joinToString(", ", " and ") { it, sb ->
//         sb.append(prefix).append(it).append(postfix)
//     }
// }

// fun ScopeBody.prefixedDescribe(prefix: String, description: String, body: Suite.() -> Unit) =
//     prefixedDescribe(prefix, "", description, body)

// // fun SpecBody.prefixedDescribe(prefix: String, suffix: String, description: String, body: SpecBody.() -> Unit) =
// //     group("${prefix}describe$suffix $description", body = body)
