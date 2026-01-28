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

public class Lab3Qn2
{
	public static void main(String[] args)
	{
		BufferedImage image,OrgImage;
		try
		{
			OrgImage=ImageIO.read(new File("E:\\7th Semester\\IPPR\\IPPR Lab Images\\messi.jpg"));
			image=ImageIO.read(new File("E:\\7th Semester\\IPPR\\IPPR Lab Images\\messi_N.jpg"));
			System.out.println("Reading Complete.");
		}
		catch(IOException e)
		{
			System.out.println("Error: "+e);
			return;
		}
		
		int[][] g=getpixel(OrgImage);	
		int[][] h=getpixel(image);	

		int[][] median=getmedian(h,3);
		int[][] mean=getmean(h,3);
		
		double SNR_Median=getsnr(g,median);
		double SNR_Mean=getsnr(g,mean);
		
		System.out.println("SNR using Median Filter:"+SNR_Median);
		System.out.println("SNR using Mean Filter  :"+SNR_Mean);


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
	
	
	static int[][] getmean(int[][] f,int size)
	{
		int[][] h=new int[f.length][f[0].length];
		int a=size/2;
		
		for(int x=a;x<f.length-a;x++)
		{
			for(int y=a;y<f[0].length-a;y++)
			{
				int count=0;
				int sum=0;
				
				for(int u=x-a;u<=x+a;u++)
				{
					for(int v=y-a;v<=y+a;v++)
					{
						sum+=f[u][v];
						
					}
				}
				
				h[x][y] =(sum/(size*size));
				
			}
		}
		return h;
	}
	
	static double getsnr(int[][] g,int[][] m)
	{
		int signalStrength=0,signalNoise=0;
		for(int x=0;x<g.length;x++)
		{
			for(int y=0;y<g[0].length;y++)
			{
				signalStrength+=Math.pow(m[x][y],2);
				signalNoise+=Math.pow((g[x][y]-m[x][y]),2);
			}
		}
		return (signalStrength/signalNoise);
	}
	

}
