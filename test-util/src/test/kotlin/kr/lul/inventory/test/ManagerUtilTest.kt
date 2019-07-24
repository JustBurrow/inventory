package kr.lul.inventory.test

import kr.lul.inventory.data.jpa.mapping.ManagerMapping.COL_EMAIL
import kr.lul.inventory.data.jpa.mapping.ManagerMapping.COL_NAME
import kr.lul.inventory.data.jpa.mapping.ManagerMapping.TABLE
import kr.lul.inventory.data.jpa.repository.ManagerRepository
import kr.lul.inventory.design.domain.Manager.Companion.ATTR_CREATED_AT
import kr.lul.inventory.design.domain.Manager.Companion.ATTR_EMAIL
import kr.lul.inventory.design.domain.Manager.Companion.ATTR_NAME
import kr.lul.inventory.design.domain.Manager.Companion.ATTR_UPDATED_AT
import kr.lul.inventory.design.domain.Manager.Companion.isValidEmail
import kr.lul.inventory.design.domain.Manager.Companion.isValidName
import kr.lul.inventory.design.domain.Manager.Companion.validateEmail
import kr.lul.inventory.design.domain.Manager.Companion.validateName
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.sql.DataSource

/**
 * @author justburrow
 * @since 2019-07-23
 */
@RunWith(SpringRunner::class)
@SpringBootTest(classes = [TestUtilModuleTestConfiguration::class])
@Transactional
class ManagerUtilTest {
    private val log = LoggerFactory.getLogger(ManagerUtilTest::class.java)!!

    @Autowired
    private lateinit var util: ManagerUtil
    @Autowired
    private lateinit var repository: ManagerRepository
    @Autowired
    private lateinit var dataSource: DataSource
    @Autowired
    private lateinit var entityManager: EntityManager

    private lateinit var jdbcTemplate: JdbcTemplate

    @Before
    fun setUp() {
        jdbcTemplate = JdbcTemplate(dataSource)
    }

    @Test
    fun `test name()`() {
        // WHEN
        val name = util.name()
        log.debug("WHEN - name='$name'")

        // THEN
        assertThat(isValidName(name))
                .isTrue()
        validateName(name)
    }

    @Test
    fun `test unusedName()`() {
        // WHEN
        val name = util.unusedName()
        log.debug("WHEN - name='$name'")

        // THEN
        assertThat(isValidName(name))
                .isTrue()
        validateName(name)

        assertThat(jdbcTemplate.queryForObject("SELECT COUNT(*) FROM $TABLE WHERE $COL_NAME = ?",
                arrayOf(name),
                Int::class.java)
        ).isZero()
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
    fun `test unusedEmail()`() {
        // WHEN
        val email = util.unusedEmail()
        log.debug("WHEN - email='$email'")

        // THEN
        assertThat(isValidEmail(email))
                .isTrue()
        validateEmail(email)

        assertThat(jdbcTemplate.queryForObject("SELECT COUNT(*) FROM $TABLE WHERE $COL_EMAIL = ?",
                arrayOf(email),
                Int::class.java)
        ).isZero()
    }

    @Test
    fun `test freshManager()`() {
        // WHEN
        val manager = util.freshManager()
        val id = manager.id
        log.debug("WHEN - manager=$manager")

        // THEN
        val saved = repository.save(manager)
        entityManager.flush()
        entityManager.clear()
        log.debug("THEN - saved=$saved")

        assertThat(saved)
                .extracting(ATTR_EMAIL, ATTR_NAME, ATTR_CREATED_AT, ATTR_UPDATED_AT, ATTR_UPDATED_AT)
                .containsSequence(manager.email, manager.name, manager.createdAt, manager.updatedAt, manager.createdAt)
        assertThat(saved.id)
                .isNotEqualTo(id)
                .isGreaterThan(0)
    }
}
