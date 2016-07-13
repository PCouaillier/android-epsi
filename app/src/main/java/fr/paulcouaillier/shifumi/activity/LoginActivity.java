package fr.paulcouaillier.shifumi.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import fr.paulcouaillier.shifumi.R;
import fr.paulcouaillier.shifumi.shifumi.ShiFuMiHistory;

public class LoginActivity extends Activity {

    protected static URL url = null;

    private String getUsername() {
        return ((EditText)super.findViewById(R.id.login_username)).getText().toString();
    }

    private String getPassword() {
        return ((EditText)super.findViewById(R.id.login_password)).getText().toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(url==null) {
            this.setURL();
        }
        setContentView(R.layout.activity_login);
        new ShiFuMiHistory(getBaseContext());
    }

    protected void setURL() {
        try {
            url = new URL("http://solebain.fr/epsi/tourolf/Connexion/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            this.onDestroy();
        }
    }

    private class LoginTask extends AsyncTask<Void, Integer, String> {

        @Nullable
        protected String doInBackground(@Nullable Void... voids) {

            try {
                URL lUrl = new URL(url.toString()+"?nom="+getUsername()+"&prenom="+getPassword());
                URLConnection conn = lUrl.openConnection();

                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));

                String inputLine;
                String res = "";
                while ((inputLine = bufferedReader.readLine()) != null) {
                    res+=inputLine;
                }
                bufferedReader.close();
                return res;

            } catch (IOException ioException) {
                return null;
            }
        }

        protected void onPostExecute(String result) {
            if(isLoginValid(result)) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            } else {
                showBadLoginMessage();
            }
        }
    }

    private void showBadLoginMessage() {
        ((TextView)super.findViewById(R.id.login_error)).setText(R.string.login_error_message);
    }

    /**
     *
     * @param result the result of asking the server
     * @return boolean
     */
    private boolean isLoginValid(@Nullable String result) {
        return result != null && result.equals("\"1\"");
    }

    /**
     *
     * @param view view passed by onclick
     */
    public void login(View view)  {
        /*
         * need to set url
         */
        // @TODO remover start activity
        startActivity(new Intent(LoginActivity.this, MainActivity.class));

        setURL();
        (new LoginTask()).execute();
    }
}
