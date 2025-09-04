package antonio.student;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@Getter
public class Etudiant {
    private int id;
    private String nom;
    private String prenom;
    private Instant dateEntree;
    private List<Groupe> historiqueGroupe;
}
