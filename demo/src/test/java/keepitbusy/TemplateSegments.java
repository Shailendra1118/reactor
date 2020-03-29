package keepitbusy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document(collection = "template_segments")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateSegments {
    private String templateId;
    //private List<Segment> segments = new ArrayList<>();
    private List<String> segmentIds;
    private String locale;
    private String scope;
    private String clientId;
    private Instant createdDate;
    private Instant updatedDate;

}
