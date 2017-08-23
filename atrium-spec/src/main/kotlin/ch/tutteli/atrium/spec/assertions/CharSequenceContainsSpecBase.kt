package ch.tutteli.atrium.spec.assertions

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.Spec

abstract class CharSequenceContainsSpecBase(spec: Spec.() -> Unit) : Spek(spec) {

    companion object {
        val text = "Hello my name is Robert"
        val helloWorld = "Hello World, I am Oskar"

        val illegalArgumentException = IllegalArgumentException::class.simpleName
        val separator = System.getProperty("line.separator")!!
    }
}
