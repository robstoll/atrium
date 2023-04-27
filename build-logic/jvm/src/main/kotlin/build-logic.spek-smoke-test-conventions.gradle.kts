plugins {
    id("ch.tutteli.gradle.plugins.spek")
}

tasks.named<JavaCompile>("compileTestJava") {
    doFirst {
        options.compilerArgs.addAll(listOf("--module-path", classpath.asPath))
        classpath = files()
    }
}
