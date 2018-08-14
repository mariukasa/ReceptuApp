package developmentltd.marius.receptuapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void paspaudusReceptas(View view) {
        Intent intent = new Intent(this, Receptas.class);
        startActivity(intent);
    }

    public void paspaudusDabar(View view) {
        Intent intent = new Intent(this, Dabar.class);
        startActivity(intent);
    }

    public void paspaudusSkaneris(View view) {
        Intent intent = new Intent(this, Scaner.class);
        startActivity(intent);
    }

    public void paspaudusSaldytuvas(View view) {
        Intent intent = new Intent(this, Saldytuvas.class);
        startActivity(intent);
    }
}
