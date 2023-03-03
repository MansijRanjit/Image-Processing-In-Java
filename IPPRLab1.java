import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.util.Arrays;
public class IPPRLab1
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
	/*	
		for(int y=0;y<image.getHeight();y++)
		{
			for(int x=0;x<image.getWidth();x++)
			{
				Color c =new Color(image.getRGB(x, y));
				int red =(c.getRed());
				int green =(c.getGreen());
				int blue =(c.getBlue());
				
				int gray=(int)(red+green+blue)/3;
				Color newColor=new Color(gray,gray,gray);
				image.setRGB(x, y,newColor.getRGB());

			}
		}
		try
		{
			ImageIO.write(image, "jpg",new File("image_greyed.jpg"));
			System.out.println("Writing complete. ");
		}
		catch(IOException e)
		{
			System.out.println("Error: "+e);
			return;
		}
		*/
		
		
		int[][] g=getpixel(image);		
		/*
	    BufferedImage i =getBIfromarray(g);
	    
	   //Save image in folder 
	   try
		{
			ImageIO.write(i, "jpg",new File("E:\\7th Semester\\IPPR\\image_greyed_leaf.jpg"));
			System.out.println("Writing complete. ");
		}
		catch(IOException e)
		{
			System.out.println("Error: "+e);
			return;
		}
	   
		//Display on console
	    displayimage(i);
	    
	    */
	    
	    //get intensity value 0 if<128 else 256
		 /* 
	      int[][] h=getpixelcompintensity(g,128);
		  BufferedImage k =getBIfromarray(h);
	      displayimage(k);
	      */
		
	    //get intensity value 0 if<mean else 256
		/*
		int mean=getmean(g);
	    int[][] w=getpixelcompintensity(g,mean);

	    BufferedImage k =getBIfromarray(w);
	    displayimage(k);
        */
		
		 //get intensity value 0 if<median else 256
		
		int median=getmedian(g);
	    int[][] w=getpixelcompintensity(g,median);

	    BufferedImage k =getBIfromarray(w);
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
	
	static int[][] getpixelcompintensity(int[][] f,int m)
	{
		
		for(int x=0;x<f.length;x++)
		{
			for(int y=0;y<f[0].length;y++)
			{
				
				if(f[x][y]>m)
				{
					f[x][y]=255;
				}
				else
				{
					f[x][y]=0;
				}
			
			}
		}
		return f;
	}
	static int getmean(int[][] f)
	{
		int sum=0;
		int count=0;
		for(int x=0;x<f.length;x++)
		{
			for(int y=0;y<f[0].length;y++)
			{
				
				sum+=f[x][y];
				count++;
			
			}
		}
		int mean=sum/count;
		return mean;
	}
	static int getmedian(int[][] f)
	{
		
		int count=f.length*f[0].length;
		int[] arr=new int[count];
		int i=0;
		for(int x=0;x<f.length;x++)
		{
			for(int y=0;y<f[0].length;y++)
			{	
				
				if(i<count)
				{
					arr[i]=f[x][y];
				}
				i++;
			
			}
		}
		Arrays.sort(arr);
		
		int md=(count+1)/2;
		int median=arr[md-1];
		return median;
	}
	

}
