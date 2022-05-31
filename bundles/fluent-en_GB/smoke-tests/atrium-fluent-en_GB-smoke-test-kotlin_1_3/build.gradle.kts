description = "Represents a JDK >= 9 smoke test for atrium-fluent-en_GB with kotlin_1_3 extension"


dependencies {
    // I don't see how to set up compileTestKotlin with --patch-module, so we have put the module-info.java directly in src/test/kotlin instead
    testImplementation(prefixedProject("fluent-en_GB"))
    testImplementation(prefixedProject("api-fluent-en_GB-kotlin_1_3"))
}

java{
    sourceCompatibility = JavaVersion.current()
    targetCompatibility = JavaVersion.current()
}
