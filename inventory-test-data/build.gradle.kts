dependencies {
    implementation(project(":inventory-design"))
    implementation(project(":inventory-data"))

    api("org.springframework.boot:spring-boot-starter")
    api("org.springframework.security:spring-security-core")
    implementation("org.apache.commons:commons-lang3")
}
