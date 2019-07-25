import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies {
    implementation(project(":inventory-design"))
    implementation(project(":inventory-data"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.apache.commons:commons-lang3")
}

tasks.withType<Jar> {
    enabled = true
}

tasks.withType<BootJar> {
    enabled = false
}
