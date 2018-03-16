package org.molina.poebrowser;

import android.content.Intent;
import android.content.SharedPreferences;
import android.drm.ProcessedData;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.bluzwong.swipeback.SwipeBackActivityHelper;

import java.util.ArrayList;

import static org.molina.poebrowser.SearchActivity.POE_QUERY;

public class MainActivity extends BaseActivity {

    public RecyclerView mRecyclerView;
    public PoeRecyclerViewAdapter mPoeRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_Poe);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        activateToolbar();

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );

        mPoeRecyclerViewAdapter = new PoeRecyclerViewAdapter(
                new ArrayList<Champion>(),
                MainActivity.this
        );
        mRecyclerView.setAdapter(mPoeRecyclerViewAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerViewClickListener(
                this,
                mRecyclerView,
                new RecyclerViewClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(
                                MainActivity.this,
                                ViewChampionDetailsActivity.class
                        );

                        intent.putExtra(
                                CHAMPION_TRANSFER,
                                mPoeRecyclerViewAdapter.getChampion(position)
                        );
                        SwipeBackActivityHelper.startSwipeActivity(MainActivity.this, intent);
//                        startActivity( intent );
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                        Toast.makeText(MainActivity.this, "Long tap", Toast.LENGTH_LONG).show();
                    }
                }
        ));
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(
                        getApplicationContext()
                );
        String query = getSavedPreferenceData(POE_QUERY);

        if ( query.length() > 0 ){
            ProcessChampions processChampions =
                    new ProcessChampions(query);
            processChampions.execute();
        }
    }

    private String getSavedPreferenceData(String poeQuery){
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(
                        getApplicationContext()
                );

        return sharedPreferences.getString(poeQuery, "500");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if ( id == R.id.menu_settings ) {
            return true;
        }

        if ( id == R.id.menu_search) {
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class ProcessChampions extends GetPoeJsonData {

        public ProcessChampions(String searchCriteria) {
            super(searchCriteria);
        }

        @Override
        public void execute() {
            super.execute();
            ProcessData processData = new ProcessData();
            processData.execute();
        }

        public class ProcessData extends DownloadJsonData {
            @Override
            protected void onPostExecute(String webData) {
                super.onPostExecute(webData);
                mPoeRecyclerViewAdapter.loadNewData(getmChampions());
            }
        }
    }
}
