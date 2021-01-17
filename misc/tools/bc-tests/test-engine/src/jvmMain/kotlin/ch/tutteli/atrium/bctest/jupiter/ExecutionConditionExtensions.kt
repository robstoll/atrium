package ch.tutteli.atrium.bctest.jupiter

import org.junit.jupiter.api.extension.ExtensionContext

val ExtensionContext.path: String
    get() = testClass.map { it.name }.orElse("") + testMethod.map { "#" + it.name }.orElse("")
