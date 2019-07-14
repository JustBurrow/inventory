package kr.lul.inventory.test.business

import kr.lul.inventory.business.borderline.cmd.CreateNounCmd
import kr.lul.inventory.business.service.params.CreateNounParams
import kr.lul.inventory.test.data.NounDataUtil
import org.slf4j.LoggerFactory

/**
 * @author justburrow
 * @since 2019-07-08
 */
class NounBusinessUtil : NounDataUtil() {
    private val log = LoggerFactory.getLogger(NounBusinessUtil::class.java)!!

    fun createParams() = CreateNounParams(key(), label(), labelCode())

    fun createCmd() = CreateNounCmd(key(), label(), labelCode())
}
