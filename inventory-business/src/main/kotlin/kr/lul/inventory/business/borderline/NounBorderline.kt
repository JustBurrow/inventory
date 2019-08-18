package kr.lul.inventory.business.borderline

import kr.lul.inventory.business.borderline.cmd.CreateCountableNounCmd
import kr.lul.inventory.business.borderline.cmd.CreateIdentifiableNounCmd
import kr.lul.inventory.business.borderline.cmd.CreateLimitedCountableNounCmd
import kr.lul.inventory.business.borderline.cmd.CreateLimitedIdentifiableNounCmd
import kr.lul.inventory.business.borderline.cmd.ReadNounCmd
import kr.lul.inventory.business.borderline.cmd.SearchNounCmd
import kr.lul.inventory.business.borderline.cmd.UpdateCountableNounCmd
import kr.lul.inventory.business.borderline.cmd.UpdateIdentifiableNounCmd
import kr.lul.inventory.business.borderline.cmd.UpdateLimitedCountableNounCmd
import kr.lul.inventory.business.borderline.cmd.UpdateLimitedIdentifiableNounCmd
import kr.lul.inventory.business.service.NotOwnerException
import kr.lul.inventory.design.domain.Noun
import kr.lul.inventory.dto.CountableNounDetailDto
import kr.lul.inventory.dto.IdentifiableNounDetailDto
import kr.lul.inventory.dto.LimitedCountableNounDetailDto
import kr.lul.inventory.dto.LimitedIdentifiableNounDetailDto
import kr.lul.inventory.dto.NounDetailDto
import kr.lul.inventory.dto.NounSimpleDto
import org.springframework.data.domain.Page

/**
 * @author justburrow
 * @since 2019-07-09
 */
interface NounBorderline {
    fun create(cmd: CreateCountableNounCmd): CountableNounDetailDto

    fun create(cmd: CreateIdentifiableNounCmd): IdentifiableNounDetailDto

    fun create(cmd: CreateLimitedCountableNounCmd): LimitedCountableNounDetailDto

    fun create(cmd: CreateLimitedIdentifiableNounCmd): LimitedIdentifiableNounDetailDto

    /**
     * @throws NotOwnerException 대상 [Noun]의 소유자가 아닐 때.
     */
    @Throws(NotOwnerException::class)
    fun <N : NounDetailDto> read(cmd: ReadNounCmd): N?

    /**
     * 소유권을 가진 [Noun] 목록 조회하기.
     */
    fun search(cmd: SearchNounCmd): Page<NounSimpleDto>

    @Throws(NotOwnerException::class)
    fun update(cmd: UpdateIdentifiableNounCmd): IdentifiableNounDetailDto

    @Throws(NotOwnerException::class)
    fun update(cmd: UpdateCountableNounCmd): CountableNounDetailDto

    @Throws(NotOwnerException::class)
    fun update(cmd: UpdateLimitedIdentifiableNounCmd): LimitedIdentifiableNounDetailDto

    @Throws(NotOwnerException::class)
    fun update(cmd: UpdateLimitedCountableNounCmd): LimitedCountableNounDetailDto
}
