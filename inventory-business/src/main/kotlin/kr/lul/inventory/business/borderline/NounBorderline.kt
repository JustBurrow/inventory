package kr.lul.inventory.business.borderline

import kr.lul.inventory.business.borderline.cmd.*
import kr.lul.inventory.dto.*

/**
 * @author justburrow
 * @since 2019-07-09
 */
interface NounBorderline {
    fun create(cmd: CreateCountableNounCmd): CountableNounDetailDto

    fun create(cmd: CreateIdentifiableNounCmd): IdentifiableNounDetailDto

    fun create(cmd: CreateLimitedCountableNounCmd): LimitedCountableNounDetailDto

    fun create(cmd: CreateLimitedIdentifiableNounCmd): LimitedIdentifiableNounDetailDto

    fun <N : NounDetailDto> read(cmd: ReadNounCmd): N?
}
