package ui.message;

import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public class MessageList 
{
	protected ArrayList<Message> messages;
	
	public MessageList()
	{
		messages = new ArrayList<Message>();
	}
	
	public void render(Graphics g)
	{
        for (Message m : messages)
        {
            m.render(g);
        }
	}
	
	public void add(Message m)
	{
		messages.add(m);
	}
	
	public void update()
	{
		for(int i = 0; i < messages.size(); i++)
		{
			Message m = messages.get(i);
			
			if(m.isDone())
			{
				messages.remove(m);
				i--;
			}
			else
			{
				m.update();
			}
		}		
	}
	
	public void clear()
	{
		messages.clear();
	}
}
