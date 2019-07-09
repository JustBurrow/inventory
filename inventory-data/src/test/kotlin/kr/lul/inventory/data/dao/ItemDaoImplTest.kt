package kr.lul.inventory.data.dao

import kr.lul.inventory.data.DataModuleTestConfiguration
import kr.lul.inventory.design.domain.Item
import kr.lul.inventory.test.data.ItemDataUtil
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
class ItemDaoImplTest {
    private val log = LoggerFactory.getLogger(ItemDaoImplTest::class.java)!!

    @Autowired
    private lateinit var itemDao: ItemDao
    @Autowired
    private lateinit var itemUtil: ItemDataUtil

    @Before
    fun setUp() {
    }

    @Test
    fun `test create() with random item`() {
        // GIVEN
        val expected = itemUtil.random()
        val id = expected.getId()
        val key = expected.getKey()
        val label = expected.getLabel()
        val labelCode = expected.getLabelCode()
        log.debug("GIVEN - expected={}", expected)

        // WHEN
        val actual = itemDao.create(expected)
        log.debug("WHEN - actual={}", actual)

        // THEN
        assertThat(actual)
                .extracting(Item.ATTR_KEY, Item.ATTR_LABEL, Item.ATTR_LABEL_CODE)
                .containsSequence(key, label, labelCode)
        assertThat(actual.getId())
                .isGreaterThan(0L)
                .isNotEqualTo(id)
    }
}
