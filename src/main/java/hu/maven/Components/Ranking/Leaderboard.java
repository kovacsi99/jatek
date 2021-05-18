package hu.maven.Components.Ranking;

import java.util.ArrayList;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

/**
 * XMl-hez tartozó annotáció.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Data
/**
 * A {@link Class} eltárolja egy listában az eredményeket.
 */
public class Leaderboard {
    @XmlElementWrapper(name = "results")
    @XmlElement(name = "result")
    ArrayList<Result> results = new ArrayList<Result>();
}