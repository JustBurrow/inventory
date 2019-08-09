package kr.lul.inventory.test

import kr.lul.inventory.data.jpa.entity.ManagerCredentialEntity
import kr.lul.inventory.data.jpa.entity.ManagerEntity
import kr.lul.inventory.data.jpa.repository.ManagerCredentialRepository
import kr.lul.inventory.data.jpa.repository.ManagerRepository
import kr.lul.inventory.design.domain.Manager
import kr.lul.inventory.design.util.TimeProvider
import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.lang3.RandomStringUtils.random
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.Instant
import java.util.concurrent.ThreadLocalRandom.current

/**
 * @author justburrow
 * @since 2019-07-23
 */
class ManagerUtil {
    @Autowired
    private lateinit var managerRepository: ManagerRepository

    @Autowired
    private lateinit var credentialRepository: ManagerCredentialRepository
    @Autowired
    private lateinit var timeProvider: TimeProvider
    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder
    val name: String
        get() {
            var name: String

            do {
                name = "test.name.${random(current().nextInt(1, 10))}"
            } while (!Manager.isValidName(name))

            return name
        }

    val unusedName: String
        get() {
            var name: String

            do {
                name = this.name
            } while (managerRepository.existsByName(name))

            return name
        }

    val email: String
        get() {
            var email: String

            do {
                email = "${RandomStringUtils.randomAlphanumeric(1, 20)}@test.email".toLowerCase()
            } while (!Manager.isValidEmail(email))

            return email
        }

    val unusedEmail: String
        get() {
            var email: String

            do {
                email = this.email
            } while (managerRepository.existsByEmail(email))

            return email
        }

    val password: String = random(current().nextInt(Manager.PASSWORD_MIN_LENGTH, 4 * Manager.PASSWORD_MIN_LENGTH))

    /**
     * 새로 저장할 수 있는 [ManagerEntity].
     */
    fun freshManager(createdAt: Instant = timeProvider.instant) =
            ManagerEntity(this.unusedEmail, this.unusedName, createdAt)

    fun manager(password: String = this.password): ManagerEntity {
        val manager = managerRepository.saveAndFlush(freshManager())
        credentialRepository.saveAndFlush(
                ManagerCredentialEntity(manager, manager.name, passwordEncoder.encode(password), manager.createdAt))
        credentialRepository.saveAndFlush(
                ManagerCredentialEntity(manager, manager.email, passwordEncoder.encode(password), manager.createdAt))

        return manager
    }
}
