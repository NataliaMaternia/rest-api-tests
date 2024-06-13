plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"
val allureVersion = "2.25.0"
val aspectJVersion = "1.9.21"
val agent: Configuration by configurations.creating {
    isCanBeConsumed = true
    isCanBeResolved = true
}
tasks.register("allureReport") {
    doLast {
        val allureResultsDir = "/allure-results"
        val allureReportDir = "/allure-report"
        exec {
            commandLine("allure", "generate", allureResultsDir, "-o", allureReportDir, "--clean")
        }
    }
}
repositories {
    mavenCentral()
}

dependencies {
    testImplementation("io.rest-assured:rest-assured:5.4.0")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.seleniumhq.selenium:selenium-java:4.12.0")
    testImplementation("io.github.bonigarcia:webdrivermanager:5.7.0")
    testImplementation("org.assertj:assertj-core:3.25.1")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.1")
    testImplementation(platform("io.qameta.allure:allure-bom:$allureVersion"))
    testImplementation("io.qameta.allure:allure-junit5")
    agent("org.aspectj:aspectjweaver:${aspectJVersion}")

}

tasks.test {
    useJUnitPlatform()
}