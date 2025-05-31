package com.example.resume.controller;

import com.example.resume.model.Resume;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ResumeController {

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("resume", new Resume());
        return "form";
    }

    @PostMapping("/generate")
    public String generateResume(@ModelAttribute Resume resume, Model model) {
        model.addAttribute("resume", resume);
        return "resume";
    }

    @PostMapping("/download-pdf")
    public void downloadPdf(@ModelAttribute Resume resume, HttpServletResponse response) throws Exception {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=resume.pdf");

        List<String> skills = resume.getSkills() != null ? resume.getSkills() : List.of();
        List<String> experience = resume.getExperience() != null ? resume.getExperience() : List.of();
        List<String> education = resume.getEducation() != null ? resume.getEducation() : List.of();

        String html = "<html><body>" +
                "<h1>" + escapeHtml(resume.getName()) + "</h1>" +
                "<p>" + escapeHtml(resume.getEmail()) + " | " + escapeHtml(resume.getPhone()) + "</p>" +
                "<h3>Summary</h3><p>" + escapeHtml(resume.getSummary()) + "</p>" +

                "<h3>Skills</h3><ul>" +
                skills.stream().map(s -> "<li>" + escapeHtml(s) + "</li>").collect(Collectors.joining()) +
                "</ul>" +

                "<h3>Experience</h3><ul>" +
                experience.stream().map(e -> "<li>" + escapeHtml(e) + "</li>").collect(Collectors.joining()) +
                "</ul>" +

                "<h3>Education</h3><ul>" +
                education.stream().map(ed -> "<li>" + escapeHtml(ed) + "</li>").collect(Collectors.joining()) +
                "</ul>" +

                "</body></html>";

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();

        try (OutputStream os = response.getOutputStream()) {
            renderer.createPDF(os);
        }
    }

    private String escapeHtml(String value) {
        return value == null ? "" : value
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#x27;");
    }
}
