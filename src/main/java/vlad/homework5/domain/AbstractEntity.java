package vlad.homework5.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import vlad.homework5.DTO.Views;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;


@Data
@NoArgsConstructor
@ToString(of = {"id"})
@EqualsAndHashCode(of = {"id"})
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
abstract public class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", updatable = false, nullable = false)
    protected Long id;

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @JsonView({Views.Public.class, Views.Internal.class})
//    protected Long id;

    @CreatedDate
    @Temporal(TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING,
    pattern = "dd-MM-yyyy hh:mm:ss")
    @JsonView(Views.Public.class)
    protected Date createdDate;

    @LastModifiedDate
    @Temporal(TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING,
    pattern = "dd-MM-yyyy hh:mm:ss")
    @JsonView(Views.Public.class)
    protected Date lastModifiedDate;

}
