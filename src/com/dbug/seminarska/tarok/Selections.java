package com.dbug.seminarska.tarok;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;

public class Selections extends SherlockFragmentActivity {
	
	TextView kdo, vKom, napovedi, kontra;
	ListView talon;
	ArrayList<String> talonCards = new ArrayList<String>();
	//ArrayList<String> choicesList = new ArrayList<String>();
	Context context;
	ArrayList<String> mSelectedItems;
	String[] choicesList, gameList, possibleExtras;
	int positionGame;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selections);
		context = this;
		
		Intent i = getIntent();
		talonCards = i.getStringArrayListExtra("talon");
		
		choicesList = getResources().getStringArray(R.array.kralji);
		gameList = getResources().getStringArray(R.array.vrednostIgre);
		possibleExtras = getResources().getStringArray(R.array.possibleExtras);
		
		Log.i("TALON", talonCards.toString());
		
		kdo = (TextView)findViewById(R.id.gameType);
		vKom = (TextView)findViewById(R.id.whatKing);
		napovedi = (TextView)findViewById(R.id.extras);
		kontra = (TextView)findViewById(R.id.kontra);
		talon = (ListView)findViewById(R.id.talonList);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.cards, R.id.allCards, talonCards);
		talon.setAdapter(adapter);


		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("Koliko greš?");
		builder.setItems(gameList, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Log.i("ITEM", "clicked at: "+which);
				kdo.setText("Igralec blabla igra "+ gameList[which]);
				positionGame = which;
			}
		});
		builder.create();
		builder.show();
		
		
		if (positionGame < 2) {
			AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
			builder2.setTitle("V katerem kralju?");
			builder2.setItems(possibleExtras, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Log.i("ITEM", "clicked at: "+which);
					vKom.setText("Igralec blabla igra "+ choicesList[which]);
					
				}
			});
			builder2.create();
			builder2.show();
		}
		
		else {
			AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
			mSelectedItems = new ArrayList<String>();
			builder2.setTitle("Napovem...");
			builder2.setMultiChoiceItems(possibleExtras, null, new DialogInterface.OnMultiChoiceClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which, boolean isChecked) {
					if (isChecked) {
						mSelectedItems.add(possibleExtras[which]);
					}
					else if (mSelectedItems.contains(which)) {
						mSelectedItems.remove(Integer.valueOf(which));
	                }
					
				}
			})
			// Set the action buttons
           .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int id) {
                   napovedi.setText("Igralec blabla napoveduje: "+ mSelectedItems);
               }
           })
           .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int id) {
            	   finish();
               }
           });;
			
			builder2.create();
			builder2.show();
		}
		
	}
		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.selections, menu);
		return true;
	}

}