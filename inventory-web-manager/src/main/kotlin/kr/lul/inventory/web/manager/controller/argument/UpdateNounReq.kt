package kr.lul.inventory.web.manager.controller.argument

import kr.lul.inventory.design.domain.Noun
import kr.lul.inventory.design.domain.NounType
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class UpdateNounReq(
        @field:NotNull
        var type: NounType? = null,

        @field:NotNull
        @field:NotEmpty
        @field:Size(max = Noun.LABEL_MAX_LENGTH)
        @field:Pattern(regexp = Noun.LABEL_PATTERN)
        var label: String? = null,

        @field:NotNull
        @field:NotEmpty
        @field:Size(max = Noun.LABEL_CODE_MAX_LENGTH)
        @field:Pattern(regexp = Noun.LABEL_CODE_PATTERN)
        var labelCode: String? = null,

        @field:Min(1)
        val limit: Int? = null,

        @field:NotNull
        @field:NotEmpty
        var description: String? = null
)
