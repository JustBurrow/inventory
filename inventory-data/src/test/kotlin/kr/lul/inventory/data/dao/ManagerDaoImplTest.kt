package kr.lul.inventory.data.dao

import kr.lul.inventory.data.DataModuleTestConfiguration
import kr.lul.inventory.design.domain.Manager.Companion.ATTR_CREATED_AT
import kr.lul.inventory.design.domain.Manager.Companion.ATTR_EMAIL
import kr.lul.inventory.design.domain.Manager.Companion.ATTR_NAME
import kr.lul.inventory.design.domain.Manager.Companion.ATTR_UPDATED_AT
import kr.lul.inventory.test.data.ManagerDataUtil
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional

/**
 * @author justburrow
 * @since 2019-07-14
 */
@RunWith(SpringRunner::class)
@SpringBootTest(classes = [DataModuleTestConfiguration::class])
@Transactional
class ManagerDaoImplTest {
    private val log = LoggerFactory.getLogger(ManagerDaoImplTest::class.java)!!

    @Autowired
    private lateinit var managerDao: ManagerDao
    @Autowired
    private lateinit var managerDataUtil: ManagerDataUtil

    @Test
    fun `test create() with random ManagerEntity`() {
        // GIVEN
        val expected = managerDataUtil.unusedManager()
        val id = expected.getId()
        val name = expected.getName()
        val email = expected.getEmail()
        val createdAt = expected.getCreatedAt()
        val updatedAt = expected.getUpdatedAt()
        log.debug("GIVEN - expected={}", expected)

        // WHEN
        val actual = managerDao.create(expected)
        log.debug("WHEN - actual={}", actual)

        // THEN
        assertThat(actual)
                .extracting(ATTR_NAME, ATTR_EMAIL, ATTR_CREATED_AT, ATTR_UPDATED_AT)
                .containsSequence(name, email, createdAt, updatedAt)
        assertThat(actual.getId())
                .isGreaterThan(0)
                .isNotEqualTo(id)
    }
}
