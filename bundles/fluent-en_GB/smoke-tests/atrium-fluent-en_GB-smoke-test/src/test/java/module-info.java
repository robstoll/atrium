module ch.tutteli.atrium.fluent.en_GB.test {
    requires ch.tutteli.atrium.fluent.en_GB;
    requires kotlin.stdlib;
    // looks like a kotlin bug, as soon as you add that line (a jar with Automatic-module name) it does not check the rest anymore :(
//    requires spek.dsl.jvm;
}
