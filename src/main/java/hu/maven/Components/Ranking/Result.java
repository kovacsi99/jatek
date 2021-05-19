package hu.maven.Components.Ranking;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.function.IntFunction;


/**
 * A {@link Class} tárolja el a játékosokat és a győztes nevét.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result extends ArrayList<Result> {
    String p1;
    String p2;
    String winner;


}