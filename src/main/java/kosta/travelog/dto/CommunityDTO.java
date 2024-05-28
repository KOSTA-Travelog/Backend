package kosta.travelog.dto;

import com.google.gson.JsonObject;
import kosta.travelog.vo.CommunityVO;
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

    public CommunityDTO(CommunityVO vo, int memberCount) {
        this.communityId = vo.getCommunityId();
        this.communityTitle = vo.getCommunityTitle();
        this.communityDescription = vo.getCommunityDescription();
        this.communityHashtag = vo.getCommunityHashtag();
        this.communityDate = vo.getCommunityDate();
        this.communityImage = vo.getCommunityImage();
        this.communityStatus = vo.getCommunityStatus();
        this.countMember = memberCount;
    }

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
