package ch.tutteli.atrium.core.polyfills

import kotlin.reflect.KClass

expect val KClass<*>.fullName: String?
