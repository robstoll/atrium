package ch.tutteli.atrium.spec

import org.jetbrains.spek.api.dsl.SpecBody

fun SpecBody.describeFun(prefix: String, funName: String, body: SpecBody.() -> Unit)
    = prefixedDescribe("${prefix}fun ", funName, body)

fun SpecBody.prefixedDescribe(prefix: String, description: String, body: SpecBody.() -> Unit)
    = group("${prefix}describe $description", body = body)
