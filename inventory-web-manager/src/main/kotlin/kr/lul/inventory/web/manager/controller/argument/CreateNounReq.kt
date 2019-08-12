package kr.lul.inventory.web.manager.controller.argument

import kr.lul.inventory.design.domain.Noun.Companion.KEY_MAX_LENGTH
import kr.lul.inventory.design.domain.Noun.Companion.KEY_MIN_LENGTH
import kr.lul.inventory.design.domain.Noun.Companion.KEY_PATTERN
import kr.lul.inventory.design.domain.Noun.Companion.LABEL_CODE_MAX_LENGTH
import kr.lul.inventory.design.domain.Noun.Companion.LABEL_CODE_MIN_LENGTH
import kr.lul.inventory.design.domain.Noun.Companion.LABEL_CODE_PATTERN
import kr.lul.inventory.design.domain.Noun.Companion.LABEL_MAX_LENGTH
import kr.lul.inventory.design.domain.Noun.Companion.LABEL_MIN_LENGTH
import kr.lul.inventory.design.domain.Noun.Companion.LABEL_PATTERN
import kr.lul.inventory.design.domain.NounType
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class CreateNounReq(
        @NotEmpty
        var type: NounType? = null,
        @NotEmpty
        @Size(min = KEY_MIN_LENGTH, max = KEY_MAX_LENGTH)
        @Pattern(regexp = KEY_PATTERN)
        var key: String? = null,
        @NotEmpty
        @Size(min = LABEL_MIN_LENGTH, max = LABEL_MAX_LENGTH)
        @Pattern(regexp = LABEL_PATTERN)
        var label: String? = null,
        @NotEmpty
        @Size(min = LABEL_CODE_MIN_LENGTH, max = LABEL_CODE_MAX_LENGTH)
        @Pattern(regexp = LABEL_CODE_PATTERN)
        var labelCode: String? = null,
        @Min(1)
        var limit: Int? = null
)
