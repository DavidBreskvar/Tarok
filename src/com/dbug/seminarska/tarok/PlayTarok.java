package com.dbug.seminarska.tarok;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;

public class PlayTarok extends SherlockFragmentActivity {

	Button start, play;
	EditText plays, score, chance;
	int numPlays, maxScore;
	double chanceProb;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.play_tarok);
		
		start = (Button)findViewById(R.id.startGame);
		plays = (EditText)findViewById(R.id.numPlays);
		score = (EditText)findViewById(R.id.winLimit);
		chance = (EditText)findViewById(R.id.winChance);
		play = (Button)findViewById(R.id.onlineGame);
		
		start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				numPlays = Integer.parseInt(plays.getText().toString());
				maxScore = Integer.parseInt(score.getText().toString());
				chanceProb = Double.parseDouble(chance.getText().toString());
				Log.i("INTENT", "Plays: "+ numPlays +"\nscore: " + maxScore +"\nchance: "+chanceProb);
				
				Intent game = new Intent(PlayTarok.this, Games.class);
				game.putExtra("plays", numPlays);
				game.putExtra("score", maxScore);
				game.putExtra("chance", chanceProb);
				startActivity(game);
			}
		});
		
		play.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(PlayTarok.this, OnlineGame.class);
				startActivity(i);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.play_tarok, menu);
		return true;
	}

}
