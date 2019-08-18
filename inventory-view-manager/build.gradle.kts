import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies {
    runtimeOnly(project(":inventory-design"))
    runtimeOnly(project(":inventory-dto"))
    runtimeOnly(project(":inventory-web-manager"))
}

tasks.withType<Jar> {
    enabled = true
}

tasks.withType<BootJar> {
    enabled = true
    launchScript()
}
