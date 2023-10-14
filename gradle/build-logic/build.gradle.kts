plugins {
    `embedded-kotlin` apply false
}

allprojects {
    group = "ch.tutteli.atrium.build-logic"
}


//TODO 1.1.0 check the following still works as expected:
//- module info still works? => not yet, for some reason compileJava does no longer complain if module-info.java is wrong
//- publishing still works? => not yet, looks like tutteli-publish plugin has a gradle 8.x issue
//    // publishJsToMavenLocal does not explicitly depend on sign but it needs to
