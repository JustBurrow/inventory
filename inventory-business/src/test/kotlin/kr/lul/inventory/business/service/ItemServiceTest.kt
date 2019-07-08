package kr.lul.inventory.business.service

import kr.lul.inventory.business.BusinessModuleTestConfiguration
import kr.lul.inventory.design.domain.Item
import kr.lul.inventory.test.business.ItemBusinessUtil
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
 * @since 2019-07-08
 */
@RunWith(SpringRunner::class)
@SpringBootTest(classes = [BusinessModuleTestConfiguration::class])
@Transactional
//@Rollback(false)
class ItemServiceTest {
    private val log = LoggerFactory.getLogger(ItemServiceTest::class.java)

    @Autowired
    private lateinit var itemService: ItemService
    @Autowired
    private lateinit var testUtil: ItemBusinessUtil

    @Test
    fun `test create item with random params`() {
        // GIVEN
        val params = testUtil.createParams()
        log.debug("GIVEN - params={}", params)

        // WHEN
        val item = itemService.create(params)
        log.debug("WHEN - item={}", item)

        // THEN
        assertThat(item)
                .extracting(Item.ATTR_KEY, Item.ATTR_LABEL, Item.ATTR_LABEL_CODE)
                .containsSequence(params.key, params.label, params.labelCode)
        assertThat(item.getId())
                .isGreaterThan(0L)
    }
}
