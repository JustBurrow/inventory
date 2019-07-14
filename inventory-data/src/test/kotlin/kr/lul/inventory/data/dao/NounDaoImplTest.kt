package kr.lul.inventory.data.dao

import kr.lul.inventory.data.DataModuleTestConfiguration
import kr.lul.inventory.design.domain.Noun
import kr.lul.inventory.test.data.NounDataUtil
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional

/**
 * @author justburrow
 * @since 2019-07-07
 */
@RunWith(SpringRunner::class)
@SpringBootTest(classes = [DataModuleTestConfiguration::class])
@Transactional
class NounDaoImplTest {
    private val log = LoggerFactory.getLogger(NounDaoImplTest::class.java)!!

    @Autowired
    private lateinit var nounDao: NounDao
    @Autowired
    private lateinit var nounUtil: NounDataUtil

    @Before
    fun setUp() {
    }

    @Test
    fun `test create() with random Noun instance`() {
        // GIVEN
        val expected = nounUtil.random()
        val id = expected.getId()
        val key = expected.getKey()
        val label = expected.getLabel()
        val labelCode = expected.getLabelCode()
        log.debug("GIVEN - expected={}", expected)

        // WHEN
        val actual = nounDao.create(expected)
        log.debug("WHEN - actual={}", actual)

        // THEN
        assertThat(actual)
                .extracting(Noun.ATTR_KEY, Noun.ATTR_LABEL, Noun.ATTR_LABEL_CODE)
                .containsSequence(key, label, labelCode)
        assertThat(actual.getId())
                .isGreaterThan(0L)
                .isNotEqualTo(id)
    }
}
