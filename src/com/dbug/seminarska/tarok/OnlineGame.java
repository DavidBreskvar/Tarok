package com.dbug.seminarska.tarok;

import java.util.ArrayList;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;

public class OnlineGame extends SherlockFragmentActivity {

	ArrayList<String> deck = new ArrayList<String>();
	ArrayList<String> currentCards = new ArrayList<String>();
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
			currentCards.add(deck.get(position));
			deck.remove(position);
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.cards, R.id.allCards, currentCards);
		cards.setAdapter(adapter);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.online_game, menu);
		return true;
	}

}
