package no.hvl.feedbackapp.service;

import no.hvl.feedbackapp.model.FeedBack;
import no.hvl.feedbackapp.model.Lecture;
import no.hvl.feedbackapp.repository.FeedBackRepository;
import org.springframework.stereotype.Service;

@Service
public class FeedBackService {

    private final FeedBackRepository feedBackRepository;

    private final FeedBackValidator feedBackValidator;

    public FeedBackService(FeedBackRepository feedBackRepository, FeedBackValidator feedBackValidator) {
        this.feedBackRepository = feedBackRepository;
        this.feedBackValidator = feedBackValidator;
    }

    public boolean createOrUpdateFeedBack(String text, String rating, Lecture lecture, String clientId) {
        FeedBack feedBack = feedBackRepository.findByClientIdAndLecture(clientId, lecture);

        if (feedBack == null) {
            feedBack = new FeedBack();
            feedBack.setLecture(lecture);
            feedBack.setClientId(clientId);
        }

        feedBack.setTextFeedback(text);
        feedBack.setLecture(lecture);

        int intRating;
        if (rating == null) {
            return false;
        }
        try {
            intRating = Integer.parseInt(rating);
        }catch(NumberFormatException e){
            return false;
        }
        feedBack.setRatingFeedback(intRating);

        feedBack.setClientId(clientId);

        if(feedBackValidator.validate(feedBack)){
            feedBackRepository.save(feedBack);
            return true;
        }
        return false;


    }
    public FeedBack getFeedBackByClientIdAndLecture(String clientId,  Lecture lecture) {
        return feedBackRepository.findByClientIdAndLecture(clientId, lecture);
    }

}
