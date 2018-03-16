package org.molina.poebrowser;

import android.app.SearchManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;

/**
 * Created by Vikin on 15/03/2018.
 */

public class SearchActivity extends BaseActivity {

    public static final String LOG_TAG = SearchActivity.class.getSimpleName();

    public static final String POE_QUERY = "POE_QUERY";

    private SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        setContentView(R.layout.layout_search);

        activateToolbarWithBackEnabled();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_search, menu);

        final MenuItem searchItem = menu.findItem(R.id.search_view);
        mSearchView = (SearchView) searchItem.getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );
        mSearchView.setIconified(false);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SharedPreferences sharedPreferences =
                        PreferenceManager.getDefaultSharedPreferences(
                                getApplicationContext()
                        );
                String regex = "\\d+";
                // Si la cadena que recibe no consta solo de numeros entonces pasa el valor 1
                sharedPreferences.edit().putString(POE_QUERY, query.matches(regex) ? query : "1").commit();
                mSearchView.clearFocus();

                finish();

                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        mSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                finish();

                return false;
            }
        });
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.menu_settings){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
