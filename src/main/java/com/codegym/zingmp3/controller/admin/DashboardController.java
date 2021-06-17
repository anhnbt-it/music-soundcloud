package com.codegym.zingmp3.controller.admin;

import com.codegym.zingmp3.model.Album;
import com.codegym.zingmp3.model.Artist;
import com.codegym.zingmp3.model.Song;
import com.codegym.zingmp3.service.AlbumService;
import com.codegym.zingmp3.service.SingerService;
import com.codegym.zingmp3.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class DashboardController {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private SingerService singerService;

    @Autowired
    private SongService songService;

    private Pageable defaultPage() {
        return PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id"));
    }

    @ModelAttribute("albums")
    public Iterable<Album> albums() {
        return albumService.findAll(defaultPage());
    }

    @ModelAttribute("singers")
    public Iterable<Artist> singers() {
        return singerService.findAll(defaultPage());
    }

    @ModelAttribute("songs")
    public Iterable<Song> songs() {
        return songService.findAll(defaultPage());
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("countAlbums", albumService.count());
        model.addAttribute("countSongs", songService.count());
        model.addAttribute("countSingers", singerService.count());
        return "admin/dashboard";
    }

}
