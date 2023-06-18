plugins {
    `embedded-kotlin` apply false
}

allprojects {
    group = "ch.tutteli.atrium.build-logic"
    version = "1.0.0"
}

//open TODOs:
//- testing samples still works?
//- stdlib jdk8 added by default?
//- module info still works? => not yet, plugin was not applied
//- dokka still works?
//- publishing still works?
