package swettdg.com.CodeFellowship.models;

import javax.persistence.*;

@Entity
public class Post {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            Long id;

    String body;
    String timestamp;

    @ManyToOne
    ApplicationUser creator;

    public Post(String body, String timestamp, ApplicationUser creator) {
        this.body = body;
        this.timestamp = timestamp;
        this.creator = creator;
    }

    public Post() {}

    @Override
    public String toString(){
        return String.format("Posted by %s on %s: %s",creator.username,timestamp,body);
    }

    public Long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public ApplicationUser getCreator() {
        return creator;
    }

}
