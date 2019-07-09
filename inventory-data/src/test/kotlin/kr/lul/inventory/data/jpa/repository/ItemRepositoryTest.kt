package kr.lul.inventory.data.jpa.repository

import kr.lul.inventory.data.DataModuleTestConfiguration
import kr.lul.inventory.design.domain.Item
import kr.lul.inventory.test.data.ItemDataUtil
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner

/**
 * @author justburrow
 * @since 2019-07-06
 */
@RunWith(SpringRunner::class)
@DataJpaTest
@ContextConfiguration(classes = [DataModuleTestConfiguration::class])
class ItemRepositoryTest {
    private val log = LoggerFactory.getLogger(ItemRepositoryTest::class.java)!!

    @Autowired
    private lateinit var itemRepository: ItemRepository

    @Autowired
    private lateinit var itemUtil: ItemDataUtil

    @Before
    fun setUp() {
        assertThat(itemRepository).isNotNull
        assertThat(itemUtil).isNotNull()
    }

    @Test
    fun `test findAll()`() {
        assertThat(itemRepository.findAll())
                .isNotNull()
                .isEmpty()
    }

    @Test
    fun `test save() with random item`() {
        // GIVEN
        val expected = itemUtil.random()
        val id = expected.getId()
        val key = expected.getKey()
        val label = expected.getLabel()
        val labelCode = expected.getLabelCode()
        log.debug("GIVEN - item={}", expected)

        // WHEN
        val actual = itemRepository.save(expected)
        log.debug("WHEN - actual={}", actual)

        // THEN
        assertThat(actual)
                .isNotNull
                .extracting(Item.ATTR_KEY, Item.ATTR_LABEL, Item.ATTR_LABEL_CODE)
                .containsSequence(key, label, labelCode)
        assertThat(actual.getId())
                .isGreaterThan(0L)
                .isNotEqualTo(id)
    }
}
