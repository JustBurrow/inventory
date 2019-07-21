package kr.lul.inventory.test.data

import kr.lul.inventory.data.jpa.repository.ManagerRepository
import kr.lul.inventory.design.domain.Manager
import kr.lul.inventory.design.domain.Manager.Companion.isValidEmail
import kr.lul.inventory.design.domain.Manager.Companion.isValidName
import kr.lul.inventory.design.domain.Manager.Companion.isValidPassword
import kr.lul.inventory.design.domain.Manager.Companion.validateEmail
import kr.lul.inventory.design.domain.Manager.Companion.validateName
import kr.lul.inventory.design.domain.Manager.Companion.validatePassword
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

/**
 * @author justburrow
 * @since 2019-07-17
 */
@RunWith(SpringRunner::class)
@SpringBootTest(classes = [TestDataModuleTestConfiguration::class])
@Transactional
class ManagerDataUtilTest {
    private val log = LoggerFactory.getLogger(ManagerDataUtilTest::class.java)!!

    @Autowired
    private lateinit var util: ManagerDataUtil
    @Autowired
    private lateinit var repository: ManagerRepository

    private lateinit var before: Instant

    @Before
    fun setUp() {
        before = Instant.now()
    }

    @Test
    fun `test name()`() {
        // WHEN
        val name = util.name()
        log.debug("WHEN - name='$name'")

        // THEN
        assertThat(isValidName(name))
                .isTrue()
        Manager.validateName(name)
    }

    @Test
    fun `test unusedName()`() {
        // WHEN
        val name = util.unusedName()
        log.debug("WHEN - name='$name'")

        // THEN
        assertThat(isValidName(name))
                .isTrue()
        assertThat(repository.existsByName(name))
                .isFalse()
        validateName(name)
    }

    @Test
    fun `test email()`() {
        // WHEN
        val email = util.email()
        log.debug("WHEN - email='$email'")

        // THEN
        assertThat(isValidEmail(email))
                .isTrue()
        validateEmail(email)
    }

    @Test
    fun `test_unusedEmail()`() {
        // WHEN
        val email = util.unusedEmail()
        log.debug("WHEN - email='$email'")

        // THEN
        assertThat(isValidEmail(email))
                .isTrue()
        assertThat(repository.existsByEmail(email))
                .isFalse()
        validateEmail(email)
    }

    @Test
    fun `test password()`() {
        // WHEN
        val password = util.password()
        log.debug("WHEN - password='$password'")

        // THEN
        assertThat(isValidPassword(password))
                .isTrue()
        validatePassword(password)
    }

    @Test
    fun `test manager()`() {
        // WHEN
        val manager = util.manager()
        log.debug("WHEN - manager=$manager")

        // THEN
        assertThat(manager.getId())
                .isEqualTo(0)
        validateName(manager.getName())
        validateEmail(manager.getEmail())
        assertThat(manager.getCreatedAt())
                .isEqualTo(manager.getUpdatedAt())
                .isAfterOrEqualTo(before)
    }

    @Test
    fun `test unusedManager()`() {
        // WHEN
        val manager = util.unusedManager()
        log.debug("WHEN - mamager=$manager")

        // THEN
        assertThat(manager.getId())
                .isEqualTo(0)
        manager.getName().apply {
            assertThat(isValidName(this))
                    .isTrue()
            assertThat(repository.existsByName(this))
                    .isFalse()
            validateName(this)
        }
        manager.getEmail().apply {
            assertThat(isValidEmail(this))
                    .isTrue()
            assertThat(repository.existsByEmail(this))
                    .isFalse()
            validateEmail(this)
        }
    }
}
