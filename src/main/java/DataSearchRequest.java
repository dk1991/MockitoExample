import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
class DataSearchRequest {
    String id;
    Date updateBefore;
    int length;
}
