package kr.lul.inventory.web.manager.controller

import kr.lul.inventory.web.manager.mapping.IndexMvc.C
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
    fun index(model: Model): String

    @PostMapping(C.API_SIGN_UP)
    fun signup(): String
}
