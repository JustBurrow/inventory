package kr.lul.inventory.web.manager.controller

import kr.lul.inventory.business.borderline.NounBorderline
import kr.lul.inventory.business.borderline.cmd.CreateNounCmd
import kr.lul.inventory.web.manager.controller.request.CreateNounReq
import kr.lul.inventory.web.manager.mapping.NounMvc.C
import kr.lul.inventory.web.manager.mapping.NounMvc.M
import kr.lul.inventory.web.manager.mapping.NounMvc.V
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ModelAttribute
import javax.validation.Valid

@Controller
internal class NounControllerImpl : NounController {
    private val log = LoggerFactory.getLogger(NounControllerImpl::class.java)!!

    @Autowired
    private lateinit var nounBorderline: NounBorderline

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kr.lul.inventory.web.manager.controller.NounController
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun createForm(model: Model): String {
        if (log.isTraceEnabled) log.trace("args : model={}", model)

        model.addAttribute(M.CREATE_NOUN_REQ, CreateNounReq(null, null, null))

        return V.CREATE_FORM
    }

    override fun create(@ModelAttribute(M.CREATE_NOUN_REQ) @Valid req: CreateNounReq, binding: BindingResult,
                        model: Model): String {
        if (log.isTraceEnabled) log.trace("args : req={}, binding={}, model={}", req, binding, model)

        val noun = nounBorderline.create(CreateNounCmd(req.key!!, req.label!!, req.labelCode!!))

        if (log.isTraceEnabled) log.trace("result : noun={}", noun)
        return "redirect:${C.GROUP}"
    }
}
