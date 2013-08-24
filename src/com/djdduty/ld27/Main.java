package com.djdduty.ld27;

import engine.JWolf2D.core.Game;

public class Main {
	public static void main(String[] args) throws Exception {
		Game game = new Game(new MainScreen());
		game.setTitle("LD27!");
		game.setDisplayMode(800, 600);
		game.start();
	}
}
