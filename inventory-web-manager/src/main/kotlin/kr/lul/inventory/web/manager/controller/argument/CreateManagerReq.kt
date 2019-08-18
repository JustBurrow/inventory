package kr.lul.inventory.web.manager.controller.argument

import kr.lul.inventory.design.domain.Manager.Companion.EMAIL_MAX_LENGTH
import kr.lul.inventory.design.domain.Manager.Companion.NAME_MAX_LENGTH
import kr.lul.inventory.design.domain.Manager.Companion.NAME_MIN_LENGTH
import kr.lul.inventory.design.domain.Manager.Companion.PASSWORD_MIN_LENGTH
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * @field:author justburrow
 * @field:since 2019-07-14
 */
data class CreateManagerReq(
        @field:NotNull
        @field:NotEmpty
        @field:Size(max = EMAIL_MAX_LENGTH)
        @field:Email
        var email: String? = null,

        @field:NotNull
        @field:NotEmpty
        @field:Size(min = NAME_MIN_LENGTH, max = NAME_MAX_LENGTH)
        var name: String? = null,

        @field:NotNull
        @field:NotEmpty
        @field:Size(min = PASSWORD_MIN_LENGTH)
        var secret: String? = null,

        @field:NotNull
        @field:NotEmpty
        @field:Size(min = PASSWORD_MIN_LENGTH)
        var confirm: String? = null
)
