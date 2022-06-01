module ch.tutteli.atrium.fluent.en_GB.smoke {
    // test dependencies are usually defined in build.gradle via --patch-module but that's quite cumbersome and I did
    // not get it running in 10 minutes so I am using this, the effect should be the same, the kotlin compiler checks if
    // I am using symbols from packages I do not require etc.

    requires ch.tutteli.atrium.fluent.en_GB;
    requires kotlin.stdlib;
    requires spek.dsl.jvm;
}
