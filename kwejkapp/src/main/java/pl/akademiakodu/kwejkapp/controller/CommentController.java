package pl.akademiakodu.kwejkapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.akademiakodu.kwejkapp.model.Comment;
import pl.akademiakodu.kwejkapp.model.Mem;
import pl.akademiakodu.kwejkapp.service.CommentService;
import pl.akademiakodu.kwejkapp.service.MemNotFoundException;
import pl.akademiakodu.kwejkapp.service.MemService;

import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * Created by itml on 25.06.2017.
 */
@RequestMapping("/comment")
@Controller
public class CommentController {

    @Autowired
    private MemService memServiceImpl;

    @Autowired
    private CommentService commentServiceImpl;

    @PostMapping
    public ModelAndView saveComment(@RequestParam("memId") Long memId,
                                    @Valid Comment comment,
                                    BindingResult bindingResult) throws MemNotFoundException {

        Mem mem = memServiceImpl.findById(memId);

        System.out.println(bindingResult.getAllErrors());
        if (bindingResult.hasErrors()) {

            ModelAndView mav = new ModelAndView("mem/details");
            mav.addObject("mem", mem);
            mav.addObject("comment", comment);
            return mav;
        } else {
            comment.setMem(mem);
            commentServiceImpl.save(comment);
            return new ModelAndView("redirect:mem/" + memId);
        }
    }
}
