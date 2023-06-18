import com.github.vlsi.gradle.dsl.configureEach

plugins {
    id("java")
    id("com.github.vlsi.crlf")
    //TODO 1.1.0 enable again once we have a way to disable per test report
//    id("com.github.vlsi.gradle-extensions")
    id("build-logic.build-params")
}

tasks.register<DependencyInsightReportTask>("allDependencyInsight") {
    group = HelpTasksPlugin.HELP_GROUP
    description =
        "Shows insights where the dependency is used. For instance: allDependencyInsight --configuration compile --dependency org.jsoup:jsoup"
}

java {
    toolchain {
        // reading JAVA_VERSION from env to enable jdk17 build in CI
        languageVersion.set(JavaLanguageVersion.of(buildParameters.java.version))
    }
    consistentResolution {
        useCompileClasspathVersions()
    }
}


tasks.withType<JavaCompile>().configureEach {
    inputs.property("java.version", System.getProperty("java.version"))
    inputs.property("java.vm.version", System.getProperty("java.vm.version"))
    sourceCompatibility = buildParameters.defaultJdkVersion.toString()
    targetCompatibility = buildParameters.defaultJdkVersion.toString()
    options.apply {
        encoding = "UTF-8"
        compilerArgs.add("-Xlint:deprecation")
        if (buildParameters.java.werror) {
            //TODO 1.2.0 enable again once we drop the kotlin_1_3 modules (end with a digit and that generates a warning
//            compilerArgs.add("-Werror")
        }
    }
}
