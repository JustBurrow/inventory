apply(plugin = "org.jetbrains.kotlin.plugin.noarg")
apply(plugin = "org.jetbrains.kotlin.plugin.jpa")

dependencies {
    implementation(project(":inventory-design"))

    implementation("org.springframework.boot:spring-boot-starter-logging")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("mysql:mysql-connector-java")

    testImplementation("org.springframework.security:spring-security-core")
    testImplementation("com.h2database:h2")

    testImplementation("org.apache.commons:commons-lang3")
}
