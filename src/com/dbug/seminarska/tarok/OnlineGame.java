package com.dbug.seminarska.tarok;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;

public class OnlineGame extends SherlockFragmentActivity {

	ArrayList<String> deck = new ArrayList<String>();
	ArrayList<String> playerOne = new ArrayList<String>();
	ArrayList<String> playerTwo = new ArrayList<String>();
	ArrayList<String> playerThree = new ArrayList<String>();
	ArrayList<String> playerFour = new ArrayList<String>();
	ArrayList<String> talon = new ArrayList<String>();
	ListView cards;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_online_game);
		
		cards = (ListView)findViewById(R.id.yourCards);
		
		String[] allCards = this.getResources().getStringArray(R.array.cardDeck);
		
		for (int i = 0; i < allCards.length; i++) {
			deck.add(allCards[i]);
		}
		
		for (int i = 0; i < 12; i++) {
			int position = (int) (Math.random()*deck.size());
			playerOne.add(deck.get(position));
			deck.remove(position);
			
			int position2 = (int) (Math.random()*deck.size());
			playerTwo.add(deck.get(position2));
			deck.remove(position2);
			
			int position3 = (int) (Math.random()*deck.size());
			playerThree.add(deck.get(position3));
			deck.remove(position3);
			
			int position4 = (int) (Math.random()*deck.size());
			playerFour.add(deck.get(position4));
			deck.remove(position4);
			
		}
		
		Log.i("DECK", deck.toString());
		
		talon = deck;

		
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.cards, R.id.allCards, playerOne);
		cards.setAdapter(adapter);
		
		Log.i("PLAYER2", playerTwo.toString());
		Log.i("PLAYER3", playerThree.toString());
		Log.i("PLAYER4", playerFour.toString());
		
		Button next = (Button)findViewById(R.id.cardsOk);
		next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(OnlineGame.this, Selections.class);
				i.putExtra("talon", talon);
				startActivity(i);
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.online_game, menu);
		return true;
	}

}
