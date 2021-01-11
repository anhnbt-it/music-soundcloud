package com.codegym.music.controller.admin;

import com.codegym.music.model.Album;
import com.codegym.music.model.Category;
import com.codegym.music.model.Singer;
import com.codegym.music.model.Song;
import com.codegym.music.service.SongService;
import com.codegym.music.storage.StorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public ModelAndView listSongs(@RequestParam("searchName") Optional<String> name, @PageableDefault(value = 3) Pageable pageable) {
        List songs; // Tạo đối tượng lưu Page singers;
            songs = songService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("admin/songs/list");
        modelAndView.addObject("songs", songs);
        return modelAndView;
    }

    @GetMapping("create")
    public ModelAndView showCreateProduct() {
        ModelAndView modelAndView = new ModelAndView("admin/songs/create");
        modelAndView.addObject("songs", new Song());
        return modelAndView;
    }

    @PostMapping("save")
    public String saveFormCreate(@ModelAttribute("song") Song song, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "admin/songs/create";
        }
        Song save = (Song) songService.save(song);
        redirect.addFlashAttribute("globalMessage", "Created song successfully");
        return "redirect:/admin/songs/create";
    }

    @GetMapping("{id}")
    public String show(@PathVariable Long id, Model model) {
        Song song= (Song) songService.findById(id);
        model.addAttribute("song", song);
        return "admin/songs/view";
    }

    @PostMapping("edit")
    public String saveFormUpdate(@ModelAttribute("song") Song song, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "admin/songs/edit";
        }
        Object save = songService.save(song);
        redirect.addFlashAttribute("globalMessage", "Updated song successfully");
        return "redirect:/admin/songs/edit/" + song.getId();
    }

    @PostMapping("delete")
    public String deleteById(@RequestParam("id") Long id, RedirectAttributes redirect) {
        songService.delete(id);
        redirect.addFlashAttribute("globalMessage", "Successfully deleted a song");
        return "redirect:/admin/songs";
    }

    @GetMapping("edit/{id}")
    public String findById(@PathVariable("id") Long id, Model model, RedirectAttributes redirect) {
        Optional<Song> song = (Optional<Song>) songService.findById(id);
        if (song.isPresent()) {
            model.addAttribute("song", song.get());
            return "admin/songs/edit";
        } else {
            redirect.addFlashAttribute("Song with ID " + id + " not found.");
            return "redirect:/admin/songs";
        }
    }
}
