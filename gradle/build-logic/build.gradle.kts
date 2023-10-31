plugins {
    `embedded-kotlin` apply false
}

allprojects {
    group = "ch.tutteli.atrium.build-logic"
}


//TODO 1.5.0 check if module info works by now:
//   => since 1.1.0, for some reason compileJava does no longer complain if module-info.java is wrong
//      => unfortunately kotlin sometimes randomly also doesn't complain
//      => not ideal but I think we can neglect that for now. I think not a lot of people are actually using
//         the module-path in tests
