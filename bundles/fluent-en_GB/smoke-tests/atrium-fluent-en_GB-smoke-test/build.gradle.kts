description = "Represents a JDK >= 9 smoke test for atrium-fluent-en_GB"
//plugins {
//    `java-library`
//}
val bundle = prefixedProject("fluent-en_GB")

dependencies {
    // I don't see how to set up compileTestKotlin with --patch-module, so we have put the module-info.java directly in src/test/kotlin instead
    implementation(bundle)
}

//java {
//    modularity.inferModulePath.set(true)
//}
//tasks.named<JavaCompile>("compileTestJava") {
//    dependsOn(bundle.tasks.named("jar"))
//    doFirst {
//        options.compilerArgs.addAll(
//            listOf(
//////                "--module-path", classpath.asPath
//            "--patch-module", "spek.dsl.jvm=${bundle.buildDir.resolve("libs/atrium-fluent-en_GB-jvm-${rootProject.version}.jar")}"
//            )
//        )
//        classpath = files()
//    }
//}

//val spekVersion: String by rootProject.extra
//
//val integrationTest by sourceSets.creating
//
////configurations[integrationTest.implementationConfigurationName].extendsFrom(configurations.testImplementation.get())
////configurations[integrationTest.runtimeOnlyConfigurationName].extendsFrom(configurations.testRuntimeOnly.get())
//
//val integrationTestJarTask = tasks.register<Jar>(integrationTest.jarTaskName) {
//    archiveClassifier.set("integration-tests")
//    from(integrationTest.output)
//}
//
//val integrationTestTask = tasks.register<Test>("integrationTest") {
//    description = "Runs integration tests."
//    group = "verification"
//    useJUnitPlatform {
//        includeEngines("spek2")
//    }
//
//    testClassesDirs = integrationTest.output.classesDirs
//    // Make sure we run the 'Jar' containing the tests (and not just the 'classes' folder) so that test resources are also part of the test module
//    classpath = configurations[integrationTest.runtimeClasspathConfigurationName] + files(integrationTestJarTask)
//
//    shouldRunAfter(tasks.test)
//}
//
//tasks.check {
//    dependsOn(integrationTestTask)
//}
//
//dependencies {
//    "integrationTestImplementation"(bundle)
//    "integrationTestImplementation"("org.junit.platform:junit-platform-commons:1.9.1")
//
//    "integrationTestImplementation"("org.spekframework.spek2:spek-dsl-jvm:$spekVersion")
//    "integrationTestRuntimeOnly"("org.spekframework.spek2:spek-runner-junit5:$spekVersion")
//
//    "integrationTestImplementation"("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
//    "integrationTestRuntimeOnly"("org.jetbrains.kotlin:kotlin-reflect") //spek requires reflect)
//}
//
//tasks.named<JavaCompile>("compileIntegrationTestJava") {
////    dependsOn(bundle.tasks.named("jar"))
//    doFirst {
//        options.compilerArgs.addAll(
//            listOf(
//                "--module-path", classpath.asPath
////            "--patch-module", "ch.tutteli.atrium.fluent.en_GB=${bundle.buildDir.resolve("libs/atrium-fluent-en_GB-jvm-${rootProject.version}.jar")}"
//            )
//        )
//        classpath = files()
//    }
//}
apply(plugin = "ch.tutteli.gradle.plugins.spek")
