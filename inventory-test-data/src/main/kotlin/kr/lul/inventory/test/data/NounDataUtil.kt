package kr.lul.inventory.test.data

import kr.lul.inventory.data.jpa.entity.NounEntity
import kr.lul.inventory.design.domain.Noun
import kr.lul.inventory.design.domain.Noun.Companion.isValidKey
import kr.lul.inventory.design.domain.Noun.Companion.isValidLabelCode
import org.apache.commons.lang3.RandomStringUtils.randomAlphabetic
import org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric
import org.slf4j.LoggerFactory

/**
 * @author justburrow
 * @since 2019-07-06
 */
open class NounDataUtil {
    private val log = LoggerFactory.getLogger(NounDataUtil::class.java)!!

    fun key(): String {
        var key: String

        do {
            key = "test.noun.key.${randomAlphabetic(1)}${randomAlphanumeric(10, 20)}".toLowerCase()
        } while (!isValidKey(key))

        return key
    }

    fun label(): String {
        var label: String

        do {
            label = randomAlphanumeric(1, Noun.LABEL_MAX_LENGTH)
        } while (!Noun.isValidLabel(label))

        return label
    }

    fun labelCode(): String {
        var labelCode: String

        do {
            labelCode = "test.noun.label.${randomAlphabetic(1)}${randomAlphanumeric(0, 10)}".toLowerCase()
        } while (!isValidLabelCode(labelCode))

        return labelCode
    }

    /**
     * @return 임의의 인스턴스를 만든다.
     */
    fun random() = NounEntity(key(), label(), labelCode())
}
