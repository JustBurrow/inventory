dependencies {
    implementation(project(":inventory-design"))
    implementation(project(":inventory-data"))

    implementation("org.springframework.boot:spring-boot-starter")

    testImplementation(project(":inventory-test-business"))
}
