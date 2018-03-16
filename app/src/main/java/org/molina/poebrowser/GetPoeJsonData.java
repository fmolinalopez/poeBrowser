package org.molina.poebrowser;


import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vikin on 15/03/2018.
 */

public class GetPoeJsonData extends GetRawData {

    private static final String LOG_TAG = GetPoeJsonData.class.getSimpleName();

    private List<Champion> mChampions;
    private Uri mDestinationUri;

    public GetPoeJsonData(String searchCriteria) {
        super(null);
        createAndUpdateUri(searchCriteria);
        mChampions = new ArrayList<>();
    }

    public List<Champion> getmChampions() {
        return mChampions;
    }

    private boolean createAndUpdateUri(String searchCriteria) {
        final String POE_BASE_API_URL = "http://api.pathofexile.com/ladders/Standard";
        final String OFFSET_PARAM = "offset";
        final String LIMIT_PARAM = "limit";

        mDestinationUri = Uri.parse(POE_BASE_API_URL).buildUpon()
                .appendQueryParameter(OFFSET_PARAM, Integer.toString(Integer.parseInt(searchCriteria)-1))
                .appendQueryParameter(LIMIT_PARAM, "20")
                .build();

        return mDestinationUri != null;
    }

    private void processResult() {
        final String POE_ENTRIES = "entries";
        final String POE_RANK = "rank";
        final String POE_DEAD = "dead";
        final String POE_ONLLINE = "online";
        final String POE_CHARACTER = "character";
        final String POE_CHARACTER_NAME = "name";
        final String POE_CHARACTER_LEVEL = "level";
        final String POE_CHARACTER_CLASS = "class";
        final String POE_CHARACTER_EXPERIENCE = "experience";
        final String POE_ACCOUNT = "account";
        final String POE_ACCOUNT_CHALLENGES = "challenges";
        final String POE_ACCOUNT_CHALLENGES_TOTAL = "total";

        if (getmDownloadStatus() != DownloadStatus.OK) {
            Log.e(LOG_TAG, "No se ha descargado el JSON");
            return;
        }

        try {
            JSONObject jsonData = new JSONObject(getmData());
            JSONArray entries = jsonData.getJSONArray(POE_ENTRIES);

            for (int i = 0; i < entries.length(); i++) {
                JSONObject jsonChamp = entries.getJSONObject(i);
                String rank = jsonChamp.getString(POE_RANK);
                boolean dead = jsonChamp.getBoolean(POE_DEAD);
                boolean online = jsonChamp.getBoolean(POE_ONLLINE);

                JSONObject character = jsonChamp.getJSONObject(POE_CHARACTER);
                String name = character.getString(POE_CHARACTER_NAME);
                String level = character.getString(POE_CHARACTER_LEVEL);
                String clase = character.getString(POE_CHARACTER_CLASS);
                String experience = character.getString(POE_CHARACTER_EXPERIENCE);

                JSONObject account = jsonChamp.getJSONObject(POE_ACCOUNT);
                JSONObject challenges = account.getJSONObject(POE_ACCOUNT_CHALLENGES);
                int total = challenges.getInt(POE_ACCOUNT_CHALLENGES_TOTAL);

                Champion champion = new Champion(rank, dead, online, name, level, clase, experience, total);
                mChampions.add(champion);
            }

            for (Champion champion: mChampions){
                Log.d(LOG_TAG, "Champion: " + champion.toString());
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "No se puede crear el objeto JSON");
            e.printStackTrace();
        }
    }

    public void execute(){
        DownloadJsonData downloadJsonData = new DownloadJsonData();
        Log.v(LOG_TAG, "Build uri: " + mDestinationUri.toString());
        downloadJsonData.execute(mDestinationUri.toString());
    }

    public class DownloadJsonData extends DownloadRawData{

        @Override
        protected void onPostExecute(String webData) {
            super.onPostExecute(webData);
            processResult();
        }

        @Override
        protected String doInBackground(String... params) {
            String [] par = { mDestinationUri.toString() };

            return super.doInBackground(par);
        }
    }
}
