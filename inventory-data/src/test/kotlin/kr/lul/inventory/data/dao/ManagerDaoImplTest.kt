package kr.lul.inventory.data.dao

import kr.lul.inventory.data.DataModuleTestConfiguration
import kr.lul.inventory.data.Util
import kr.lul.inventory.design.domain.Manager.Companion.ATTR_CREATED_AT
import kr.lul.inventory.design.domain.Manager.Companion.ATTR_EMAIL
import kr.lul.inventory.design.domain.Manager.Companion.ATTR_NAME
import kr.lul.inventory.design.domain.Manager.Companion.ATTR_UPDATED_AT
import kr.lul.inventory.design.util.TimeProvider
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
 * @since 2019-07-14
 */
@RunWith(SpringRunner::class)
@SpringBootTest(classes = [DataModuleTestConfiguration::class])
@Transactional
class ManagerDaoImplTest {
    private val log = LoggerFactory.getLogger(ManagerDaoImplTest::class.java)!!

    @Autowired
    private lateinit var dao: ManagerDao

    @Autowired
    private lateinit var timeProvider: TimeProvider
    @Autowired
    private lateinit var util: Util

    private lateinit var before: Instant

    @Before
    fun setUp() {
        before = timeProvider.instant
    }

    @Test
    fun `test create() with random ManagerEntity`() {
        // GIVEN
        val expected = util.freshManager()
        val id = expected.id
        val name = expected.name
        val email = expected.email
        val createdAt = expected.createdAt
        val updatedAt = expected.updatedAt
        log.debug("GIVEN - expected=$expected")

        // WHEN
        val actual = dao.create(expected)
        log.debug("WHEN - actual=$actual")

        // THEN
        assertThat(actual)
                .extracting(ATTR_NAME, ATTR_EMAIL, ATTR_CREATED_AT, ATTR_UPDATED_AT)
                .containsSequence(name, email, createdAt, updatedAt)
        assertThat(actual.id)
                .isNotEqualTo(id)
                .isPositive()
                .isGreaterThan(0)
    }
}
