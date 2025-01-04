package animation;

import battlefield.Cell;

public class DelayedAnimation
{
	private Animation animation;
	private int timeLeft;


	public DelayedAnimation(Animation animation, int timeLeft)
	{
		this(animation, timeLeft, 0, 0);
	}

	public DelayedAnimation(Animation animation, int timeLeft, Cell c)
	{
		this(animation, timeLeft, c.getX(), c.getY());
	}

	public DelayedAnimation(Animation animation, int timeLeft, float x, float y)
	{
		this.timeLeft = timeLeft;
		this.animation = animation;
		this.animation.setPosition(x, y);
	}
		
	public void update()
	{
		if(timeLeft == 0)
		{
			AnimationManager.add(animation);
		}
		
		timeLeft--;
	}
	
	public boolean isDone()
	{
		return timeLeft < 0;
	}

}
