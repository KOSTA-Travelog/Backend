package kosta.travelog.dto;

import com.google.gson.JsonObject;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommunityDTO {
    private int communityId;
    private String communityTitle;
    private String communityDescription;
    private String communityHashtag;
    private LocalDate communityDate;
    private String communityImage;
    private char communityStatus;
    private int countMember;

    @Override
    public String toString() {
        JsonObject json = new JsonObject();
        json.addProperty("communityId" , this.getCommunityId());
        json.addProperty("communityTitle" , this.getCommunityTitle());
        json.addProperty("communityDescription" , this.getCommunityDescription());
        json.addProperty("communityHashtag" , this.getCommunityHashtag());
        json.addProperty("communityDate" , this.getCommunityDate().toString());
        json.addProperty("communityImage" , this.getCommunityImage());
        json.addProperty("communityStatus" , this.getCommunityStatus());
        json.addProperty("countMember" , this.getCountMember());
        
        return json.toString();
    }
}
