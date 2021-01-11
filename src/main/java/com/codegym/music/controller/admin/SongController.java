package com.codegym.music.controller.admin;

import com.codegym.music.model.*;
import com.codegym.music.service.SongService;
import com.codegym.music.storage.StorageException;
import com.codegym.music.storage.StorageService;
import com.codegym.music.validator.CustomFileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin/songs")
public class SongController {

    @Autowired
    SongService songService;

    @GetMapping
    public ModelAndView index() {
        Iterable<Song> songs = songService.findAll();
        ModelAndView modelAndView = new ModelAndView("admin/songs/list");
        modelAndView.addObject("songs", songs);
        return modelAndView;
    }
}
