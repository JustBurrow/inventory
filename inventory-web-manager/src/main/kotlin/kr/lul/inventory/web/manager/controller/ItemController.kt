package kr.lul.inventory.web.manager.controller

import kr.lul.inventory.web.manager.controller.request.CreateItemReq
import kr.lul.inventory.web.manager.mapping.ItemMvc.C
import kr.lul.inventory.web.manager.mapping.ItemMvc.M
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

/**
 * @author justburrow
 * @since 2019-07-10
 */
@RequestMapping(C.GROUP)
interface ItemController {
    @GetMapping(C.API_CREATE_FORM)
    fun createForm(model: Model): String

    @PostMapping(C.API_CREATE)
    fun create(@ModelAttribute(M.CREATE_REQ) @Valid req: CreateItemReq, result: BindingResult, model: Model): String
}
