package kr.lul.inventory.business.borderline

import kr.lul.inventory.business.BusinessModuleTestConfiguration
import kr.lul.inventory.design.domain.Item
import kr.lul.inventory.test.business.ItemBusinessUtil
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * @author justburrow
 * @since 2019-07-09
 */
@RunWith(SpringRunner::class)
@SpringBootTest(classes = [BusinessModuleTestConfiguration::class])
class ItemBorderlineTest {
    private val log = LoggerFactory.getLogger(ItemBorderlineTest::class.java)!!

    @Autowired
    private lateinit var itemBorderline: ItemBorderline
    @Autowired
    private lateinit var testUtil: ItemBusinessUtil

    @Before
    fun setUp() {
    }

    @Test
    fun `test create(cmd) with random`() {
        // GIVEN
        val cmd = testUtil.createCmd()
        log.debug("GIVEN - cmd={}", cmd)

        // WHEN
        val dto = itemBorderline.create(cmd)
        log.debug("WHEN - dto={}", dto)

        // Then
        assertThat(dto)
                .extracting(Item.ATTR_KEY, Item.ATTR_LABEL, Item.ATTR_LABEL_CODE)
                .containsSequence(cmd.key, cmd.label, cmd.labelCode)
        assertThat(dto.id)
                .isGreaterThan(0L)
    }
}
