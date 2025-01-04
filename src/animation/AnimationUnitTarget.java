package animation;

import battlefield.Cell;
import core.Main;
import unit.Unit;

public class AnimationUnitTarget extends Animation
{	
	Unit unit;

	public AnimationUnitTarget(AnimatedSpriteSheet sheet, Unit unit, float scale)
	{
		this(sheet, unit);
		this.imageScaling = scale;
	}

	public AnimationUnitTarget(AnimatedSpriteSheet sheet, Unit unit)
	{
		super(sheet);
		this.frameRate = sheet.getFrameRate();
		this.unit = unit;
		this.sheet = sheet;
	}

	public void update()
	{
		super.update();

		// End this if there is not a valid target
		if(unit == null)
		{
			timer = duration;
			return;
		}

		setPosition(unit);

	//	System.out.println(cell.getYPixel() - extraHeight);

	}
		
}
