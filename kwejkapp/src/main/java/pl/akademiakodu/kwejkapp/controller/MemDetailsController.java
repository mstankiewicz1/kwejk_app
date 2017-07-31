package pl.akademiakodu.kwejkapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.akademiakodu.kwejkapp.model.Comment;
import pl.akademiakodu.kwejkapp.model.Mem;
import pl.akademiakodu.kwejkapp.service.MemNotFoundException;
import pl.akademiakodu.kwejkapp.service.MemService;

/**
 * Created by itml on 24.06.2017.
 */
@RequestMapping("/mem")
@Controller
public class MemDetailsController {

    @Autowired
    private MemService memServiceImpl;

    @GetMapping("/{id}")
    public ModelAndView getDetails(@PathVariable("id") Long id) {

        if (id == null || id < 0) {
            return redirect();
        } else {
            try {
                Mem mem = memServiceImpl.findById(id);
                ModelAndView mav = new ModelAndView("mem/details");
                mav.addObject("mem", mem);
                mav.addObject("comment", new Comment());
                return mav;
            } catch (MemNotFoundException e) {
                return redirect();
            }
        }
    }

    @GetMapping("random")
    public ModelAndView findRandom() throws MemNotFoundException {
        try {
            Mem mem = memServiceImpl.findRandom();
            ModelAndView mav = new ModelAndView("mem/details");
            mav.addObject("mem", mem);
            mav.addObject("comment", new Comment());
            return mav;
        } catch (MemNotFoundException e) {
            return redirect();
        }
    }

    private ModelAndView redirect() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/");
        return mav;
    }
}
