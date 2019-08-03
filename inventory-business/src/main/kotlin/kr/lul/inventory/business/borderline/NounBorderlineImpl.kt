package kr.lul.inventory.business.borderline

import kr.lul.inventory.business.borderline.cmd.CreateNounCmd
import kr.lul.inventory.dto.NounDetailDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
internal class NounBorderlineImpl : NounBorderline {
    override fun create(cmd: CreateNounCmd): NounDetailDto {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
