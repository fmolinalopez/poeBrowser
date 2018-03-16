package org.molina.poebrowser;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
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
        champName.setMovementMethod(new ScrollingMovementMethod());
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

        ImageView image = findViewById(R.id.champ_image);
        image.setImageResource(getChampionClassImage(champion.getClase()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_champion_details, menu);

        return true;
    }

    protected int getChampionClassImage(String clase) {
        switch (clase.toLowerCase()) {
            case "ranger":
                return R.drawable.ranger;
            case "deadeye":
                return R.drawable.deadeye;
            case "raider":
                return R.drawable.raider;
            case "pathfinder":
                return R.drawable.pathfinder;
            case "scion":
                return R.drawable.scion;
            case "marauder":
                return R.drawable.marauder;
            case "juggernaut":
                return R.drawable.juggernaut;
            case "berserker":
                return R.drawable.berserker;
            case "chieftain":
                return R.drawable.chieftain;
            case "shadow":
                return R.drawable.shadow;
            case "assassin":
                return R.drawable.assasin;
            case "saboteur":
                return R.drawable.saboteur;
            case "trickster":
                return R.drawable.trickster;
            case "witch":
                return R.drawable.witch;
            case "necromancer":
                return R.drawable.necromancer;
            case "elementalist":
                return R.drawable.elementalist;
            case "occultist":
                return R.drawable.occultist;
            case "duelist":
                return R.drawable.duelist;
            case "slayer":
                return R.drawable.slayer;
            case "gladiator":
                return R.drawable.gladiator;
            case "champion":
                return R.drawable.champion;
            case "templar":
                return R.drawable.templar;
            case "inquisitor":
                return R.drawable.inquisitor;
            case "hierophant":
                return R.drawable.hierophant;
            case "guardian":
                return R.drawable.guardian;
            default:
                return R.drawable.placeholder;
        }
    }
}
