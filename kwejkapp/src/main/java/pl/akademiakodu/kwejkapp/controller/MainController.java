package pl.akademiakodu.kwejkapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.akademiakodu.kwejkapp.service.MemService;

/**
 * Created by itml on 24.06.2017.
 */
@RequestMapping("/")
@Controller
public class MainController {

    @Autowired
    private MemService memServiceImpl;

    @GetMapping
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("mem/list");
        mav.addObject("list", memServiceImpl.getList());
        return mav;
    }
}
