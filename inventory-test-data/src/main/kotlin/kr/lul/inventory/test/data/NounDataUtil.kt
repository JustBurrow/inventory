package kr.lul.inventory.test.data

import kr.lul.inventory.design.domain.Noun.Companion.isValidKey
import kr.lul.inventory.design.domain.Noun.Companion.isValidLabel
import kr.lul.inventory.design.domain.Noun.Companion.isValidLabelCode
import org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric
import org.slf4j.LoggerFactory

/**
 * @author justburrow
 * @since 2019-07-18
 */
class NounDataUtil {
    private val log = LoggerFactory.getLogger(NounDataUtil::class.java)!!

    fun key(): String {
        var key: String
        do {
            key = "test.noun.key.k${randomAlphanumeric(0, 10)}".toLowerCase()
        } while (!isValidKey(key))

        return key
    }

    fun label(): String {
        var label: String

        do {
            label = "test noun label : ${randomAlphanumeric(1, 10)}"
        } while (!isValidLabel(label))

        return label
    }

    fun labelCode(): String {
        var labelCode: String

        do {
            labelCode = "test.noun.label.code.${randomAlphanumeric(1, 10)}".toLowerCase()
        } while (!isValidLabelCode(labelCode))

        return labelCode
    }
}
