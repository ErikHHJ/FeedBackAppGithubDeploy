package no.hvl.feedbackapp.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import no.hvl.feedbackapp.model.FeedBack;
import no.hvl.feedbackapp.model.Lecture;
import no.hvl.feedbackapp.repository.LectureRepository;
import no.hvl.feedbackapp.service.FeedBackService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping("/feedback")
public class FeedBackController {

    private final FeedBackService feedBackService;
    private final LectureRepository lectureRepository;

    public FeedBackController(FeedBackService feedBackService,
                              LectureRepository lectureRepository) {
        this.feedBackService = feedBackService;
        this.lectureRepository = lectureRepository;
    }
    @GetMapping("/{token}")
    public String showFeedBackPage(
            @PathVariable("token") String token,
            Model model,
            HttpServletRequest request,
            HttpServletResponse response) {
        Lecture lecture = lectureRepository.findByToken(token);


        if(lecture == null) {

            return "invalid-token";
        }

        model.addAttribute("lecture", lecture);

        String clientId = getOrCreateClientId(request, response);
        FeedBack feedBack = feedBackService.getFeedBackByClientIdAndLecture(clientId, lecture);
        if(feedBack != null) {
            model.addAttribute("feedBack", feedBack);
        }

        return "feedback-page";
    }
    @PostMapping("/{token}")
    public String submitFeedback(@PathVariable String token,
                                 @RequestParam(required = false) String text,
                                 @RequestParam(required = false)String rating,
                                 HttpServletResponse response,
                                 HttpServletRequest request,
                                 RedirectAttributes ra) {
        Lecture lecture = lectureRepository.findByToken(token);
        if(lecture == null) {
            return "invalid-token";
        }

        String clientId = getOrCreateClientId(request, response);

        if(!feedBackService.createOrUpdateFeedBack(text, rating, lecture, clientId)) {
            ra.addFlashAttribute("message", "Feedback could not be submitted");
            return "redirect:/feedback/" + token;
        }
        ra.addFlashAttribute("message", "Feedback submitted");
        return "redirect:/feedback/" + token;
    }
    private String getOrCreateClientId(HttpServletRequest request, HttpServletResponse response) {

        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if("clientId".equals((cookie.getName()))){
                    return cookie.getValue();
                }
            }
        }

        String clientId = UUID.randomUUID().toString();
        Cookie cookie = new Cookie("clientId", clientId);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60*60); // 1 hour expiration

        response.addCookie(cookie);

        return clientId;
    }
}
