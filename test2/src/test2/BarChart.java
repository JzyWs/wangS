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
		CategoryDataset dataset = getDataSet();//����õ����ݴ��ݸ�CategoryDataset��Ķ���
       JFreeChart chart = ChartFactory.createBarChart3D(
      		                 "����ͳ�Ʊ�", // ͼ�����
                           "ͳ����Ŀ", // Ŀ¼�����ʾ��ǩ
                           "����", // ��ֵ�����ʾ��ǩ
                           dataset, // ���ݼ�
                           PlotOrientation.VERTICAL, // ͼ����ˮƽ����ֱ
                           true, // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������false)
                           false,  // �Ƿ����ɹ���
                           false  // �Ƿ�����URL����
                           );
       CategoryPlot plot=chart.getCategoryPlot();//��ȡͼ���������
       CategoryAxis domainAxis=plot.getDomainAxis();         //ˮƽ�ײ��б�
       domainAxis.setLabelFont(new Font("����",Font.BOLD,14));         //ˮƽ�ײ�����
       domainAxis.setTickLabelFont(new Font("����",Font.BOLD,12));  //��ֱ����
       ValueAxis rangeAxis=plot.getRangeAxis();//��ȡ��״
       rangeAxis.setLabelFont(new Font("����",Font.BOLD,15));
       chart.getLegend().setItemFont(new Font("����", Font.BOLD, 15));
       chart.getTitle().setFont(new Font("����",Font.BOLD,20));//���ñ�������
         
         frame1=new ChartPanel(chart,true);  //����Ҳ������chartFrame,����ֱ������һ��������Frame
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
	        	if(a1.equals("��"))
	        		count1++;
	        	if(a2.equals("��"))
	        		count2++;
	        	if(a3.equals("��"))
	        		count3++;
	        	if(a4.equals("��"))
	        		count4++;
	        	//System.out.println(result);
	        }
	        System.out.println("���人��������"+count1+"�ں����������人����������"+count2+"����������֢״��������"+count3+"��ȷ�ϱ���Ⱦ���ˣ�"+count4);
		} catch (BiffException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(count1, "�Ƿ����人", "�Ƿ����人");
        dataset.addValue(count2, "�Ƿ�����������人��", "�Ƿ�����������人��");
        dataset.addValue(count3, "�Ƿ�����������֢״", "�Ƿ�����������֢״");
        dataset.addValue(count4, "�Ƿ���ȷ�ϱ���Ⱦ", "�Ƿ���ȷ�ϱ���Ⱦ");
        return dataset;
	   }
	public ChartPanel getChartPanel(){
		   return frame1;
	}

	public static void main(String[] args) {
		JFrame frame=new JFrame("����ͳ�Ʊ�");
		frame.setLayout(new GridLayout(2,2,10,10));
		frame.add(new BarChart().getChartPanel());   //�������ͼ
		frame.setBounds(0, 0, 900, 800);
		frame.setVisible(true);

	}

}
