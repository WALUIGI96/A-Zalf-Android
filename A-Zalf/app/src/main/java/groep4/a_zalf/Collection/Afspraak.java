package groep4.a_zalf.Collection;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by brunodelsing on 12/10/15.
 */
public class Afspraak {

    private Calendar tijdstip, tijdsduur;

    private Diagnose diagnose;
    private Informatie informatie;

    private Arts arts;
    private Patient patient;

    public Afspraak(Calendar tijdstip, Calendar tijdsduur, Arts arts, Patient patient, Informatie informatie) {
        this.tijdstip = tijdstip;
        this.tijdsduur = tijdsduur;
        this.arts = arts;
        this.patient = patient;
        this.informatie = informatie;
        this.diagnose = null;
    }

    public void setDiagnose(Diagnose diagnose) {
        this.diagnose = diagnose;

    }
}
