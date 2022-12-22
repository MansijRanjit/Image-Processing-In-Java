import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Lab3 
{
	public static void main(String[] args)
	{
		BufferedImage image;
		try
		{
			image=ImageIO.read(new File("E:\\7th Semester\\IPPR\\stop.png"));
			System.out.println("Reading Complete.");
			
		}
		catch(IOException e)
		{
			System.out.println("Error: "+e);
			return;
		}
		
		int[][] g=getpixel(image);		
		int[][] median=getmedian(g,3);
	    BufferedImage k =getBIfromarray(median);
	    displayimage(k);




	}
	static int[][] getpixel(BufferedImage image)
	{
		int[][] f= new int[image.getWidth()][image.getHeight()];
		for(int y=0;y<image.getHeight();y++)
		{
			for(int x=0;x<image.getWidth();x++)
			{
				Color c =new Color(image.getRGB(x, y));
				int red=(c.getRed());
				int blue=(c.getBlue());
				int green=(c.getGreen());
				
				f[x][y]=(red+blue+green)/3;
			
			}
		}
		return f;
	}
	
	static int[][] getmedian(int[][] f,int size)
	{
		int[][] h=new int[f.length][f[0].length];
		int a=size/2;
		
		for(int x=a;x<f.length-a;x++)
		{
			for(int y=a;y<f[0].length-a;y++)
			{
				int[] A=new int[size*size];
				int count=0;
				
				for(int u=x-a;u<=x+a;u++)
				{
					for(int v=y-a;v<=y+a;v++)
					{
						A[count]=f[u][v];
						count++;
					}
				}
				Arrays.sort(A);
				h[x][y] =A[size+1];
				
			}
		}
		return h;
	}
	
	static BufferedImage getBIfromarray(int[][] f)
	{
		BufferedImage img =new BufferedImage(f.length,f[0].length,BufferedImage.TYPE_BYTE_GRAY);
		
		for(int x=0;x<f.length;x++)
		{
			for(int y=0;y<f[0].length;y++)
			{
				Color newCol =new Color(f[x][y],f[x][y],f[x][y]);
				img.setRGB(x,y,newCol.getRGB());
				
			}
		}
		return img;		
	}
	
	static void displayimage(BufferedImage bi)
	{
		ImageIcon icon =new ImageIcon(bi);
		JFrame frame =new JFrame();
		frame.setLayout(new FlowLayout());
		frame.setSize(400,400);
		JLabel lbl =new JLabel();
		lbl.setIcon(icon);
		frame.add(lbl);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
