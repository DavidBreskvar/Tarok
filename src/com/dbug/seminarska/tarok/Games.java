package com.dbug.seminarska.tarok;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Games extends SherlockFragmentActivity {

	int plays, score, diff, A, B, C, highestScore, whoPlays, type, gameValue;
	TextView gameStat, player;
	boolean outcome;
	StringBuilder gameBuilder, playerBuilder;
	String gameType, playing;
	double chance;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_play);
		
		plays = getIntent().getIntExtra("plays", 0);
		score = getIntent().getIntExtra("score", 0);
		chance = getIntent().getDoubleExtra("chance", 0);
		
		Log.i("INTENT", "Plays: "+ plays +"\nscore: " + score +"\nchance: "+chance);
		
		gameStat = (TextView)findViewById(R.id.gameStat);
		player = (TextView)findViewById(R.id.playerStat);
		
		A = 0;
		B = 0;
		C = 0;
		highestScore = 0;
		gameBuilder = new StringBuilder();
		playerBuilder = new StringBuilder();
		playerBuilder.append("A   B   C \n0   0   0\n--------------------\n");
		gameBuilder.append("\n\n\n");
	
		for (int i = 0; (i < plays) && (score >= highestScore); i ++) {
			Log.i("LOOP", "started for loop");
			outcome = Math.random() < (chance);
			//true for win, false for lose
			diff = (int)(Math.random()*36);
			//difference of the game
			
			whoPlays = (int) (Math.random()*4+1);
			//decides who will play the game
			
			type = (int) (Math.random()*4+1);
			//one two or three, difficult of the game played
			Log.i("LOOPING", "outcome " + outcome + "whoPlays = "+whoPlays + "type: "+type);
			if (type == 1) {
				gameType = "One";
				gameValue = 30;
			}
			else if (type == 2) {
				gameType = "Two";
				gameValue = 20;
			}
			else {
				gameType = "Three";
				gameValue = 10;
			}
			
			//if outcome is false, differnece is negative
			if (!outcome) {
				diff *= (-1);
				gameValue *= (-1);
			}
			
			int newDif = diff % 5;
			newDif *= 5;
			
			int change = gameValue + newDif;
			
			Log.i("Diff", "random: " + diff + "   " + whoPlays);
			if (whoPlays == 1) {
				//player A plays
				playing = "A";
				A = A + change;
			}
			else if (whoPlays == 2) {
				//player B plays
				playing = "B";
				B = B + change;
			}
			else {
				//player C plays
				playing = "C";
				C = C + change;
			}
			
			if ((A > B) && (A > C)) highestScore = A;
			else if ((B > A ) && (B > C)) highestScore = B;
			else if ((C > A) && (C > B)) highestScore = C;
			
			
			gameBuilder.append("["+(i+1)+"] "+gameType + ", " + playing + ", " + newDif +"\n");
			playerBuilder.append(A + "   " + B + "   " + C + "\n");
			String gamers = gameBuilder.toString();
			String players = playerBuilder.toString();
			Log.i("GAME", gamers);
			Log.i("PLAYER", players);
			
		}

		String gamers = gameBuilder.toString();
		String players = playerBuilder.toString();
		gameStat.setText(gamers);
		player.setText(players);
		
		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.games, menu);
		return true;
	}

}
