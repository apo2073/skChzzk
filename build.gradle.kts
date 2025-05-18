plugins {
    kotlin("jvm") version "2.1.20"
    id("com.gradleup.shadow") version "9.0.0-beta10"
}

group = "kr.apo2073"
version = "1.2"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/") {
        name = "papermc-repo"
    }
    maven("https://oss.sonatype.org/content/groups/public/") {
        name = "sonatype"
    }
    maven("https://repo.skriptlang.org/releases")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.1-R0.1-SNAPSHOT")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.github.SkriptLang:Skript:2.11.1")
    implementation(files("libs/chzzk4j-0.1.1.jar"))
//    implementation("io.github.R2turnTrue:chzzk4j:0.1.1")
}

val targetJavaVersion = 21
kotlin {
    jvmToolchain(targetJavaVersion)
}

tasks.build {
    dependsOn("shadowJar")
}

tasks.processResources {
    val props = mapOf("version" to version)
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("plugin.yml") {
        expand(props)
    }
}

tasks.shadowJar {
    minimize()
    archiveClassifier.set("all")
    mergeServiceFiles()
    archiveFileName.set("skChzzk.jar")
//    destinationDirectory=file("C:\\Users\\PC\\Desktop\\dsadasd\\plugins")
    dependencies {
        include(dependency(files("libs/chzzk4j-0.1.1.jar")))
//        include(dependency("io.github.R2turnTrue:chzzk4j:0.1.1"))
    }
}
