package groep4.a_zalf.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import groep4.a_zalf.Collection.Arts;
import groep4.a_zalf.R;

public class AfspraakMakenMetArts extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lvArtsen;
    private List<Arts> artsen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afspraak_maken_met_arts);
        initializeUIComponents();

        artsen = new ArrayList<>();
        artsen.add(new Arts("Bianca"));
        artsen.add(new Arts("Friso"));
        artsen.add(new Arts("Philips"));
        artsen.add(new Arts("Theodore"));
        artsen.add(new Arts("Trudy"));
        AfspraakArtsListAdapter aala = new AfspraakArtsListAdapter(getApplicationContext(), artsen);
        lvArtsen.setAdapter(aala);
        lvArtsen.setOnItemClickListener(this);
    }

    private void initializeUIComponents() {
        lvArtsen = (ListView) findViewById(R.id.lvArtsen);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
