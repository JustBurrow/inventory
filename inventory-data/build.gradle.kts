apply(plugin = "org.jetbrains.kotlin.plugin.noarg")
apply(plugin = "org.jetbrains.kotlin.plugin.jpa")

dependencies {
    implementation(project(":inventory-design"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("mysql:mysql-connector-java")

    testImplementation(project(":inventory-test-data"))

    testImplementation("com.h2database:h2")
}
