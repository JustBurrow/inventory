package kr.lul.inventory.web.manager.controller

import kr.lul.inventory.web.manager.controller.request.CreateNounReq
import kr.lul.inventory.web.manager.mapping.NounMvc.C
import kr.lul.inventory.web.manager.mapping.NounMvc.M
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
interface NounController {
    @GetMapping(C.API_CREATE_FORM)
    fun createForm(model: Model): String

    @PostMapping(C.API_CREATE)
    fun create(@ModelAttribute(M.CREATE_NOUN_REQ) @Valid req: CreateNounReq, result: BindingResult,
               model: Model): String
}
