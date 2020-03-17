package test2;

import java.awt.Font;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import java.awt.GridLayout;
import javax.swing.JFrame;

import jxl.*;
import jxl.read.biff.BiffException;
import java.io.*;

public class BarChart {
	ChartPanel frame1;
	public  BarChart(){
		CategoryDataset dataset = getDataSet();//将获得的数据传递给CategoryDataset类的对象
       JFreeChart chart = ChartFactory.createBarChart3D(
      		                 "疫情统计表", // 图表标题
                           "统计项目", // 目录轴的显示标签
                           "人数", // 数值轴的显示标签
                           dataset, // 数据集
                           PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                           true, // 是否显示图例(对于简单的柱状图必须是false)
                           false,  // 是否生成工具
                           false  // 是否生成URL链接
                           );
       CategoryPlot plot=chart.getCategoryPlot();//获取图表区域对象
       CategoryAxis domainAxis=plot.getDomainAxis();         //水平底部列表
        domainAxis.setLabelFont(new Font("黑体",Font.BOLD,14));         //水平底部标题
        domainAxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));  //垂直标题
        ValueAxis rangeAxis=plot.getRangeAxis();//获取柱状
        rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));
         chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
         chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体
         
         frame1=new ChartPanel(chart,true);  //这里也可以用chartFrame,可以直接生成一个独立的Frame
	}
	
	private static CategoryDataset getDataSet() {
		int count1=0;
        int count2=0;
        int count3=0;
        int count4=0;
		try {
			Workbook book = Workbook.getWorkbook(new File("test.xls"));
			Sheet sheet =book.getSheet(0);
			int rows =sheet.getRows();
	        int cols =sheet.getColumns();
	        for(int i=1;i<rows;i++)
	        {
	        	String a1 =sheet.getCell(3, i).getContents();
	            String a2 =sheet.getCell(4, i).getContents();
	            String a3 =sheet.getCell(5, i).getContents();
	            String a4 =sheet.getCell(6, i).getContents();
	        	if(a1.equals("是"))
	        		count1++;
	        	if(a2.equals("是"))
	        		count2++;
	        	if(a3.equals("是"))
	        		count3++;
	        	if(a4.equals("是"))
	        		count4++;
	        	//System.out.println(result);
	        }
	        System.out.println("在武汉的人数："+count1+"在湖北（不含武汉）的人数："+count2+"有疫情疑似症状的人数："+count3+"已确认被感染的人："+count4);
		} catch (BiffException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(count1, "是否在武汉", "是否在武汉");
        dataset.addValue(count2, "是否湖北（不含武汉）", "是否湖北（不含武汉）");
        dataset.addValue(count3, "是否有疫情疑似症状", "是否有疫情疑似症状");
        dataset.addValue(count4, "是否已确认被感染", "是否已确认被感染");
        return dataset;
	   }
	public ChartPanel getChartPanel(){
		   return frame1;
	}

	public static void main(String[] args) {
		JFrame frame=new JFrame("疫情统计表");
		frame.setLayout(new GridLayout(2,2,10,10));
		frame.add(new BarChart().getChartPanel());   //添加柱形图
		frame.setBounds(0, 0, 900, 800);
		frame.setVisible(true);

	}

}
