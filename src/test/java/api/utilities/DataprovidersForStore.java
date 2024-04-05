package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataprovidersForStore {
	
	@DataProvider(name="Data")
	public String[][] getAllData() throws IOException
	{
		String path=System.getProperty("user.dir")+"//testData//Userdata.xlsx";
		XLUtility xl=new XLUtility(path);
		
		int rownum=xl.getRowCount("Sheet2");
		int colcount=xl.getCellCount("Sheet2",1);
		
		String apidata[][]=new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				apidata[i-1][j]=xl.getCellData("Sheet2", i, j);
			}
		}
		return apidata;
	}
	

	@DataProvider(name="ID")
	public String[] getID() throws IOException
	{
		String path=System.getProperty("user.dir")+"//testData//Userdata.xlsx";
		XLUtility xl=new XLUtility(path);
		
		int rownum=xl.getRowCount("Sheet2");
		
		String apidata[]=new String[rownum];
		for(int i=1;i<=rownum;i++)
		{
			apidata[i-1]=xl.getCellData("Sheet2", i, 0);
		}

		return apidata;

	}
}