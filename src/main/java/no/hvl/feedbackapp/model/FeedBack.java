package no.hvl.feedbackapp.model;


import jakarta.persistence.*;

@Entity
@Table(name = "feedback", schema = "feedbackapp")
public class FeedBack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String textFeedback;

    private int ratingFeedback;

    @ManyToOne
    private Lecture lecture;

    @Column(nullable = false)
    private String clientId;



    public FeedBack() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTextFeedback() {
        return textFeedback;
    }

    public void setTextFeedback(String textFeedback) {
        this.textFeedback = textFeedback;
    }

    public int getRatingFeedback() {
        return ratingFeedback;
    }

    public void setRatingFeedback(int ratingFeedback) {
        this.ratingFeedback = ratingFeedback;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
