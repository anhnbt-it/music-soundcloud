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
@RequestMapping("admin/songgs")
public class SongController {

    @Autowired
    SongService songService;

//    @Autowired
//    private StorageService storageService;
//
//    @Autowired
//    private CustomFileValidator customFileValidator;

//    @GetMapping("create")
//    public ModelAndView showCreateForm() {
//        ModelAndView modelAndView = new ModelAndView("admin/songs/create");
//        modelAndView.addObject("song", new Album());
//        return modelAndView;
//    }
//
//    @PostMapping
//    public String saveBlog(@Validated @ModelAttribute("song") Song song, BindingResult result, RedirectAttributes redirect) {
//        MultipartFile file = song.getImageData();
//        String fileName = file.getOriginalFilename();
//        customFileValidator.validate(song, result);
//        if (result.hasErrors()) {
//            return "admin/songs/create";
//        }
//        try {
//            storageService.store(file);
//            song.setImage(fileName);
//        } catch (StorageException e) {
//            song.setImage("150.png");
//        }
//        songService.save(song);
//        redirect.addFlashAttribute("globalMessage", "Successfully created a new song: " + song.getId());
//        return "redirect:/admin/songs/create";
//    }

//    @GetMapping
//    public ModelAndView index(@RequestParam("s") Optional<String> s,
//                              @RequestParam(defaultValue = "0") Integer pageNo,
//                              @RequestParam(defaultValue = "10") Integer pageSize,
//                              @RequestParam(defaultValue = "id") String sortBy) {
        @GetMapping
        public ModelAndView index() {
        Iterable<Song> songs;
//        Page<Song> songs;
//        if (s.isPresent()) {
//            songs = songService.findAllByNameContains(s.get(), pageNo, pageSize, sortBy);
//        } else {
            songs = songService.findAll();
//        }
        ModelAndView modelAndView = new ModelAndView("admin/songs/list");
        modelAndView.addObject("songs", songs);
//        modelAndView.addObject("txtSearch", s);
        return modelAndView;
    }

//    @GetMapping("{id}")
//    public String show(@PathVariable Long id, Model model) {
//        Song song= songService.findById(id).get();
//        model.addAttribute("song", song);
//        return "admin/songs/view";
//    }
}
