package kr.lul.inventory.web.manager.controller.argument

import kr.lul.inventory.design.domain.Manager.Companion.EMAIL_MAX_LENGTH
import kr.lul.inventory.design.domain.Manager.Companion.NAME_MAX_LENGTH
import kr.lul.inventory.design.domain.Manager.Companion.NAME_MIN_LENGTH
import kr.lul.inventory.design.domain.Manager.Companion.PASSWORD_MIN_LENGTH
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

/**
 * @author justburrow
 * @since 2019-07-14
 */
data class CreateManagerReq(
        @NotEmpty
        @Size(max = EMAIL_MAX_LENGTH)
        @Email
        var email: String? = null,
        @NotEmpty
        @Size(min = NAME_MIN_LENGTH, max = NAME_MAX_LENGTH)
        var name: String? = null,
        @NotEmpty
        @Size(min = PASSWORD_MIN_LENGTH)
        var secret: String? = null,
        @NotEmpty
        @Size(min = PASSWORD_MIN_LENGTH)
        var confirm: String? = null
)
