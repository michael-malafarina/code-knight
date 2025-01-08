package states.map;

import core.Utility;
import org.newdawn.slick.Graphics;
import ui.reward.BattleNode;

import java.util.ArrayList;

import static core.Utility.random;

public class NodeSet
{
    protected Node[][] nodes;
    protected Node currentNode;

    public static final int WIDTH = 12;
    public static final int HEIGHT = 6;

    public NodeSet()
    {
        nodes = new Node[WIDTH][HEIGHT];
    }

    public void begin()
    {
        // Each Layer Of Nodes
        for (int i = 0; i < WIDTH; i++)
        {
            for (int j = 0; j < HEIGHT; j++)
            {
                Node n = createNode(i, j);
                nodes[i][j] = n;
            }
        }

        topEdge();
        leftEdge();
        rightEdge();
        bottomEdge();


        holes();
        linkAll();
//        currentNode = nodes[0][0];
    }

    public void topEdge()
    {
        int r = random(3, 20);

        for (int i = r; i < WIDTH; i++)
        {
            nodes[i][0] = null;
        }

    }

    public void leftEdge()
    {
        int r = random(3, 6);

        for (int i = r; i < HEIGHT; i++)
        {
            nodes[0][i] = null;
        }
    }

    public void rightEdge()
    {
        int r = random(-2, 4);

        for (int i = r - 1; i >= 0; i--)
        {
            nodes[WIDTH - 1][i] = null;
        }
    }

    public void bottomEdge()
    {
        int r = random(-6, WIDTH - 2);

        for (int i = r - 1; i >= 0; i--)
        {
            nodes[i][HEIGHT - 1] = null;
        }
    }

    public void holes()
    {
        int r = random(8, 20);

        for (int i = 0; i < r; i++)
        {
            int x;
            int y;
            do
            {
                x = Utility.random(1, WIDTH - 2);
                y = Utility.random(1, HEIGHT - 2);
            }
            while (!isSurroundedByNodes(x, y));

            nodes[x][y] = null;
        }
    }


    public boolean isSurroundedByNodes(int x, int y)
    {
        return nodes[x- 1][y - 1] != null &&
                nodes[x - 1][y] != null &&
                nodes[x - 1][y + 1] != null &&
                nodes[x][y - 1] != null &&
                nodes[x][y + 1] != null &&
                nodes[x + 1][y - 1] != null &&
                nodes[x + 1][y] != null &&
                nodes[x + 1][y + 1] != null;
    }


    public Node createNode(int x, int y)
    {
        Node newNode;
        int stage = x + y;

        if (stage == 0)
        {
            newNode = new RecruitNode(this, x, y);
            newNode.setOrigin();
        }
        else if (stage == 1 || stage == 2)
        {
            newNode = new RecruitNode(this, x, y);
        }
        else
        {
            newNode = new BattleNode(this, x, y);
        }

//        if (hasNode(x - 1, y))
//        {
//            linkNodes(nodes[x - 1][y], newNode);
//        }
//
//        if (hasNode(x, y - 1))
//        {
//            linkNodes(nodes[x][y - 1], newNode);
//        }

        return newNode;
    }

    public void linkAll()
    {
        for (int x = 0; x < WIDTH; x++)
        {
            for (int y = 0; y < HEIGHT; y++)
            {

                if (hasNode(x - 1, y) && hasNode(x, y))
                {
                    linkNodes(nodes[x - 1][y], nodes[x][y]);
                }

                if (hasNode(x, y - 1) && hasNode(x, y))
                {
                    linkNodes(nodes[x][y - 1], nodes[x][y]);
                }
            }
        }
    }

    public boolean inBounds(int x, int y)
    {
        return x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT;
    }

    public boolean hasNode(int x, int y)
    {
        return inBounds(x, y) && nodes[x][y] != null;
    }

//    public Node getValidNextNode(Node origin, ArrayList<Node> nextLayer)
//    {
//        Node candidate = nextLayer.get(Utility.random(nextLayer.size()));
//
//        if (candidate.isNext(origin.getStage()) && candidate.isNear(origin.getSlot()) && doesNotCross(origin, candidate))
//        {
//            return candidate;
//        }
//        else
//        {
//            return getValidNextNode(origin, nextLayer);
//        }
//    }
//
//    public Node getNearestPreviousNode(Node origin)
//    {
//        Node candidate = getNode(origin.stage - 1, origin.slot);
//
//        if(candidate != null)
//        {
//            return candidate;
//        }
//
//        candidate = getNode(origin.stage - 1, origin.slot - 1);
//
//        if(candidate != null)
//        {
//            return candidate;
//        }
//
//        return getNode(origin.stage - 1, origin.slot + 1);
//
//    }
//
//    public boolean doesNotCross(Node a, Node b)
//    {
//        // Going downward
//        if (a.getSlot() + 1 == b.getSlot())
//        {
//            Node c = getNode(a.getStage(), a.getSlot() + 1);
//            Node d = getNode(b.getStage(), b.getSlot() - 1);
//
//            if (c == null || d == null)
//            {
//                return true;
//            }
//            else
//            {
//                return !c.isLinked(d);
//            }
//        }
//
//        // Going upward
//        if (a.getSlot() - 1 == b.getSlot())
//        {
//            Node c = getNode(a.getStage(), a.getSlot() - 1);
//            Node d = getNode(b.getStage(), b.getSlot() + 1);
//
//            if (c == null || d == null)
//            {
//                return true;
//            }
//            else
//            {
//                return !c.isLinked(d);
//            }
//        }
//
//
//        return true;
//    }


    public void linkNodes(Node a, Node b)
    {
        a.addNextNode(b);
        b.addPreviousNode(a);
    }

    public Node getCurrentNode()
    {
        return currentNode;
    }

//    public boolean hasNode(int stage, int slot)
//    {
//        for (Node n : nodes)
//        {
//            if (n.getStage() == stage && n.getSlot() == slot)
//            {
//                return true;
//            }
//        }
//        return false;
//    }

//    public Node getNode(int stage, int slot)
//    {
//        for (Node n : nodes)
//        {
//            if (n.getStage() == stage && n.getSlot() == slot)
//            {
//                return n;
//            }
//        }
//        return null;
//    }

    public void setCurrentNode(Node node)
    {
        this.currentNode = node;
        Map.setStage(node.getStage() + 1);

    }

    public void render(Graphics g)
    {

        for (int i = 0; i < WIDTH; i++)
        {
            for (int j = 0; j < HEIGHT; j++)
            {
                if (hasNode(i, j))
                {
                    nodes[i][j].renderLink(g);
                }
            }
        }

        for (int i = 0; i < WIDTH; i++)
        {
            for (int j = 0; j < HEIGHT; j++)
            {
                if (hasNode(i, j))
                {
                    nodes[i][j].render(g);
                }
            }
        }

        for (int i = 0; i < WIDTH; i++)
        {
            for (int j = 0; j < HEIGHT; j++)
            {
                if (hasNode(i, j))
                {
                    nodes[i][j].renderTwo(g);
                }
            }
        }

    }

    public void mousePressed(int button, int x, int y)
    {
        for (int i = 0; i < WIDTH; i++)
        {
            for (int j = 0; j < HEIGHT; j++)
            {
                if (hasNode(i, j))
                {
                    nodes[i][j].mousePressed(button, x, y);
                }
            }
        }
    }
}
