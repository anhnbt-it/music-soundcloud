package com.codegym.music.controller.admin;


import com.codegym.music.model.Album;
import com.codegym.music.service.AlbumService;
import com.codegym.music.storage.StorageException;
import com.codegym.music.storage.StorageService;
import com.codegym.music.validator.CustomFileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("admin/albums")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private StorageService storageService;

    @Autowired
    CustomFileValidator customFileValidator;

    @GetMapping("create")
    public ModelAndView showCreateProduct() {
        ModelAndView modelAndView = new ModelAndView("admin/album/create");
        modelAndView.addObject("album", new Album());
        return modelAndView;
    }

    @PostMapping("save")
    public String save(@ModelAttribute("album") Album album, BindingResult result, RedirectAttributes redirect) {
        MultipartFile multipartFile = album.getImageData();
        String fileName = multipartFile.getOriginalFilename();
        customFileValidator.validate(album, result);
        if (result.hasErrors()) {
            return "admin/album/create";
        }
        try {
            storageService.store(multipartFile);
            album.setImageURL(fileName);
        } catch (StorageException e) {
            album.setImageURL("150.png");
        }
        albumService.save(album);
        redirect.addFlashAttribute("globalMessage", "Successfully created a new album: " + album.getId());
        return "redirect:/admin/albums/create";
    }

    @GetMapping
    public ModelAndView index(@RequestParam("s") Optional<String> s,
                              @RequestParam(defaultValue = "0") Integer pageNo,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "id") String sortBy) {
        Page<Album> albums; // Tạo đối tượng lưu Page singers;
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, sortBy));
        if (s.isPresent()) {
            // Kiểm tra xem nếu Parameter search được truyền vào thì gọi service có 2 tham số
            albums = albumService.findAllByNameContains(s.get(), paging);
        } else {
            albums = albumService.findAll(paging);
        }
        ModelAndView modelAndView = new ModelAndView("admin/album/list");
        modelAndView.addObject("albums", albums);
        modelAndView.addObject("txtSearch", s);
        return modelAndView;
    }

    @GetMapping("{id}")
    public String show(@PathVariable Long id, Model model) {
        Album album= albumService.findById(id).get();
        model.addAttribute("album", album);
        return "admin/album/view";
    }

    @GetMapping("edit-album/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirect) {
        Optional<Album> album = albumService.findById(id);
        if (album.isPresent()) {
            model.addAttribute("album", album.get());
            return "admin/album/edit";
        } else {
            redirect.addFlashAttribute("message", "Album not found!");
            return "redirect:/admin/albums";
        }
    }

    @PostMapping("edit-album")
    public ModelAndView updateBlog(@ModelAttribute("album") Album album) {
        albumService.save(album);

        ModelAndView modelAndView = new ModelAndView("admin/album/edit");
        modelAndView.addObject("album", album);
        modelAndView.addObject("message", "Album updated sucessfully");
        return modelAndView;
    }

    @PostMapping("delete")
    public String deleteById(@RequestParam("id") Long id, RedirectAttributes redirect) {
        albumService.deleteById(id);
        redirect.addFlashAttribute("globalMessage", "Successfully deleted a category");
        return "redirect:/admin/categories";
    }
}
