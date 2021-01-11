package com.codegym.music.controller.admin;

import com.codegym.music.model.*;
import com.codegym.music.service.SongService;
import com.codegym.music.storage.StorageException;
import com.codegym.music.storage.StorageService;
import com.codegym.music.validator.CustomFileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("admin/songs")
public class SongController {

    @Autowired
    SongService songService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private CustomFileValidator customFileValidator;

    @GetMapping("create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("admin/songs/create");
        modelAndView.addObject("song", new Song());
        return modelAndView;
    }

    @PostMapping("save")
    public String save(@Valid @ModelAttribute("song") Song song, BindingResult result, RedirectAttributes redirect) {
        MultipartFile multipartFile = song.getImageData();
        String fileName = multipartFile.getOriginalFilename();
        MultipartFile mp3File = song.getMp3Data();
        String mp3Name = mp3File.getOriginalFilename();
        customFileValidator.validate(song, result);
        if (result.hasErrors()) {
            return "admin/songs/create";
        }
        try {
            storageService.store(multipartFile);
            song.setImage(fileName);
            storageService.store(mp3File);
            song.setUrl(mp3Name);
        } catch (StorageException e) {
            song.setImage("150.png");
            song.setUrl("aaa");
        }
        songService.save(song);
        redirect.addFlashAttribute("globalMessage", "Successfully created a new song: " + song.getId());
        return "redirect:/admin/songs/create";
    }

    @GetMapping
    public ModelAndView index(@RequestParam("s") Optional<String> s,
                              @RequestParam(defaultValue = "0") Integer pageNo,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "id") String sortBy) {
        Page<Song> songs;
        if (s.isPresent()) {
            songs = songService.findAllByNameContains(s.get(), pageNo, pageSize, sortBy);
        } else {
            songs = songService.findAll(pageNo, pageSize, sortBy);
        }
        ModelAndView modelAndView = new ModelAndView("admin/songs/list");
        modelAndView.addObject("songs", songs);
        modelAndView.addObject("txtSearch", s);
        return modelAndView;
    }

    @GetMapping("{id}")
    public String show(@PathVariable Long id, Model model) {
        Song song = songService.findById(id).get();
        model.addAttribute("song", song);
        return "admin/songs/view";
    }

    @GetMapping("edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirect) {
        Optional<Song> song = songService.findById(id);
        if (song.isPresent()) {
            model.addAttribute("song", song.get());
            return "admin/songs/edit";
        } else {
            redirect.addFlashAttribute("message", "Song not found!");
            return "redirect:/admin/songs";
        }
    }

    @PostMapping("edit")
    public String updateBlog(@Validated @ModelAttribute("song") Song song) {
        songService.save(song);

        ModelAndView modelAndView = new ModelAndView("admin/songs/edit");
        modelAndView.addObject("song", song);
        modelAndView.addObject("message", "Song updated sucessfully");
        return "redirect:/admin/songs";
    }

    @PostMapping("delete")
    public String deleteById(@RequestParam("id") Long id, RedirectAttributes redirect) {
        songService.deleteById(id);
        redirect.addFlashAttribute("globalMessage", "Successfully deleted a song");
        return "redirect:/admin/songs";
    }
}
