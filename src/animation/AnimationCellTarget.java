package animation;

import battlefield.Cell;
import unit.Unit;

/*

THIS CLASS IS A STUB, REWORK IT FOR ZONE EFFECTS

 */
public class AnimationCellTarget extends Animation
{
	Cell cell;

	public AnimationCellTarget(AnimatedSpriteSheet sheet, Cell cell)
	{
		super(sheet);
		this.frameRate = sheet.getFrameRate();
		this.cell = cell;
		this.sheet = sheet;
	}

	public AnimationCellTarget(AnimatedSpriteSheet sheet, Unit unit)
	{
		this(sheet,unit.getCell());
	}

	public void update()
	{
		super.update();

		// End this if there is not a valid target
		if(cell == null)
		{
			timer = duration;
			return;
		}

		setPosition(cell);
		y = cell.getY() ;

		
	//	System.out.println(cell.getYPixel() - extraHeight);

	}
		
}
