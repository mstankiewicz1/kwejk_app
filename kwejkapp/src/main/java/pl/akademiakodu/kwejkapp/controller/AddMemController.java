package pl.akademiakodu.kwejkapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import pl.akademiakodu.kwejkapp.model.Mem;
import pl.akademiakodu.kwejkapp.service.FileService;
import pl.akademiakodu.kwejkapp.service.FileServiceException;
import pl.akademiakodu.kwejkapp.service.MemService;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by itml on 24.06.2017.
 */
@RequestMapping("/add")
@Controller
public class AddMemController {

    private static Logger LOG = LoggerFactory.getLogger(AddMemController.class);

    @Autowired
    private FileService fileService;

    @Autowired
    private MemService memService;

    @GetMapping
    public ModelAndView add() {
        return getFormMAV(new Mem());
    }

    @PostMapping
    public ModelAndView add(@RequestParam("file") MultipartFile file, @Valid Mem mem,
                            BindingResult bindingResult) {
        LOG.info("File received {}", file);
        LOG.info("Mem received {}", mem);

        if (bindingResult.hasErrors()) {
            if (file.isEmpty()) {
                bindingResult.addError(new FieldError("mem",
                        "imagePath", "Plik nie może być pusty"));
            }
            return getFormMAV(mem);
        } else {
            try {
                String uploadedFile = fileService.store(file);
                LOG.info("File stored {}", uploadedFile);
                mem.setImagePath(uploadedFile);
                memService.save(mem);

                return new ModelAndView("redirect:/");
            } catch (IOException | FileServiceException e) {
                e.printStackTrace();
                LOG.error("Error during file store", e);

                bindingResult.addError(new FieldError("mem",
                        "imagePath",
                        "Problem z wysyłką pliku"));

                return getFormMAV(mem);
            }
        }
    }

    private ModelAndView getFormMAV(@Valid Mem mem) {
        ModelAndView mav = new ModelAndView("mem/add");
        mav.addObject("mem", mem);
        return mav;
    }
}
