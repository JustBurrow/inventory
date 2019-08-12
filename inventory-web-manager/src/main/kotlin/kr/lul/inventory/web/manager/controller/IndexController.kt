package kr.lul.inventory.web.manager.controller

import kr.lul.inventory.web.manager.controller.argument.CurrentManager
import kr.lul.inventory.web.manager.mapping.IndexMvc.C
import kr.lul.inventory.web.manager.mapping.ManagerMvc
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

/**
 * @author justburrow
 * @since 2019-07-14
 */
@RequestMapping(C.GROUP)
interface IndexController {
    @GetMapping(C.API_INDEX)
    fun index(manager: CurrentManager?, model: Model): String

    @GetMapping(C.API_SIGN_UP)
    fun signupForm(): String = "forward:${ManagerMvc.C.FULL_API_CREATE_FORM}"

    @PostMapping(C.API_SIGN_UP)
    fun signup(): String = "forward:${ManagerMvc.C.FULL_API_CREATE}"
}
