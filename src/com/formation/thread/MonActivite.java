package com.formation.thread;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SlidingDrawer;

public class MonActivite extends Activity {

	private Button monBouton;
	private ProgressDialog progressDialog;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        progressDialog = new ProgressDialog(this);
        
        monBouton = (Button) findViewById(R.id.MonBouton);
        
        monBouton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				traitementDesDonneesAvecPourcentage();
			}
		});
    }
	
	private void traitementDesDonneesAvecPourcentage() {
		
		// On ajoute un message à notre progress dialog
		progressDialog.setMessage("Chargement en cours");
		// On donne un titre à notre progress dialog
		progressDialog.setTitle("Mon chargement");
		// On spécifie le style 
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		// On spécifie le maximum
		progressDialog.setMax(10);
		// On affiche notre message
		progressDialog.show();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				// Boucle de 1 a 10
				for (int i = 0; i < 10; i++) {
					try {
						// Attends 500 millisecondes
						Thread.sleep(500);
						// On change la valeur de la progression
						progressDialog.setProgress(i+1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				handler.sendEmptyMessage(0);
				
				// A la fin du traitement, on fait disparaitre notre message
				progressDialog.dismiss();
			}
		}).start();	
	}
	
	private void traitementDesDonnees() {
		
		// On ajoute un message à notre progress dialog
		progressDialog.setMessage("Chargement en cours");
		// On affiche notre message
		progressDialog.show();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				// Boucle de 1 a 10
				for (int i = 0; i < 10; i++) {
					try {
						// Attends 500 millisecondes
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				handler.sendEmptyMessage(0);
				
				// A la fin du traitement, on fait disparaitre notre message
				progressDialog.dismiss();
			}
		}).start();
	}
	
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if(msg.what == 0) {
				monBouton.setText("C'est bon");
			}
		};
	};
}