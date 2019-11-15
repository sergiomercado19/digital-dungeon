package unsw.dungeon;

import javafx.scene.media.AudioClip;

public class SoundEffects {

	public static void playSwordKill() {
		AudioClip audio = new AudioClip(SoundEffects.class.getResource("/sword_kill.mp3").toExternalForm());
		audio.play();
	}

	public static void playDoorOpen() {
		AudioClip audio = new AudioClip(SoundEffects.class.getResource("/door_open.mp3").toExternalForm());
		audio.play();
	}

	public static void playPickupPotion() {
		AudioClip audio = new AudioClip(SoundEffects.class.getResource("/pickup_potion.mp3").toExternalForm());
		audio.play();
	}

	public static void playPickupSword() {
		AudioClip audio = new AudioClip(SoundEffects.class.getResource("/pickup_sword.mp3").toExternalForm());
		audio.play();
	}

	public static void playPickupTreasure() {
		AudioClip audio = new AudioClip(SoundEffects.class.getResource("/pickup_treasure.mp3").toExternalForm());
		audio.play();
	}

	public static void playVictoryTune() {
		AudioClip audio = new AudioClip(SoundEffects.class.getResource("/game_won.mp3").toExternalForm());
		audio.play();
	}

	public static void playDefeatTune() {
		AudioClip audio = new AudioClip(SoundEffects.class.getResource("/game_lost.mp3").toExternalForm());
		audio.play();
	}

}
