package miu.videokabbee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import java.util.Date;


@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PolicyEmailMessage {
    private String to;
    private String subject="Policy Update Notification";
    private String message="\\\\n\\\\nThe platform's security and privacy policies have been updated. Please review the latest policies and agree to continue using the platform.";
    private Date date;
}
