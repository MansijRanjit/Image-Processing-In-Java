import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class IPPRLab2HistogramEq 
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
		int[] n=occourenceCount(g);
		int imgSize=g.length*g[0].length;
		double[] p=probability(n,imgSize);
		double[] s=histogramEq(p);
		
		int[][] w=getpixelcompintensity(g,s);
		BufferedImage k =getBIfromarray(w);
	    displayimage(k,"Output");
	    displayimage(image,"Input");
		
		

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
	
	static int[] occourenceCount(int[][] r)
	{
		int[] o= new int[256];
		int count=0;
		
		for(int x=0;x<r.length;x++)
		{
			for(int y=0;y<r[0].length;y++)
			{
				o[r[x][y]]++;
			}
			
		}
		/*for(int i=0;i<256;i++)
		{
			System.out.println(o[i]);
		}*/
		
		return o;
	}
	
	static double[] probability(int[] n,int imgSize)
	{
		double[] p= new double[256];
		
		for(int i=0;i<256;i++)
		{
			p[i]=(double) n[i]/imgSize;
		}
		return p;	
	}
	
	static double[] histogramEq(double[] p)
	{
		double[] s=new double[256];
		for(int i=0;i<256;i++)
		{
			for(int j=0;j<=i;j++)
			{
				s[i]=0;
				s[i]+=p[j];
			}
			s[i]=s[i]*255;
		}
		return s;
		
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
	
	static int[][] getpixelcompintensity(int[][] f,double[] m)
	{
		int z=0;
		for(int x=0;x<f.length;x++)
		{
			for(int y=0;y<f[0].length;y++)
			{
				
			    z=f[x][y];
				f[x][y]=(int) m[z];
			}
		}
		return f;
	}
	
	static void displayimage(BufferedImage bi,String text)
	{
		ImageIcon icon =new ImageIcon(bi);
		JFrame frame =new JFrame(text);
		frame.setLayout(new FlowLayout());
		frame.setSize(400,400);
		JLabel lbl =new JLabel();
		lbl.setIcon(icon);
		frame.add(lbl);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	

}
