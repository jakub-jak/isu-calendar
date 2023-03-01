import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.Random;

/*
 Essentially, this is just a gradient panel that can randomly swap each colour to the other side. Can also randomly choose how displaced the colors are on the y axis
 */

public class gradient extends JPanel{
    int monthFrom;
    Color color1;
    Color color2;
    ArrayList<Color> colorChoice;
    ArrayList<Integer> randomInt = new ArrayList<Integer>();
    public gradient(int monthValue){
        monthFrom = monthValue;
        colorChoice = new ArrayList<Color>();

        for(int i = 0; i <= 35; i++){
            randomInt.add(i);
        }

        if(monthFrom >= 1 && monthFrom <= 2){
            color1 = new Color(98, 182, 203);
            color2 = new Color(171, 196, 255);
        } else if(monthFrom > 2 && monthFrom <= 5){
            color1 = new Color(207, 231, 149);
            color2 = new Color(173, 210, 194);
        } else if(monthFrom > 5 && monthFrom <= 8){
            color1 = new Color(240, 128, 128);
            color2 = new Color(251, 196, 171);
        } else if(monthFrom > 8 && monthFrom <= 11){
            color1 = new Color(179, 106, 94);
            color2 = new Color(217, 174, 148);
        } else if(monthFrom == 12){
            color1 = new Color(98, 182, 203);
            color2 = new Color(171, 196, 255);
        }
        colorChoice.add(color1);
        colorChoice.add(color2);
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth(); 
        int h = getHeight();
    
        Color tempColor = getRandomElementColor(colorChoice);
        Color tempColor2;

        if(colorChoice.indexOf(tempColor) == 0){
            tempColor2 = colorChoice.get(1);
        } else{
            tempColor2 = colorChoice.get(0);
        }

        GradientPaint gp = new GradientPaint(0, getRandomElement(randomInt), tempColor, w, getRandomElement(randomInt), tempColor2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }
    public Color getRandomElementColor(ArrayList<Color> list)
    {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }
    public int getRandomElement(ArrayList<Integer> list)
    {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }
}
