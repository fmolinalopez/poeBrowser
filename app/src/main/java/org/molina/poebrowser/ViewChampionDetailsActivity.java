package org.molina.poebrowser;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Vikin on 15/03/2018.
 */

public class ViewChampionDetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.champion_details);

        activateToolbarWithBackEnabled();

        Intent intent = getIntent();
        Champion champion = (Champion) intent.getSerializableExtra(CHAMPION_TRANSFER);

        TextView champName = findViewById(R.id.name);
        champName.setText("Nombre: " + champion.getName());

        TextView rank = findViewById(R.id.rank);
        rank.setText("Rango: " + champion.getRank());

        TextView dead = findViewById(R.id.dead);
        dead.setText("Muerto: " + champion.getDead());

        TextView online = findViewById(R.id.online);
        online.setText("Online: " + champion.getOnline());

        TextView level = findViewById(R.id.level);
        level.setText("Nivel: " + champion.getLevel());

        TextView clase = findViewById(R.id.clase);
        clase.setText("Clase: " + champion.getClase());

        TextView experience = findViewById(R.id.experience);
        experience.setText("Experiencia: " + champion.getExperience());

        TextView challenges = findViewById(R.id.challenges);
        challenges.setText("Desafios: " + champion.getChallenges());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_champion_details, menu);

        return true;
    }
}
