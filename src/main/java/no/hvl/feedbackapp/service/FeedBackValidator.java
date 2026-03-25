package no.hvl.feedbackapp.service;

import no.hvl.feedbackapp.model.FeedBack;
import no.hvl.feedbackapp.model.Lecture;
import org.springframework.stereotype.Service;

@Service
public class FeedBackValidator {


    public boolean validate(FeedBack feedBack) {
        String text = feedBack.getTextFeedback();
        int rating = feedBack.getRatingFeedback();


        if(rating > 5 && rating < 1) return false;
        if(text.length() > 200) return false;
        return true;
    }
}
