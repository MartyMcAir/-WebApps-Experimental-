package com.controllers;

import com.model.Person;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

// baeldung
// used to test HttpClientPostingTest
@RestController("/upload")
public class UploadController {

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String postUser(@RequestParam final String username, @RequestParam final String password) {
        return "Success" + username;
    }

    @RequestMapping(value = "/users/detail", method = RequestMethod.POST)
    public String postUserDetail(@RequestBody final Person entity) {
        return "Success" + entity.getId();
    }

    @RequestMapping(value = "/users/multipart", method = RequestMethod.POST)
    public String uploadFile(@RequestParam final String username, @RequestParam final String password, @RequestParam("file") final MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                final DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH.mm.ss");
                final String fileName = dateFormat.format(new Date());
                final File fileServer = new File(fileName);
                fileServer.createNewFile();
                final byte[] bytes = file.getBytes();
                final BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fileServer));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded " + username;
            } catch (final Exception e) {
                return "You failed to upload " + e.getMessage();
            }
        } else {
            return "You failed to upload because the file was empty.";
        }
    }

    @RequestMapping(value = "/users/upload", method = RequestMethod.POST)
    public String postMultipart(@RequestParam("file") final MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                final DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH.mm.ss");
                final String fileName = dateFormat.format(new Date());
                final File fileServer = new File(fileName);
                fileServer.createNewFile();
                final byte[] bytes = file.getBytes();
                final BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fileServer));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded ";
            } catch (final Exception e) {
                return "You failed to upload " + e.getMessage();
            }
        } else {
            return "You failed to upload because the file was empty.";
        }
    }
}
