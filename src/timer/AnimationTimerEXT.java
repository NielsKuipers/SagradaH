package timer;
import javafx.animation.AnimationTimer;

public abstract class AnimationTimerEXT extends AnimationTimer {

	private long sleepNs;

	private long prevTime = 0;

	protected AnimationTimerEXT(long sleepMs) {
		this.sleepNs = sleepMs * 1_000_000;
	}

	@Override
	public void handle(long now) {

		// some delay
		if ((now - prevTime) < sleepNs) {
			return;
		}

		prevTime = now;

		doAction();
	}

	public abstract void doAction();

}