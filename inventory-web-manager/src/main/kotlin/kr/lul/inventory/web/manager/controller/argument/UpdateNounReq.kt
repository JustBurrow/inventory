package kr.lul.inventory.web.manager.controller.argument

import kr.lul.inventory.design.domain.Noun
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class UpdateNounReq(
        @NotEmpty
        @Size(min = Noun.LABEL_MIN_LENGTH, max = Noun.LABEL_MAX_LENGTH)
        @Pattern(regexp = Noun.LABEL_PATTERN)
        val label: String? = null,

        @NotEmpty
        @Size(min = Noun.LABEL_CODE_MIN_LENGTH, max = Noun.LABEL_CODE_MAX_LENGTH)
        @Pattern(regexp = Noun.LABEL_CODE_PATTERN)
        val labelCode: String? = null,

        @Min(1)
        val limit: Int? = null,

        @NotNull
        val description: String? = null
)
